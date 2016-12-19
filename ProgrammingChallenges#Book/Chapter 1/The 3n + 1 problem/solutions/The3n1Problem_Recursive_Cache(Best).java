import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 *
 * @author xergioalex
 */
public class The3n1Problem {
    static Map<Integer, Integer> cache = new HashMap<Integer, Integer>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token;
        String inputLine;

        // Init base case
        cache.put(1, 1);

        while((inputLine = reader.readLine()) != null) {
            token = new StringTokenizer(inputLine);

            int i = Integer.parseInt(token.nextToken());
            int j = Integer.parseInt(token.nextToken());

            System.out.format("%d %d %d\n", i, j, getMaximumCycleLength(i, j));
        }
    }

    /* Get the cycle lenght of a number */
    public static int getCycleLength(int number) {
        int originalNumber = number;

        if (cache.containsKey(number)) {
            return cache.get(number);
        }  else if (number == 1) {
            return 1;
        } else if (number % 2 == 1) {
            number = 3 * number + 1;
        } else {
            number = number / 2;
        }
        int cycleLength = getCycleLength(number) + 1;
        cache.put(originalNumber, cycleLength);

        return cycleLength;
    }

    /* Get maximum cycle lenght of numbers between i && j */
    public static int getMaximumCycleLength(int i, int j) {
        /* Swap */
        if (i > j) {
            i = i + j;
            j = i - j;
            i = i - j;
        }

        /* Calc max length */
        int max = 0, cycleLenght;
        for (int num = i; num <= j; num++) {
            if (cache.containsKey(num)) {
                cycleLenght = cache.get(num);
            } else {
                cycleLenght = getCycleLength(num);
            }

            if (max < cycleLenght) {
                max = cycleLenght;
            }
        }
        return max;
    }

}