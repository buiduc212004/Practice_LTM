/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Practice_WS.mYGG2qQe_WS_ObjectService;

import java.io.*;
import java.util.*;
import java.net.*;
import vn.medianews.*;

public class mYGG2qQe_WS_ObjectService {
    
    public static List<EmployeeY> sortByDate(List<EmployeeY> list){
        Collections.sort(list, new Comparator<EmployeeY>(){
            @Override
            public int compare(EmployeeY e1, EmployeeY e2){
                return e1.getStartDate().compare(e2.getStartDate());
            }
        });
        return list;
    }

    public static void main(String args[]) {
        String studentCode = "B22DCCN218";
        String qCode = "mYGG2qQe";
        
        ObjectService_Service oss = new ObjectService_Service();
        ObjectService port = oss.getObjectServicePort();
        
        List<EmployeeY> employeeYs = port.requestListEmployeeY(studentCode, qCode);
        for(EmployeeY e:employeeYs){
            System.out.println(e.getName() + "|" + e.getStartDate());
        }
        
        List<EmployeeY> sortedList = sortByDate(employeeYs);    
        for(EmployeeY e : sortedList){
            System.out.println(e.getName() + "|" + e.getStartDate());
        }
        
        port.submitListEmployeeY(studentCode, qCode, sortedList);
    }
}
