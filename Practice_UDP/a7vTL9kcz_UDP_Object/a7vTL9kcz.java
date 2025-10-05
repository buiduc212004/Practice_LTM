/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Practice_UDP.a7vTL9kcz_UDP_Object;

import java.io.*;
import java.util.*;
import java.net.*;

public class a7vTL9kcz {
    
    public static String normalizeName(String name){
        StringBuilder sb = new StringBuilder();
        String[] words = name.trim().split("\\s+");
        for(String word:words){
            sb.append(word.substring(0, 1).toUpperCase()).append(word.substring(1).toLowerCase());
            if(word != words[words.length - 1]) sb.append(" ");
        }
        return sb.toString();
    }
    
    public static double increaseSalary(double salary, String hireDate){
        String[] words = hireDate.trim().split("-");
        double x = 0;
        for(int i = 0; i < words[0].length(); i++){
            x += words[0].charAt(i) - '0';
        }
        double newSalary = salary * (1 + x/100);
        return newSalary;
    }
    
    public static String normalizeHireDate(String hireDate){
        StringBuilder sb = new StringBuilder();
        String[] words = hireDate.trim().split("-");
        for(int i = words.length - 1; i >= 0; i--){
            sb.append(words[i]);
            if(i != 0) sb.append("/");
        }
        return sb.toString();
    }

    public static void main(String args[]) throws SocketException, UnknownHostException, IOException, ClassNotFoundException {
        String host = "203.162.10.109";
        int port = 2209;
        String studentCode = "B22DCCN218";
        String code = "7vTL9kcz";
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
            Employee employee = (Employee) ois.readObject();
            
            String hireDate = employee.getHireDate();
            employee.setName(normalizeName(employee.getName()));
            employee.setSalary(increaseSalary(employee.getSalary(), hireDate));
            employee.setHireDate(normalizeHireDate(employee.getHireDate()));
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] requestIDBytesToSend = requestID.getBytes();
            baos.write(requestIDBytesToSend, 0, Math.min(requestIDBytesToSend.length, 8));
            while(baos.size() < 8){
                baos.write(' ');
            }
            
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(employee);
            oos.flush();
            
            sendData = baos.toByteArray();
            sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
            socket.send(sendPacket);
            
            System.out.println(requestID + ";" + employee.getName() + " " + employee.getSalary() + " " + employee.getHireDate());
        }
    }
}
