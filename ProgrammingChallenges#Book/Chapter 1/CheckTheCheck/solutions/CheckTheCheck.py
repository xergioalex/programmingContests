from sys import stdin

class King:
    def __init__(self, x, y, isWhite):
        self.x = x
        self.y = y
        self.isWhite = isWhite


class Chess:
    def __init__(self, field, kingWhite, kingBlack):
        self.field = field
        self.kingWhite = kingWhite;
        self.kingBlack = kingBlack;
    

    def checkTheCheck(self):
        if (self.checkKing(self.kingWhite)):
            return "white king is in check."
        elif (self.checkKing(self.kingBlack)):
            return "black king is in check."
        return "no king is in check."
    
    
    def checkKing(self, king):
        return self.diagonalCheck(king) or self.straightCheck(king) or self.knightCheck(king)

    
    def diagonalCheck(self, king):
        # Verify top left
        for i, j in zip(range(king.y-1, -1, -1), range(king.x-1, -1, -1)):
            result = self.diagonalCheckValidation(king, i, j)
            if (result == -1):
                break
            elif (result == 1):
                return True
        
        # Verify top right
        for i, j in zip(range(king.y-1, -1, -1), range(king.x+1, 8)):
            result = self.diagonalCheckValidation(king, i, j);
            if (result == -1):
                break
            elif (result == 1):
                return True
        
        # Verify bottom left
        for i, j in zip(range(king.y+1, 8), range(king.x-1, -1, -1)):
            result = self.diagonalCheckValidation(king, i, j);
            if (result == -1):
                break
            elif (result == 1):
                return True
        
        # Verify bottom right
        for i, j in zip(range(king.y+1, 8), range(king.x+1, 8)):
            result = self.diagonalCheckValidation(king, i, j);
            if (result == -1):
                break
            elif (result == 1):
                return True

        return False;
    
    def diagonalCheckValidation(self, king, i, j):
        if (self.field[i][j] == '.'):
            return 0
        else:
            # Verify if another piece with same color is on the way. 97 is ascii number for 'a' simbol
            if ((king.isWhite and ord(self.field[i][j]) < 97) or ((not king.isWhite) and ord(self.field[i][j]) >= 97)):
                return -1
            # Check pawn
            if ((king.isWhite and self.field[i][j] == 'p' and king.y - i == 1) or ((not king.isWhite) and self.field[i][j] == 'P' and i - king.y == 1)):
                return 1
            
            # Check bishop and queen
            if (self.field[i][j] == 'b' or self.field[i][j] == 'q' or self.field[i][j] == 'B' or self.field[i][j] == 'Q'):
                return 1
        
        return -1
    
    def straightCheck(self, king):
        # Verify top
        j = king.x
        for i in range(king.y-1, -1, -1):
            result = self.straightCheckValidation(king, i, j)
            if (result == -1):
                break
            elif (result == 1):
                return True
        
        # Verify bottom
        j = king.x;
        for i in range(king.y+1, 8):
            result = self.straightCheckValidation(king, i, j)
            if (result == -1):
                break
            elif (result == 1):
                return True
        
        # Verify left
        i = king.y
        for j in range(king.x-1, -1, -1):        
            result = self.straightCheckValidation(king, i, j)
            if (result == -1):
                break
            elif (result == 1):
                return True
        
        # Verify right
        i = king.y
        for j in range(king.x+1, 8):        
            result = self.straightCheckValidation(king, i, j)
            if (result == -1):
                break
            elif (result == 1):
                return True
        
        return False;
    
    def straightCheckValidation(self, king, i, j):
        if (self.field[i][j] == '.'):
            return 0
        else:
            # Verify if another piece with same color is on the way. 97 is ascii number for 'a' simbol
            if ((king.isWhite and ord(self.field[i][j]) < 97) or ((not king.isWhite) and ord(self.field[i][j]) >= 97)):
                return -1

            # Check rook and queen
            if (self.field[i][j] == 'r' or self.field[i][j] == 'q' or self.field[i][j] == 'R' or self.field[i][j] == 'Q'):
                return 1
        
        return -1
    
    def knightCheck(self, king):
        # Verify top left
        i = king.y-1;
        j = king.x-2;
        if (self.knightCheckValidation(king, i, j)):
            return True
        
        i = king.y-2;
        j = king.x-1;
        if (self.knightCheckValidation(king, i, j)):
            return True
        
        # Verify top right
        i = king.y-2;
        j = king.x+1;
        if (self.knightCheckValidation(king, i, j)):
            return True
        
        i = king.y-1;
        j = king.x+2;
        if (self.knightCheckValidation(king, i, j)):
            return True
        
        # Verify bottom left
        i = king.y+1;
        j = king.x-2;
        if (self.knightCheckValidation(king, i, j)):
            return True
        
        i = king.y+2;
        j = king.x-1;
        if (self.knightCheckValidation(king, i, j)):
            return True
        
        # Verify bottom right
        i = king.y+2;
        j = king.x+1;
        if (self.knightCheckValidation(king, i, j)):
            return True
        
        i = king.y+1;
        j = king.x+2;
        if (self.knightCheckValidation(king, i, j)):
            return True
        
        return False
    
    def knightCheckValidation(self, king, i, j):
        if (i >= 0 and i < 8 and j >= 0 and j < 8):
            if ((king.isWhite and self.field[i][j] == 'n') or ((not king.isWhite) and self.field[i][j] == 'N')):
                return True
        
        return False


# Main program
def main():
    gameNumber = 1
    field = [['' for x in range(8)] for y in range(8)]
    while True:
        # Fill field
        isEmpty = 1
        for i in range(0, 8):
            inputLine = stdin.readline()
            for j in range (0, 8):
                field[i][j] = inputLine[j]
                if (field[i][j] != '.'):
                    isEmpty = 0
                if (field[i][j] == 'K'):
                    kingWhite = King(j, i, True)
                if (field[i][j] == 'k'):
                    kingBlack = King(j, i, False)

        if (isEmpty):
            break
        
        stdin.readline()

        # Verify chess
        chess = Chess(field, kingWhite, kingBlack)
        answer = chess.checkTheCheck()
        print("Game #%d: %s" % (gameNumber, answer))
        gameNumber = gameNumber + 1
        

main()