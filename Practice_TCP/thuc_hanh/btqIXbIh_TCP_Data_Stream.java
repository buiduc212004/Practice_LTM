/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package thuc_hanh;

import java.util.*;
import java.io.*;
import java.net.*;

public class btqIXbIh_TCP_Data_Stream {

    public static void main(String args[]) throws IOException {
        String host = "203.162.10.109";
        int port = 2207;
        String studentCode = "B22DCCN218";
        String code = "btqIXbIh";
        String request = studentCode + ";" + code;
        
        try (Socket socket = new Socket(host, port)){
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            
            out.writeUTF(request);
            
            int k = in.readInt();
            String arrStr = in.readUTF();
            String[] numbers = arrStr.split(",");
            int[] intArray = new int[numbers.length];
            for(int i = 0; i < numbers.length; i++){
                intArray[i] = Integer.parseInt(numbers[i].trim());
            }
            
            StringBuilder result = new StringBuilder();
            for(int i = 0; i < intArray.length; i += k){
                int end = Math.min(i + k, intArray.length);
                for(int j = end - 1; j >= i; j--){
                    result.append(intArray[j]);
                    if(j != i || end < intArray.length) result.append(",");
                }
            }
            String processedArray = result.toString();
            
            out.writeUTF(processedArray);
            System.out.println("Sent processed array: " + processedArray);
        } catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("Collection closed");
        
    }
}
