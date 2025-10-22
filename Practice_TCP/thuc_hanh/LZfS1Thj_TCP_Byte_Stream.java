/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Practice_TCP.thuc_hanh;

import java.io.*;
import java.util.*;
import java.net.*;

public class LZfS1Thj_TCP_Byte_Stream {

    public static void main(String args[]) throws IOException {
        String host = "203.162.10.109";
        int port = 2206;
        String studentCode = "B22DCCN218";
        String qCode = "LZfS1Thj";
        String request = studentCode + ";" + qCode;
        
        try(Socket socket = new Socket(host, port)){
            OutputStream os = socket.getOutputStream();
            InputStream is = socket.getInputStream(); 
            
            os.write(request.getBytes());
            os.flush();
            
            byte[] buffer = new byte[1024];
            int bytesRead = is.read(buffer);
            String serverRespone = new String(buffer, 0, bytesRead);
            String[] numbers = serverRespone.trim().split("\\|");
            int sum = 0;
            for(int i = 0; i < numbers.length; i++){
                sum += Integer.parseInt(numbers[i]);
            }
            
            String result = String.valueOf(sum);
            os.write(result.getBytes());
            os.flush();
            
            System.out.println(result);
        }
        
    }
}
