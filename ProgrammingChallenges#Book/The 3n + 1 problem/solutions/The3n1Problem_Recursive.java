import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author xergioalex
 */
public class The3n1Problem {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token;
        String inputLine;

        while((inputLine = reader.readLine()) != null) {
            token = new StringTokenizer(inputLine);

            int i = Integer.parseInt(token.nextToken());
            int j = Integer.parseInt(token.nextToken());

            System.out.format("%d %d %d\n", i, j, getMaximumCycleLength(i, j));
        }
    }

    /* Get the cycle lenght of a number */
    public static int getCycleLength(int number, int cycleLenght) {
        cycleLenght++;

        if (number == 1) {
            return cycleLenght;
        } else if (number % 2 == 1) {
            number = 3 * number + 1;
        } else {
            number = number / 2;
        }
        return getCycleLength(number, cycleLenght);
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
        int max = 0;
        for (int num = i; num <= j; num++) {
            int cycleLenght = getCycleLength(num, 0);
            if (max < cycleLenght) {
                max = cycleLenght;
            }
        }
        return max;
    }

}
