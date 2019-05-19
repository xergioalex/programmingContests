#include <stdio.h>

double expenses[1000] = {0};

/* Main program */
int main () {
    int n, i;
    double expense;

    while (scanf("%d", &n) != EOF) {
        if (n == 0) {
            break;
        }

        double total = 0, average = 0, toExchangePos = 0, toExchangeNeg = 0;

        for (i = 0; i < n; i++) {
            scanf("%lf", &expense);
            expenses[i] = expense;
            total += expenses[i];
        }

        /* Get average */
        average = total / (double) n;

        for (i = 0; i < n; i++) {
            double dif = expenses[i] - average;
            /* Set two digits accuracy */
            dif = (double) (((long) (dif * 100.0)) / 100.0 );

            if (dif > 0) {
                toExchangePos += dif;
            } else {
                toExchangeNeg += dif;
            }
        }

        double minToExchange = (-toExchangeNeg > toExchangePos)? -toExchangeNeg: toExchangePos;
        printf("$%.2f\n", minToExchange);
    }

    return 0;
}