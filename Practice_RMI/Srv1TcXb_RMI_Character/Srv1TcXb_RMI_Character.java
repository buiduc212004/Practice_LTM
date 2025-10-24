/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Practice_RMI.Srv1TcXb_RMI_Character;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;

public class Srv1TcXb_RMI_Character {
    
    public static String countCharacter(String s){
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for(char c : s.toCharArray()){
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }
        StringBuilder sb = new StringBuilder();
        for(char c : s.toCharArray()){
            if(frequencyMap.containsKey(c)){
                sb.append(c).append(frequencyMap.get(c));
                frequencyMap.remove(c);
            }
        }
        return sb.toString();
    }

    public static void main(String args[]) throws RemoteException, NotBoundException {
        String studentCode = "B22DCCN218";
        String qCode = "Srv1TcXb";
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        CharacterService sv = (CharacterService)rg.lookup("RMICharacterService");
        String s = sv.requestCharacter(studentCode, qCode);
        String result = countCharacter(s);
        sv.submitCharacter(studentCode, qCode, result);
    }
}
