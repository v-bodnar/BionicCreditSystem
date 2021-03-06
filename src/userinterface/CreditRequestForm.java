package userinterface;

import java.rmi.RemoteException;
import java.util.Date;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.GenericDao;
import dao.PersistException;
import entity.CreditProgram;
import entity.CreditRequest;
import entity.User;
import rmi.DAOStubFactory;
import service.DataGenerator;
import service.FieldChecker;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 
 * @author bodnar
 */
public class CreditRequestForm extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4750899432895213082L;
	/**
	 * Creates new form CreditProgramAddForm
	 */
	public CreditRequestForm(User currentUser, CreditProgram currentCreditProgram) {
		this.currentUser = currentUser;
		this.currentCreditProgram = currentCreditProgram;
		initComponents();
	}
	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;

		mainPanel = new javax.swing.JPanel();
		title = new javax.swing.JLabel();
		titleLabel = new javax.swing.JLabel();
		titleValue = new javax.swing.JLabel();
		minSumLabel = new javax.swing.JLabel();
		minSumValue = new javax.swing.JLabel();
		maxSumLabel = new javax.swing.JLabel();
		maxSumValue = new javax.swing.JLabel();
		sumLabel = new javax.swing.JLabel();
		sumField = new javax.swing.JTextField();
		periodLabel = new javax.swing.JLabel();
		periodValue = new javax.swing.JLabel();
		percentLabel = new javax.swing.JLabel();
		percentValue = new javax.swing.JLabel();
		descrLabel = new javax.swing.JLabel();
		descrText = new javax.swing.JTextArea();
		addButton = new javax.swing.JButton();
		statusPanel = new javax.swing.JPanel();
		statusLabel = new javax.swing.JLabel();
		descrScrollPane = new javax.swing.JScrollPane();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Запрос кредитной программы");
		setBounds(new java.awt.Rectangle(0, 0, 0, 0));
		setPreferredSize(new java.awt.Dimension(600, 600));
		setLocation(360, 100);
		getContentPane().setLayout(new java.awt.GridBagLayout());

		mainPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
		mainPanel.setToolTipText("");
		mainPanel.setMaximumSize(new java.awt.Dimension(450, 350));
		mainPanel.setMinimumSize(new java.awt.Dimension(450, 350));
		mainPanel.setPreferredSize(new java.awt.Dimension(580, 350));
		mainPanel.setRequestFocusEnabled(false);
		mainPanel.setLayout(new java.awt.GridBagLayout());

		title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		title.setText("<html><h3>Добавление кредитной программы</h3></html>");
		title.setToolTipText("");
		title.setPreferredSize(new java.awt.Dimension(460, 25));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
		mainPanel.add(title, gridBagConstraints);

		titleLabel.setLabelFor(titleValue);
		titleLabel.setText("Название кредитной программы");
		titleLabel.setMaximumSize(new java.awt.Dimension(250, 25));
		titleLabel.setMinimumSize(new java.awt.Dimension(250, 25));
		titleLabel.setName(""); // NOI18N
		titleLabel.setPreferredSize(new java.awt.Dimension(250, 25));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
		mainPanel.add(titleLabel, gridBagConstraints);

		titleValue.setText(currentCreditProgram.getTitle());
		titleValue.setMaximumSize(new java.awt.Dimension(120, 25));
		titleValue.setMinimumSize(new java.awt.Dimension(120, 25));
		titleValue.setName(""); // NOI18N
		titleValue.setPreferredSize(new java.awt.Dimension(130, 25));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
		mainPanel.add(titleValue, gridBagConstraints);

		minSumLabel.setLabelFor(minSumValue);
		minSumLabel.setText("Минимальная сумма кредита");
		minSumLabel.setMaximumSize(new java.awt.Dimension(250, 25));
		minSumLabel.setMinimumSize(new java.awt.Dimension(250, 25));
		minSumLabel.setPreferredSize(new java.awt.Dimension(250, 25));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
		mainPanel.add(minSumLabel, gridBagConstraints);

		minSumValue.setText(Float.toString(currentCreditProgram.getMinSum()));
		minSumValue.setMaximumSize(new java.awt.Dimension(120, 25));
		minSumValue.setMinimumSize(new java.awt.Dimension(120, 25));
		minSumValue.setPreferredSize(new java.awt.Dimension(130, 25));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
		mainPanel.add(minSumValue, gridBagConstraints);

		maxSumLabel.setLabelFor(maxSumValue);
		maxSumLabel.setText("Максимальная сумма кредита");
		maxSumLabel.setMaximumSize(new java.awt.Dimension(250, 25));
		maxSumLabel.setMinimumSize(new java.awt.Dimension(250, 25));
		maxSumLabel.setPreferredSize(new java.awt.Dimension(250, 25));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
		mainPanel.add(maxSumLabel, gridBagConstraints);

		maxSumValue.setText(Float.toString(currentCreditProgram.getMaxSum()));
		maxSumValue.setPreferredSize(new java.awt.Dimension(130, 25));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
		mainPanel.add(maxSumValue, gridBagConstraints);
		
		sumLabel.setLabelFor(sumField);
		sumLabel.setText("Желаемая сумма кредита");
		sumLabel.setToolTipText("Введите желаемую сумму кредита");
		sumLabel.setMaximumSize(new java.awt.Dimension(250, 25));
		sumLabel.setMinimumSize(new java.awt.Dimension(250, 25));
		sumLabel.setPreferredSize(new java.awt.Dimension(250, 25));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
		mainPanel.add(sumLabel, gridBagConstraints);

		sumField.setPreferredSize(new java.awt.Dimension(130, 25));
		sumField.setToolTipText("Введите желаемую сумму кредита");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
		mainPanel.add(sumField, gridBagConstraints);

		periodLabel.setLabelFor(periodValue);
		periodLabel.setText("Период, мес");
		periodLabel.setMaximumSize(new java.awt.Dimension(250, 25));
		periodLabel.setMinimumSize(new java.awt.Dimension(250, 25));
		periodLabel.setPreferredSize(new java.awt.Dimension(250, 25));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 6;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
		mainPanel.add(periodLabel, gridBagConstraints);

		periodValue.setText(Integer.toString(currentCreditProgram.getPeriod()));
		periodValue.setPreferredSize(new java.awt.Dimension(130, 25));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 6;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
		mainPanel.add(periodValue, gridBagConstraints);

		percentLabel.setLabelFor(percentValue);
		percentLabel.setText("Процентная ставка");
		percentLabel.setMaximumSize(new java.awt.Dimension(250, 25));
		percentLabel.setMinimumSize(new java.awt.Dimension(250, 25));
		percentLabel.setPreferredSize(new java.awt.Dimension(250, 25));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 7;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
		mainPanel.add(percentLabel, gridBagConstraints);

		percentValue.setText(Float.toString(currentCreditProgram.getYearPercent()));
		percentValue.setMaximumSize(new java.awt.Dimension(120, 25));
		percentValue.setMinimumSize(new java.awt.Dimension(120, 25));
		percentValue.setPreferredSize(new java.awt.Dimension(130, 25));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 7;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
		mainPanel.add(percentValue, gridBagConstraints);

		descrLabel.setLabelFor(descrText);
		descrLabel.setText("Описание");
		descrLabel.setMaximumSize(new java.awt.Dimension(250, 25));
		descrLabel.setMinimumSize(new java.awt.Dimension(250, 25));
		descrLabel.setPreferredSize(new java.awt.Dimension(250, 25));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 8;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
		mainPanel.add(descrLabel, gridBagConstraints);
		
		descrScrollPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(
				2, 2, 2, 2));
		
		descrText.setText(currentCreditProgram.getFullDescription());
		descrText.setColumns(20);
		descrText.setRows(5);
		descrText.setLineWrap(true);
		descrText.setWrapStyleWord(true);
		descrText.setEnabled(false);
		descrText.setToolTipText("Описание кредитной программы");
		descrText.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		descrText.setMargin(new java.awt.Insets(0, 0, 0, 0));
		descrScrollPane.setViewportView(descrText);
		
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 8;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		mainPanel.add(descrScrollPane, gridBagConstraints);

		addButton.setText("Добавить");
		addButton.setMargin(new java.awt.Insets(10, 10, 10, 10));
		addButton.setPreferredSize(new java.awt.Dimension(137, 25));
		addButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addButtonActionPerformed(evt);
			}
		});

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 9;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
		mainPanel.add(addButton, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		getContentPane().add(mainPanel, gridBagConstraints);

		statusPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
		statusPanel.setPreferredSize(new java.awt.Dimension(580, 200));
		statusPanel.setLayout(new java.awt.GridLayout(1, 1));

		statusLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
		statusPanel.add(statusLabel);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(5, 2, 5, 2);
		getContentPane().add(statusPanel, gridBagConstraints);
		sumField.setInputVerifier(new sumFieldVerifier());


		pack();
	}// </editor-fold>

	private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {
		Integer id = null;
		Date now = new Date();

		if (FieldChecker.checkRequestSum(sumField.getText(), currentCreditProgram.getMinSum(),currentCreditProgram.getMaxSum()) == "") {

        	try {
        		id = creditRequestDao.getNextPK();
        		CreditRequest creditRequest = new CreditRequest(id, 1, currentCreditProgram.getId(), currentUser.getId(), false, DataGenerator.getCardNumber(currentUser.getInn()), Float.parseFloat(sumField.getText()), now, null,0);
        		creditRequestDao.persist(creditRequest);
				JOptionPane.showMessageDialog(statusPanel,
						"Ваш запрос кредитной программы отправлен с номером: " + id);
        		this.dispose();
        	} catch (NumberFormatException | RemoteException | PersistException e) {
    			e.printStackTrace();
    			new ErrorFrame(e);
    		}
			
		} else {
			statusLabel.setText(FieldChecker.checkRequestSum(sumField.getText(), currentCreditProgram.getMinSum(),currentCreditProgram.getMaxSum()));
		}

	}

	class sumFieldVerifier extends InputVerifier {
		public boolean verify(JComponent input) {
			JTextField textField = (JTextField) input;
			statusLabel.setText(FieldChecker.checkRequestSum(textField.getText(), currentCreditProgram.getMinSum(),currentCreditProgram.getMaxSum()));
			return FieldChecker.checkRequestSum(textField.getText(), currentCreditProgram.getMinSum(),currentCreditProgram.getMaxSum()) == "";
		}
	}

	// Variables declaration - do not modify
	
	private javax.swing.JPanel mainPanel;
	private javax.swing.JLabel title;
	private javax.swing.JLabel titleLabel;
	private javax.swing.JLabel titleValue;
	private javax.swing.JLabel minSumValue;
	private javax.swing.JLabel minSumLabel;
	private javax.swing.JLabel maxSumValue;
	private javax.swing.JLabel maxSumLabel;
	private javax.swing.JTextField sumField;
	private javax.swing.JLabel sumLabel;
	private javax.swing.JLabel periodValue;
	private javax.swing.JLabel periodLabel;
	private javax.swing.JLabel percentValue;
	private javax.swing.JLabel percentLabel;
	private javax.swing.JLabel descrLabel;
	private javax.swing.JTextArea descrText;
	private javax.swing.JButton addButton;
	private javax.swing.JLabel statusLabel;
	private javax.swing.JPanel statusPanel;
	private javax.swing.JScrollPane descrScrollPane;
	private CreditProgram currentCreditProgram;
	private User currentUser;
	private static DAOStubFactory daoStubFactory = MainFrame.getDaoStubFactory();
	//private GenericDao<Account> accountDao = daoStubFactory.getAccountDao();
	//private GenericDao<CreditProgram> creditProgramDao = daoStubFactory.getCreditProgramDao();
	private GenericDao<CreditRequest> creditRequestDao = daoStubFactory.getCreditRequestDao();
	//private GenericDao<Credits> creditsDao = daoStubFactory.getCreditsDao();
	//private GenericDao<Currency> currencyDao = daoStubFactory.getCurrencyDao();
	//private GenericDao<Role> roleDao = daoStubFactory.getRoleDao();
	//private GenericDao<Transaction> transactionDao = daoStubFactory.getTransactionDao();
	//private GenericDao<User> userDao = daoStubFactory.getUserDao();
}
