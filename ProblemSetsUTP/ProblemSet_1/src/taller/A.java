//package taller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class A {

    public static void main(String[] args) throws IOException {        
        BufferedReader input = new BufferedReader(new FileReader("b.in"));
        //BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String in = input.readLine();
        while(in!=null){
            int n= Integer.valueOf(in);   
            if(n==0) break;
            BigInteger valor_total=BigInteger.ZERO;
            
            for (int i = 0; i < n; i++) {
                StringTokenizer token = new StringTokenizer(input.readLine());
                int valor_unitario = Integer.valueOf(token.nextToken());
                int cantidad = Integer.valueOf(token.nextToken());
                int aux = valor_unitario*cantidad;                
                valor_total= valor_total.add(new BigInteger(aux+""))  ;
                
            }
            System.out.println(valor_total);  
            in = input.readLine();
        }
    }
}
