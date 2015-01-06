package rmi;
import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import server.RemoteInterface;
import userinterface.ErrorFrame;
import dao.GenericDao;
import entity.*;

import java.rmi.Remote;

/**
* <h1>DAOStubFactory!</h1>
* Factory that generates Stubs for Remote DAO
* @author  Bodnar Volodymyr
* @version 0.1
*/
public class DAOStubFactory {
	
	private static Logger log = Logger.getLogger(DAOStubFactory.class.getName());
	static protected final Logger log_file = Logger.getLogger(DAOStubFactory.class.getName());
	private static Context namingContext;
	private static Preferences root = Preferences.userRoot();
	private static final Preferences NODE = root.node("/com/bionic/credit_program/client");
	private static final String RMI_Server = NODE.get("SERVER_IP", "localhost");

	private GenericDao<Account> accountDao;
	private GenericDao<CreditProgram> creditProgramDao;
	private GenericDao<CreditRequest> creditRequestDao;
	private GenericDao<Credits> creditsDao;
	private GenericDao<Currency> currencyDao;
	private GenericDao<Role> roleDao;
	private GenericDao<Transaction> transactionDao;
	private GenericDao<User> userDao;
	private RemoteInterface remoteLogger;
	
	@SuppressWarnings("unchecked")
	public DAOStubFactory(){
		JFrame progressFrame = showProgress();
		createLogFiles();
		log_file.info("initializing DAOStubFactory");
		log.info("initializing DAOStubFactory");
		try {
			namingContext = new InitialContext();
			accountDao = (GenericDao<Account>) namingContext.lookup("rmi://" + RMI_Server + "/account_dao");
			creditProgramDao = (GenericDao<CreditProgram>) namingContext.lookup("rmi://" + RMI_Server + "/ceredit_program_dao");
			creditRequestDao = (GenericDao<CreditRequest>) namingContext.lookup("rmi://" + RMI_Server + "/credit_request_dao");
			creditsDao = (GenericDao<Credits>) namingContext.lookup("rmi://" + RMI_Server + "/credits_dao");
			currencyDao = (GenericDao<Currency>) namingContext.lookup("rmi://" + RMI_Server + "/currency_dao");
			roleDao  = (GenericDao<Role>) namingContext.lookup("rmi://" + RMI_Server + "/role_dao");	
			transactionDao = (GenericDao<Transaction>) namingContext.lookup("rmi://" + RMI_Server + "/transaction_dao");
			userDao = (GenericDao<User>) namingContext.lookup("rmi://" + RMI_Server + "/user_dao");
			remoteLogger = (RemoteInterface)namingContext.lookup("rmi://" + RMI_Server + "/remote_logger");	
			//System.setProperty("java.security.policy", "client.policy");
			progressFrame.dispose();
		} catch (NamingException e) {
			progressFrame.dispose();
			new ErrorFrame(e);
			e.printStackTrace();
		}
	}
	
	private JFrame showProgress(){
		JFrame progressFrame = new JFrame("Загрузка");
		JProgressBar progressBar = new JProgressBar();
		progressBar.setIndeterminate(true); 
		JLabel progressLabel = new JLabel("Подождите, идет подключение к серверу...");
		progressFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		progressFrame.setLayout(new BorderLayout());
		progressFrame.add(progressLabel,BorderLayout.BEFORE_FIRST_LINE);
		progressFrame.add(progressBar,BorderLayout.AFTER_LAST_LINE);
		progressFrame.pack();
		progressFrame.setLocationRelativeTo(null);
		progressFrame.setVisible(true);
		return progressFrame;
	}
	
	public Remote getRemoteLogger() {
		log_file.info("RemoteLogger requested");
		return remoteLogger;
	}
	
	public GenericDao<Account> getAccountDao() {
		log_file.info("AccountDao requested");
		return accountDao;
	}

	public GenericDao<CreditProgram> getCreditProgramDao() {
		log_file.info("CreditProgramDao requested");
		return creditProgramDao;
	}

	public GenericDao<CreditRequest> getCreditRequestDao() {
		log_file.info("CreditRequestDao requested");
		return creditRequestDao;
	}

	public GenericDao<Credits> getCreditsDao() {
		log_file.info("CreditsDao requested");
		return creditsDao;
	}

	public GenericDao<Currency> getCurrencyDao() {
		log_file.info("CurrencyDao requested");
		return currencyDao;
	}

	public GenericDao<Role> getRoleDao() {
		log_file.info("RoleDao requested");
		return roleDao;
	}

	public GenericDao<Transaction> getTransactionDao() {
		log_file.info("TransactionDao requested");
		return transactionDao;
	}

	public GenericDao<User> getUserDao() {
		log_file.info("UserDao requested");
		return userDao;
	}	
	private static void createLogFiles(){
		String home = System.getProperty("user.home");
		File dir = new File(home + "\\Bionic");
		if (!dir.exists()) {
			dir.mkdirs();
		}
		FileHandler fh;
		try {
			
			fh = new FileHandler(home + "\\Bionic\\DAOStubFactory.log");
			log_file.addHandler(fh);
		} catch (SecurityException | IOException e1) {
			e1.printStackTrace();
			new ErrorFrame(e1);
		}  
		log_file.setLevel(Level.ALL);
	}
}