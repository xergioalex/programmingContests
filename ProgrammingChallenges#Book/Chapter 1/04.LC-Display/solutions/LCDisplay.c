#include <stdio.h>
#include <string.h>


int rowsBaseSize = 5;
int colsBaseSize = 3;
char lcdDigits[10][5][3] = {
    {/* zero */
        {' ','-',' ' },
        {'|',' ','|' },
        {' ',' ',' ' },
        {'|',' ','|' },
        {' ','-',' ' }
    },
    {/* one */
        {' ',' ',' ' },
        {' ',' ','|' },
        {' ',' ',' ' },
        {' ',' ','|' },
        {' ',' ',' ' }
    },
    {/* two */
        {' ','-',' ' },
        {' ',' ','|' },
        {' ','-',' ' },
        {'|',' ',' ' },
        {' ','-',' ' }
    },
    {/* three */
        {' ','-',' ' },
        {' ',' ','|' },
        {' ','-',' ' },
        {' ',' ','|' },
        {' ','-',' ' }
    },
    {/* four */
        {' ',' ',' ' },
        {'|',' ','|' },
        {' ','-',' ' },
        {' ',' ','|' },
        {' ',' ',' ' }
    },
    {/* five */
        {' ','-',' ' },
        {'|',' ',' ' },
        {' ','-',' ' },
        {' ',' ','|' },
        {' ','-',' ' }
    },
    {/* six */
        {' ','-',' ' },
        {'|',' ',' ' },
        {' ','-',' ' },
        {'|',' ','|' },
        {' ','-',' ' }
    },
    {/* seven */
        {' ','-',' ' },
        {' ',' ','|' },
        {' ',' ',' ' },
        {' ',' ','|' },
        {' ',' ',' ' }
    },
    {/* eight */
        {' ','-',' ' },
        {'|',' ','|' },
        {' ','-',' ' },
        {'|',' ','|' },
        {' ','-',' ' }
    },
    {/* nine */
        {' ','-',' ' },
        {'|',' ','|' },
        {' ','-',' ' },
        {' ',' ','|' },
        {' ','-',' ' }
    }
};

void printCharRepeat(char character, int times) {
    int i;
    for (i = 0; i < times; i++) {
        printf("%c", character);
    }
}



void printLCDNumber(int size, char number[10]) {
    int rows, rowsCount, repeatRows, indexNumber, lcdNumber, columns, digitsCount;
    char character;

    for (rows = 0; rows < rowsBaseSize; rows++) {
        rowsCount = (rows % 2 == 0)? 1: size;
        for (repeatRows = 0; repeatRows < rowsCount; repeatRows++) {

            digitsCount = strlen(number);
            for (indexNumber = 0; indexNumber < digitsCount; indexNumber++) {
                lcdNumber = number[indexNumber] - '0';
                for (columns = 0; columns < colsBaseSize; columns++) {
                    character = lcdDigits[lcdNumber][rows][columns];

                    printCharRepeat(character, (columns % 2 == 0)? 1: size);
                }

                if (indexNumber < digitsCount - 1) {
                    printf(" ");
                }
            }

            printf("\n");
        }
    }
    printf("\n");
}


/* Main program */
int main () {
    int i, j, n, m, resultIndex = 1;
    int s;
    char number[10];

    while (scanf("%d %s", &s, number) != EOF) {
        if (s == 0) {
            break;
        }

        printLCDNumber(s, number);
    }

    return 0;
}
