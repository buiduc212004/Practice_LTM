/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Practice_UDP;

import java.util.*;
import java.io.*;
import java.net.*;

public class a1BhwGekr_UDP_Data_Type {
    
    public static String nomalizeString(String s){
        StringBuilder sb = new StringBuilder();
        String[] words = s.trim().split(" ");
        for(String word:words){
            sb.append(word.substring(0, 1).toUpperCase()).append(word.substring(1).toLowerCase()).append(" ");
        }
        return sb.toString();
    }

    public static void main(String args[]) throws SocketException, UnknownHostException, IOException {
        String host = "203.162.10.109";
        int port = 2207;
        String studentCode = "B22DCCN218";
        String code = "1BhwGekr";
        String request = ";" + studentCode + ";" + code;
        
        try(DatagramSocket socket = new DatagramSocket()){
            byte[] sendData = request.getBytes();
            InetAddress address = InetAddress.getByName(host);
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
            socket.send(sendPacket);
            
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            
            String respone = new String(receivePacket.getData(), 0, receivePacket.getLength());
            String[] parts = respone.trim().split(";");
            if(parts.length != 2){
                throw new IOException("Invalid server respone: " + respone);
            }
            String requestID = parts[0];
            String num = parts[1];
            
            int sumDigits = 0;
            for(int i = 0; i < num.length(); i++){
                sumDigits += num.charAt(i) - '0';
            }
            
            String result = requestID + ";" + sumDigits;
            
            sendData = result.getBytes();
            sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
            socket.send(sendPacket);
            
            System.out.println(result);
        }
    }
}
