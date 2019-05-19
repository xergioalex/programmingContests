//package taller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader("b.in"));
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
            System.out.println(generarSecuencia(xo,a,b,n));
            in = input.readLine();
        }
    }

    private static int generarSecuencia(int xi, int a, int b, int n) {
        TreeSet<Integer> tree= new TreeSet<Integer>();
        tree.add(xi);
        int i = 1;
        while(true){
            xi=(a*xi+b) % n;
            if(tree.contains(xi))
                return i;
            else{
               tree.add(xi);
               i++;
            }
        }        
    }
}
