/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Practice_UDP.URvq5cTe_UDP_Object;

import java.io.*;
import java.util.*;
import java.net.*;

public class URvq5cTe {

    public static String normalizeName(String name) {
        String[] words = name.trim().split("\\s+");
        if (words.length == 0) return "";
        if (words.length == 1) return words[0].toUpperCase();
        StringBuilder sb = new StringBuilder();
        sb.append(words[words.length - 1].toUpperCase()).append(", ");
        for (int i = 0; i < words.length - 1; i++) {
            String word = words[i];
            if (word.length() > 0) {
                sb.append(word.substring(0, 1).toUpperCase())
                  .append(word.substring(1).toLowerCase());
                if (i < words.length - 2) {
                    sb.append(" ");
                }
            }
        }
        return sb.toString();
    }

    public static String normalizeDate(String dayOfBirth) {
        String[] parts = dayOfBirth.trim().split("[/-]");
        if (parts.length != 3) throw new IllegalArgumentException("Invalid date format: " + dayOfBirth);
        return parts[1] + "/" + parts[0] + "/" + parts[2];
    }

    public static String normalizeUserName(String name) {
        String[] words = name.trim().split("\\s+");
        if (words.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length - 1; i++) {
            if (words[i].length() > 0) {
                sb.append(words[i].substring(0, 1).toLowerCase());
            }
        }
        if (words[words.length - 1].length() > 0) {
            sb.append(words[words.length - 1].toLowerCase());
        }
        return sb.toString();
    }

    public static void main(String[] args) throws SocketException, UnknownHostException, IOException, ClassNotFoundException {
        String host = "203.162.10.109";
        int port = 2209;
        String studentCode = "B22DCCN218";
        String code = "URvq5cTe";
        String request = ";" + studentCode + ";" + code;

        try (DatagramSocket socket = new DatagramSocket()) {
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
            Customer customer = (Customer) ois.readObject();
            
            String name = customer.getName();
            customer.setName(normalizeName(customer.getName()));
            customer.setDayOfBirth(normalizeDate(customer.getDayOfBirth()));
            customer.setUserName(normalizeUserName(name));

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] requestIDBytesToSend = requestID.getBytes();
            baos.write(requestIDBytesToSend, 0, Math.min(requestIDBytesToSend.length, 8));
            while (baos.size() < 8) {
                baos.write(' ');
            }
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(customer);
            oos.flush();

            sendData = baos.toByteArray();
            sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
            socket.send(sendPacket);

            System.out.println(requestID + ";" + customer.getName() + " " + customer.getDayOfBirth() + " " + customer.getUserName());

        }
    }
}