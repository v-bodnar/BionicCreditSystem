package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteInterface extends Remote{
	public void writeLog(String logString) throws RemoteException;
	

}
