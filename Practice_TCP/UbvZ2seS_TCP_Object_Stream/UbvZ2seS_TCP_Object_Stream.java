/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package UbvZ2seS_TCP_Object_Stream;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UbvZ2seS_TCP_Object_Stream {

    public static void main(String args[]) throws IOException{
        String host = "203.162.10.109";
        int port = 2209;
        String studentCode = "B22DCCN218";
        String code = "UbvZ2seS";
        String request = studentCode + ";" + code;
        
        try (Socket socket = new Socket(host, port)){
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            
            out.writeObject(request);
            out.flush();
            
            Address address = (Address) in.readObject();
            
            String[] words = address.addressLine.split("\\s+");
            StringBuilder normalizedAddress = new StringBuilder();
            for (String word : words) {
                if (!word.isEmpty()) {
                    word = word.replaceAll("[^a-zA-Z0-9\\s]", "");
                    if (!word.isEmpty()) {
                        normalizedAddress.append(word.substring(0, 1).toUpperCase())
                                .append(word.substring(1).toLowerCase())
                                .append(" ");
                    }
                }
            }
            address.addressLine = normalizedAddress.toString().trim();
            
            address.postalCode = address.postalCode.replaceAll("[^-0-9]", "");
            
            out.writeObject(address);
            out.flush();
            System.out.println("Sent to server: " + address.addressLine + ", " + address.postalCode);
        } catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UbvZ2seS_TCP_Object_Stream.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Collection closed");
    }
}
