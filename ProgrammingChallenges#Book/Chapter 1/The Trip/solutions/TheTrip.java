import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author xergioalex
 */
public class TheTrip {
    static double[] expenses = new double[1000];

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String inputLine;

        while((inputLine = reader.readLine()) != null) {
            int n = Integer.parseInt(inputLine);

            if (n == 0) {
                break;
            }

            double total = 0, average = 0, toExchangePos = 0, toExchangeNeg = 0;

            for (int i = 0; i < n; i++) {
                inputLine = reader.readLine();
                expenses[i] = Double.parseDouble(inputLine);
                total += expenses[i];
            }

            /* Get average */
            average = total / (double) n;

            for (int i = 0; i < n; i++) {
                double dif = expenses[i] - average;
                /* Set two digits accuracy */
                dif = (double) (((long) (dif * 100.0)) / 100.0 );

                if (dif > 0) {
                    toExchangePos += dif;
                } else {
                    toExchangeNeg += dif;
                }
            }

            double minToExchange = (-toExchangeNeg > toExchangePos)? -toExchangeNeg: toExchangePos;
            System.out.format("$%.2f\n", minToExchange);
        }
    }
}