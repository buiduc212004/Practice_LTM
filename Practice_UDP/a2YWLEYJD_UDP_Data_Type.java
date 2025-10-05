/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Practice_UDP;

import java.util.*;
import java.io.*;
import java.net.*;

public class a2YWLEYJD_UDP_Data_Type {
    
    public static void main(String args[]) throws SocketException, UnknownHostException, IOException {
        String host = "203.162.10.109";
        int port = 2207;
        String studentCode = "B22DCCN218";
        String code = "2YWLEYJD";
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
            String[] arrString = parts[1].trim().split(",");
            int[] numbers = new int[arrString.length];
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            for(int i = 0; i < numbers.length; i++){
                numbers[i] = Integer.parseInt(arrString[i]);
                max = Math.max(max, numbers[i]);
                min = Math.min(numbers[i], min);
            }
            String result = requestID + ";" + max + "," + min;
            
            sendData = result.getBytes();
            sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
            socket.send(sendPacket);
            
            System.out.println("Sent to server: " + result);
        }
    }
}
