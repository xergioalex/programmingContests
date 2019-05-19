import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author xergioalex
 */
public class Minesweeper {
    public static class MineField {
        int n, m;
        char[][] field;

        public MineField(int n, int m, BufferedReader reader) throws IOException {
            this.n = n;
            this.m = m;
            this.field = new char[this.n][this.m];

            /* Fill field */
            for (int i = 0; i < n; i++) {
                String inputLine = reader.readLine();
                for (int j = 0; j < m; j++) {
                    this.field[i][j] = inputLine.charAt(j);
                }
            }
        }

        public int calcMines(int i, int j) {
            int mines = 0;
            for (int k = i-1; k <= i+1; k++) {
                for (int l = j-1; l <= j+1; l++) {
                    if (k >= 0 && k < this.n && l >= 0  && l < this.m) {
                        if (this.field[k][l] == '*') {
                            mines++;
                        }
                    }
                }
            }

            return mines;
        }

        public void resolveMinesweeper() {
            for (int i = 0; i < this.n; i++) {
                for (int j = 0; j < this.m; j++) {
                    if (this.field[i][j] == '.') {
                        this.field[i][j] = (char) (this.calcMines(i, j) + 48);
                    }
                }
            }
        }

        @Override
        public String toString(){
            String out = "";
            for (int i = 0; i < this.n; i++) {
                for (int j = 0; j < this.m; j++) {
                    out += this.field[i][j];
                }
                if (i != this.n -1) {
                    out+="\n";
                }
            }

            return out;
        }
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token;
        String inputLine;

        int resultIndex = 1;
        while((inputLine = reader.readLine()) != null) {
            token = new StringTokenizer(inputLine);

            int n = Integer.parseInt(token.nextToken());
            int m = Integer.parseInt(token.nextToken());

            if (n == 0 && m == 0) {
                break;
            }

            if (resultIndex > 1) {
                System.out.println();
            }

            MineField minefield = new MineField(n, m, reader);
            /* Resolve minesweeper */
            minefield.resolveMinesweeper();

            System.out.format("Field #%d:\n", resultIndex);
            System.out.println(minefield);
            resultIndex++;
        }
    }

}