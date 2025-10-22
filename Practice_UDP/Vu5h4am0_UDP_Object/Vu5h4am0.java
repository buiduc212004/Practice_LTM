/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Practice_UDP.Vu5h4am0_UDP_Object;

import java.io.*;
import java.net.*;
import java.util.*;

public class Vu5h4am0 {
    
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

    public static void main(String args[]) throws SocketException, UnknownHostException, IOException, ClassNotFoundException {
        String host = "203.162.10.109";
        int port = 2209;
        String studentCode = "B22DCCN218";
        String code = "Vu5h4am0";
        String request = ";" + studentCode + ";" + code;
        
        try(DatagramSocket socket = new DatagramSocket()){
            byte[] sendData = request.getBytes();
            InetAddress address = InetAddress.getByName(host);
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
            socket.send(sendPacket);
            
            byte[] receiveData = new byte[65535];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            
            ByteArrayInputStream bais = new ByteArrayInputStream(receivePacket.getData(), 0, receivePacket.getLength());
            byte[] requestIDBytes = new byte[8];
            bais.read(requestIDBytes, 0, 8);
            String requestID = new String(requestIDBytes);
            
            ObjectInputStream ois = new ObjectInputStream(bais);
            Product product = (Product) ois.readObject();
            
//            product.setName(product.getName());
//            product.setQuantity(product.getQuantity());
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] requestIDBytesSend = requestID.getBytes();
            baos.write(requestIDBytesSend, 0, Math.min(requestIDBytesSend.length, 8));
            while(baos.size() < 8){
                baos.write(' ');
            }
            
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(product);
            oos.flush();
            
            sendData = baos.toByteArray();
            sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
            socket.send(sendPacket);
            
            System.out.println(product.getName() + " ; " + product.getQuantity());
        }
    }
}
