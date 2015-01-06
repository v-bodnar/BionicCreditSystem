package server;

import javax.naming.*;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dao.GenericDao;
import dao.PersistException;
import entity.CreditProgram;
import entity.CreditRequest;
import entity.User;
import mysql.MySqlAccountDao;
import mysql.MySqlCreditProgramDao;
import mysql.MySqlCreditRequestDao;
import mysql.MySqlCreditsDao;
import mysql.MySqlCurrencyDao;
import mysql.MySqlDaoFactory;
import mysql.MySqlRoleDao;
import mysql.MySqlTransactionDao;
import mysql.MySqlUserDao;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import rmi.DAOStubFactory;
import service.DataGenerator;
import service.LogFormatter;
import userinterface.ErrorFrame;
import userinterface.ServerFrame;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

public class Server {	
	public static ServerFrame SF;
	private static final String SEP = "/";
	private static Preferences root = Preferences.userRoot();
	private static final Preferences NODE = root.node("/com/bionic/credit_program/client");
	private static final String RMI_Server = NODE.get("SERVER_IP", "localhost");
	private static final URL SQL_QUERY_URL = Server.class.getClassLoader().getResource("files" + SEP + "credit_install.sql");
	static protected final Logger log = Logger.getLogger(Registry.class.getName());
	private static DAOStubFactory daoStubFactory;
	private static GenericDao<CreditProgram> creditProgramDao;
	private static GenericDao<CreditRequest> creditRequestDao;
	private static GenericDao<User> userDao;
	
	public static DAOStubFactory getDaoStubFactory() {
		return daoStubFactory;
	}

	public static void setDaoStubFactory(DAOStubFactory daoStubFactory) {
		Server.daoStubFactory = daoStubFactory;
	}

	public static void main(String[] args) {
		//java.awt.EventQueue.invokeLater(SF = new ServerFrame());
		SF = new ServerFrame();
		SF.setVisible(true);
		Thread RMIThread = new Thread(new Runnable(){
			public void run(){
				createLogFiles();
				startRMI();
				daoStubFactory = new DAOStubFactory();
				//accountDao = daoStubFactory.getAccountDao();
				creditProgramDao = daoStubFactory.getCreditProgramDao();
				creditRequestDao = daoStubFactory.getCreditRequestDao();
				//creditsDao = daoStubFactory.getCreditsDao();
				//currencyDao = daoStubFactory.getCurrencyDao();
				//roleDao = daoStubFactory.getRoleDao();
				//transactionDao = daoStubFactory.getTransactionDao();
				userDao = daoStubFactory.getUserDao();
				
				while(true){
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
						new ErrorFrame(e1);
					}
					try {
						Context namingContext = new InitialContext();
						namingContext.lookup("rmi://" + RMI_Server + "/account_dao");
						namingContext.lookup("rmi://" + RMI_Server + "/ceredit_program_dao");
						namingContext.lookup("rmi://" + RMI_Server + "/credit_request_dao");
						namingContext.lookup("rmi://" + RMI_Server + "/credits_dao");
						namingContext.lookup("rmi://" + RMI_Server + "/currency_dao");
						namingContext.lookup("rmi://" + RMI_Server + "/role_dao");
						namingContext.lookup("rmi://" + RMI_Server + "/transaction_dao");
						namingContext.lookup("rmi://" + RMI_Server + "/user_dao");		
						namingContext.lookup("rmi://" + RMI_Server + "/remote_logger");	
					} catch (NamingException e) {
						bindNames();
					}
				}
			}
		});
		RMIThread.start();
	}
	
	private static List<String> readSqlFile(URL fileName){
		ArrayList<String> result = new ArrayList<>();

		// Открываем файл и считаем данные
		BufferedReader file;
		try {
			file = new BufferedReader(new InputStreamReader(fileName.openStream()));
			Scanner scr = new Scanner(file);
			scr.useDelimiter(";");
			while (scr.hasNext()) {
				result.add(scr.next());
			}
			scr.close();
			file.close(); // Закрываем файл
		} catch (IOException e) {
			e.printStackTrace();
			new ErrorFrame(e);
		}
		return result;
	}
	
	synchronized public static void createTables() {
		try{
        	for (String sql : readSqlFile(SQL_QUERY_URL)){
	        	Connection conn = new MySqlDaoFactory().getContext();
	        	PreparedStatement statement = conn.prepareStatement(sql);
	            statement.executeUpdate();
        	}
			User admin = new User(1, 1, 1, "admin", "aq1sw2de3", "Владимир", "Юрьевич", "Боднар", 0000000000, 2500, "Григоренко 15", "bodik@list.ru", new Date(), true);
			userDao.persist(admin);
			JPanel statusPanel = new JPanel();
			JOptionPane.showMessageDialog(statusPanel,
					"Таблицы сформированы!");
		}catch(PersistException e){
			e.printStackTrace();
			new ErrorFrame(e);
		}catch(SQLException e){
			e.printStackTrace();
			if (e.getMessage().equals("Table 'roles' already exists")){
				JPanel statusPanel = new JPanel();
				JOptionPane.showMessageDialog(statusPanel,
						"Таблицы уже сформированы!");
			}
		} catch (RemoteException e) {
			e.printStackTrace();
			new ErrorFrame(e);
		}
	}
	
	private static void bindNames() {
		try{
		Connection conn = new MySqlDaoFactory().getContext();
		Context namingContext = new InitialContext();
		MySqlAccountDao accountDao = new MySqlAccountDao(conn);
		MySqlCreditProgramDao cereditProgram = new MySqlCreditProgramDao(conn);
		MySqlCreditRequestDao creditRequest = new MySqlCreditRequestDao(conn);
		MySqlCreditsDao creditsDao = new MySqlCreditsDao(conn);
		MySqlCurrencyDao CurrencyDao = new MySqlCurrencyDao(conn);
		MySqlRoleDao roleDao = new MySqlRoleDao(conn);
		MySqlTransactionDao transactionDao = new MySqlTransactionDao(conn);
		MySqlUserDao userDao = new MySqlUserDao(conn);
		RemoteLogger remoteLogger = new RemoteLogger();
		
		namingContext.rebind("rmi:account_dao", accountDao);
		namingContext.rebind("rmi:ceredit_program_dao", cereditProgram);
		namingContext.rebind("rmi:credit_request_dao", creditRequest);
		namingContext.rebind("rmi:credits_dao", creditsDao);
		namingContext.rebind("rmi:currency_dao", CurrencyDao);
		namingContext.rebind("rmi:role_dao", roleDao);
		namingContext.rebind("rmi:transaction_dao", transactionDao);
		namingContext.rebind("rmi:user_dao", userDao);
		namingContext.rebind("rmi:remote_logger", remoteLogger);
		SF.setStatusLabel("<html><div>Server is up!</div> <div> Last binding: " + new Date() + "</div></html>");
		log.info("Server is up!</div> <div> Last binding: " + new Date());
		}catch(PersistException | NamingException e){
			e.printStackTrace();
			new ErrorFrame(e);
		}
	}
	private static void startRMI() {
		createLogFiles();
		String status = "";
		try {
			status += "<html><div>Constructing server implementation...";
			log.info("<html><div>Constructing server implementation...");
			SF.setStatusLabel(status);

			status += "Creating RMI Registry...";
			log.info("Creating RMI Registry...");
			SF.setStatusLabel(status);
			Registry registry = LocateRegistry.createRegistry(1099);
			status += "<div>" + registry + "</div>";
			log.info("" + registry);
			SF.setStatusLabel(status);
			status += "Creating DAO";
			log.info("Creating DAO");
			SF.setStatusLabel(status);

			status += "Binding DAO to RMI Registry...";
			log.info("Binding DAO to RMI Registry...");
			SF.setStatusLabel(status);
			bindNames();

		} catch (Exception e) {
			e.printStackTrace();
			new ErrorFrame(e);
		}
		status += "<div>Waiting for invocations from clients...</div></html>";
		log.info("Waiting for invocations from clients...");
		SF.setStatusLabel(status);
	}

	private static void createLogFiles() {
		String home = System.getProperty("user.home");
		try {
			File dir = new File(home + SEP + "Bionic");
			if (!dir.exists()) {
				dir.mkdir();
			}
			FileHandler fh;
			fh = new FileHandler(home + SEP + "Bionic" + SEP + "Server.log");
			log.addHandler(fh);
		} catch (SecurityException | IOException e1) {
			e1.printStackTrace();
			new ErrorFrame(e1);
		}
		log.setLevel(Level.ALL);
	}
	
	public static void addTestData(){
		final int USERS_COUNT = 10;
		final int REQUESTS_PER_USER = 10;
		try {
			addCreditPrograms();
			List<User> usersList = new ArrayList<User>();
			for (int i=0; i < USERS_COUNT; i++){
				Integer userId = userDao.getNextPK();
				String firstName = DataGenerator.getFirstName();
				String middleName = DataGenerator.getMiddleName();
				String lastName = DataGenerator.getLastName();
				User newUser = new User(userId, 3, 1, DataGenerator.getUserName(firstName,lastName), "aq1sw2de3", firstName, middleName, lastName, DataGenerator.getInn(userId), DataGenerator.getIncome(), DataGenerator.getAddress(), DataGenerator.getEmail(firstName,lastName), new Date(), true);
				usersList.add(newUser);
				userDao.persist(newUser);
			}
			for (User user :usersList ){
				for (int i=0; i < REQUESTS_PER_USER; i++){
					Random r = new Random();
					List<CreditProgram> creditProgramsList = creditProgramDao.getAll();
					Integer requestId = creditRequestDao.getNextPK();
					Integer creditId = creditProgramsList.get(r.nextInt(creditProgramsList.size())).getId();
					Integer min = Math.round(creditProgramDao.getByPK(creditId).getMinSum());
					Integer max = Math.round(creditProgramDao.getByPK(creditId).getMaxSum());
					Float sum = (float)(r.nextInt(max - min) + min);
					
					CreditRequest newRequest = new CreditRequest(requestId, 1, creditId , user.getId(), false, DataGenerator.getCardNumber(user.getInn()), sum, new Date(), null, null);
					creditRequestDao.persist(newRequest);
				}
			}
		} catch (RemoteException | PersistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			new ErrorFrame(e);
		}
		
	}
	
	private static void addCreditPrograms() throws PersistException, RemoteException {
		Logger inform = new LogFormatter().getLogger();
		inform.setLevel(Level.INFO);

		{
			inform.info("Creating credit Program");
			Integer id = creditProgramDao.getNextPK();
			String title = "Кредит на покупку авто";
			String shortDescription = "Возможность приобрести автомобиль с площадки банка, сэкономив до 6% его стоимости. Оформление документов в ГАИ банк берет на себя, и Вы уже через час получаете ключи от машины, техпаспорт и новые номера.";
			String fullDescription = "Вы получаете решение по рассрочке за 30 минут. Приобретая новое авто с площадки банка. Вы экономите до 6% от его стоимости.Все заботы, связанные с оформлением документов в ГАИ ПриватБанк берет на себя. Вам остается лишь подписать договор, и уже через час у Вас в руках будут ключи, техпаспорт и номера нового автомобиля.Вы бесплатно получаете систему GPS-мониторинга. С ее помощью можно в режиме онлайн узнать, где находится Ваш автомобиль или какой маршрут был пройден за указанный период. Это очень удобно, если автомобилем будете управлять не только Вы, а кто-нибудь еще, например, Ваши родственники или Ваши работники.";
			Integer period = 36;
			float yearPercent = 21;
			float minSum = 10000;
			float maxSum = 250000;

			CreditProgram creditProgram = new CreditProgram(id, 1, title,
					yearPercent, period, minSum, maxSum, shortDescription,
					fullDescription, new Date(), 1, true);

			creditProgram = creditProgramDao.persist(creditProgram);

			inform.info("Credit Program created:" + "Credit Program id: "
					+ creditProgram.getId() + "\n" + "Title: "
					+ creditProgram.getTitle() + "\n" + "Short Description: "
					+ creditProgram.getShortDescription() + "\n"
					+ "Full Description: " + creditProgram.getFullDescription()
					+ "\n" + "Minimum Sum: " + creditProgram.getMinSum() + "\n"
					+ "Maximum Sum: " + creditProgram.getMaxSum() + "\n"
					+ "Year Percent: " + creditProgram.getYearPercent() + "\n"
					+ "Period: " + creditProgram.getPeriod() + " month. \n");

		}
		{
			inform.info("Creating credit Program");
			Integer id = creditProgramDao.getNextPK();
			String title = "Кредит на покупку жилья";
			String shortDescription = "Вы можете приобрести в кредит жилье и коммерческую недвижимость на выгодных условиях. Большой выбор залоговой недвижимости по доступным ценам во всех регионах Украины. Пониженная кредитная ставка, оперативное оформление и безопасность сделки создают привлекательные условия для клиентов.";
			String fullDescription = "Вы можете приобрести недвижимость в кредит на выгодных условиях: первоначальный взнос – от 25%, максимальный срок – до 20 лет, ставка по кредиту – от 18% годовых, никаких скрытых комиссий и платежей. При оформлении кредита страхуется недвижимость и Ваша жизнь а также трудоспособность, что защитит Вас от форс-мажорных обстоятельств. Если платёж по кредиту не под силу Вашим финансовым возможностям, Вы можете взять недвижимость в долгосрочную аренду!";
			Integer period = 120;
			float yearPercent = 18;
			float minSum = 100000;
			float maxSum = 500000;

			CreditProgram creditProgram = new CreditProgram(id, 1, title,
					yearPercent, period, minSum, maxSum, shortDescription,
					fullDescription, new Date(), 1, true);

			creditProgram = creditProgramDao.persist(creditProgram);

			inform.info("Credit Program created:" + "Credit Program id: "
					+ creditProgram.getId() + "\n" + "Title: "
					+ creditProgram.getTitle() + "\n" + "Short Description: "
					+ creditProgram.getShortDescription() + "\n"
					+ "Full Description: " + creditProgram.getFullDescription()
					+ "\n" + "Minimum Sum: " + creditProgram.getMinSum() + "\n"
					+ "Maximum Sum: " + creditProgram.getMaxSum() + "\n"
					+ "Year Percent: " + creditProgram.getYearPercent() + "\n"
					+ "Period: " + creditProgram.getPeriod() + " month. \n");

		}
		{
			inform.info("Creating credit Program");
			Integer id = creditProgramDao.getNextPK();
			String title = "Кредитная Карта «Универсальная»";
			String shortDescription = "Самый простой способ получения кредита. Вы можете оформить карту 'Универсальная' бесплатно и быстро в любом отделении Банка, имея при себе только паспорт. Главное преимущество карты 'Универсальная'  – бесплатное пользование кредитными деньгами банка до 2 месяцев с момента траты. После внесения на карту деньги снова доступны Вам.";
			String fullDescription = "Карта «Универсальная» – это платежная пластиковая карта Банка, которая дает Вам возможность пользоваться как собственными деньгами на карте, так и кредитными средствами банка. На эту карту Вам могут перечислять деньги Ваши родственники, знакомые или партнеры. Картой «Универсальная» можно рассчитываться в торговых точках, совершать оплату в Интернете и терминалах самообслуживания, а также снимать наличные в любой точке мира. При оплате за границей комиссия за конвертацию средств с валюты карты в валюту страны взымается согласно официальному обменному курсу банка на день покупки.";
			Integer period = 2;
			float yearPercent = 25;
			float minSum = 1000;
			float maxSum = 25000;

			CreditProgram creditProgram = new CreditProgram(id, 1, title,
					yearPercent, period, minSum, maxSum, shortDescription,
					fullDescription, new Date(), 1, true);
			creditProgram = creditProgramDao.persist(creditProgram);

			inform.info("Credit Program created:" + "Credit Program id: "
					+ creditProgram.getId() + "\n" + "Title: "
					+ creditProgram.getTitle() + "\n" + "Short Description: "
					+ creditProgram.getShortDescription() + "\n"
					+ "Full Description: " + creditProgram.getFullDescription()
					+ "\n" + "Minimum Sum: " + creditProgram.getMinSum() + "\n"
					+ "Maximum Sum: " + creditProgram.getMaxSum() + "\n"
					+ "Year Percent: " + creditProgram.getYearPercent() + "\n"
					+ "Period: " + creditProgram.getPeriod() + " month. \n");
			
}
	}

}
