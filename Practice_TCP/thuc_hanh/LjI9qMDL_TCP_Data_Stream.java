/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package thuc_hanh;

import java.io.*;
import java.net.*;
import java.util.*;

public class LjI9qMDL_TCP_Data_Stream {

    public static void main(String args[]) throws IOException {
        String host = "203.162.10.109";
        int port = 2207;
        String studentCode = "B22DCCN218";
        String code = "LjI9qMDL";
        String request = studentCode + ";" + code;
        
        try (Socket socket = new Socket(host, port)){
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            
            out.writeUTF(request);
            
            int a = in.readInt();
            int b = in.readInt();
            System.out.println("Received a = " + a + ", b = " + b);
            
            int sum = a + b;
            int mul = a * b;
            out.writeInt(sum);
            out.writeInt(mul);
            System.out.println("Sent sum = " + sum + ", mul = " + mul);
            
        } catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("Connection closed.");
    }
}
