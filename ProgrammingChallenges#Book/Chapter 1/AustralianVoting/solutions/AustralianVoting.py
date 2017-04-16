from sys import stdin

class Candidate:
    def __init__(self, name, counter, eliminated):
        self.name = name
        self.counter = counter
        self.eliminated = eliminated


class Ballot:
    def __init__(self, votes):
        self.votes = votes
        self.ppos = 0


def getCandidates(candidatesCount, candidates):
    # print("entraaaa ..... %d" % candidatesCount)
    for i in range(0, candidatesCount):
        candidates[i] = Candidate(stdin.readline(), 0, False)    


def getBallots(ballots):
    count = 0

    for inputLine in stdin:
        # print("Entraaaaa")
        if (inputLine == "" or inputLine == "\n"):
            break

        votes = [(int(item) - 1) for item in inputLine.split()]
        ballots[count] = Ballot(votes)
        count = count + 1
        
    return count


def findWinners(candidatesCount, candidates, ballots, ballotsCount):

    for i in range(0, ballotsCount):
        candidates[ballots[i].votes[0]].counter = candidates[ballots[i].votes[0]].counter + 1

    winnerCount = -1;
    while (winnerCount == -1):
        # Update preferences position and adding votes to no eliminated candidates
        for i in range(0, ballotsCount):
            pposUpdated = False
            while (candidates[ballots[i].votes[ballots[i].ppos]].eliminated):
                ballots[i].ppos = ballots[i].ppos + 1
                pposUpdated = True

            if (pposUpdated):
                candidates[ballots[i].votes[ballots[i].ppos]].counter = candidates[ballots[i].votes[ballots[i].ppos]].counter + 1;

        bestCounter = 0;
        worstCounter = 1000;

        for i in range(0, candidatesCount):
            if (candidates[i].eliminated):
                continue
            
            if (candidates[i].counter > bestCounter):
                bestCounter = candidates[i].counter
            
            if (candidates[i].counter < worstCounter):
                worstCounter = candidates[i].counter;
        

        if (bestCounter == worstCounter or bestCounter*2 > ballotsCount):
            winnerCount = bestCounter
        else:
            for i in range(0, candidatesCount):
                if (candidates[i].counter == worstCounter):
                    candidates[i].eliminated = True;


    # Print winners
    for i in range(0, candidatesCount):
        if (candidates[i].counter == winnerCount and (not candidates[i].eliminated)):
            print(candidates[i].name, end="")


# Main program
def main():
    candidatesCount = 0
    ballotsCount = 0
    candidates = [False for x in range(20)]
    ballots = [False for x in range(1000)]

    cases = int(stdin.readline())
    stdin.readline()
    for i in range(0, cases):
        candidatesCount = int(stdin.readline())
        # print("Candidates count: %d" % candidatesCount)

        # Read stdio
        getCandidates(candidatesCount, candidates)
        ballotsCount = getBallots(ballots)
        findWinners(candidatesCount, candidates, ballots, ballotsCount)

        if (i < cases - 1):
            print();

main()
