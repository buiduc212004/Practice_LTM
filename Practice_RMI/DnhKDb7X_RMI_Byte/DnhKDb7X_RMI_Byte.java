/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Practice_RMI.DnhKDb7X_RMI_Byte;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class DnhKDb7X_RMI_Byte {
    
    public static String bytesToHex(byte[] data){
        StringBuilder sb = new StringBuilder();
        for(byte b:data){
            String hex = Integer.toHexString(b & 0xFF);
            if(hex.length() == 1){
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    public static void main(String args[]) throws RemoteException, NotBoundException {
        String studentCode = "B22DCCN218";
        String qCode = "DnhKDb7X";
        
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        ByteService bs = (ByteService) rg.lookup("RMIByteService");
        
        byte[] data = bs.requestData(studentCode, qCode);
        
        String hexString = bytesToHex(data);
        System.out.println(hexString);
        
        byte[] hexBytes = hexString.getBytes();
        bs.submitData(studentCode, qCode, hexBytes);
    }
}
