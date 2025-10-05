/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package thuc_hanh;

import java.util.*;
import java.io.*;
import java.net.*;

public class a8zu9EfGk_TCP_Byte_Stream {
    
    public static boolean isPrime(int n){
        if( n <= 1 ) return false;
        if( n <= 3 ) return true;
        if( n % 2 == 0 || n % 3 == 0 ) return false;
        for(int i = 5; i * i <= n; i += 6){
            if( n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }

    public static void main(String args[]) {
        String host = "203.162.10.109";
        int port = 2206;
        String studentCode = "B22DCCN218";
        String code = "8zu9EfGk";
        String request = studentCode + ";" + code;
        
        try (Socket socket = new Socket(host, port)){
            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();
            
            out.write(request.getBytes());
            out.flush();
            
            byte[] buffer = new byte[1024];
            int bytesRead = in.read(buffer);
            String arrStr = new String(buffer, 0, bytesRead);
            String[] numbers = arrStr.split(",");
            int sumPrime = 0;
            
            for(String num : numbers){
                int n = Integer.parseInt(num.trim());
                if(isPrime(n)){
                    sumPrime += n;
                }
            }
            
            String sumStr = String.valueOf(sumPrime);
            out.write(sumStr.getBytes());
            out.flush();
            System.out.println("Sent sum of primes: " + sumStr);
        } catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Colletion closed");
    }
}
