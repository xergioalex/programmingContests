#include <stdio.h>
#include <string.h>

struct King {
    int x;
    int y;
    int isWhite;
};

struct ChessField {
    struct King kingWhite;
    struct King kingBlack;
    char field[8][8];
};

struct ChessField chess;

void checkTheCheck(char *answer);
int checkKing(struct King king);
int diagonalCheck(struct King king);
int diagonalCheckValidation(struct King king, int i, int j);
int straightCheck(struct King king);
int straightCheckValidation(struct King king, int i, int j);
int knightCheck(struct King king);
int knightCheckValidation(struct King king, int i, int j);



/* Main program */
int main () {
    int i, j, isEmpty, gameNumber = 1;
    struct King kingWhite, kingBlack;
    char answer[100];
    char inputLine[10];

    while (1) {
        /* Fill field */            
        isEmpty = 1;
        for (i = 0; i < 8; i++) {
            fgets(inputLine, sizeof inputLine, stdin);
            for (j = 0; j < 8; j++) {
                chess.field[i][j] = inputLine[j];
                if (chess.field[i][j] != '.') {
                    isEmpty = 0;
                }
                if (chess.field[i][j] == 'K') {
                    chess.kingWhite.x = j;
                    chess.kingWhite.y = i;
                    chess.kingWhite.isWhite = 1;
                }
                if (chess.field[i][j] == 'k') {
                    chess.kingBlack.x = j;
                    chess.kingBlack.y = i;
                    chess.kingBlack.isWhite = 0;
                }
            }
        }

        if (isEmpty) {
            break;
        }
        fgets(inputLine, sizeof inputLine, stdin);

        /* Verify chess */
        checkTheCheck(answer);
        printf("Game #%d: %s\n", gameNumber, answer);
        gameNumber++;
    }

    return 0;
}


void checkTheCheck(char *answer) {
    if (checkKing(chess.kingWhite)) {
        strcpy(answer, "white king is in check.");
    } else if (checkKing(chess.kingBlack)) {
        strcpy(answer, "black king is in check.");
    } else {
        strcpy(answer, "no king is in check.");
    }
}

int checkKing(struct King king) {
    return diagonalCheck(king) || straightCheck(king) || knightCheck(king);
}

int diagonalCheck(struct King king) {
    int i, j;
    /* Verify top left */
    for (i = king.y-1, j = king.x-1; i >= 0 && j >= 0; i--, j--) {
        int result = diagonalCheckValidation(king, i, j);
        if (result == -1) {
            break;
        } else if (result == 1) {
            return 1;
        }
    }
    
    /* Verify top right */
    for (i = king.y-1, j = king.x+1; i >= 0 && j < 8; i--, j++) {
        int result = diagonalCheckValidation(king, i, j);
        if (result == -1) {
            break;
        } else if (result == 1) {
            return 1;
        }
    }
    
    /* Verify bottom left */
    for (i = king.y+1, j = king.x-1; i < 8 && j >= 0; i++, j--) {
        int result = diagonalCheckValidation(king, i, j);
        if (result == -1) {
            break;
        } else if (result == 1) {
            return 1;
        }
    }
    
    /* Verify bottom right */
    for (i = king.y+1, j = king.x+1; i < 8 && j < 8; i++, j++) {
        int result = diagonalCheckValidation(king, i, j);
        if (result == -1) {
            break;
        } else if (result == 1) {
            return 1;
        }
    }
    
    return 0;
}

int diagonalCheckValidation(struct King king, int i, int j) {
    if (chess.field[i][j] == '.') {
        return 0;
    } else {
        /* Verify if another piece with same color is on the way. 97 is ascii number for 'a' simbol */
        if ((king.isWhite && chess.field[i][j] < 97) || (!king.isWhite && chess.field[i][j] >= 97)) {
            return -1;
        }
        /* Check pawn */
        if ((king.isWhite && chess.field[i][j] == 'p' && king.y - i == 1) || (!king.isWhite && chess.field[i][j] == 'P' && i - king.y == 1)) {
            return 1;
        }
        /* Check bishop && queen */
        if (chess.field[i][j] == 'b' || chess.field[i][j] == 'q' || chess.field[i][j] == 'B' || chess.field[i][j] == 'Q') {
            return 1;
        }
    }
    
    return -1;
}

int straightCheck(struct King king) {
    int i, j;
    /* Verify top */
    for (i = king.y-1, j = king.x; i >= 0; i--) {
        int result = straightCheckValidation(king, i, j);
        if (result == -1) {
            break;
        } else if (result == 1) {
            return 1;
        }
    }
    
    /* Verify bottom */
    for (i = king.y+1, j = king.x; i < 8; i++) {
        int result = straightCheckValidation(king, i, j);
        if (result == -1) {
            break;
        } else if (result == 1) {
            return 1;
        }
    }
    
    /* Verify left */
    for (i = king.y, j = king.x-1; j >= 0; j--) {
        int result = straightCheckValidation(king, i, j);
        if (result == -1) {
            break;
        } else if (result == 1) {
            return 1;
        }
    }
    
    /* Verify right */
    for (i = king.y, j = king.x+1; j < 8; j++) {
        int result = straightCheckValidation(king, i, j);
        if (result == -1) {
            break;
        } else if (result == 1) {
            return 1;
        }
    }
    
    return 0;
}

int straightCheckValidation(struct King king, int i, int j) {
    if (chess.field[i][j] == '.') {
        return 0;
    } else {
        /* Verify if another piece with same color is on the way. 97 is ascii number for 'a' simbol */
        if ((king.isWhite && chess.field[i][j] < 97) || (!king.isWhite && chess.field[i][j] >= 97)) {
            return -1;
        }
        /* Check rook && queen */
        if (chess.field[i][j] == 'r' || chess.field[i][j] == 'q' || chess.field[i][j] == 'R' || chess.field[i][j] == 'Q') {
            return 1;
        }
    }
    
    return -1;
}

int knightCheck(struct King king) {
    int i, j;
    /* Verify top left */
    i = king.y-1;
    j = king.x-2;
    if (knightCheckValidation(king, i, j)) {
        return 1;
    }
    
    i = king.y-2;
    j = king.x-1;
    if (knightCheckValidation(king, i, j)) {
        return 1;
    }
    
    /* Verify top right */
    i = king.y-2;
    j = king.x+1;
    if (knightCheckValidation(king, i, j)) {
        return 1;
    }
    
    i = king.y-1;
    j = king.x+2;
    if (knightCheckValidation(king, i, j)) {
        return 1;
    }
    
    /* Verify bottom left */
    i = king.y+1;
    j = king.x-2;
    if (knightCheckValidation(king, i, j)) {
        return 1;
    }
    
    i = king.y+2;
    j = king.x-1;
    if (knightCheckValidation(king, i, j)) {
        return 1;
    }
    
    
    /* Verify bottom right */
    i = king.y+2;
    j = king.x+1;
    if (knightCheckValidation(king, i, j)) {
        return 1;
    }
    
    i = king.y+1;
    j = king.x+2;
    if (knightCheckValidation(king, i, j)) {
        return 1;
    }
    
    return 0;
}

int knightCheckValidation(struct King king, int i, int j) {
    if (i >= 0 && i < 8 && j >= 0 && j < 8) {
        if ((king.isWhite && chess.field[i][j] == 'n') || (!king.isWhite && chess.field[i][j] == 'N')) {
            return 1;
        }
    }
    return 0;
}