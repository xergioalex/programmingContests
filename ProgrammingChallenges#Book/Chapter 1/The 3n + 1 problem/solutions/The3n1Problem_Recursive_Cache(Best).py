import sys

# Caching previous soluctions
cache = {1: 1}

# Get the cycle lenght of a number
def getCycleLength(number):
    originalNumber = number


    cacheNumber = cache.get(number)
    if cacheNumber is not None:
        return cacheNumber
    elif (number == 1):
        return 1
    elif (number % 2 == 1):
        number = 3 * number + 1
    else:
        number = number / 2

    cycleLength = getCycleLength(number) + 1
    cache[originalNumber] = cycleLength

    return cycleLength


# Get maximum cycle lenght of numbers between i && j
def getMaximumCycleLength(i, j):
    # Swap
    if (i > j):
        i = i + j
        j = i - j
        i = i - j

    # Calc max length
    max = 0
    for num in range(i, j+1):
        cycleLenght = cache.get(num)
        if cycleLenght is None:
            cycleLenght = getCycleLength(num)
        if (max < cycleLenght):
            max = cycleLenght

    return max


# Main program
def main():
    for line in sys.stdin:
        i, j = map(int, line.split())

        print(i, j, getMaximumCycleLength(i, j))

main()

