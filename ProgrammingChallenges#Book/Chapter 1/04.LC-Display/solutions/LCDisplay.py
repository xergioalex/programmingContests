from sys import stdin

rowsBaseSize = 5
colsBaseSize = 3
lcdDigits = [
        [# zero
            [' ','-',' '],
            ['|',' ','|'],
            [' ',' ',' '],
            ['|',' ','|'],
            [' ','-',' ']
        ],
        [# one
            [' ',' ',' '],
            [' ',' ','|'],
            [' ',' ',' '],
            [' ',' ','|'],
            [' ',' ',' ']
        ],
        [# two
            [' ','-',' '],
            [' ',' ','|'],
            [' ','-',' '],
            ['|',' ',' '],
            [' ','-',' ']
        ],
        [# three
            [' ','-',' '],
            [' ',' ','|'],
            [' ','-',' '],
            [' ',' ','|'],
            [' ','-',' ']
        ],
        [# four
            [' ',' ',' '],
            ['|',' ','|'],
            [' ','-',' '],
            [' ',' ','|'],
            [' ',' ',' ']
        ],
        [# five
            [' ','-',' '],
            ['|',' ',' '],
            [' ','-',' '],
            [' ',' ','|'],
            [' ','-',' ']
        ],
        [# six
            [' ','-',' '],
            ['|',' ',' '],
            [' ','-',' '],
            ['|',' ','|'],
            [' ','-',' ']
        ],
        [# seven
            [' ','-',' '],
            [' ',' ','|'],
            [' ',' ',' '],
            [' ',' ','|'],
            [' ',' ',' ']
        ],
        [# eight
            [' ','-',' '],
            ['|',' ','|'],
            [' ','-',' '],
            ['|',' ','|'],
            [' ','-',' ']
        ],
        [# nine
            [' ','-',' '],
            ['|',' ','|'],
            [' ','-',' '],
            [' ',' ','|'],
            [' ','-',' ']
        ]
    ]

def printCharRepeat(character, times):
    for i in range(0, times):
        print("%c" % character, end = "")


def printLCDNumber(size, number):

    for rows in range(0, rowsBaseSize):
        rowsCount =  1 if (rows % 2 == 0) else size

        for repeatRows in range(0, rowsCount):
            digitsCount = len(number)

            for indexNumber in range(0, digitsCount):
                lcdNumber = int(number[indexNumber])

                for columns in range(0, colsBaseSize):
                    character = lcdDigits[lcdNumber][rows][columns]
                    timesToRepeat = 1 if (columns % 2 == 0) else size
                    printCharRepeat(character,  timesToRepeat)

                if (indexNumber < digitsCount - 1):
                    print(" ", end = "")

            print()

    print()


# Main program
def main():
    for line in stdin:
        token = line.split();

        s = int(token[0])
        number = token[1]

        if (s == 0):
            break

        printLCDNumber(s, number)


main()

