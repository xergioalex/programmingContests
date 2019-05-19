#include <stdio.h>

struct MineField {
    int n;
    int m;
    char field[100][100];
};

struct MineField minefield;

void printMineField();
int calcMines(int i, int j);
void resolveMinesweeper();

/* Main program */
int main () {
    int i, j, n, m, resultIndex = 1;
    char inputLine[100];

    while (scanf("%d %d", &minefield.n, &minefield.m) != EOF) {
        if (minefield.n == 0 && minefield.m == 0) {
            break;
        }

        if (resultIndex > 1) {
            printf("\n");
        }

        for (i = 0; i < minefield.n; i++) {
            scanf("%s", inputLine);
            for (j = 0; j < minefield.m; j++) {
                minefield.field[i][j] = inputLine[j];
            }
        }

        /* Resolve minesweeper */
        resolveMinesweeper();

        printf("Field #%d:\n", resultIndex);
        printMineField();
        resultIndex++;
    }

    return 0;
}


void printMineField() {
    int i, j;
    for (i = 0; i < minefield.n; i++) {
        for (j = 0; j < minefield.m; j++) {
            printf("%c", minefield.field[i][j]);
        }
        if (i != minefield.n -1) {
            printf("\n");
        }
    }
    printf("\n");
}

int calcMines(int i, int j) {
    int k, l, mines = 0;
    for (k = i-1; k <= i+1; k++) {
        for (l = j-1; l <= j+1; l++) {
            if (k >= 0 && k < minefield.n && l >= 0  && l < minefield.m) {
                if (minefield.field[k][l] == '*') {
                    mines++;
                }
            }
        }
    }

    return mines;
}

void resolveMinesweeper() {
    int i, j;
    for (i = 0; i < minefield.n; i++) {
        for (j = 0; j < minefield.m; j++) {
            if (minefield.field[i][j] == '.') {
                minefield.field[i][j] = calcMines(i, j) + 48;
            }
        }
    }
}