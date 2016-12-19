from sys import stdin

class MineField:
    def __init__(self, n, m):
        self.n = n
        self.m = m
        self.field = [['' for x in range(m)] for y in range(n)]

        # Fill field
        for i in range(n):
            line = stdin.readline()
            for j in range(m):
                self.field[i][j] = line[j]

    def calcMines(self, i, j):
        mines = 0
        for k in range(i-1, i+2):
            for l in range(j-1, j+2):
                if (k >= 0 and k < self.n and l >= 0  and l < self.m):
                    if (self.field[k][l] == '*'):
                        mines += 1

        return mines;

    def resolveMinesweeper(self):
        for i in range(self.n):
            for j in range(self.m):
                if (self.field[i][j] == '.'):
                    self.field[i][j] = chr(self.calcMines(i, j) + 48)

    def __str__(self):
        out = "";
        for i in range(self.n):
            for j in range(self.m):
                out += self.field[i][j];

            if (i != self.n - 1):
                out+="\n";

        return out;


# Main program
def main():
    resultIndex = 1

    for line in stdin:
        n, m = map(int, line.split())

        if (n == 0 and m == 0):
            break;

        if (resultIndex > 1):
            print();

        minefield = MineField(n, m)
        # Resolve minesweeper
        minefield.resolveMinesweeper();

        print("Field #%d:" % (resultIndex))
        print(minefield)
        resultIndex += 1

main()