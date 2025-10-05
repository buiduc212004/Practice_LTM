/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Practice_UDP;

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class zSaEc0oN_UDP_String {

    public static void main(String args[]) throws SocketException, UnsupportedEncodingException, UnknownHostException, IOException {
        String host = "203.162.10.109";
        int port = 2208;
        String studentCode = "B22DCCN218";
        String code = "zSaEc0oN";
        String request = ";" + studentCode + ";" + code;
        
        try(DatagramSocket socket = new DatagramSocket()){
            byte[] sendData = request.getBytes("UTF-8");
            InetAddress address = InetAddress.getByName(host);
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
            socket.send(sendPacket);
            
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            
            String response = new String(receivePacket.getData(), 0, receivePacket.getLength(), "UTF-8");
            String[] parts = response.split(";", 2);
            if(parts.length != 2){
                throw new IOException("Invalid server response format: " + response);
            }
            String requestID = parts[0];
            String data = parts[1];
            
            String[] words = data.trim().split("\\s+");
            Arrays.sort(words, Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));
            String sortedWords = String.join(",", words);
            
            String result = requestID + ";" + sortedWords;
            sendData = result.getBytes("UTF-8");
            sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
            socket.send(sendPacket);
            
            System.out.println("Sent to server: " + result);
            
        }
    }
}
