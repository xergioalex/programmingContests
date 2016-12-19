import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author XergioAleX
 */
public class GraphicalEditor {

    public static class Position {
        int x;
        int y;

        public Position(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static class GEditor{
        int M = 0, N = 0;

        char[][] picture = new char[250][250];
        boolean[][] marked = new boolean[250][250];

        public GEditor() {}

        void processCommand(char command, StringTokenizer token) {
            switch (command) {
                case 'I':
                    // commandI(int M, int N)
                    this.commandI(Integer.valueOf(token.nextToken()), Integer.valueOf(token.nextToken()));
                    break;
                case 'C':
                    this.commandC();
                    break;
                case 'L':
                    // commandI(int X, int Y, char C)
                    this.commandL(Integer.valueOf(token.nextToken())-1, Integer.valueOf(token.nextToken())-1, token.nextToken().charAt(0));
                    break;
                case 'V':
                    // commandV(int X, int Y1, int Y2, char C)
                    this.commandV(Integer.valueOf(token.nextToken())-1, Integer.valueOf(token.nextToken())-1, Integer.parseInt(token.nextToken())-1, token.nextToken().charAt(0));
                    break;
                case 'H':
                    // commandH(int X1, int X2, int Y, char C)
                    this.commandH(Integer.valueOf(token.nextToken())-1, Integer.valueOf(token.nextToken())-1, Integer.parseInt(token.nextToken())-1, token.nextToken().charAt(0));
                    break;
                case 'K':
                    // commandK(int X1, int X2, int Y1, int Y2, char C)
                    this.commandK(Integer.valueOf(token.nextToken())-1, Integer.valueOf(token.nextToken())-1, Integer.parseInt(token.nextToken())-1, Integer.parseInt(token.nextToken())-1, token.nextToken().charAt(0));
                    break;
                case 'F':
                    // commandF(int X, int Y, char C)
                    this.commandF(Integer.valueOf(token.nextToken())-1, Integer.valueOf(token.nextToken())-1, token.nextToken().charAt(0));
                    break;
                case 'S':
                    // commandS(string Name)
                    this.commandS(token.nextToken());
                    break;
            }
        }

        public void commandI(int M, int N){
            this.M = M;
            this.N = N;

            for (int i = 0; i < this.N; i++) {
                for (int j = 0; j < this.M; j++) {
                    this.picture[i][j] = 'O';
                }
            }
        }


        public void commandC(){
            for (int i = 0; i < this.N; i++) {
                for (int j = 0; j < this.M; j++) {
                    this.picture[i][j] = 'O';
                }
            }
        }

        public void commandL(int X, int Y, char C){
            this.picture[Y][X] = C;
        }

        public void commandV(int X, int Y1, int Y2, char C){
            if (Y1 > Y2) {
                Y1 = Y1+Y2;
                Y2 = Y1-Y2;
                Y1 = Y1-Y2;
            }
            for (int i = Y1; i <= Y2; i++)
                this.picture[i][X] = C;
        }

        public void commandH(int X1,int X2,int Y, char C){
            if (X1 > X2) {
                X1 = X1+X2;
                X2 = X1-X2;
                X1 = X1-X2;
            }
            for (int i = X1; i <= X2; i++)
                this.picture[Y][i] = C;
        }

        public void commandK(int X1, int Y1, int X2, int Y2, char C){
            if (X1 > X2){
                X1 = X1+X2;
                X2 = X1-X2;
                X1 = X1-X2;
            }
            if (Y1 > Y2) {
                Y1 = Y1+Y2;
                Y2 = Y1-Y2;
                Y1 = Y1-Y2;
            }
            for (int i = Y1; i <= Y2; i++) {
                for (int j = X1; j <= X2; j++) {
                    this.picture[i][j] = C;
                }
            }
        }

        public void commandF(int X, int Y, char C){
            this.resetMarkedValues();

            char pixel = this.picture[Y][X];

            this.picture[Y][X]=C;
            this.marked[Y][X] = true;
            Queue<Position> queue = new LinkedList();
            queue.offer(new Position(X,Y));
            while(!queue.isEmpty()){
                Position temp = queue.poll();
                if(temp.y-1 >= 0 && !this.marked[temp.y-1][temp.x] && this.picture[temp.y-1][temp.x] == pixel){
                    this.marked[temp.y-1][temp.x]=true;
                    this.picture[temp.y-1][temp.x]=C;
                    queue.offer(new Position(temp.x,temp.y-1));
                }
                if(temp.y+1 < this.N && !this.marked[temp.y+1][temp.x] && this.picture[temp.y+1][temp.x] == pixel){
                    this.marked[temp.y+1][temp.x]=true;
                    this.picture[temp.y+1][temp.x]=C;
                    queue.offer(new Position(temp.x,temp.y+1));
                }
                if(temp.x-1 >= 0 && !this.marked[temp.y][temp.x-1] && this.picture[temp.y][temp.x-1] == pixel){
                    this.marked[temp.y][temp.x-1]=true;
                    this.picture[temp.y][temp.x-1]=C;
                    queue.offer(new Position(temp.x-1,temp.y));
                }
                if(temp.x+1 < this.M && !this.marked[temp.y][temp.x+1] && this.picture[temp.y][temp.x+1] == pixel){
                    this.marked[temp.y][temp.x+1] = true;
                    this.picture[temp.y][temp.x+1] = C;
                    queue.offer(new Position(temp.x+1,temp.y));
                }
            }
        }

        void resetMarkedValues() {
            for (int i = 0; i < this.N; i++) {
                for (int j = 0; j < this.M; j++) {
                    this.marked[i][j] = false;
                }
            }
        }

        // Writes the picture in the file Name.
        void commandS(String Name) {
            System.out.println(Name);
            System.out.println(this.toString());
        }

        @Override
        public String toString(){
            StringBuilder out = new StringBuilder("");

            for (int i = 0; i < this.N; i++) {
                for (int j = 0; j < this.M; j++) {
                    out.append(this.picture[i][j]);
                }
                if (i != this.N - 1) {
                    out.append("\n");
                }


            }

            return out.toString();
        }

    }

    public static void main(String[] XergioAleX) throws FileNotFoundException, IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token;
        String inputLine;

        GEditor geditor = new GEditor();

        while((inputLine = reader.readLine()) != null) {
            token = new StringTokenizer(inputLine);

            char command = token.nextToken().charAt(0);
            if (command == 'X') {
                break;
            }

            geditor.processCommand(command, token);
        }
    }
}
