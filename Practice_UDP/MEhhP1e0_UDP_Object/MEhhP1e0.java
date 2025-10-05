/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Practice_UDP.MEhhP1e0_UDP_Object;

import java.util.*;
import java.io.*;
import java.net.*;

public class MEhhP1e0 {
    
    public static String normalizeName(String name){
        StringBuilder sb = new StringBuilder();
        String[] words = name.trim().split("\\s+");
        for(String word:words){
            sb.append(word.substring(0, 1).toUpperCase()).append(word.substring(1).toLowerCase());
            if(word != words[words.length - 1]) sb.append(" ");
        }
        return sb.toString();
    }
    
    public static String normailizeMail(String name){
        StringBuilder sb = new StringBuilder();
        String[] words = name.trim().split("\\s+");
        sb.append(words[words.length - 1].toLowerCase());
        for(int i = 0; i < words.length - 1; i++){
            sb.append(words[i].substring(0, 1).toLowerCase());
        }
        sb.append("@ptit.edu.vn");
        return sb.toString();
    }

    public static void main(String args[]) throws SocketException, UnknownHostException, IOException, ClassNotFoundException {
        String host = "203.162.10.109";
        int port = 2209;
        String studentCode = "B22DCCN218";
        String code = "MEhhP1e0";
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
            Student student = (Student) ois.readObject();
            
            String name = student.getName();
            student.setName(normalizeName(student.getName()));
            student.setEmail(normailizeMail(name));
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] requestIDBytesToSend = requestID.getBytes();
            baos.write(requestIDBytesToSend, 0, Math.min(requestIDBytesToSend.length, 8));
            while(baos.size() < 8){
                baos.write(' ');
            }
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(student);
            oos.flush();
            
            sendData = baos.toByteArray();
            sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
            socket.send(sendPacket);
            
            System.out.println(requestID + ";" + student.getName() + " " + student.getEmail());
        }
    }
}
