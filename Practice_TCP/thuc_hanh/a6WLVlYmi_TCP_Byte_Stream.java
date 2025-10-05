/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package thuc_hanh;

import java.util.*;
import java.io.*;
import java.net.*;

public class a6WLVlYmi_TCP_Byte_Stream {

    public static void main(String args[]) throws IOException {
        String host = "203.162.10.109";
        int port = 2206;
        String studentCode = "B22DCCN218";
        String code = "6WLVlYmi";
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
            int[] intArr = new int[numbers.length];
            for (int i = 0; i < numbers.length; i++){
                intArr[i] = Integer.parseInt(numbers[i].trim());
            }
            
            double sum = 0;
            for (int num : intArr){
                sum += num;
            }
            double average = sum / intArr.length;
            double target = 2 * average;
            
            int minDiff = Integer.MAX_VALUE;
            int num1 = 0, num2 = 0;
            for(int i = 0; i < intArr.length; i++){
                for(int j = i + 1; j < intArr.length; j++){
                    int currentSum = intArr[i] + intArr[j];
                    int diff = Math.abs(currentSum - (int) target);
                    if( diff < minDiff ){
                        minDiff = diff;
                        num1 = intArr[i];
                        num2 = intArr[j];
                    }
                }
            }
            
            if( num1 > num2 ){
                int temp = num1;
                num1 = num2;
                num2 = temp;
            }
            String result = num1 + "," + num2;
            
            out.write(result.getBytes());
            out.flush();
            System.out.println("Sent result: " + result);
            
        } catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Colletion closed");
    }
}
