/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Practice_WS.lQmEpOl0_WS_CharacterService;

import vn.medianews.*;
import java.util.*;
import java.io.*;
import java.net.*;

public class lQmEpOl0_WS_CharacterService {
    
    public static int countVowels(String s){
        int cnt = 0;
        s = s.toLowerCase();
        for(char c:s.toCharArray()){
            if("aieuo".indexOf(c) >= 0) cnt++;
        }
        return cnt;
    }
    
    public static List<String> groupByVowelCount(List<String> words){
        Map<Integer, List<String>> map = new HashMap<>();
        for(String w:words){
            int v = countVowels(w);
            map.computeIfAbsent(v, k -> new ArrayList<>()).add(w);
        }
        List<Integer> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys);
        
        List<String> result = new ArrayList<>();
        for (int key : keys) {
            List<String> list = map.get(key);
            Collections.sort(list);
            result.add(String.join(", ", list));
        }
        
        return result;
    }
    
    public static void main(String args[]) {
        String studentCode = "B22DCCN218";
        String qCode = "lQmEpOl0";
        
        CharacterService_Service css = new CharacterService_Service();
        CharacterService port = css.getCharacterServicePort();
        
        List<String> input = port.requestStringArray(studentCode, qCode);
        
        List<String> output = groupByVowelCount(input);
        System.out.println("Kết quả: " + output);
        
        port.submitCharacterStringArray(studentCode, qCode, output);
    }
}

