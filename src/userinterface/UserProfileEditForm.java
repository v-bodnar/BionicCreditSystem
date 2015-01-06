package userinterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.List;

import javax.naming.NamingException;
import javax.swing.InputVerifier;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.GenericDao;
import dao.PersistException;
import entity.Role;
import entity.User;
import rmi.DAOStubFactory;
import service.DataGenerator;
import service.FieldChecker;

import java.awt.GridBagConstraints;
public class UserProfileEditForm extends javax.swing.JFrame {
	

	private static final long serialVersionUID = -4966703344898240662L;
	
	public UserProfileEditForm(User currentUser, User editedUser) {
		//System.out.println("worked");
		this.currentUser = currentUser;
		this.editedUser = editedUser;
	    try {
			initComponents();
		} catch (NumberFormatException | RemoteException | PersistException | NamingException e) {
			e.printStackTrace();
			new ErrorFrame(e);
		}
	}

private void initComponents() throws RemoteException, NamingException, PersistException {
	GridBagConstraints gridBagConstraints;
    mainPanel = new javax.swing.JPanel();
    Title = new javax.swing.JLabel();
    loginLabel = new javax.swing.JLabel();
    loginField = new javax.swing.JTextField();
    passwordLabel = new javax.swing.JLabel();
    passwordField = new javax.swing.JPasswordField();
    lastNameLabel = new javax.swing.JLabel();
    lastNameField = new javax.swing.JTextField();
    incomeLabel = new javax.swing.JLabel();
    incomeField = new javax.swing.JTextField();
    firstNameLabel = new javax.swing.JLabel();
    firstNameField = new javax.swing.JTextField();
    innLabel = new javax.swing.JLabel();
    innField = new javax.swing.JTextField();
    middleNameLabel = new javax.swing.JLabel();
    middleNameField = new javax.swing.JTextField();
    emailLabel = new javax.swing.JLabel();
    emailField = new javax.swing.JTextField();
    addressLabel = new javax.swing.JLabel();
    addressField = new javax.swing.JTextField();
    regButton = new javax.swing.JButton();
    statusPanel = new javax.swing.JPanel();
    statusLabel = new javax.swing.JLabel();
    roleLabel = new javax.swing.JLabel();
    roleBox = new javax.swing.JComboBox<String>();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Регистрация в системе Кредитный Менеджер");
    setBounds(new java.awt.Rectangle(0, 0, 0, 0));
    setPreferredSize(new java.awt.Dimension(500, 600));
    getContentPane().setLayout(new java.awt.GridLayout(2, 1, 5, 5));
    setLocation(360,100);

    mainPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
    mainPanel.setToolTipText("");
    mainPanel.setMaximumSize(new java.awt.Dimension(480, 250));
    mainPanel.setMinimumSize(new java.awt.Dimension(480, 250));
    mainPanel.setPreferredSize(new java.awt.Dimension(480, 250));
    mainPanel.setLayout(new java.awt.GridBagLayout());

    Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    Title.setText("<html><h3>Изменение данных о пользователе</h3></html>");
    Title.setToolTipText("");
    Title.setPreferredSize(new java.awt.Dimension(460, 25));
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.gridwidth = 4;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    mainPanel.add(Title, gridBagConstraints);

    loginLabel.setLabelFor(loginField);
    loginLabel.setText("Логин");
    loginLabel.setToolTipText("Введите желаемый логин");
    loginLabel.setMaximumSize(new java.awt.Dimension(90, 25));
    loginLabel.setMinimumSize(new java.awt.Dimension(90, 25));
    loginLabel.setName(""); // NOI18N
    loginLabel.setPreferredSize(new java.awt.Dimension(90, 25));
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    mainPanel.add(loginLabel, gridBagConstraints);
    
    loginField.setText(editedUser.getUserName());
    loginField.setToolTipText("Введите желаемый логин");
    loginField.setMaximumSize(new java.awt.Dimension(120, 25));
    loginField.setMinimumSize(new java.awt.Dimension(120, 25));
    loginField.setName(""); // NOI18N
    loginField.setPreferredSize(new java.awt.Dimension(130, 25));
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    mainPanel.add(loginField, gridBagConstraints);

    passwordLabel.setLabelFor(passwordField);
    passwordLabel.setText("Пароль");
    passwordLabel.setToolTipText("Введите желаемый пароль. Пароль должен содержать цифры и буквы!");
    passwordLabel.setMaximumSize(new java.awt.Dimension(90, 25));
    passwordLabel.setMinimumSize(new java.awt.Dimension(90, 25));
    passwordLabel.setName(""); // NOI18N
    passwordLabel.setPreferredSize(new java.awt.Dimension(90, 25));
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    mainPanel.add(passwordLabel, gridBagConstraints);

    passwordField.setText("");
    passwordField.setToolTipText("Введите желаемый пароль. Пароль должен содержать цифры и буквы!");
    passwordField.setMaximumSize(new java.awt.Dimension(120, 25));
    passwordField.setMinimumSize(new java.awt.Dimension(120, 25));
    passwordField.setName(""); // NOI18N
    passwordField.setPreferredSize(new java.awt.Dimension(130, 25));
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 4;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    mainPanel.add(passwordField, gridBagConstraints);

    lastNameLabel.setLabelFor(lastNameField);
    lastNameLabel.setText("Фамилия");
    lastNameLabel.setToolTipText("Введите Вашу фамилию");
    lastNameLabel.setMaximumSize(new java.awt.Dimension(90, 25));
    lastNameLabel.setMinimumSize(new java.awt.Dimension(90, 25));
    lastNameLabel.setPreferredSize(new java.awt.Dimension(90, 25));
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 5;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    mainPanel.add(lastNameLabel, gridBagConstraints);

    lastNameField.setText(editedUser.getLastName());
    lastNameField.setToolTipText("Введите Вашу фамилию");
    lastNameField.setMaximumSize(new java.awt.Dimension(120, 25));
    lastNameField.setMinimumSize(new java.awt.Dimension(120, 25));
    lastNameField.setPreferredSize(new java.awt.Dimension(130, 25));
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 4;
    gridBagConstraints.gridy = 5;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    mainPanel.add(lastNameField, gridBagConstraints);

    incomeLabel.setLabelFor(incomeField);
    incomeLabel.setText("Доход");
    incomeLabel.setToolTipText("Введите сумму Ваших доходов за последние пол года");
    incomeLabel.setMaximumSize(new java.awt.Dimension(90, 25));
    incomeLabel.setMinimumSize(new java.awt.Dimension(90, 25));
    incomeLabel.setPreferredSize(new java.awt.Dimension(90, 25));
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 4;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    mainPanel.add(incomeLabel, gridBagConstraints);

    incomeField.setText(Float.toString(editedUser.getIncome()));
    incomeField.setToolTipText("Введите сумму Ваших доходов за последние пол года");
    incomeField.setMaximumSize(new java.awt.Dimension(120, 25));
    incomeField.setMinimumSize(new java.awt.Dimension(120, 25));
    incomeField.setPreferredSize(new java.awt.Dimension(130, 25));
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 4;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    mainPanel.add(incomeField, gridBagConstraints);

    firstNameLabel.setLabelFor(firstNameField);
    firstNameLabel.setText("Имя");
    firstNameLabel.setToolTipText("Введите Ваше имя");
    firstNameLabel.setMaximumSize(new java.awt.Dimension(90, 25));
    firstNameLabel.setMinimumSize(new java.awt.Dimension(90, 25));
    firstNameLabel.setPreferredSize(new java.awt.Dimension(90, 25));
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 4;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    mainPanel.add(firstNameLabel, gridBagConstraints);

    firstNameField.setText(editedUser.getFirstName());
    firstNameField.setToolTipText("Введите Ваше имя");
    firstNameField.setPreferredSize(new java.awt.Dimension(130, 25));
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 4;
    gridBagConstraints.gridy = 4;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    mainPanel.add(firstNameField, gridBagConstraints);

    innLabel.setLabelFor(innField);
    innLabel.setText("Идент. код");
    innLabel.setToolTipText("Введите Ваш идентификационный код");
    innLabel.setMaximumSize(new java.awt.Dimension(90, 25));
    innLabel.setMinimumSize(new java.awt.Dimension(90, 25));
    innLabel.setPreferredSize(new java.awt.Dimension(90, 25));
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    mainPanel.add(innLabel, gridBagConstraints);

    innField.setText(Long.toString(editedUser.getInn()));
    innField.setToolTipText("Введите Ваш идентификационный код");
    innField.setMaximumSize(new java.awt.Dimension(120, 25));
    innField.setMinimumSize(new java.awt.Dimension(120, 25));
    innField.setPreferredSize(new java.awt.Dimension(130, 25));
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    mainPanel.add(innField, gridBagConstraints);

    middleNameLabel.setLabelFor(middleNameField);
    middleNameLabel.setText("Отчество");
    middleNameLabel.setToolTipText("Введите Ваше отчество");
    middleNameLabel.setMaximumSize(new java.awt.Dimension(90, 25));
    middleNameLabel.setMinimumSize(new java.awt.Dimension(90, 25));
    middleNameLabel.setPreferredSize(new java.awt.Dimension(90, 25));
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 5;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    mainPanel.add(middleNameLabel, gridBagConstraints);

    middleNameField.setText(editedUser.getMiddleName());
    middleNameField.setToolTipText("Введите Ваше отчество");
    middleNameField.setPreferredSize(new java.awt.Dimension(130, 25));
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 5;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    mainPanel.add(middleNameField, gridBagConstraints);

    emailLabel.setLabelFor(emailField);
    emailLabel.setText("E-mail");
    emailLabel.setToolTipText("Введите Ваш адрес электронной почты");
    emailLabel.setMaximumSize(new java.awt.Dimension(90, 25));
    emailLabel.setMinimumSize(new java.awt.Dimension(90, 25));
    emailLabel.setPreferredSize(new java.awt.Dimension(90, 25));
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    mainPanel.add(emailLabel, gridBagConstraints);

    emailField.setText(editedUser.getEmail());
    emailField.setToolTipText("Введите Ваш адрес электронной почты");
    emailField.setMaximumSize(new java.awt.Dimension(120, 25));
    emailField.setMinimumSize(new java.awt.Dimension(120, 25));
    emailField.setPreferredSize(new java.awt.Dimension(130, 25));
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 4;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    mainPanel.add(emailField, gridBagConstraints);

    addressLabel.setLabelFor(addressField);
    addressLabel.setText("Адрес");
    addressLabel.setToolTipText("Введите адрес Вашей регистрации");
    addressLabel.setMaximumSize(new java.awt.Dimension(90, 25));
    addressLabel.setMinimumSize(new java.awt.Dimension(90, 25));
    addressLabel.setPreferredSize(new java.awt.Dimension(90, 25));
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 6;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    mainPanel.add(addressLabel, gridBagConstraints);

    addressField.setText(editedUser.getAddress());
    addressField.setToolTipText("Введите адрес Вашей регистрации");
    addressField.setPreferredSize(new java.awt.Dimension(110, 25));

    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 6;
    gridBagConstraints.gridwidth = 3;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    mainPanel.add(addressField, gridBagConstraints);

    regButton.setText("Применить");
    regButton.setMargin(new java.awt.Insets(10, 10, 10, 10));
    regButton.setPreferredSize(new java.awt.Dimension(137, 25));
    
    loginField.setInputVerifier(new loginFieldVerifier());
    passwordField.setInputVerifier(new passwordFieldVerifier());
    firstNameField.setInputVerifier(new nameFieldVerifier());
    lastNameField.setInputVerifier(new nameFieldVerifier());
    middleNameField.setInputVerifier(new nameFieldVerifier());
    innField.setInputVerifier(new innFieldVerifier());
    incomeField.setInputVerifier(new incomeFieldVerifier());
    addressField.setInputVerifier(new addressFieldVerifier());
    
    regButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            regButtonActionPerformed(evt);
        }
    });
    addressField.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            regButtonActionPerformed(evt);
        }
    });
    roleLabel.setLabelFor(roleLabel);
    roleLabel.setText("Роль");
    roleLabel.setToolTipText("Выберите роль в системе которую выполняет пользователь");
    roleLabel.setMaximumSize(new java.awt.Dimension(90, 25));
    roleLabel.setMinimumSize(new java.awt.Dimension(90, 25));
    roleLabel.setPreferredSize(new java.awt.Dimension(90, 25));
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 7;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    mainPanel.add(roleLabel, gridBagConstraints);
    
	List<Role> roles = roleDao.getAll();
	for(Role role : roles)roleBox.addItem(role.getTitle());
	roleBox.setSelectedItem(roleDao.getByPK(editedUser.getIdRole()).getTitle());
	if(currentUser == null) roleBox.setEnabled(false);
	
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 7;
    gridBagConstraints.gridwidth = 2;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    mainPanel.add(roleBox, gridBagConstraints);
    
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 8;
    gridBagConstraints.gridwidth = 2;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    mainPanel.add(regButton, gridBagConstraints);

    getContentPane().add(mainPanel);

    statusPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
    statusPanel.setLayout(new java.awt.GridLayout(1, 1));

    statusLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
    statusPanel.add(statusLabel);

    getContentPane().add(statusPanel);
    
    
    if(currentUser.getIdRole() != 1){
	    roleBox.setEnabled(false);
	    firstNameField.setEnabled(false);
	    middleNameField.setEnabled(false);
	    lastNameField.setEnabled(false);
	    innField.setEnabled(false);
	    incomeField.setEnabled(false);
    }
   
    
    pack();
}// </editor-fold>                              
   

    private void regButtonActionPerformed(ActionEvent evt) {  
    	try {
    	if( (editedUser.getUserName().equals(loginField.getText()) ||
    	FieldChecker.checkUserName(loginField.getText())=="")
    	&& FieldChecker.checkPassword(passwordField.getText())==""
    	&& FieldChecker.checkEmail(emailField.getText())==""
    	&& FieldChecker.checkNameField(firstNameField.getText())==""
    	&& FieldChecker.checkNameField(middleNameField.getText())==""
    	&& FieldChecker.checkNameField(lastNameField.getText())==""
    	&& (FieldChecker.checkInn(innField.getText())==""
    	|| Long.parseLong(innField.getText()) == editedUser.getInn())
    	&& FieldChecker.checkIfNull(addressField.getText())==""
    	&& FieldChecker.checkIncome(incomeField.getText())==""
    		){

        		List<Role> roles = roleDao.getAll();
        		Integer idRole;
        		if(roleBox.isEnabled()) idRole = roles.get(roleBox.getSelectedIndex()).getId();
        		else  idRole = 3;
        		
        		if(roleBox.isEnabled()) editedUser.setIdRole(idRole);
        		if(loginField.isEnabled()) editedUser.setUserName(loginField.getText());
        		if(passwordField.isEnabled()) editedUser.setPassword(passwordField.getText());
        		if(firstNameField.isEnabled()) editedUser.setFirstName(DataGenerator.formatName(firstNameField.getText()));
        		if(middleNameField.isEnabled()) editedUser.setMiddleName(DataGenerator.formatName(middleNameField.getText()));
        		if(lastNameField.isEnabled()) editedUser.setLastName(DataGenerator.formatName(lastNameField.getText()));
        		if(innField.isEnabled()) editedUser.setInn(Long.parseLong(innField.getText()));
        		if(incomeField.isEnabled()) editedUser.setIncome(Float.parseFloat(incomeField.getText()));
        		if(addressField.isEnabled()) editedUser.setAddress(addressField.getText());
        		if(emailField.isEnabled()) editedUser.setEmail(emailField.getText());
        	    
        		userDao.update(editedUser);
				JOptionPane.showMessageDialog(statusPanel,
						"Данные обновлены!");
        		this.dispose();

        	
    	}else{
    		String errorText = 
    		    	FieldChecker.checkPassword(passwordField.getText()) + 
    		    	FieldChecker.checkEmail(emailField.getText()) + 
    		    	FieldChecker.checkNameField(firstNameField.getText()) + 
    		    	FieldChecker.checkNameField(middleNameField.getText()) + 
    		    	FieldChecker.checkNameField(lastNameField.getText()) + 
    		    	FieldChecker.checkIfNull(addressField.getText()) + 
    		    	FieldChecker.checkIncome(incomeField.getText());
    		if(!editedUser.getUserName().equals(loginField.getText())) errorText += FieldChecker.checkUserName(loginField.getText());
    		if(Long.parseLong(innField.getText()) != editedUser.getInn()) errorText+= FieldChecker.checkInn(innField.getText());
    		statusLabel.setText(errorText);	
    	}
    	
    	} catch (NumberFormatException | RemoteException | PersistException e) {
			e.printStackTrace();
			new ErrorFrame(e);
		}

    }                                         
    class loginFieldVerifier extends InputVerifier {
        public boolean verify(JComponent input) {
            JTextField tf = (JTextField) input;
            try {
            if(!(editedUser.getUserName().equals(loginField.getText())))
            statusLabel.setText(FieldChecker.checkUserName(loginField.getText()));	
            
				return FieldChecker.checkUserName(tf.getText())=="" || editedUser.getUserName().equals(loginField.getText());
			} catch (RemoteException e) {
				statusLabel.setText(e.getMessage());
				 return false; 
			} 
        }
    }
    class passwordFieldVerifier extends InputVerifier {
        public boolean verify(JComponent input) {
            JTextField textField = (JTextField) input;
            statusLabel.setText(FieldChecker.checkPassword(textField.getText()));	
            return FieldChecker.checkPassword(textField.getText())=="";
        }
    }
    class nameFieldVerifier extends InputVerifier {
        public boolean verify(JComponent input) {
            JTextField textField = (JTextField) input;
            statusLabel.setText(FieldChecker.checkNameField(textField.getText()));	
            return FieldChecker.checkNameField(textField.getText())=="";
        }
    }
    class emailFieldVerifier extends InputVerifier {
        public boolean verify(JComponent input) {
            JTextField textField = (JTextField) input;
            statusLabel.setText(FieldChecker.checkEmail(textField.getText()));	
            return FieldChecker.checkEmail(textField.getText())=="";
        }
    }
    class innFieldVerifier extends InputVerifier {
        public boolean verify(JComponent input) {
            JTextField textField = (JTextField) input;
            if(!(Long.parseLong(innField.getText()) == editedUser.getInn()))
            statusLabel.setText(FieldChecker.checkInn(textField.getText()));	
            return FieldChecker.checkInn(textField.getText())=="" || Long.parseLong(innField.getText()) == editedUser.getInn();
        }
    }
    class incomeFieldVerifier extends InputVerifier {
        public boolean verify(JComponent input) {
            JTextField textField = (JTextField) input;
            statusLabel.setText(FieldChecker.checkIncome(textField.getText()));	
            return FieldChecker.checkIncome(textField.getText())=="";
        }
    }
    class addressFieldVerifier extends InputVerifier {
        public boolean verify(JComponent input) {
            JTextField textField = (JTextField) input;
            statusLabel.setText(FieldChecker.checkIfNull(textField.getText()));	
            return FieldChecker.checkIfNull(textField.getText())=="";
        }
    }



    // Variables declaration - do not modify                     
    private javax.swing.JLabel Title;
    private javax.swing.JTextField addressField;
    private javax.swing.JLabel addressLabel;
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField firstNameField;
    private javax.swing.JLabel firstNameLabel;
    private javax.swing.JTextField incomeField;
    private javax.swing.JLabel incomeLabel;
    private javax.swing.JTextField innField;
    private javax.swing.JLabel innLabel;
    private javax.swing.JTextField lastNameField;
    private javax.swing.JLabel lastNameLabel;
    private javax.swing.JTextField loginField;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTextField middleNameField;
    private javax.swing.JLabel middleNameLabel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JButton regButton;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JLabel roleLabel;
    private JComboBox<String> roleBox;
    private User currentUser;
    private User editedUser;
	private static DAOStubFactory daoStubFactory = MainFrame.getDaoStubFactory();
	//private GenericDao<Account> accountDao = daoStubFactory.getAccountDao();
	//private GenericDao<CreditProgram> creditProgramDao = daoStubFactory.getCreditProgramDao();
	//private GenericDao<CreditRequest> creditRequestDao = daoStubFactory.getCreditRequestDao();
	//private GenericDao<Credits> creditsDao = daoStubFactory.getCreditsDao();
	//private GenericDao<Currency> currencyDao = daoStubFactory.getCurrencyDao();
	private GenericDao<Role> roleDao = daoStubFactory.getRoleDao();
	//private GenericDao<Transaction> transactionDao = daoStubFactory.getTransactionDao();
	private GenericDao<User> userDao = daoStubFactory.getUserDao();
    // End of variables declaration                   
}

