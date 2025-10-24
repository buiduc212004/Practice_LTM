/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Practice_RMI.a5IkLmFgL_RMI_Data;

import java.util.*;
import java.io.*;
import java.net.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class a5IkLmFgL_RMI_Data {
    
    public static String coinChange(int amount){
        int[] arrs = {10, 5, 2, 1};
        List<Integer> coinsUsed = new ArrayList<>();
        int remaining = amount;
        int coinCount = 0;
        
        for(int a : arrs){
            while(remaining >= a){
                coinsUsed.add(a);
                remaining -= a;
                coinCount++;
            }
        }
        
        if(remaining != 0){
            return "-1";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(coinCount).append("; ");
        for(int i = 0; i < coinsUsed.size(); i++){
            sb.append(coinsUsed.get(i));
            if(i < coinsUsed.size() - 1){
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public static void main(String args[]) throws RemoteException, NotBoundException {
        String studentCode = "B22DCCN218";
        String qCode = "5IkLmFgL";
        
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        DataService ds = (DataService) rg.lookup("RMIDataService");
        
        Object data = ds.requestData(studentCode, qCode);
        int amount = ((Integer) data).intValue();
        
        String result = coinChange(amount);
        System.out.println(result);
        
        ds.submitData(studentCode, qCode, result);
    }
}
