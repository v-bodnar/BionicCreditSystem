package userinterface;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.*;

import dao.GenericDao;
import dao.PersistException;
import rmi.DAOStubFactory;
import server.RemoteInterface;
import entity.User;

public class MainFrame extends JFrame {
	
	// Variables declaration - do not modify   
	private static final long serialVersionUID = 1689926637423268384L;               
    private JButton authButton;
    private JPanel authPanel;
    private JTextField login;
    private JLabel loginLabel;
    private JLabel logoImage;
    private JPasswordField password;
    private JLabel passwordLabel;
    private JButton regButton;
    private JButton prefsButton;
    private JPanel regPanel;
    private JLabel statusLabel;
    private JPanel statusPanel;
    private JLabel addUserButton;
    private JLabel creditProgramButton;
    private JLabel prefButton;
    private JLabel profileButton;
    private JLabel requestButton;
    private JLabel transactionButton;
    private JLabel usersButton;
    private GridBagConstraints gbAuth;
    private GridBagConstraints gbReg;
    private GridBagConstraints gbStatus;
    private GridBagConstraints gbLogo;
	private final String SALT = "Zaq1xsw2cde3~";
	private User authUser;
	private User currentUser;
	private static DAOStubFactory daoStubFactory = new DAOStubFactory();
	private static RemoteInterface remoteLogger = (RemoteInterface)daoStubFactory.getRemoteLogger();
	//private GenericDao<Account> accountDao = daoStubFactory.getAccountDao();
	//private GenericDao<CreditProgram> creditProgramDao = daoStubFactory.getCreditProgramDao();
	//private GenericDao<CreditRequest> creditRequestDao = daoStubFactory.getCreditRequestDao();
	//private GenericDao<Credits> creditsDao = daoStubFactory.getCreditsDao();
	//private GenericDao<Currency> currencyDao = daoStubFactory.getCurrencyDao();
	//private GenericDao<Role> roleDao = daoStubFactory.getRoleDao();
	//private GenericDao<Transaction> transactionDao = daoStubFactory.getTransactionDao();
	private GenericDao<User> userDao = daoStubFactory.getUserDao();
	private final ImageIcon ACCOUNT_ICON = new ImageIcon(getClass().getResource("/images/account_icon.png"));
	private final ImageIcon LOGO_ICON = new ImageIcon(getClass().getResource("/images/credit.png"));
	private final ImageIcon CREDIT_ICON = new ImageIcon(getClass().getResource("/images/credit_icon.png"));
	private final ImageIcon PERSONAL_ICON = new ImageIcon(getClass().getResource("/images/personal_icon.png"));
	private final ImageIcon PREF_ICON = new ImageIcon(getClass().getResource("/images/pref_icon.png"));
	private final ImageIcon REQUEST_ICON = new ImageIcon(getClass().getResource("/images/request_icon.png"));
	private final ImageIcon USER_ADD_ICON = new ImageIcon(getClass().getResource("/images/users_add_icon.png"));
	private final ImageIcon USERS_ICON = new ImageIcon(getClass().getResource("/images/users_icon.png"));
    // End of variables declaration   
	
	
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
    }

                         
    private void initComponents() {
        profileButton = new javax.swing.JLabel();
        transactionButton = new javax.swing.JLabel();
        requestButton = new javax.swing.JLabel();
        addUserButton = new javax.swing.JLabel();
        creditProgramButton = new javax.swing.JLabel();
        usersButton = new javax.swing.JLabel();
        prefButton = new javax.swing.JLabel();
        logoImage = new javax.swing.JLabel();
        authPanel = new javax.swing.JPanel();
        loginLabel = new javax.swing.JLabel();
        login = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        authButton = new javax.swing.JButton();
        regPanel = new javax.swing.JPanel();
        regButton = new javax.swing.JButton();
        prefsButton = new javax.swing.JButton();
        statusPanel = new javax.swing.JPanel();
        statusLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Кредитный менеджер");
        setBounds(new java.awt.Rectangle(1, 1, 1, 1));
        setMaximumSize(new java.awt.Dimension(250, 600));
        setMinimumSize(new java.awt.Dimension(250, 600));
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());
        setLocation(100,100);
        
        statusLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        
        logoImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logoImage.setIcon(LOGO_ICON); // NOI18N
        logoImage.setToolTipText("");
        
        profileButton.setIcon(PERSONAL_ICON); // NOI18N
        profileButton.setText("<html><a href=\"\"><h3>Профиль</h3></a></html>");
        profileButton.setToolTipText("");
        profileButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        transactionButton.setIcon(ACCOUNT_ICON); // NOI18N
        transactionButton.setText("<html><a href=\"\"><h3>Пополнение счета</h3></a></html>");
        transactionButton.setToolTipText("");
        transactionButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        requestButton.setIcon(REQUEST_ICON); // NOI18N
        requestButton.setText("<html><a href=\"\"><h3>Запросы на кредит</h3></a></html>");
        requestButton.setToolTipText("");
        requestButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        addUserButton.setIcon(USER_ADD_ICON); // NOI18N
        addUserButton.setText("<html><a href=\"\"><h3>Добавить пользователя</h3></a></html>");
        addUserButton.setToolTipText("");
        addUserButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        creditProgramButton.setIcon(CREDIT_ICON); // NOI18N
        creditProgramButton.setText("<html><a href=\"\"><h3>Кредитные Программы</h3></a></html>");
        creditProgramButton.setToolTipText("");
        creditProgramButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        usersButton.setIcon(USERS_ICON); // NOI18N
        usersButton.setText("<html><a href=\"\"><h3>Пользователи</h3></a></html>");
        usersButton.setToolTipText("");
        usersButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        prefButton.setIcon(PREF_ICON); // NOI18N
        prefButton.setText("<html><a href=\"\"><h3>Настройки</h3></a></html>");
        prefButton.setToolTipText("");
        prefButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
              
        gbLogo = new java.awt.GridBagConstraints();
        gbLogo.gridy = 0;
        gbLogo.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbLogo.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(logoImage, gbLogo);

        authPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Авторизация", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(153, 153, 153)));
        authPanel.setOpaque(false);
        authPanel.setPreferredSize(new java.awt.Dimension(230, 200));
        authPanel.setLayout(new java.awt.GridLayout(5, 1, 5, 5));

        loginLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loginLabel.setText("Логин");
        loginLabel.setToolTipText("");
        authPanel.add(loginLabel);
        authPanel.add(login);

        passwordLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        passwordLabel.setLabelFor(password);
        passwordLabel.setText("Пароль");
        passwordLabel.setToolTipText("");
        authPanel.add(passwordLabel);

        password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
					authButtonActionPerformed(evt);
            }
        });
        authPanel.add(password);

        authButton.setText("Войти");
        authButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
					authButtonActionPerformed(evt);
            }
        });
        authPanel.add(authButton);

        gbAuth = new java.awt.GridBagConstraints();
        gbAuth.gridy = 1;
        gbAuth.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbAuth.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(authPanel, gbAuth);

        regPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Регистрация", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(153, 153, 153)));
        regPanel.setMaximumSize(null);
        statusPanel.setPreferredSize(new java.awt.Dimension(230, 150));
        regPanel.setName(""); // NOI18N
        regPanel.setLayout(new java.awt.GridLayout(2, 1, 5,5));

        regButton.setText("Регистрация");
        regButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                    	new RegistrationForm(currentUser).setVisible(true);
                    }
                });
            }
        });
        regPanel.add(regButton);
        
        prefsButton.setText("Настройки");
        prefsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                    	new ClientPrefFrame().setVisible(true);
                    }
                });
            }
        });
        regPanel.add(prefsButton);

        requestButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                    	new CreditRequestFrame(currentUser).setVisible(true);
                    }
                });
            }
        });
        creditProgramButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                    	new CreditProgramsFrame(currentUser).setVisible(true);
                    }
                });
            }
        });
        profileButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                    	new UserProfile(currentUser).setVisible(true);
                    }
                });
            }
        });
        addUserButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                    	new RegistrationForm(currentUser).setVisible(true);
                    }
                });
            }
        });
        usersButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                    	new UsersFrame(currentUser).setVisible(true);
                    }
                });
            }
        });
        prefButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                    	new ClientPrefFrame().setVisible(true);
                    }
                });
            }
        });
        
        
        gbReg = new java.awt.GridBagConstraints();
        gbReg.gridy = 2;
        gbReg.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbReg.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(regPanel, gbReg);

        statusPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(153, 153, 153)));
        statusPanel.setMaximumSize(null);
        statusPanel.setPreferredSize(new java.awt.Dimension(230, 200));
        statusPanel.setLayout(new java.awt.GridLayout(1, 1));

        statusPanel.add(statusLabel);

        gbStatus = new java.awt.GridBagConstraints();
        gbStatus.gridy = 3;
        gbStatus.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbStatus.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(statusPanel, gbStatus);

        pack();
    }// </editor-fold>                        
            

    private void authButtonActionPerformed(ActionEvent evt){                                           

			if (authorized()) {
				currentUser = authUser;
					switch (authUser.getIdRole())
					{
					case 1: 
						this.remove(regPanel);
				    	authPanel.removeAll();
				    	gbStatus.gridy = 2;
				    	getContentPane().add(statusPanel, gbStatus);
				    	authPanel.setPreferredSize(new java.awt.Dimension(230, 280));
				    	statusPanel.setPreferredSize(new java.awt.Dimension(230, 180));
				        authPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Главное меню", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(153, 153, 153)));
				    	authPanel.setLayout(new java.awt.GridLayout(7, 1, 5, 5));
				    	authPanel.add(profileButton);
				    	authPanel.add(usersButton);
				    	authPanel.add(addUserButton);
				    	authPanel.add(creditProgramButton);
				    	authPanel.add(requestButton);
				    	//authPanel.add(transactionButton);
				    	authPanel.add(prefButton);
				     	revalidate();
				    	repaint();
						break;
					case 2: 
						this.remove(regPanel);
				    	authPanel.removeAll();
				    	gbStatus.gridy = 2;
				    	getContentPane().add(statusPanel, gbStatus);
				    	authPanel.setPreferredSize(new java.awt.Dimension(230, 220));
				    	statusPanel.setPreferredSize(new java.awt.Dimension(230, 240));
				        authPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Главное меню", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(153, 153, 153)));
				    	authPanel.setLayout(new java.awt.GridLayout(5, 1, 5, 5));
				    	authPanel.add(profileButton);
				    	authPanel.add(addUserButton);
				    	authPanel.add(creditProgramButton);
				    	authPanel.add(requestButton);
				    	//authPanel.add(transactionButton);
				    	revalidate();
				    	repaint();
						break;
					case 3: 
						this.remove(regPanel);
				    	authPanel.removeAll();
				    	gbStatus.gridy = 2;
				    	getContentPane().add(statusPanel, gbStatus);
				    	authPanel.setPreferredSize(new java.awt.Dimension(230, 130));
				    	statusPanel.setPreferredSize(new java.awt.Dimension(230, 320));
				        authPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Главное меню", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(153, 153, 153)));
				    	authPanel.setLayout(new java.awt.GridLayout(3, 1, 5, 5));
				    	authPanel.add(profileButton);
				    	authPanel.add(creditProgramButton);
				    	//authPanel.add(transactionButton);
				    	revalidate();
				    	repaint();
						break;

					} 
				statusLabel.setText("<html><div align ='center'>Добро пожаловать,<br/> "+authUser.getFirstName()+"</div></html>");
			}else{
				statusLabel.setText("<html><div align ='center'> Логин или пароль <BR> введены не верно!</div></html>");

			}
    }
    private boolean authorized(){
	  	String username = login.getText();
		try {
			authUser = userDao.getEntity("users","username",username);
			
			if(hashPassword(authUser.getInn()).equals(authUser.getPasswordHash())  && authUser.isEnabled()) 
				remoteLogger.writeLog("User connected: " + authUser.getId() + " " + authUser.getFirstName() + " " + authUser.getLastName());
			else remoteLogger.writeLog("Пароль для логина " + username + " введен не верно!");
		} catch (RemoteException e) {
			e.printStackTrace();
			new ErrorFrame(new RemoteException("Ошибка связи с сервером! Возможно сервер не запущен. Проверьте настройки."));
		} catch (PersistException e) {
			e.printStackTrace();
			new ErrorFrame(new PersistException("Данного логина не существует: " + username));
			try {
				remoteLogger.writeLog("Данного логина не существует: " + username);
			} catch (RemoteException e1) {
				e1.printStackTrace();
				new ErrorFrame(e1);
			}
			statusLabel.setText("<html><div align ='center'> Логин или пароль <BR> введены не верно!</div></html>");
		}
		return hashPassword(authUser.getInn()).equals(authUser.getPasswordHash())  && authUser.isEnabled();
    }
    
    private String hashPassword(Long inn){
    	String hash = null;
  	  	MessageDigest cript;
			try {
				cript = MessageDigest.getInstance("SHA-1");
		          cript.reset();
		          String pass = SALT+Long.toString(inn)+new String(password.getPassword());
		          //System.out.println(pass);
		          cript.update(pass.getBytes());
		          hash = byteArrayToHexString(cript.digest());
		          //System.out.println(hash);
			} catch (NoSuchAlgorithmException e) {
    			e.printStackTrace();
    			new ErrorFrame(e);
			}
    	return hash;
    }
    
    private static String byteArrayToHexString(byte[] b) {
    	  String result = "";
    	  for (int i=0; i < b.length; i++) {
    	    result +=
    	          Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
    	  }
    	  return result;
    	}
    

	public static DAOStubFactory getDaoStubFactory() {
		return daoStubFactory;
	}


	public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
	}
                
}