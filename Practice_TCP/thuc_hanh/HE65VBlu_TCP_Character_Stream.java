/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package thuc_hanh;

import java.util.*;
import java.io.*;
import java.net.*;

public class HE65VBlu_TCP_Character_Stream {

    public static void main(String args[]) throws IOException {
        String host = "203.162.10.109";
        int port = 2208;
        String studentCode = "B22DCCN218";
        String code = "HE65VBlu";
        String request = studentCode + ";" + code;
        
        try (Socket socket = new Socket(host, port)){
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            out.write(request);
            out.newLine();
            out.flush();
            
            String serverResponse = in.readLine();
            String[] words = serverResponse.split("\\s+");
            
            StringBuilder encodedResult = new StringBuilder();
            for(String word : words){
                String reversedWord = new StringBuilder(word).reverse().toString();
                StringBuilder rleWord = new StringBuilder();
                for (int i = 0; i < reversedWord.length(); i++){
                    int count = 1;
                    while( i + 1 < reversedWord.length() && reversedWord.charAt(i) == reversedWord.charAt(i + 1)){
                        count++;
                        i++;
                    }
                    rleWord.append(reversedWord.charAt(i));
                    if( count > 1 ) rleWord.append(count);
                }
                encodedResult.append(rleWord.toString()).append(" ");
            }
            String processedString = encodedResult.toString().trim();
            
            out.write(processedString);
            out.newLine();
            out.flush();
            System.out.println("Sent encoded string: " + processedString);
        
        } catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("Connectino closed");
    
    }
}
