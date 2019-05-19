//package taller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class C {
    public static void main(String[] args) throws IOException {
        
        BufferedReader input = new BufferedReader(new FileReader("C8.in"));
        //BufferedReader input = new BufferedReader(new InputStreamReader(System.in));                
        String in = input.readLine();
        while(in != null){
            int n = Integer.valueOf(in);
            if(n==0) break;
            TreeSet<Integer> tree= new TreeSet<Integer>();
            StringTokenizer token = new StringTokenizer(input.readLine());
            boolean space = false;    
            boolean repeat = false;
            for (int i = 1; i <= n; i++) {
                int aux = Integer.valueOf(token.nextToken());
                if(tree.contains(aux)){
                    repeat = true;
                    if(!space){
                        System.out.print(i);
                        space=true;
                    }
                    else System.out.print(" "+i);
                }
                else{                    
                    tree.add(aux);
                }                
            }
            if(!repeat) System.out.print(0);
            System.out.println("");
            in = input.readLine();
        }        
    }
}
