/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package thuc_hanh;

import java.io.*;
import java.net.*;
import java.util.*;

public class vQuUXQGS_TCP_Byte_Stream {

    public static void main(String args[]) {
        String host = "203.162.10.109";
        int port = 2206;
        String studentCode = "B22DCCN218";
        String code = "vQuUXQGS";
        String request = studentCode + ";" + code;
        
        try (Socket socket = new Socket(host, port)){
            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();
            
            out.write(request.getBytes());
            out.flush();
            
            byte[] buffer = new byte[1024];
            int bytesRead = in.read(buffer);
            String arrStr = new String(buffer, 0, bytesRead).trim();
            String[] numbers = arrStr.split(",");
            int[] intArr = new int[numbers.length];
            for(int i = 0; i < numbers.length; i++){
                intArr[i] = Integer.parseInt(numbers[i].trim());
            }
            int minDiff = Integer.MAX_VALUE;
            int position = 0;
            int leftSum = 0, rightSum = 0;
            
            for(int i = 0; i < intArr.length; i++){
                int totalSum = 0;
                for(int num : intArr) totalSum += num;
                leftSum = 0;
                for(int j = 0; j < i; j++){
                    leftSum += intArr[j];
                }
                rightSum = totalSum - leftSum - intArr[i];
                int diff = Math.abs(rightSum - leftSum);
                if( diff < minDiff ){
                    minDiff = diff;
                    position = i + 1;
                }
            }
            leftSum = 0;
            for(int j = 0; j < position - 1; j++){
                leftSum += intArr[j];
            }
            rightSum = 0;
            for(int j = position; j < intArr.length; j++){
                rightSum += intArr[j];
            }
            String result = position - 1 + "," + leftSum + "," + rightSum + "," + minDiff;
            
            out.write(result.getBytes());
            out.flush();
            System.out.println("Sent result to server: " + result);
            
        } catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Collection closed");
    }
}
