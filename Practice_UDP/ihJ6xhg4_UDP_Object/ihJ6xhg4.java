/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Practice_UDP.ihJ6xhg4_UDP_Object;

import java.io.*;
import java.net.*;
import java.util.*;

public class ihJ6xhg4 {
    
    public static String nomarlizeName(String name){
        StringBuilder sb = new StringBuilder();
        String[] words = name.trim().split(" ");
        sb.append(words[words.length - 1]).append(" ");
        for(int i = 1; i < words.length - 1; i++){
            sb.append(words[i]).append(" ");
        }
        sb.append(words[0]);
        return sb.toString();
    }
    
    public static int nomarlizeQuantity(int quantity){
        String number = quantity + "";
        StringBuilder sb = new StringBuilder(number);
        sb.reverse();
        return Integer.parseInt(sb.toString());
    }

    public static void main(String args[]) throws UnknownHostException, SocketException, IOException, ClassNotFoundException {
        String host = "203.162.10.109";
        int port = 2209;
        String studentCode = "B22DCCN218";
        String code = "ihJ6xhg4";
        String request = ";" + studentCode + ";" + code;
        
        try(DatagramSocket socket = new DatagramSocket()){
            byte[] sendData = request.getBytes();
            InetAddress address = InetAddress.getByName(host);
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
            
            byte[] receiveData = new byte[65535];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            
            ByteArrayInputStream bais = new ByteArrayInputStream(receivePacket.getData(), 0, receivePacket.getLength());
            byte[] requestIDBytes = new byte[8];
            bais.read(requestIDBytes, 0, 8);
            String requestId = new String(requestIDBytes, "").trim();
            
            ObjectInputStream ois = new ObjectInputStream(bais);
            Product product = (Product) ois.readObject();
            
            product.setName(nomarlizeName(product.getName()));
            product.setQuantity(nomarlizeQuantity(product.getQuantity()));
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos.write(requestId.getBytes());
            while(baos.size() < 8){
                baos.write(' ');
            }
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(product);
            oos.flush();
            
            sendData = baos.toByteArray();
            sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
            socket.send(sendPacket);
            
            System.out.println("Sent to server: " + requestId + "" + product.getName() + " " + product.getQuantity());
        }
        
    }
}
