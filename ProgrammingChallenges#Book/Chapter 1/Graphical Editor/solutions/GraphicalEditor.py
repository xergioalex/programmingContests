from sys import stdin

class MineField:
    def __init__(self, n, m):
        self.n = n
        self.m = m
        self.field = [['' for x in range(m)] for y in range(n)]

        # Fill field
        for i in range(n):
            line = stdin.readline()
            for j in range(m):
                self.field[i][j] = line[j]

    def calcMines(self, i, j):
        mines = 0
        for k in range(i-1, i+2):
            for l in range(j-1, j+2):
                if (k >= 0 and k < self.n and l >= 0  and l < self.m):
                    if (self.field[k][l] == '*'):
                        mines += 1

        return mines;

    def resolveMinesweeper(self):
        for i in range(self.n):
            for j in range(self.m):
                if (self.field[i][j] == '.'):
                    self.field[i][j] = chr(self.calcMines(i, j) + 48)

    def __str__(self):
        out = "";
        for i in range(self.n):
            for j in range(self.m):
                out += self.field[i][j];

            if (i != self.n - 1):
                out+="\n";

        return out;



class Position:
    def __init__(self, x, y):
        self.x = x
        self.y = y

class GEditor:
    def __init__(self):
        self.M = 0
        self.N = 0
        self.picture = [['0' for x in range(250)] for y in range(250)]
        self.picture = [[False for x in range(250)] for y in range(250)]

        def processCommand(self, command, token):
            switch = {
                'I' : caseI,
                'C' : caseC,
                'L' : caseL,
                'V' : caseV,
                'H' : caseH,
                'K' : caseK,
                'F' : caseF,
                'S' : caseS
            }

            def caseI(self, token):
                # commandI(int M, int N)
                self.commandI(token[1], token[2])

            def caseC(self, token):
                self.commandC()

            def caseL(self, token):
                # commandI(int X, int Y, char C)
                self.commandL(int(token[1])-1, int(token[2])-1, (token[3])[0])

            def caseV(self, token):
                # commandV(int X, int Y1, int Y2, char C)
                self.commandV(int(token[1])-1, int(token[2])-1, int(token[3])-1, (token[4])[0])

            def caseH(self, token):
                # commandH(int X1, int X2, int Y, char C)
                self.commandH(int(token[1])-1, int(token[2])-1, int(token[3])-1, (token[4])[0])

            def caseK(self, token):
                # commandK(int X1, int X2, int Y1, int Y2, char C)
                self.commandK(int(token[1])-1, int(token[2])-1, int(token[3])-1, int(token[4])-1, (token[5])[6])

            def caseF(self, token):
                # commandF(int X, int Y, char C)
                self.commandF(int(token[1])-1, int(token[2])-1, (token[3])[0])

            def caseS(self, token):
                # commandS(string Name)
                self.commandS(token[1]);

            switch[command](token)

        def commandI(self, M, N):
            self.M = M;
            self.N = N;

            for i in range(0, self.N):
                for j in range(0, self.M):
                    self.picture[i][j] = 'O'


#         void commandC() {
#             for (int i = 0; i < self.N; i++) {
#                 for (int j = 0; j < self.M; j++) {
#                     self.picture[i][j] = 'O';
#                 }
#             }
#         }

#         void commandL(int X, int Y, char C) {
#             self.picture[Y][X] = C;
#         }

#         void commandV(int X, int Y1, int Y2, char C){
#             if (Y1 > Y2) {
#                 Y1 = Y1+Y2;
#                 Y2 = Y1-Y2;
#                 Y1 = Y1-Y2;
#             }
#             for (int i = Y1; i <= Y2; i++)
#                 self.picture[i][X] = C;
#         }

#         void commandH(int X1,int X2,int Y, char C){
#             if (X1 > X2) {
#                 X1 = X1+X2;
#                 X2 = X1-X2;
#                 X1 = X1-X2;
#             }
#             for (int i = X1; i <= X2; i++)
#                 self.picture[Y][i] = C;
#         }

#         void commandK(int X1, int Y1, int X2, int Y2, char C){
#             if (X1 > X2){
#                 X1 = X1+X2;
#                 X2 = X1-X2;
#                 X1 = X1-X2;
#             }
#             if (Y1 > Y2) {
#                 Y1 = Y1+Y2;
#                 Y2 = Y1-Y2;
#                 Y1 = Y1-Y2;
#             }
#             for (int i = Y1; i <= Y2; i++) {
#                 for (int j = X1; j <= X2; j++) {
#                     self.picture[i][j] = C;
#                 }
#             }
#         }

#         void commandF(int X, int Y, char C){
#             self.resetMarkedValues();

#             char pixel = self.picture[Y][X];

#             self.picture[Y][X] = C;
#             self.marked[Y][X] = true;
#             queue<Position*> pixelQueue;
#             pixelQueue.push(new Position(X, Y));
#             while(!pixelQueue.empty()){
#                 Position* temp = pixelQueue.front();
#                 pixelQueue.pop();
#                 if(temp->y-1 >= 0 && !self.marked[temp->y-1][temp->x] && self.picture[temp->y-1][temp->x] == pixel){
#                     self.marked[temp->y-1][temp->x]=true;
#                     self.picture[temp->y-1][temp->x]=C;
#                     pixelQueue.push(new Position(temp->x,temp->y-1));
#                 }
#                 if(temp->y+1 < self.N && !self.marked[temp->y+1][temp->x] && self.picture[temp->y+1][temp->x] == pixel){
#                     self.marked[temp->y+1][temp->x]=true;
#                     self.picture[temp->y+1][temp->x]=C;
#                     pixelQueue.push(new Position(temp->x,temp->y+1));
#                 }
#                 if(temp->x-1 >= 0 && !self.marked[temp->y][temp->x-1] && self.picture[temp->y][temp->x-1] == pixel){
#                     self.marked[temp->y][temp->x-1]=true;
#                     self.picture[temp->y][temp->x-1]=C;
#                     pixelQueue.push(new Position(temp->x-1,temp->y));
#                 }
#                 if(temp->x+1 < self.M && !self.marked[temp->y][temp->x+1] && self.picture[temp->y][temp->x+1] == pixel){
#                     self.marked[temp->y][temp->x+1] = true;
#                     self.picture[temp->y][temp->x+1] = C;
#                     pixelQueue.push(new Position(temp->x+1,temp->y));
#                 }
#             }
#         }

#         void resetMarkedValues() {
#             for (int i = 0; i < self.N; i++) {
#                 for (int j = 0; j < self.M; j++) {
#                     self.marked[i][j] = false;
#                 }
#             }
#         }

#         /* Writes the picture in the file Name. */
#         void commandS(char Name[100]) {
#             printf("%s\n", Name);
#             self.printGEditor();
#         }

#         void printGEditor(){
#             for (int i = 0; i < self.N; i++) {
#                 for (int j = 0; j < self.M; j++) {
#                     printf("%c", self.picture[i][j]);
#                 }
#                 printf("\n");
#             }
#         }
# };

# Main program
def main():
    geditor = GEditor()

    for line in stdin:
        token = line.split();

        command = (token[0])[0]

        if (command == 'X'):
            break

        geditor.processCommand(command, token);


main()

