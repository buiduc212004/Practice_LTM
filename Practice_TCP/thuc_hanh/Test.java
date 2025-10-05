/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package thuc_hanh;

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class Product implements Serializable{
    private static final long serialVersionUID = 20231107;
    int id;
    String name;
    double price;
    int discount;

    public Product(int id, String name, double price, int discount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
    }    
    
}

public class Test {

    public static void main(String args[]) throws IOException {
        String host = "203.162.10.109";
        int port = 2209;
        String studentCode = "B22DCCN218";
        String code = "PEY8aw0b";
        String request = studentCode + ";" + code;
        
        try (Socket socket = new Socket(host, port)){
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            
            out.writeObject(request);
            out.flush();
            
            Product product = (Product) in.readObject();
            
            int discount = 0;
            
            String number = String.format("%d", product.price);
            for(int i = 0; i < number.length(); i++){
                discount += number.charAt(i) - '0';
            }
            
            product.discount = discount;
            
            out.writeObject(product);
            out.flush();
            
            System.out.println(product.discount);
            
        } catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Collection closed");
    }
}
