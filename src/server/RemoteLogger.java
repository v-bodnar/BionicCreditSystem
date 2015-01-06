package server;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import userinterface.ErrorFrame;

public class RemoteLogger implements RemoteInterface{
	
	//private static final long serialVersionUID = 1995098330202654581L;
	private final String SEP = "/";
	private final Logger log = Logger.getLogger(Registry.class.getName());
	
	public RemoteLogger(){
		try {
			UnicastRemoteObject.exportObject(this, 0);
		} catch (RemoteException e) {
			e.printStackTrace();
			new ErrorFrame(e);
		}
		createLogFiles();
	}
	
	public void writeLog(String logString) {
		log.info(logString);
	}
	
	private void createLogFiles() {
		String home = System.getProperty("user.home");
		try {
			File dir = new File(home + SEP + "Bionic");
			if (!dir.exists()) {
				dir.mkdir();
			}
			FileHandler fh;
			fh = new FileHandler(home + SEP + "Bionic" + SEP + "Auth.log");
			log.addHandler(fh);
		} catch (SecurityException | IOException e1) {
			e1.printStackTrace();
			new ErrorFrame(e1);
		}
		log.setLevel(Level.ALL);
	}

}
