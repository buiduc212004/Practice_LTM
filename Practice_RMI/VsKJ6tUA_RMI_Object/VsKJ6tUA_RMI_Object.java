/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Practice_RMI.VsKJ6tUA_RMI_Object;

import java.io.*;
import java.util.*;
import java.net.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class VsKJ6tUA_RMI_Object {
    
    public static Product normalizeProduct(Product product){
        
        product.setCode(product.getCode().toUpperCase());
        product.setExportPrice(product.getImportPrice() * 1.2);
        
        return product;
    }

    public static void main(String args[]) throws RemoteException, NotBoundException {
        String studentCode = "B22DCCN218";
        String qCode = "VsKJ6tUA";
        
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        ObjectService os = (ObjectService) rg.lookup("RMIObjectService");
        
        Serializable data = os.requestObject(studentCode, qCode);
        Product product = (Product) data;
        
        Product normalizeProduct = normalizeProduct(product);
        
        os.submitObject(studentCode, qCode, normalizeProduct);
        System.out.println();
    }
}
