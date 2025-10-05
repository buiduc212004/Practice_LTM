/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Practice_UDP;

import java.io.*;
import java.net.*;
import java.util.*;

public class mZPukPQm_UDP_Data_Type {

    public static void main(String args[]) throws SocketException, UnknownHostException, IOException {
        String host = "203.162.10.109";
        int port = 2207;
        String studentCode = "B22DCCN218";
        String code = "mZPukPQm";
        String request = ";" + studentCode + ";" + code;
        
        try(DatagramSocket socket = new DatagramSocket()){
            byte[] sendData = request.getBytes();
            InetAddress address = InetAddress.getByName(host);
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
            socket.send(sendPacket);
            
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            
            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            String[] parts = response.trim().split(";");
            String requestId = parts[0];
            String[] arrString = parts[1].split(",");
            int[] numbers = new int[arrString.length];
            for(int i = 0; i < numbers.length; i++){
                numbers[i] = Integer.parseInt(arrString[i]);
            }
            Arrays.sort(numbers);
            
            String result = requestId + ";" + numbers[numbers.length - 2] + "," + numbers[1];
            
            sendData = result.getBytes();
            sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
            socket.send(sendPacket);
            
            System.out.println(result);
        }
    }
}
