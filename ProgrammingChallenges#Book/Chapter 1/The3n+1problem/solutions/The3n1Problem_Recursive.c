#include <stdio.h>

/* Get the cycle lenght of a number */
int getCycleLength(int number, int cycleLenght) {
    cycleLenght++;

    if (number == 1) {
        return cycleLenght;
    } else if (number % 2 == 1) {
        number = 3 * number + 1;
    } else {
        number = number / 2;
    }
    return getCycleLength(number, cycleLenght);
}

/* Get maximum cycle lenght of numbers between i && j */
int getMaximumCycleLength(int i, int j) {
    /* Swap */
    if (i > j) {
        i = i + j;
        j = i - j;
        i = i - j;
    }

    /* Calc max length */
    int max = 0, num;
    for (num = i; num <= j; num++) {
        int cycleLenght = getCycleLength(num, 0);
        if (max < cycleLenght) {
            max = cycleLenght;
        }
    }
    return max;
}

/* Main program */
int main () {
    int i, j;

    while (scanf("%d %d", &i, &j) != EOF ) {
        printf ("%d %d %d\n", i, j, getMaximumCycleLength(i, j));
    }

    return 0;
}