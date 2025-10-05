/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Practice_UDP;

import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.util.*;

public class ILT1tujZ_UDP_Data_Type {

    public static void main(String args[]) throws SocketException, UnknownHostException, IOException {
        String host = "203.162.10.109";
        int port = 2207;
        String studentCode = "B22DCCN218";
        String code = "ILT1tujZ";
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
            String[] parts = respone.split(";", 3);
            String requestID = parts[0];
            BigInteger a = new BigInteger(parts[1]);
            BigInteger b = new BigInteger(parts[2]);
            
            BigInteger sum = a.add(b);
            BigInteger difference = a.subtract(b);
            
            String result = requestID + ";" + sum.toString() + "," + difference.toString();
            
            sendData = result.getBytes();
            sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
            socket.send(sendPacket);
            
            System.out.println("Sent to server: " + result);
        }
    }
}
