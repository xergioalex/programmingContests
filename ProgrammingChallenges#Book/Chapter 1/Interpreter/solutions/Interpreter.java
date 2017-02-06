import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author XergioAleX
 */
public class Interpreter {

    public static class Computer {
        int address = 0;
        int[] registers = new int[10];
        String[] RAM = new String[1000];

        public Computer(){
            for (int i = 0; i < 1000; i++) {
                this.RAM[i] = "000";
            }
        }
        
        public int runProgram() {
            int countInstructions = 1;
            while (this.RAM[this.address].charAt(0) != '1') {
                countInstructions++;
                switch (this.RAM[this.address].charAt(0)) {
                    case '0':
                        this.instruction0(this.RAM[this.address].charAt(1)-48, this.RAM[this.address].charAt(2)-48);
                        break;
                    case '2':
                        this.instruction2(this.RAM[this.address].charAt(1)-48, this.RAM[this.address].charAt(2)-48);
                        break;
                    case '3':
                        this.instruction3(this.RAM[this.address].charAt(1)-48, this.RAM[this.address].charAt(2)-48);
                        break;
                    case '4':
                        this.instruction4(this.RAM[this.address].charAt(1)-48, this.RAM[this.address].charAt(2)-48);
                        break;
                    case '5':
                        this.instruction5(this.RAM[this.address].charAt(1)-48, this.RAM[this.address].charAt(2)-48);
                        break;
                    case '6':
                        this.instruction6(this.RAM[this.address].charAt(1)-48, this.RAM[this.address].charAt(2)-48);
                        break;
                    case '7':
                        this.instruction7(this.RAM[this.address].charAt(1)-48, this.RAM[this.address].charAt(2)-48);
                        break;
                    case '8':
                        this.instruction8(this.RAM[this.address].charAt(1)-48, this.RAM[this.address].charAt(2)-48);
                        break;
                    case '9':
                        this.instruction9(this.RAM[this.address].charAt(1)-48, this.RAM[this.address].charAt(2)-48);
                        break;
                }
                
                this.address++;
            }
            
            return countInstructions;
        }

        public void instruction0(int d, int s){
            if (this.registers[s] != 0) {
                this.address = this.registers[d]-1;
            }
        }
        
        public void instruction2(int d, int n){
            this.registers[d] = n;
            this.registers[d] %= 1000;
        }
        
        public void instruction3(int d, int n){
            this.registers[d] += n;
            this.registers[d] %= 1000;
        }
        
        public void instruction4(int d, int n){
            this.registers[d] *= n;
            this.registers[d] %= 1000;
        }
        
        public void instruction5(int d, int s){
            this.registers[d] = this.registers[s];
            this.registers[d] %= 1000;
        }
        
        public void instruction6(int d, int s){
            this.registers[d] += this.registers[s];
            this.registers[d] %= 1000;
        }
        
        public void instruction7(int d, int s){
            this.registers[d] *= this.registers[s];
            this.registers[d] %= 1000;
        }
        
        public void instruction8(int d, int a){
            this.registers[d] = Integer.parseInt(this.RAM[this.registers[a]]);
            this.registers[d] %= 1000;
        }
        
        public void instruction9(int s, int a){
            this.RAM[this.registers[a]] = Integer.toString(this.registers[s]);
        }

    }

    public static void main(String[] XergioAleX) throws FileNotFoundException, IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token;
        String inputLine;

        int cases = Integer.parseInt(reader.readLine());
        reader.readLine();
        
        for (int i = 0; i < cases; i++) {
            Computer pc = new Computer();
            
            int index = 0;
            while ((inputLine = reader.readLine()) != null && !inputLine.equals("")) {
                pc.RAM[index] = inputLine;
                index++;
            }
            
            System.out.println(pc.runProgram());
            
            if (i != cases-1) {
                System.out.println("");
            }
        }
        
    }
}
