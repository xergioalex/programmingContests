#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct Candidate {
    char name[100];
    int counter;
    int eliminated;
};

struct Ballot {
    int votes[20];
    int ppos; /* preference position */
};

int candidatesCount, ballotsCount;
struct Candidate candidates[20];
struct Ballot ballots[1000];

void getCandidates();
void getBallots();
void findWinners();

int main() {
    int i, cases;
    char inputLine[10];

    fgets(inputLine, sizeof inputLine, stdin);
    cases = atoi(inputLine);
    fgets(inputLine, sizeof inputLine, stdin);

    /* Loop in cases */
    for (i = 0; i < cases; i++) {
        fgets(inputLine, sizeof inputLine, stdin);
        candidatesCount = atoi(inputLine);

        /* Read stdio */
        getCandidates();
        getBallots();
        findWinners();
        if (i < cases - 1) {
            printf("\n");
        }
    }
}


void getCandidates() {
    int i;
    char inputLine[100];
    for (i = 0; i < candidatesCount; i++) {
        fgets(candidates[i].name, sizeof inputLine, stdin);
        candidates[i].counter = 0;
        candidates[i].eliminated = 0;
    }
}

void getBallots() {
    char inputLine[1000];
    int count = 0, index;
    while ((fgets(inputLine, sizeof inputLine, stdin) != NULL) && inputLine[0] != '\n' && inputLine[0] != '\0') {
        char *token = strtok(inputLine, " ");
        index = 0;
        while(token != NULL) {
            ballots[count].ppos = 0;
            ballots[count].votes[index] = atoi(token) - 1;
            token = strtok(NULL, " ");
            index++;
        }
        count++;
    }
    ballotsCount = count;
}

void findWinners() {
    int i, j, pposUpdated, bestCounter, worstCounter;
    int winnerCount = -1;

    for (i = 0; i < ballotsCount; i++) {
        candidates[ballots[i].votes[0]].counter++;
    }

    while (winnerCount == -1) {
        /* Update preferences position && adding votes to no eliminated candidates */
        for (i = 0; i < ballotsCount; i++) {
            pposUpdated = 0;
            while (candidates[ballots[i].votes[ballots[i].ppos]].eliminated) {
                ballots[i].ppos++;
                pposUpdated = 1;
            }
            
            if (pposUpdated) {
                candidates[ballots[i].votes[ballots[i].ppos]].counter++;
            }
        }

        bestCounter = 0;
        worstCounter = 1000;

        
        for (i = 0; i < candidatesCount; i++) {
            if (candidates[i].eliminated) {
                continue;
            }
            if (candidates[i].counter > bestCounter) {
                bestCounter = candidates[i].counter;
            }
            if (candidates[i].counter < worstCounter) {
                worstCounter = candidates[i].counter;
            }
        }

        if (bestCounter == worstCounter || bestCounter*2 > ballotsCount) {
            winnerCount = bestCounter;
        } else {
            for (i = 0; i < candidatesCount; i++) {
                if (candidates[i].counter == worstCounter) {
                    candidates[i].eliminated = 1;
                }
            }
        }
    }


    /* Print winners */
    for (i = 0; i < candidatesCount; ++i) {
        if (candidates[i].counter == winnerCount && !candidates[i].eliminated) {
            printf("%s", candidates[i].name);
        }
    }
}