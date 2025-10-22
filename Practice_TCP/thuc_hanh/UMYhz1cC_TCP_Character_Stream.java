/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Practice_TCP.thuc_hanh;

import java.util.*;
import java.io.*;
import java.net.*;

public class UMYhz1cC_TCP_Character_Stream {

    public static void main(String args[]) throws IOException {
        String host = "203.162.10.109";
        int port = 2208;
        String studentCode = "B22DCCN218";
        String code = "UMYhz1cC";
        String request = studentCode + ";" + code;
        
        try(Socket socket = new Socket(host, port)){
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            bw.write(request);
            bw.newLine();
            bw.flush();
            
            String serverRespone = br.readLine();
            List<String> eduList = new ArrayList<>();
            String[] strArr = serverRespone.trim().split(",");
            for(int i = 0; i < strArr.length; i++){
                if(strArr[i].contains(".edu")){
                    eduList.add(strArr[i].trim());
                }
            }
            
            String result = String.join(", ", eduList);
            bw.write(result);
            bw.newLine();
            bw.flush();
            System.out.println(result);
        }
    }
}
