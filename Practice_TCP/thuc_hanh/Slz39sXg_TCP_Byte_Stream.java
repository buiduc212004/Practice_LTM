/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package thuc_hanh;

import java.util.*;
import java.io.*;
import java.net.*;


public class Slz39sXg_TCP_Byte_Stream {

    public static void main(String args[]) throws IOException {
        String host = "203.162.10.109";
        int port = 2206;
        String studentCode = "B22DCCN218";
        String code = "Slz39sXg";
        String request = studentCode + ";" + code;
        
        try (Socket socket = new Socket(host, port)){
            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();
            
            out.write(request.getBytes());
            out.flush();
            
            byte[] buffer = new byte[1024];
            int bytesRead = in.read(buffer);
            String serverResponse = new String(buffer, 0, bytesRead);
            String[] numbers = serverResponse.split("\\|");
            int sum = 0;
            for(String num : numbers){
                sum += Integer.parseInt(num.trim());
            }
            
            String sumStr = String.valueOf(sum);
            out.write(sumStr.getBytes());
            out.flush();
            
            System.out.println("Sum sent to server: " + sum);
            
        } catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("Connection closed.");
    }
}
