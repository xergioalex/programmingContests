import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author xergioalex
 */
public class LCDisplay {
    public static class Display {
        int size;
        String number;

        char[][][] lcdDigits = {
            {/* zero */
                {' ','-',' ' },
                {'|',' ','|' },
                {' ',' ',' ' },
                {'|',' ','|' },
                {' ','-',' ' }
            },
            {/* one */
                {' ',' ',' ' },
                {' ',' ','|' },
                {' ',' ',' ' },
                {' ',' ','|' },
                {' ',' ',' ' },
            },
            {/* two */
                {' ','-',' ' },
                {' ',' ','|' },
                {' ','-',' ' },
                {'|',' ',' ' },
                {' ','-',' ' },
            },
            {/* three */
                {' ','-',' ' },
                {' ',' ','|' },
                {' ','-',' ' },
                {' ',' ','|' },
                {' ','-',' ' },
            },
            {/* four */
                {' ',' ',' ' },
                {'|',' ','|' },
                {' ','-',' ' },
                {' ',' ','|' },
                {' ',' ',' ' },
            },
            {/* five */
                {' ','-',' ' },
                {'|',' ',' ' },
                {' ','-',' ' },
                {' ',' ','|' },
                {' ','-',' ' },
            },
            {/* six */
                {' ','-',' ' },
                {'|',' ',' ' },
                {' ','-',' ' },
                {'|',' ','|' },
                {' ','-',' ' },
            },
            {/* seven */
                {' ','-',' ' },
                {' ',' ','|' },
                {' ',' ',' ' },
                {' ',' ','|' },
                {' ',' ',' ' },
            },
            {/* eight */
                {' ','-',' ' },
                {'|',' ','|' },
                {' ','-',' ' },
                {'|',' ','|' },
                {' ','-',' ' },
            },
            {/* nine */
                {' ','-',' ' },
                {'|',' ','|' },
                {' ','-',' ' },
                {' ',' ','|' },
                {' ','-',' ' },
            }
        };

        public Display(int size, String number) {
            this.size = size;
            this.number = number;
        }

        void setNumber(String number) {
            this.number = number;
        }

        void setSize(int size) {
            this.size = size;
        }

        public String repeatChar(char character, int times) {
            String out = "";

            for (int i = 0; i < times; i++) {
                out += character;
            }

            return out;
        }


        @Override
        public String toString(){
            StringBuilder out = new StringBuilder("");

            for (int rows = 0; rows < this.lcdDigits[0].length; rows++) {
                int rowsCount = (rows % 2 == 0)? 1: this.size;
                for (int repeatRows = 0; repeatRows < rowsCount; repeatRows++) {
                    for (int indexNumber = 0; indexNumber < number.length(); indexNumber++) {
                        int lcdNumber = Character.getNumericValue(this.number.charAt(indexNumber));
                        for (int columns = 0; columns < this.lcdDigits[0][0].length; columns++) {
                            char character = this.lcdDigits[lcdNumber][rows][columns];

                            out.append(this.repeatChar(character, (columns % 2 == 0)? 1: this.size));
                        }

                        if (indexNumber < number.length() - 1) {
                            out.append(" ");
                        }
                    }

                    if (rows < this.lcdDigits[0].length - 1) {
                        out.append("\n");
                    }
                }
            }

            return out.toString();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token;
        String inputLine;

        Display display = new Display(2, "0");
        int resultIndex = 0;

        while((inputLine = reader.readLine()) != null) {
            token = new StringTokenizer(inputLine);

            int s = Integer.parseInt(token.nextToken());
            String n = token.nextToken();

            if (s == 0) {
                break;
            }

            display.setSize(s);
            display.setNumber(n);

            System.out.println(display);
            System.out.println();

            resultIndex++;
        }
    }
}