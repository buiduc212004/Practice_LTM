/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package PEY8aw0b_TCP_Object_Stream;

import java.io.Serializable;

public class Product implements Serializable{
    private static final long serialVersionUID = 20231107;
    public int id;
    public String name;
    public double price;
    public int discount;

    public Product(int id, String name, double price, int discount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
    }
    
}
