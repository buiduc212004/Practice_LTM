/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package thuc_hanh;

import java.util.*;
import java.io.*;
import java.net.*;

public class mmNwfBPa_TCP_Character_Stream {

    public static void main(String args[]) throws IOException {
        String host = "203.162.10.109";
        int port = 2208;
        String studentCode = "B22DCCN218";
        String code = "mmNwfBPa";
        String request = studentCode + ";" + code;
        
        try (Socket socket = new Socket(host, port)){
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            out.write(request);
            out.newLine();
            out.flush();
            
            String serverResponse = in.readLine();
            String[] words = serverResponse.split("\\s+");
            java.util.Arrays.sort(words, (w1, w2) -> {
                if(w1.length() != w2.length()){
                    return w1.length() - w2.length();
                }
                return Arrays.asList(words).indexOf(w1) - Arrays.asList(words).indexOf(w2);
            });
            
            String sortedWords = String.join(", ", words);
            out.write(sortedWords);
            out.newLine();
            out.flush();
            System.out.println("Sent sorted words: " + sortedWords);
            
        } catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("Connection closed.");
    }
}
