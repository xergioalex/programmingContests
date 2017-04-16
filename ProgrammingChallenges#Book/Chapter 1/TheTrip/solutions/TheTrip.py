from sys import stdin

# Main program
def main():
    expenses = [0]*1000

    for line in stdin:
        n = int(line)

        if (n == 0):
            break;

        total, toExchangePos, toExchangeNeg = (0,)*3

        for i in range(n):
            line = stdin.readline()
            expenses[i] = float(line)
            total += expenses[i]

        # Get average
        average = total / n

        for i in range(n):
            dif = expenses[i] - average
            # Set two digits accuracy
            dif = float((int(dif * 100.0)) / 100.0)

            if (dif > 0):
                toExchangePos += dif
            else:
                toExchangeNeg += dif

        if (-toExchangeNeg > toExchangePos):
            minToExchange = -toExchangeNeg
        else:
            minToExchange = toExchangePos

        print("$%.2f" % minToExchange)

main()

