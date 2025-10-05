/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package UbvZ2seS_TCP_Object_Stream;

import java.io.Serializable;

class Address implements Serializable{
    private static final long serialVersionUID = 20180801L;
    public int id;
    public String code;
    public String addressLine;
    public String city;
    public String postalCode;
    
    public Address(int id, String code, String addressLine, String city, String postalCode){
        this.id = id;
        this.code = code;
        this.addressLine = addressLine;
        this.city = city;
        this.postalCode = postalCode;
    }
}

