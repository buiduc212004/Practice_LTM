/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Practice_RMI.VsKJ6tUA_RMI_Object;

import java.io.Serializable;
import java.rmi.*;

public interface ObjectService extends Remote {
    public Serializable requestObject(String studentCode, String qAlias) throws RemoteException;

    public void submitObject(String studentCode, String qAlias, Serializable object) throws RemoteException;
}
