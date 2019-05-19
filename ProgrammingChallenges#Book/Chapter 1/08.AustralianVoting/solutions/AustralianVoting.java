package programmingcontests;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author xergioalex
 */
public class AustralianVoting {

    public static int candidatesCount, ballotsCount;
    public static Candidate[] candidates = new Candidate[20];
    public static Ballot ballots[] = new Ballot[1000];
    
    public static class Candidate {
        String name;
        int counter;
        boolean eliminated;

        public Candidate(String name, int counter, boolean eliminated){
            this.name = name;
            this.counter = counter;
            this.eliminated = eliminated;
        }
    }
    
    public static class Ballot {
        int[] votes;
        int ppos;

        public Ballot(){
            this.votes = new int[20];
            this.ppos = 0;
        }
    }
    
    public static void getCandidates(BufferedReader reader) throws IOException {
        for (int i = 0; i < candidatesCount; i++) {
            candidates[i] = new Candidate(reader.readLine(), 0, false);
        }
    }

    public static void getBallots(BufferedReader reader) throws IOException {
        int count = 0;
        String inputLine;
        StringTokenizer token;
        
        while ((inputLine = reader.readLine()) != null && !inputLine.equals("")) {
            token = new StringTokenizer(inputLine);
            int index = 0;
            ballots[count] = new Ballot();
            while (token.hasMoreTokens()) {
                ballots[count].votes[index] = Integer.parseInt(token.nextToken()) - 1;
                index++;
            }
            count++;
        }

        ballotsCount = count;
    }

    public static void findWinners() {
        boolean pposUpdated;
        int bestCounter, worstCounter;
        int winnerCount = -1;

        for (int i = 0; i < ballotsCount; i++) {
            candidates[ballots[i].votes[0]].counter++;
        }

        while (winnerCount == -1) {
            /* Update preferences position && adding votes to no eliminated candidates */
            for (int i = 0; i < ballotsCount; i++) {
                pposUpdated = false;
                while (candidates[ballots[i].votes[ballots[i].ppos]].eliminated) {
                    ballots[i].ppos++;
                    pposUpdated = true;
                }

                if (pposUpdated) {
                    candidates[ballots[i].votes[ballots[i].ppos]].counter++;
                }
            }

            bestCounter = 0;
            worstCounter = 1000;


            for (int i = 0; i < candidatesCount; i++) {
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
                for (int i = 0; i < candidatesCount; i++) {
                    if (candidates[i].counter == worstCounter) {
                        candidates[i].eliminated = true;
                    }
                }
            }
        }


        /* Print winners */
        for (int i = 0; i < candidatesCount; ++i) {
            if (candidates[i].counter == winnerCount && !candidates[i].eliminated) {
                System.out.println(candidates[i].name);
            }
        }
    }
    
    public static void main(String[] XergioAleX) throws FileNotFoundException, IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(reader.readLine());
        reader.readLine();
        
        /* Loop in cases */
        for (int i = 0; i < cases; i++) {
            candidatesCount = Integer.parseInt(reader.readLine());

            /* Read stdio */
            getCandidates(reader);
            getBallots(reader);
            
            findWinners();
            if (i < cases - 1) {
                System.out.println();
            }
        }
    }
}
