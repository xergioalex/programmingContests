#include <stdio.h>
#include <string.h>
#include <queue>

using namespace std;

class Position {
    public:
        int x, y;

        Position(int x, int y) {
            this->x = x;
            this->y = y;
        }
};

class GEditor {
    public:
        int M, N;
        char picture[250][250];
        int marked[250][250];

        GEditor() {}

        void processCommand(char command) {
            char C;
            char Name[100];
            int M, N, X, Y, X1, X2, Y1, Y2;


            switch (command) {
                case 'I':
                    // commandI(int M, int N)
                    scanf("%d %d", &M, &N);
                    this->commandI(M, N);
                    break;
                case 'C':
                    this->commandC();
                    break;
                case 'L':
                    // commandI(int X, int Y, char C)
                    scanf("%d %d %c", &X, &Y, &C);
                    this->commandL(X-1, Y-1, C);
                    break;
                case 'V':
                    // commandV(int X, int Y1, int Y2, char C)
                    scanf("%d %d %d %c", &X, &Y1, &Y2, &C);
                    this->commandV(X-1, Y1-1, Y2-1, C);
                    break;
                case 'H':
                    // commandH(int X1, int X2, int Y, char C)
                    scanf("%d %d %d %c", &X1, &X2, &Y, &C);
                    this->commandH(X1-1, X2-1, Y-1, C);
                    break;
                case 'K':
                    // commandK(int X1, int X2, int Y1, int Y2, char C)
                    scanf("%d %d %d %d %c", &X1, &X2, &Y1, &Y2, &C);
                    this->commandK(X1-1, X2-1, Y1-1, Y2-1, C);
                    break;
                case 'F':
                    // commandF(int X, int Y, char C)
                    scanf("%d %d %c", &X, &Y, &C);
                    this->commandF(X-1, Y-1, C);
                    break;
                case 'S':
                    // commandS(string Name)
                    scanf("%s", Name);
                    this->commandS(Name);
                    break;
            }
        }

        void commandI(int M, int N) {
            this->M = M;
            this->N = N;

            for (int i = 0; i < this->N; i++) {
                for (int j = 0; j < this->M; j++) {
                    this->picture[i][j] = 'O';
                }
            }
        }


        void commandC() {
            for (int i = 0; i < this->N; i++) {
                for (int j = 0; j < this->M; j++) {
                    this->picture[i][j] = 'O';
                }
            }
        }

        void commandL(int X, int Y, char C) {
            this->picture[Y][X] = C;
        }

        void commandV(int X, int Y1, int Y2, char C){
            if (Y1 > Y2) {
                Y1 = Y1+Y2;
                Y2 = Y1-Y2;
                Y1 = Y1-Y2;
            }
            for (int i = Y1; i <= Y2; i++)
                this->picture[i][X] = C;
        }

        void commandH(int X1,int X2,int Y, char C){
            if (X1 > X2) {
                X1 = X1+X2;
                X2 = X1-X2;
                X1 = X1-X2;
            }
            for (int i = X1; i <= X2; i++)
                this->picture[Y][i] = C;
        }

        void commandK(int X1, int Y1, int X2, int Y2, char C){
            if (X1 > X2){
                X1 = X1+X2;
                X2 = X1-X2;
                X1 = X1-X2;
            }
            if (Y1 > Y2) {
                Y1 = Y1+Y2;
                Y2 = Y1-Y2;
                Y1 = Y1-Y2;
            }
            for (int i = Y1; i <= Y2; i++) {
                for (int j = X1; j <= X2; j++) {
                    this->picture[i][j] = C;
                }
            }
        }

        void commandF(int X, int Y, char C){
            this->resetMarkedValues();

            char pixel = this->picture[Y][X];

            this->picture[Y][X] = C;
            this->marked[Y][X] = true;
            queue<Position*> pixelQueue;
            pixelQueue.push(new Position(X, Y));
            while(!pixelQueue.empty()){
                Position* temp = pixelQueue.front();
                pixelQueue.pop();
                if(temp->y-1 >= 0 && !this->marked[temp->y-1][temp->x] && this->picture[temp->y-1][temp->x] == pixel){
                    this->marked[temp->y-1][temp->x]=true;
                    this->picture[temp->y-1][temp->x]=C;
                    pixelQueue.push(new Position(temp->x,temp->y-1));
                }
                if(temp->y+1 < this->N && !this->marked[temp->y+1][temp->x] && this->picture[temp->y+1][temp->x] == pixel){
                    this->marked[temp->y+1][temp->x]=true;
                    this->picture[temp->y+1][temp->x]=C;
                    pixelQueue.push(new Position(temp->x,temp->y+1));
                }
                if(temp->x-1 >= 0 && !this->marked[temp->y][temp->x-1] && this->picture[temp->y][temp->x-1] == pixel){
                    this->marked[temp->y][temp->x-1]=true;
                    this->picture[temp->y][temp->x-1]=C;
                    pixelQueue.push(new Position(temp->x-1,temp->y));
                }
                if(temp->x+1 < this->M && !this->marked[temp->y][temp->x+1] && this->picture[temp->y][temp->x+1] == pixel){
                    this->marked[temp->y][temp->x+1] = true;
                    this->picture[temp->y][temp->x+1] = C;
                    pixelQueue.push(new Position(temp->x+1,temp->y));
                }
            }
        }

        void resetMarkedValues() {
            for (int i = 0; i < this->N; i++) {
                for (int j = 0; j < this->M; j++) {
                    this->marked[i][j] = false;
                }
            }
        }

        /* Writes the picture in the file Name. */
        void commandS(char Name[100]) {
            printf("%s\n", Name);
            this->printGEditor();
        }

        void printGEditor(){
            for (int i = 0; i < this->N; i++) {
                for (int j = 0; j < this->M; j++) {
                    printf("%c", this->picture[i][j]);
                }
                printf("\n");
            }
        }
};

/* Main program */
int main() {
    int i, j, n, m, resultIndex = 1;

    char inputLine[30];
    char command;

    GEditor* geditor =  new GEditor();

    while (scanf("%c", &command) != EOF) {
        if (command == 'X') {
            break;
        }

        geditor->processCommand(command);
    }

    return 0;
}