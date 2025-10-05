/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package thuc_hanh;

import java.io.*;
import java.net.*;
import java.util.*;

public class Ez9OhrxR_TCP_Data_Stream {

    public static void main(String args[]) throws IOException {
        String host = "203.162.10.109";
        int port = 2207;
        String studentCode = "B22DCCN218";
        String code = "Ez9OhrxR";
        String request = studentCode + ";" + code;
        
        try (Socket socket = new Socket(host, port)){
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            
            out.writeUTF(request);
            
            String arrStr = in.readUTF();
            String[] numbers = arrStr.split(",");
            int[] intArray = new int[numbers.length];
            for(int i = 0; i < numbers.length; i++){
                intArray[i] = Integer.parseInt(numbers[i].trim());
            }
            
            int changeCount = 0;
            int totalVariation = 0;
            
            for(int i = 1; i < intArray.length; i++){
                int diff = intArray[i] - intArray[i - 1];
                totalVariation += Math.abs(diff);
                if( i > 1 ){
                    int prevDiff = intArray[i - 1] - intArray[i - 2];
                    if((diff > 0 && prevDiff < 0) || (diff < 0 && prevDiff > 0)){
                        changeCount += 1;
                    }
                }
            }
            
            out.writeInt(changeCount);
            out.writeInt(totalVariation);
            System.out.println("Sent to server: " + changeCount + " " + totalVariation);
            
        } catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Collection closed");
    }
}
