package taller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Bwriter {
     public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("salidaB.txt"));  
        BufferedReader input = new BufferedReader(new FileReader("B1.in"));
        //BufferedReader input = new BufferedReader(new InputStreamReader(System.in));                
        String in = input.readLine();
        while(in != null){
            StringTokenizer token = new StringTokenizer(in);
            int xo = Integer.valueOf(token.nextToken());
            int a = Integer.valueOf(token.nextToken());
            int b = Integer.valueOf(token.nextToken());
            int n = Integer.valueOf(token.nextToken());
            if(xo==0 && a==0 && b==0 && n==0)
                break;
            writer.append(generarSecuencia(xo,a,b,n)+"");
            writer.flush();                        
            writer.newLine();
            //System.out.println("");
            //System.out.println(generarSecuencia(xo,a,b,n));
            in = input.readLine();
        }
    }

    private static int generarSecuencia(long xi, int a, int b, int n) {
        TreeSet<Long> tree= new TreeSet<Long>();
        tree.add(xi);
        int i = 1;
        //System.out.print(xi+" ");
        while(true){
            xi=(a*xi+b) % n;
            //System.out.print(xi+" ");
            if(tree.contains(xi))                
                return i;
            else{               
               tree.add(xi);
               i++;
            }
        }        
    }
}
