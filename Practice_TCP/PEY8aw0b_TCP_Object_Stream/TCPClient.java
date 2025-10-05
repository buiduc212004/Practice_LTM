/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package PEY8aw0b_TCP_Object_Stream;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPClient {

    public static void main(String[] args) throws IOException {
        String host = "203.162.10.109";
        int port = 2209;
        String studentCode = "B22DCCN218";
        String code = "PEY8aw0b";
        String request = studentCode + ";" + code;

        try (Socket socket = new Socket(host, port)) {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            out.writeObject(request);
            out.flush();

            Product product = (Product) in.readObject();

            int priceIntPart = (int) product.price;
            int discount = 0;
            String sum = String.format("%d", priceIntPart);
            for (int i = 0; i < sum.length(); i++) {
                discount += sum.charAt(i) - '0';
            }
            product.discount = discount;

            out.writeObject(product);
            out.flush();
            System.out.println("Sent to server: " + product.discount);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }   catch (ClassNotFoundException ex) {
                Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Connection closed");
    }
}
