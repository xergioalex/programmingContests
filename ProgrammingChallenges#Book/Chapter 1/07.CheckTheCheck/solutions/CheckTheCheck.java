import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author xergioalex
 */
public class CheckTheCheck {
    
    public static class King {
        int x, y;
        boolean isWhite;

        public King(int x, int y, boolean isWhite){
            this.x = x;
            this.y = y;
            this.isWhite = isWhite;
        }
    }
    
    public static class Chess {
        char[][] field = new char[8][8];
        King kingBlack, kingWhite;

        public Chess(char[][] field, King kingWhite, King kingBlack) {
           this.field = field;
           this.kingWhite = kingWhite;
           this.kingBlack = kingBlack;
        }

        public String checkTheCheck() {
            if (this.checkKing(this.kingWhite)) {
                return "white king is in check.";
            } else if (this.checkKing(this.kingBlack)) {
                return "black king is in check.";
            }
            
            return "no king is in check.";
        }
        
        public boolean checkKing(King king) {
            return diagonalCheck(king) || straightCheck(king) || knightCheck(king);
        }
        
        public boolean diagonalCheck(King king) {
            // Verify top left
            for (int i = king.y-1, j = king.x-1; i >= 0 && j >= 0; i--, j--) {
                int result = diagonalCheckValidation(king, i, j);
                if (result == -1) {
                    break;
                } else if (result == 1) {
                    return true;
                }
            }
            
            // Verify top right
            for (int i = king.y-1, j = king.x+1; i >= 0 && j < 8; i--, j++) {
                int result = diagonalCheckValidation(king, i, j);
                if (result == -1) {
                    break;
                } else if (result == 1) {
                    return true;
                }
            }
            
            // Verify bottom left
            for (int i = king.y+1, j = king.x-1; i < 8 && j >= 0; i++, j--) {
                int result = diagonalCheckValidation(king, i, j);
                if (result == -1) {
                    break;
                } else if (result == 1) {
                    return true;
                }
            }
            
            // Verify bottom right
            for (int i = king.y+1, j = king.x+1; i < 8 && j < 8; i++, j++) {
                int result = diagonalCheckValidation(king, i, j);
                if (result == -1) {
                    break;
                } else if (result == 1) {
                    return true;
                }
            }
            
            return false;
        }
        
        public int diagonalCheckValidation(King king, int i, int j) {
            if (this.field[i][j] == '.') {
                return 0;
            } else {
                // Verify if another piece with same color is on the way. 97 is ascii number for 'a' simbol
                if ((king.isWhite && this.field[i][j] < 97) || (!king.isWhite && this.field[i][j] >= 97)) {
                    return -1;
                }
                // Check pawn
                if ((king.isWhite && this.field[i][j] == 'p' && king.y - i == 1) || (!king.isWhite && this.field[i][j] == 'P' && i - king.y == 1)) {
                    return 1;
                }
                // Check bishop && queen
                if (this.field[i][j] == 'b' || this.field[i][j] == 'q' || this.field[i][j] == 'B' || this.field[i][j] == 'Q') {
                    return 1;
                }
            }
            
            return -1;
        }
        
        public boolean straightCheck(King king) {
            // Verify top
            for (int i = king.y-1, j = king.x; i >= 0; i--) {
                int result = straightCheckValidation(king, i, j);
                if (result == -1) {
                    break;
                } else if (result == 1) {
                    return true;
                }
            }
            
            // Verify bottom
            for (int i = king.y+1, j = king.x; i < 8; i++) {
                int result = straightCheckValidation(king, i, j);
                if (result == -1) {
                    break;
                } else if (result == 1) {
                    return true;
                }
            }
            
            // Verify left
            for (int i = king.y, j = king.x-1; j >= 0; j--) {
                int result = straightCheckValidation(king, i, j);
                if (result == -1) {
                    break;
                } else if (result == 1) {
                    return true;
                }
            }
            
            // Verify right
            for (int i = king.y, j = king.x+1; j < 8; j++) {
                int result = straightCheckValidation(king, i, j);
                if (result == -1) {
                    break;
                } else if (result == 1) {
                    return true;
                }
            }
            
            return false;
        }
        
        public int straightCheckValidation(King king, int i, int j) {
            if (this.field[i][j] == '.') {
                return 0;
            } else {
                // Verify if another piece with same color is on the way. 97 is ascii number for 'a' simbol
                if ((king.isWhite && this.field[i][j] < 97) || (!king.isWhite && this.field[i][j] >= 97)) {
                    return -1;
                }
                // Check rook && queen
                if (this.field[i][j] == 'r' || this.field[i][j] == 'q' || this.field[i][j] == 'R' || this.field[i][j] == 'Q') {
                    return 1;
                }
            }
            
            return -1;
        }
        
        public boolean knightCheck(King king) {
            int i, j;
            // Verify top left
            i = king.y-1;
            j = king.x-2;
            if (knightCheckValidation(king, i, j)) {
                return true;
            }
            
            i = king.y-2;
            j = king.x-1;
            if (knightCheckValidation(king, i, j)) {
                return true;
            }
            
            // Verify top right
            i = king.y-2;
            j = king.x+1;
            if (knightCheckValidation(king, i, j)) {
                return true;
            }
            
            i = king.y-1;
            j = king.x+2;
            if (knightCheckValidation(king, i, j)) {
                return true;
            }
            
            // Verify bottom left
            i = king.y+1;
            j = king.x-2;
            if (knightCheckValidation(king, i, j)) {
                return true;
            }
            
            i = king.y+2;
            j = king.x-1;
            if (knightCheckValidation(king, i, j)) {
                return true;
            }
            
            
            // Verify bottom right
            i = king.y+2;
            j = king.x+1;
            if (knightCheckValidation(king, i, j)) {
                return true;
            }
            
            i = king.y+1;
            j = king.x+2;
            if (knightCheckValidation(king, i, j)) {
                return true;
            }
            
            return false;
        }
        
        public boolean knightCheckValidation(King king, int i, int j) {
            if (i >= 0 && i < 8 && j >= 0 && j < 8) {
                if ((king.isWhite && this.field[i][j] == 'n') || (!king.isWhite && this.field[i][j] == 'N')) {
                    return true;
                }
            }
            return false;
        }
        
    }
    
    public static void main(String[] XergioAleX) throws FileNotFoundException, IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token;        
        String inputLine;
        
        char[][] field = new char [8][8];
        King kingWhite = null, kingBlack = null;
        int gameNumber = 1;
        while(true) {
            /* Fill field */            
            boolean isEmpty = true;
            for (int i = 0; i < 8; i++) {
                inputLine = reader.readLine();
                for (int j = 0; j < 8; j++) {
                    field[i][j] = inputLine.charAt(j);
                    if (field[i][j] != '.') {
                        isEmpty = false;
                    }
                    if (field[i][j] == 'K') {
                        kingWhite = new King(j, i, true);
                    }
                    if (field[i][j] == 'k') {
                        kingBlack = new King(j, i, false);
                    }
                }
            }
            
            if (isEmpty) {
                break;
            }
            reader.readLine();
            
            /* Verify chess */
            Chess chess = new Chess(field, kingWhite, kingBlack);
            String answer = chess.checkTheCheck();
            System.out.format("Game #%d: %s\n", gameNumber, answer);
            gameNumber++;
        }
    }
}
