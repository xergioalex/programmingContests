from sys import stdin
import queue

class Position:
    def __init__(self, x, y):
        self.x = x
        self.y = y


class GEditor:
    def __init__(self):
        self.M = 0
        self.N = 0
        self.picture = [['' for x in range(255)] for y in range(255)]
        self.marked  = [[False for x in range(255)] for y in range(255)]

    def processCommand(self, command):
        def I():
            # commandI(int M, int N)
            self.commandI(int(command[1]), int(command[2]))

        def C():
            self.commandC()

        def L():
            # commandL(int X, int Y, char C)
            self.commandL(int(command[1])-1, int(command[2])-1, command[3])

        def V():
            # commandV(int X, int Y1, int Y2, char C)
            self.commandV(int(command[1])-1, int(command[2])-1, int(command[3])-1, command[4])

        def H():
            # commandH(int X1, int X2, int Y, char C)
            self.commandH(int(command[1])-1, int(command[2])-1, int(command[3])-1, command[4])

        def H():
            # commandH(int X1, int X2, int Y, char C)
            self.commandH(int(command[1])-1, int(command[2])-1, int(command[3])-1, command[4])

        def K():
            # commandK(int X1, int X2, int Y1, int Y2, char C)
            self.commandK(int(command[1])-1, int(command[2])-1, int(command[3])-1, int(command[4])-1, command[5]);

        def F():
            # commandF(int X, int Y, char C)
            self.commandF(int(command[1])-1, int(command[2])-1, command[3]);

        def S():
            # commandS(string Name)
            self.commandS(command[1]);
            
        switch = {
            'I': I,
            'C': C,
            'L': L,
            'V': V,
            'H': H,
            'K': K,
            'F': F,
            'S': S
        }

        if (command[0] in switch.keys()):
            switch[command[0]]()


    def commandI(self, M, N):
        self.M = M
        self.N = N

        for i in range(0, self.N):
            for j in range(0, self.M):
                self.picture[i][j] = 'O'


    def commandC(self):
        for i in range(0, self.N):
            for j in range(0, self.M):
                self.picture[i][j] = 'O'


    def commandL(self, X, Y, C):
        self.picture[Y][X] = C


    def commandV(self, X, Y1, Y2, C):
        if (Y1 > Y2):
            Y1 = Y1+Y2
            Y2 = Y1-Y2
            Y1 = Y1-Y2
        
        for i in range(Y1, Y2+1):
            self.picture[i][X] = C

    def commandH(self, X1, X2, Y, C):
        if (X1 > X2):
            X1 = X1+X2
            X2 = X1-X2
            X1 = X1-X2

        for i in range(X1, X2+1):
            self.picture[Y][i] = C

    def commandK(self, X1, Y1, X2, Y2, C):
        if (X1 > X2):
            X1 = X1+X2
            X2 = X1-X2
            X1 = X1-X2

        if (Y1 > Y2):
            Y1 = Y1+Y2
            Y2 = Y1-Y2
            Y1 = Y1-Y2

        for i in range(Y1, Y2+1):
            for j in range(X1, X2+1):
                self.picture[i][j] = C


    def commandF(self, X, Y, C):
        self.resetMarkedValues()

        pixel = self.picture[Y][X]

        self.picture[Y][X] = C
        self.marked[Y][X] = True

        pixelQueue = queue.Queue()
        pixelQueue.put(Position(X, Y))

        while (not pixelQueue.empty()):
            temp = pixelQueue.get()
            
            if (temp.y-1 >= 0 and not self.marked[temp.y-1][temp.x] and self.picture[temp.y-1][temp.x] == pixel):
                self.marked[temp.y-1][temp.x] = True
                self.picture[temp.y-1][temp.x] = C
                pixelQueue.put(Position(temp.x,temp.y-1))
            if (temp.y+1 < self.N and not self.marked[temp.y+1][temp.x] and self.picture[temp.y+1][temp.x] == pixel):
                self.marked[temp.y+1][temp.x] = True
                self.picture[temp.y+1][temp.x] = C
                pixelQueue.put(Position(temp.x,temp.y+1))
            if (temp.x-1 >= 0 and not self.marked[temp.y][temp.x-1] and self.picture[temp.y][temp.x-1] == pixel):
                self.marked[temp.y][temp.x-1] = True
                self.picture[temp.y][temp.x-1] = C
                pixelQueue.put(Position(temp.x-1,temp.y))
            if (temp.x+1 < self.M and not self.marked[temp.y][temp.x+1] and self.picture[temp.y][temp.x+1] == pixel):
                self.marked[temp.y][temp.x+1] = True
                self.picture[temp.y][temp.x+1] = C
                pixelQueue.put(Position(temp.x+1,temp.y))


    def resetMarkedValues(self):
        for i in range(0, self.N):
            for j in range(0, self.M):
                self.marked[i][j] = False
    

    # Writes the picture in the file Name.
    def commandS(self, Name):
        print(Name)
        self.printGEditor();


    def printGEditor(self):
        for i in range(0, self.N):
            for j in range(0, self.M):
                print(self.picture[i][j], end = "")
            print()



# Main program
def main():    
    geditor = GEditor()

    for line in stdin:
        command = line.split()

        if (command[0] == 'X'):
            break

        geditor.processCommand(command)

main()
