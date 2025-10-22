/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Practice_TCP.thuc_hanh;

import java.io.*;
import java.util.*;
import java.net.*;

public class M2Vfsdhb_TCP_Data_Stream {
    
    public static void main(String args[]) throws IOException {
        String host = "203.162.10.109";
        int port = 2207;
        String studentCode = "B22DCCN218";
        String code = "M2Vfsdhb";
        String request = studentCode + ";" + code;
        
        try(Socket socket = new Socket(host, port)){
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            
            dos.writeUTF(request);
            
            int a = dis.readInt();
            
            String result = Integer.toBinaryString(a) + ";" + Integer.toHexString(a).toUpperCase();
            
            dos.writeUTF(result);
            System.out.println(result);
        }
    }
}
