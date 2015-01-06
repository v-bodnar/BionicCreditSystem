package userinterface;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.prefs.BackingStoreException;
import java.util.prefs.InvalidPreferencesFormatException;
import java.util.prefs.Preferences;

import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import service.FieldChecker;

public class ServerPrefFrame extends JFrame {
	private static final long serialVersionUID = 3517834437888246123L;
	private JPanel formPanel = new JPanel();
	private JLabel serverLabel = new JLabel();
	private JPanel statusPanel = new JPanel();
	private JLabel statusLabel = new JLabel();
	private JTextField hostField = new JTextField();
	private JLabel portLabel = new JLabel();
	private JTextField portField = new JTextField();
	private JLabel dbNameLabel = new JLabel();
	private JTextField dbNameField = new JTextField();
	private JLabel userLabel = new JLabel();
	private JTextField userField = new JTextField();
	private JLabel passwordLabel = new JLabel();
	private JPasswordField passwordField = new JPasswordField();
	private JButton saveButton = new JButton();
	private JButton exportButton = new JButton();
	private JButton importButton = new JButton();
	//private JLabel statusLabel = new JLabel();
	private GridBagConstraints gridBagConstraints;
	
	private Preferences root = Preferences.userRoot();
	private final Preferences NODE = root.node("/com/bionic/credit_program/server");
	private final String SERVER_IP = NODE.get("DB_HOST", "89.252.29.137");
	private final String DB_PORT = NODE.get("DB_PORT", "3306");
	private final String DB_NAME = NODE.get("DB_NAME", "credit");
	private final String DB_USER = NODE.get("DB_USER", "bodnar");
	private final String DB_PASSWORD = NODE.get("DB_PASSWORD", "aq1sw2de3");

	
	public ServerPrefFrame(){
		initComponents();
	}
	
	private void initComponents() {
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Настройки");
		setPreferredSize(new java.awt.Dimension(500, 600));
		//getContentPane().setLayout(new java.awt.BorderLayout(5, 5));
		setLocation(360, 100);
		setSize(new java.awt.Dimension(500, 600 ));
		
		formPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
		formPanel.setPreferredSize(new java.awt.Dimension(450, 300));
		formPanel.setRequestFocusEnabled(false);
		formPanel.setLayout(new java.awt.GridBagLayout());
		
		serverLabel.setLabelFor(hostField);
		serverLabel.setText("Имя или IP сервера БД: ");
		serverLabel.setToolTipText("Введите доменное имя сервера баз данных или его ip");
		serverLabel.setName(""); // NOI18N
		serverLabel.setPreferredSize(new java.awt.Dimension(150, 25));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
		formPanel.add(serverLabel, gridBagConstraints);
		
		hostField.setText(SERVER_IP);
		hostField.setToolTipText("Введите доменное имя сервера баз данных или его ip");
		hostField.setName(""); // NOI18N
		hostField.setPreferredSize(new java.awt.Dimension(130, 25));
		hostField.setInputVerifier(new hostFieldVerifier());
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
		formPanel.add(hostField, gridBagConstraints);
		
		portLabel.setLabelFor(portField);
		portLabel.setText("Порт: ");
		portLabel.setToolTipText("Введите порт подключения");
		portLabel.setName(""); // NOI18N
		portLabel.setPreferredSize(new java.awt.Dimension(150, 25));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
		formPanel.add(portLabel, gridBagConstraints);
		
		portField.setText(DB_PORT);
		portField.setToolTipText("Введите порт подключения");
		portField.setName(""); // NOI18N
		portField.setPreferredSize(new java.awt.Dimension(130, 25));
		portField.setInputVerifier(new portFieldVerifier());
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
		formPanel.add(portField, gridBagConstraints);
		
		dbNameLabel.setLabelFor(dbNameField);
		dbNameLabel.setText("Имя Базы Данных: ");
		dbNameLabel.setToolTipText("Введите Имя Базы Данных");
		dbNameLabel.setName(""); // NOI18N
		dbNameLabel.setPreferredSize(new java.awt.Dimension(150, 25));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
		formPanel.add(dbNameLabel, gridBagConstraints);
		
		dbNameField.setText(DB_NAME);
		dbNameField.setToolTipText("Введите Имя Базы Данных");
		dbNameField.setName(""); // NOI18N
		dbNameField.setPreferredSize(new java.awt.Dimension(130, 25));
		dbNameField.setInputVerifier(new dbNameFieldVerifier());
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
		formPanel.add(dbNameField, gridBagConstraints);
		
		userLabel.setLabelFor(userField);
		userLabel.setText("Логин: ");
		userLabel.setToolTipText("Введите логин пользователя Базы данных");
		userLabel.setName(""); // NOI18N
		userLabel.setPreferredSize(new java.awt.Dimension(150, 25));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
		formPanel.add(userLabel, gridBagConstraints);
		
		userField.setText(DB_USER);
		userField.setToolTipText("Введите логин пользователя Базы данных");
		userField.setName(""); // NOI18N
		userField.setPreferredSize(new java.awt.Dimension(130, 25));
		userField.setInputVerifier(new userFieldVerifier());
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
		formPanel.add(userField, gridBagConstraints);
		
		passwordLabel.setLabelFor(passwordField);
		passwordLabel.setText("Пароль: ");
		passwordLabel.setToolTipText("Введите пароль пользователя Базы данных");
		passwordLabel.setName(""); // NOI18N
		passwordLabel.setPreferredSize(new java.awt.Dimension(150, 25));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
		formPanel.add(passwordLabel, gridBagConstraints);
		
		passwordField.setText(DB_PASSWORD);
		passwordField.setToolTipText("Введите пароль пользователя Базы данных");
		passwordField.setName(""); // NOI18N
		passwordField.setPreferredSize(new java.awt.Dimension(130, 25));
		passwordField.setInputVerifier(new passwordFieldVerifier());
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
		formPanel.add(passwordField, gridBagConstraints);
		
		saveButton.setText("Сохранить");
		saveButton.setName(""); // NOI18N
		saveButton.setPreferredSize(new java.awt.Dimension(130, 25));
		saveButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				saveButtonActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
		formPanel.add(saveButton, gridBagConstraints);
		
		exportButton.setText("Экспорт");
		exportButton.setName(""); // NOI18N
		exportButton.setPreferredSize(new java.awt.Dimension(130, 25));
		exportButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				exportButtonActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 6;
		gridBagConstraints.gridwidth = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
		formPanel.add(exportButton, gridBagConstraints);
		
		importButton.setText("Импорт");
		importButton.setName(""); // NOI18N
		importButton.setPreferredSize(new java.awt.Dimension(130, 25));
		importButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				importButtonActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 6;
		gridBagConstraints.gridwidth = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
		formPanel.add(importButton, gridBagConstraints);
		
/*
		statusLabel.setName(""); // NOI18N
		statusLabel.setPreferredSize(new java.awt.Dimension(150, 25));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 6;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
		formPanel.add(statusLabel, gridBagConstraints);
	*/	
		//getContentPane().setLayout(BorderLayout.);
		getContentPane().add(formPanel,BorderLayout.PAGE_START);
		getContentPane().add(statusPanel,BorderLayout.PAGE_END);
		
		pack();
	}
	
	private class hostFieldVerifier extends InputVerifier {
		public boolean verify(JComponent input) {
			JTextField textField = (JTextField) input;
			statusLabel.setText(FieldChecker.checkIfNull(textField.getText()));
			return FieldChecker.checkIfNull(textField.getText()) == "";
		}
	}
	private class portFieldVerifier extends InputVerifier {
		public boolean verify(JComponent input) {
			JTextField textField = (JTextField) input;
			statusLabel.setText(FieldChecker.checkPort(textField.getText()));
			return FieldChecker.checkPort(textField.getText()) == "";
		}
	}
	private class userFieldVerifier extends InputVerifier {
		public boolean verify(JComponent input) {
			JTextField textField = (JTextField) input;
			statusLabel.setText(FieldChecker.checkLatin(textField.getText()));
			return FieldChecker.checkLatin(textField.getText()) == "";
		}
	}
	private class passwordFieldVerifier extends InputVerifier {
		public boolean verify(JComponent input) {
			JTextField textField = (JTextField) input;
			statusLabel.setText(FieldChecker.checkIfNull(textField.getText()));
			return FieldChecker.checkIfNull(textField.getText()) == "";
		}
	}
	private class dbNameFieldVerifier extends InputVerifier {
		public boolean verify(JComponent input) {
			JTextField textField = (JTextField) input;
			statusLabel.setText(FieldChecker.checkLatin(textField.getText()));
			return FieldChecker.checkLatin(textField.getText()) == "";
		}
	}
	
	private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {
		if (FieldChecker.checkIfNull(hostField.getText()) == "") {
			NODE.put("DB_HOST",hostField.getText());
			NODE.put("DB_PORT",portField.getText());
			NODE.put("DB_NAME",dbNameField.getText());
			NODE.put("DB_USER",userField.getText());
			NODE.put("DB_PASSWORD",passwordField.getText());
			JOptionPane.showMessageDialog(statusPanel,
					"Настройки сохранены!");
    		this.dispose();
		} else {
			statusLabel.setText(FieldChecker.checkIfNull(hostField.getText()));
		}
	}
	private void exportButtonActionPerformed(java.awt.event.ActionEvent evt) {
		if (FieldChecker.checkIfNull(hostField.getText()) == "") {
			String home = System.getProperty("user.home");
			File f = new File(home + "\\Bionic\\CreditServer.config");
			File dir = new File(home + "\\Bionic");
			try{
			if(!f.exists()){
				dir.mkdir();
				f.createNewFile();
			}	
			FileOutputStream out = new FileOutputStream(home + "\\Bionic\\CreditServer.config");
			NODE.exportNode(out);
			JOptionPane.showMessageDialog(statusPanel,
					"Настройки импортированы!");
			}catch(IOException | BackingStoreException e){
				e.printStackTrace();
				new ErrorFrame(e);
			}
		} else {
			statusLabel.setText(FieldChecker.checkIfNull(hostField.getText()));
		}
	}
	private void importButtonActionPerformed(java.awt.event.ActionEvent evt) {
		if (FieldChecker.checkIfNull(hostField.getText()) == "") {
			String home = System.getProperty("user.home");
			File f = null;
			f = new File(home + "\\Bionic\\CreditServer.config");
			if(!f.exists()){
				JOptionPane.showMessageDialog(statusPanel,
						"Сначала экспортируйте настройки!");
			}else
				try{
				FileInputStream in = new FileInputStream(home + "\\Bionic\\CreditServer.config");
				Preferences.importPreferences(in);
				final Preferences NODE1 = root.node("/com/bionic/credit_program/server");
				hostField.setText(NODE1.get("DB_HOST","89.252.29.137"));
				portField.setText(NODE1.get("DB_PORT","3306"));
				dbNameField.setText(NODE1.get("DB_NAME","credit"));
				userField.setText(NODE1.get("DB_USER","bodnar"));
				passwordField.setText(NODE1.get("DB_PASSWORD","aq1sw2de3"));	
				}catch(IOException | InvalidPreferencesFormatException e){
					e.printStackTrace();
					new ErrorFrame(e);
				};
			
		} else {
			statusLabel.setText(FieldChecker.checkIfNull(hostField.getText()));
		}
	}


	
	public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerPrefFrame().setVisible(true);
            }
        });
	}
}
