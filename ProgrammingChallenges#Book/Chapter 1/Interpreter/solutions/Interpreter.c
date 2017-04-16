#include <stdio.h>
#include <string.h>
#include <stdlib.h>

/* PC properties */
struct Computer {
    int address;
    int registers[10];
    char RAM[1000][5];
};

struct Computer pc;

void resetComputer();
void instruction0(int d, int s);
void instruction2(int d, int n);
void instruction3(int d, int n);
void instruction4(int d, int n);
void instruction5(int d, int s);
void instruction6(int d, int s);
void instruction7(int d, int s);
void instruction8(int d, int a);
void instruction9(int s, int a);
int runProgram();

/* Main program */
int main () {
    int i, cases, index;
    char inputLine[5];
    fgets(inputLine, sizeof inputLine, stdin);
    cases = atoi(inputLine);
    fgets(inputLine, sizeof inputLine, stdin);

    for (i = 0; i < cases; i++) {
        resetComputer();

        index = 0;
        while ((fgets(inputLine, sizeof inputLine, stdin) != NULL) && inputLine[0] != '\n' && inputLine[0] != '\0') {
            strcpy(pc.RAM[index], inputLine);
            index++;
        }

        printf("%d\n", runProgram());
        
        if (i != cases-1) {
            printf("\n");
        }
    }


    return 0;
}



void resetComputer() {
    pc.address = 0;

    int i;
    for (i = 0; i < 10; i++) {
        pc.registers[i] = 0;
    }

    for (i = 0; i < 1000; i++) {
        strcpy(pc.RAM[i], "000");
    }
}

void instruction0(int d, int s){
    if (pc.registers[s] != 0) {
        pc.address = pc.registers[d]-1;
    }
}

void instruction2(int d, int n){
    pc.registers[d] = n;
    pc.registers[d] %= 1000;
}

void instruction3(int d, int n){
    pc.registers[d] += n;
    pc.registers[d] %= 1000;
}

void instruction4(int d, int n){
    pc.registers[d] *= n;
    pc.registers[d] %= 1000;
}

void instruction5(int d, int s){
    pc.registers[d] = pc.registers[s];
    pc.registers[d] %= 1000;
}

void instruction6(int d, int s){
    pc.registers[d] += pc.registers[s];
    pc.registers[d] %= 1000;
}

void instruction7(int d, int s){
    pc.registers[d] *= pc.registers[s];
    pc.registers[d] %= 1000;
}

void instruction8(int d, int a){
    pc.registers[d] = atoi(pc.RAM[pc.registers[a]]);
    pc.registers[d] %= 1000;
}

void instruction9(int s, int a){
    sprintf(pc.RAM[pc.registers[a]], "%d", pc.registers[s]);
}

int runProgram() {    
    int countInstructions = 1;
    while (pc.RAM[pc.address][0] != '1') {
        countInstructions++;
        switch (pc.RAM[pc.address][0]) {
            case '0':
                instruction0(pc.RAM[pc.address][1]-48, pc.RAM[pc.address][2]-48);
                break;
            case '2':
                instruction2(pc.RAM[pc.address][1]-48, pc.RAM[pc.address][2]-48);
                break;
            case '3':
                instruction3(pc.RAM[pc.address][1]-48, pc.RAM[pc.address][2]-48);
                break;
            case '4':
                instruction4(pc.RAM[pc.address][1]-48, pc.RAM[pc.address][2]-48);
                break;
            case '5':
                instruction5(pc.RAM[pc.address][1]-48, pc.RAM[pc.address][2]-48);
                break;
            case '6':
                instruction6(pc.RAM[pc.address][1]-48, pc.RAM[pc.address][2]-48);
                break;
            case '7':
                instruction7(pc.RAM[pc.address][1]-48, pc.RAM[pc.address][2]-48);
                break;
            case '8':
                instruction8(pc.RAM[pc.address][1]-48, pc.RAM[pc.address][2]-48);
                break;
            case '9':
                instruction9(pc.RAM[pc.address][1]-48, pc.RAM[pc.address][2]-48);
                break;
        }
        
        pc.address++;
    }
    
    return countInstructions;
}