/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Practice_UDP.a9vdik4SV_UDP_Object;

import java.io.*;
import java.net.*;
import java.util.*;

public class a9vdik4SV {
    
    public static String normalizeTitle(String title){
        StringBuilder sb = new StringBuilder();
        String[] words = title.trim().split("\\s+");
        for(int i = 0; i < words.length; i++){
            sb.append(words[i].substring(0, 1).toUpperCase()).append(words[i].substring(1).toLowerCase());
            if(words.length - 1 != i) sb.append(" ");
        }
        return sb.toString();
    }
    public static String normalizeAuthor(String author){
        StringBuilder sb = new StringBuilder();
        String[] words = author.trim().split("\\s+");
        sb.append(words[0].toUpperCase()).append(", ");
        for(int i = 1; i < words.length; i++){
            sb.append(words[i].substring(0, 1).toUpperCase()).append(words[i].substring(1).toLowerCase());
            if(words.length - 1 != i) sb.append(" ");
        }
        return sb.toString();
    }
    public static String normalizeISBN(String isbn){
        StringBuilder sb = new StringBuilder();
        sb.append(isbn.substring(0, 3)).append("-")
                .append(isbn.substring(3, 4)).append("-")
                .append(isbn.substring(4, 6)).append("-")
                .append(isbn.substring(6, 12)).append("-")
                .append(isbn.substring(12));
        return sb.toString();
    }
    public static String normalizePublishDate(String publishDate){
        StringBuilder sb = new StringBuilder();
        String[] words = publishDate.trim().split("-");
        sb.append(words[1]).append("/").append(words[0]);
        return sb.toString();
    }

    public static void main(String args[]) throws SocketException, UnknownHostException, IOException, ClassNotFoundException {
        String host = "203.162.10.109";
        int port = 2209;
        String studentCode = "B22DCCN218";
        String code = "9vdik4SV";
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
            Book book = (Book) ois.readObject();
            
            book.setTitle(normalizeTitle(book.getTitle()));
            book.setAuthor(normalizeAuthor(book.getAuthor()));
            book.setIsbn(normalizeISBN(book.getIsbn()));
            book.setPublishDate(normalizePublishDate(book.getPublishDate()));
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] requestIDByteToSend = requestID.getBytes();
            baos.write(requestIDByteToSend, 0, Math.min(requestIDByteToSend.length, 8));
            while(baos.size() < 8){
                baos.write(' ');
            }
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(book);
            oos.flush();
            
            sendData = baos.toByteArray();
            sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
            socket.send(sendPacket);
            
            System.out.println(requestID + ";" + book.getTitle() + " " + book.getAuthor() + " " + book.getIsbn() + " " + book.getPublishDate());
        }
    }
}
