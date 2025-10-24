/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Practice_WS.XJ3i4SKi_WS_DataService;

import vn.medianews.*;
import java.util.*;
import java.io.*;
import java.net.*;

public class XJ3i4SKi_WS_DataService {

    public static void main(String args[]) {
        String studentCode = "B22DCCN218";
        String qCode = "XJ3i4SKi";
        
        DataService_Service dss = new DataService_Service();
        DataService port = dss.getDataServicePort();
        
        List<Integer> numbers = port.getData(studentCode, qCode);
        
        List<String> resultList = new ArrayList<>();
        for(Integer n : numbers){
            String result = Integer.toOctalString(n) + "|" + Integer.toHexString(n).toUpperCase();
            resultList.add(result);
        }
        
        for(String s:resultList){
            System.out.println(s);
        }
        
        port.submitDataStringArray(studentCode, qCode, resultList);
    }
}
