/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Practice_TCP.thuc_hanh.KzEnWmMO_TCP_Object_Stream;

import java.io.*;
import java.util.*;
import java.net.*;

public class KzEnWmMO {
    
    public static String normalizeName(String name){
        StringBuilder sb = new StringBuilder();
        String[] words = name.trim().split("\\s+");
        sb.append(words[words.length-1]).append(" ");
        for(int i = 1; i < words.length - 1; i++){
            sb.append(words[i]).append(" ");
        }
        sb.append(words[0]);
        return sb.toString();
    }
    
    public static int normalizeQuantity(int quantity){
        StringBuilder sb = new StringBuilder(quantity + "");
        return Integer.parseInt(sb.reverse().toString());
    }

    public static void main(String args[]) throws IOException, ClassNotFoundException {
        String host = "203.162.10.109";
        int port = 2209;
        String studentCode = "B22DCCN218";
        String qCode = "KzEnWmMO";
        String request = studentCode + ";" + qCode;
        
        try(Socket socket = new Socket(host, port)){
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            
            oos.writeObject(request);
            oos.flush();
            
            Laptop laptop = (Laptop) ois.readObject();
            
            laptop.setName(normalizeName(laptop.getName()));
            laptop.setQuantity(normalizeQuantity(laptop.getQuantity()));
            
            oos.writeObject(laptop);
            oos.flush();
            
            System.out.println(laptop.getName() + " " + laptop.getQuantity());
        }
    }
}
