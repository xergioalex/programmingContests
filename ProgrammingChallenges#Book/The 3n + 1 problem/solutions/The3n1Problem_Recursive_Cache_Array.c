#include <stdio.h>
#include <limits.h>

int cache[INT_MAX] = {0};

/* Get the cycle lenght of a number */
int getCycleLength(int number) {
    int originalNumber = number;

    int cacheNumber = cache[number];
    if (cacheNumber != 0) {
        return cacheNumber;
    }  else if (number == 1) {
        return 1;
    } else if (number % 2 == 1) {
        number = 3 * number + 1;
    } else {
        number = number / 2;
    }
    int cycleLength = getCycleLength(number) + 1;
    cache[originalNumber] = cycleLength;

    return cycleLength;
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
        int cycleLenght = cache[num];
        if (cycleLenght == 0) {
            cycleLenght = getCycleLength(num);
        }
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