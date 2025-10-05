/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package thuc_hanh;

import java.util.*;
import java.io.*;
import java.net.*;

public class XQzgQlEL_TCP_Character_Stream {

    public static void main(String args[]) throws IOException {
        Socket socket = new Socket("203.162.10.109", 2208);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        String id = "B22DCCN218";
        String code = "XQzgQlEL";
        
        String message = id + ";" + code;
        out.write(message);
        out.newLine();
        out.flush();
        
        String domains = in.readLine();
        System.out.println(domains);
        
        String[] arr = domains.split(",");
        List<String> eduDomains = new ArrayList<>();
        for(String d : arr){
            if(d.trim().endsWith(".edu")){
                eduDomains.add(d.trim());
            }
        }
        String result = String.join(", ", eduDomains);
        System.out.println("Tên miền .edu: " + result);
        out.write(result);
        out.newLine();
        out.flush();
        
        in.close();
        out.close();
        socket.close();
        
    }
}
