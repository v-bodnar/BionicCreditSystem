package userinterface;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import rmi.DAOStubFactory;
import tablemodels.CreditsTableModel;
import tablemodels.UserCreditRequestTableModel;
import entity.Credits;
import dao.GenericDao;
import dao.PersistException;
import entity.CreditProgram;
import entity.CreditRequest;
import entity.Role;
import entity.User;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 
 * @author bodnar
 */
public class UserProfile extends javax.swing.JFrame {
	private static Logger log = Logger.getLogger(UserProfile.class.getName());
	private static final long serialVersionUID = 165605891702026603L;
	private static DAOStubFactory daoStubFactory = MainFrame.getDaoStubFactory();
	//private GenericDao<Account, Integer> accountDao = daoStubFactory.getAccountDao();
	private GenericDao<CreditProgram> creditProgramDao = daoStubFactory.getCreditProgramDao();
	private GenericDao<CreditRequest> creditRequestDao = daoStubFactory.getCreditRequestDao();
	private GenericDao<Credits> creditsDao = daoStubFactory.getCreditsDao();
	//private GenericDao<Currency> currencyDao = daoStubFactory.getCurrencyDao();
	private GenericDao<Role> roleDao = daoStubFactory.getRoleDao();
	//private GenericDao<Transaction> transactionDao = daoStubFactory.getTransactionDao();
	private static GenericDao<User> userDao = daoStubFactory.getUserDao();
	
	/**
	 * Creates new form UserProfile
	 */
	public UserProfile(User currentUser) {
		log.setLevel(Level.OFF);
		this.currentUser = currentUser;
		log.info("Starting to initialize frame components");
		try {
			initComponents();
		} catch (RemoteException | PersistException e) {
			e.printStackTrace();
			new ErrorFrame(e);
		}
		log.info("Finished to initialize frame components");
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 * @throws PersistException 
	 * @throws RemoteException 
	 */
	private void initComponents() throws RemoteException, PersistException {
		java.awt.GridBagConstraints gridBagConstraints;

		userInfoPanel = new javax.swing.JPanel();
		userIconLabel = new javax.swing.JLabel();
		lastNameLabel = new javax.swing.JLabel();
		lastNameValue = new javax.swing.JLabel();
		emailLabel = new javax.swing.JLabel();
		emailValue = new javax.swing.JLabel();
		firstNameLabel = new javax.swing.JLabel();
		firstNameValue = new javax.swing.JLabel();
		incomeLabel = new javax.swing.JLabel();
		incomeValue = new javax.swing.JLabel();
		middleNameLabel = new javax.swing.JLabel();
		middleNameValue = new javax.swing.JLabel();
		roleLabel = new javax.swing.JLabel();
		roleValue = new javax.swing.JLabel();
		innLabel = new javax.swing.JLabel();
		innValue = new javax.swing.JLabel();
		addressLabel = new javax.swing.JLabel();
		addressValue = new javax.swing.JLabel();
		editProfileButton = new javax.swing.JToggleButton();
		statusPanel = new javax.swing.JPanel();
		statusLabel = new javax.swing.JLabel();
		jTabbedPanel = new javax.swing.JTabbedPane();
		creditScrollPanel = new javax.swing.JScrollPane();
		log.info("Starting to initialize creditTableModel");
		creditTableModel = new CreditsTableModel(currentUser);
		log.info("Starting to initialize creditTable");
		creditTable = new javax.swing.JTable(creditTableModel);
		requestScrollPanel = new javax.swing.JScrollPane();
		log.info("Starting to initialize requestTableModel");
		requestTableModel = new UserCreditRequestTableModel(currentUser);
		log.info("Starting to initialize requestTable");
		requestTable = new javax.swing.JTable(requestTableModel);
		
		creditTable.setAutoCreateRowSorter(true);
		requestTable.setAutoCreateRowSorter(true);

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setMaximumSize(new java.awt.Dimension(600, 600));
		setMinimumSize(new java.awt.Dimension(600, 600));
		setPreferredSize(new java.awt.Dimension(600, 600));
		getContentPane().setLayout(new java.awt.GridBagLayout());
		setLocation(360, 100);
		setTitle("Профиль пользователя");

		userInfoPanel.setBorder(javax.swing.BorderFactory
				.createTitledBorder(""));
		userInfoPanel.setPreferredSize(new java.awt.Dimension(570, 220));
		userInfoPanel.setLayout(new java.awt.GridBagLayout());

		userIconLabel.setIcon(new javax.swing.ImageIcon(
				"F:\\Users\\Bionic\\Credit\\personal.png")); // NOI18N
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridheight = 7;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		userInfoPanel.add(userIconLabel, gridBagConstraints);

		lastNameLabel.setText("<html><strong>Фамилия:</strong><html>");
		lastNameLabel.setMaximumSize(new java.awt.Dimension(80, 25));
		lastNameLabel.setMinimumSize(new java.awt.Dimension(80, 25));
		lastNameLabel.setPreferredSize(new java.awt.Dimension(80, 25));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		userInfoPanel.add(lastNameLabel, gridBagConstraints);

		lastNameValue.setText(currentUser.getLastName());
		lastNameValue.setMaximumSize(new java.awt.Dimension(90, 25));
		lastNameValue.setMinimumSize(new java.awt.Dimension(90, 25));
		lastNameValue.setPreferredSize(new java.awt.Dimension(90, 25));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		userInfoPanel.add(lastNameValue, gridBagConstraints);

		emailLabel.setText("<html><strong>E-mail:</strong><html>");
		emailLabel.setMaximumSize(new java.awt.Dimension(80, 25));
		emailLabel.setMinimumSize(new java.awt.Dimension(80, 25));
		emailLabel.setPreferredSize(new java.awt.Dimension(80, 25));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		userInfoPanel.add(emailLabel, gridBagConstraints);

		emailValue.setText(currentUser.getEmail());
		emailValue.setMaximumSize(new java.awt.Dimension(90, 25));
		emailValue.setMinimumSize(new java.awt.Dimension(90, 25));
		emailValue.setPreferredSize(new java.awt.Dimension(90, 25));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 4;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		userInfoPanel.add(emailValue, gridBagConstraints);

		firstNameLabel.setText("<html><strong>Имя:</strong><html>");
		firstNameLabel.setMaximumSize(new java.awt.Dimension(80, 25));
		firstNameLabel.setMinimumSize(new java.awt.Dimension(80, 25));
		firstNameLabel.setPreferredSize(new java.awt.Dimension(80, 25));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		userInfoPanel.add(firstNameLabel, gridBagConstraints);

		firstNameValue.setText(currentUser.getFirstName());
		firstNameValue.setMaximumSize(new java.awt.Dimension(90, 25));
		firstNameValue.setMinimumSize(new java.awt.Dimension(90, 25));
		firstNameValue.setPreferredSize(new java.awt.Dimension(90, 25));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		userInfoPanel.add(firstNameValue, gridBagConstraints);

		incomeLabel.setText("<html><strong>Income:</strong><html>");
		incomeLabel.setMaximumSize(new java.awt.Dimension(80, 25));
		incomeLabel.setMinimumSize(new java.awt.Dimension(80, 25));
		incomeLabel.setPreferredSize(new java.awt.Dimension(80, 25));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		userInfoPanel.add(incomeLabel, gridBagConstraints);

		incomeValue.setText(Float.toString(currentUser.getIncome()));
		incomeValue.setMaximumSize(new java.awt.Dimension(90, 25));
		incomeValue.setMinimumSize(new java.awt.Dimension(90, 25));
		incomeValue.setPreferredSize(new java.awt.Dimension(90, 25));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 4;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		userInfoPanel.add(incomeValue, gridBagConstraints);

		middleNameLabel.setText("<html><strong>Отчество:</strong><html>");
		middleNameLabel.setMaximumSize(new java.awt.Dimension(80, 25));
		middleNameLabel.setMinimumSize(new java.awt.Dimension(80, 25));
		middleNameLabel.setPreferredSize(new java.awt.Dimension(80, 25));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		userInfoPanel.add(middleNameLabel, gridBagConstraints);

		middleNameValue.setText(currentUser.getMiddleName());
		middleNameValue.setMaximumSize(new java.awt.Dimension(90, 25));
		middleNameValue.setMinimumSize(new java.awt.Dimension(90, 25));
		middleNameValue.setPreferredSize(new java.awt.Dimension(90, 25));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		userInfoPanel.add(middleNameValue, gridBagConstraints);

		roleLabel.setText("<html><strong>Роль:</strong><html>");
		roleLabel.setMaximumSize(new java.awt.Dimension(80, 25));
		roleLabel.setMinimumSize(new java.awt.Dimension(80, 25));
		roleLabel.setPreferredSize(new java.awt.Dimension(80, 25));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		userInfoPanel.add(roleLabel, gridBagConstraints);

		roleValue.setText(roleDao.getByPK(currentUser.getIdRole()).getTitle());
		roleValue.setMaximumSize(new java.awt.Dimension(90, 25));
		roleValue.setMinimumSize(new java.awt.Dimension(90, 25));
		roleValue.setPreferredSize(new java.awt.Dimension(90, 25));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 4;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		userInfoPanel.add(roleValue, gridBagConstraints);

		innLabel.setText("<html><strong>Идент. код:</strong><html>");
		innLabel.setMaximumSize(new java.awt.Dimension(80, 25));
		innLabel.setMinimumSize(new java.awt.Dimension(80, 25));
		innLabel.setPreferredSize(new java.awt.Dimension(80, 25));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		userInfoPanel.add(innLabel, gridBagConstraints);

		innValue.setText(Long.toString(currentUser.getInn()));
		innValue.setMaximumSize(new java.awt.Dimension(90, 25));
		innValue.setMinimumSize(new java.awt.Dimension(90, 25));
		innValue.setPreferredSize(new java.awt.Dimension(90, 25));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.gridwidth = 3;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		userInfoPanel.add(innValue, gridBagConstraints);

		addressLabel.setText("<html><strong>Адрес:</strong><html>");
		addressLabel.setMaximumSize(new java.awt.Dimension(80, 25));
		addressLabel.setMinimumSize(new java.awt.Dimension(80, 25));
		addressLabel.setPreferredSize(new java.awt.Dimension(80, 25));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		userInfoPanel.add(addressLabel, gridBagConstraints);

		addressValue.setText(currentUser.getAddress());
		addressValue.setMaximumSize(new java.awt.Dimension(90, 25));
		addressValue.setMinimumSize(new java.awt.Dimension(90, 25));
		addressValue.setPreferredSize(new java.awt.Dimension(90, 25));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.gridwidth = 3;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		userInfoPanel.add(addressValue, gridBagConstraints);

		editProfileButton.setText("Редактировать");
		editProfileButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						editProfileButtonActionPerformed(evt);
					}
				});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 6;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		userInfoPanel.add(editProfileButton, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.weightx = 1;
		gridBagConstraints.weighty = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		getContentPane().add(userInfoPanel, gridBagConstraints);

		statusPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
		statusPanel.setMaximumSize(new java.awt.Dimension(570, 140));
		statusPanel.setMinimumSize(new java.awt.Dimension(570, 140));
		statusPanel.setPreferredSize(new java.awt.Dimension(570, 120));
		statusPanel.setRequestFocusEnabled(false);
		statusPanel.setLayout(new java.awt.GridLayout(1, 1, 5, 5));

		statusLabel.setText("");
		statusLabel.setToolTipText("");
		statusLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5,
				5, 5));
		statusLabel.setPreferredSize(new java.awt.Dimension(560, 110));
		statusPanel.add(statusLabel);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.weightx = 1;
		gridBagConstraints.weighty = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		getContentPane().add(statusPanel, gridBagConstraints);

		jTabbedPanel.setMaximumSize(new java.awt.Dimension(570, 200));
		jTabbedPanel.setMinimumSize(new java.awt.Dimension(570, 200));
		jTabbedPanel.setPreferredSize(new java.awt.Dimension(570, 200));
		
		log.info("Adding CreditTable");
		creditScrollPanel.setViewportView(creditTable);
		jTabbedPanel.addTab("Кредиты", creditScrollPanel);
		log.info("Adding requestTable");
		requestScrollPanel.setViewportView(requestTable);
		jTabbedPanel.addTab("Заявки", requestScrollPanel);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.weightx = 1;
		gridBagConstraints.weighty = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		log.info("Adding jTabbedPanel");
		getContentPane().add(jTabbedPanel, gridBagConstraints);

		MouseListener requestTableMouseListener = new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent event) {
				int row = requestTable.rowAtPoint(event.getPoint());
				int col = requestTable.columnAtPoint(event.getPoint());
				if (col == 1){
					try {
						final CreditProgram clickedProgram = creditProgramDao.getByPK(creditRequestDao.getByPK((Integer)requestTable.getValueAt(row, 0)).getIdCreditProgram());
				        java.awt.EventQueue.invokeLater(new Runnable() {
				        	public void run() {
				        		new CreditProgramFrame(clickedProgram).setVisible(true);
				            }
				        });
					} catch (RemoteException | PersistException e) {
						e.printStackTrace();
						new ErrorFrame(e);
					}

				}
				if (col == 7 && requestTable.getValueAt(row, 5) == null) {
					Integer idRequest = (Integer) requestTable.getValueAt(row,0);
					try {
						CreditRequest selectedRequest = creditRequestDao.getByPK(idRequest);
						JPanel statusPanel = new JPanel();
						Object[] options = { "Да", "Нет" };
						int n = JOptionPane.showOptionDialog(statusPanel,
								"Вы действительно хотите отменить заявку на кредит номер " + idRequest + "?",
								"Внимание!", JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, 
								options, // the titles of buttons
								options[0]); // default button title
						if (n == 0) {
							creditRequestDao.delete(selectedRequest);
						JOptionPane.showMessageDialog(statusPanel,
								"Заявка с номером " + idRequest + " удалена!");
						requestTableModel.formDataArray();
						requestTableModel.fireTableDataChanged();
						}
						
					} catch (RemoteException
							| PersistException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		};
		requestTable.addMouseListener(requestTableMouseListener);
		
		MouseListener creditTableMouseListener = new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent event) {
				int row = requestTable.rowAtPoint(event.getPoint());
				int col = requestTable.columnAtPoint(event.getPoint());
				if (col == 1) {
					try {
						
				        final CreditProgram clickedProgram = creditProgramDao.getByPK(creditsDao.getByPK((Integer)creditTable.getValueAt(row, 0)).getIdCreditProgram());
				        java.awt.EventQueue.invokeLater(new Runnable() {
				        	public void run() {
				        		new CreditProgramFrame(clickedProgram).setVisible(true);
				            }
				        });
					} catch (RemoteException | PersistException e) {
						e.printStackTrace();
						new ErrorFrame(e);
					}	
					
				}

			}
		};
		creditTable.addMouseListener(creditTableMouseListener);

		pack();
	}// </editor-fold>

	private void editProfileButtonActionPerformed(java.awt.event.ActionEvent evt) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new UserProfileEditForm(currentUser, currentUser).setVisible(true);
				UserProfile.this.dispose();
			}
		});
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				User currentUser = null;
				try {
					currentUser = userDao.getByPK(2);
				} catch (RemoteException | PersistException e) {
	    			e.printStackTrace();
	    			new ErrorFrame(e);
				}
				new UserProfile(currentUser).setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify
	private javax.swing.JLabel addressLabel;
	private javax.swing.JLabel addressValue;
	private javax.swing.JScrollPane creditScrollPanel;
	private javax.swing.JTable creditTable;
	private javax.swing.JToggleButton editProfileButton;
	private javax.swing.JLabel emailLabel;
	private javax.swing.JLabel emailValue;
	private javax.swing.JLabel firstNameLabel;
	private javax.swing.JLabel firstNameValue;
	private javax.swing.JLabel incomeLabel;
	private javax.swing.JLabel incomeValue;
	private javax.swing.JLabel innLabel;
	private javax.swing.JLabel innValue;
	private javax.swing.JTabbedPane jTabbedPanel;
	private javax.swing.JLabel lastNameLabel;
	private javax.swing.JLabel lastNameValue;
	private javax.swing.JLabel middleNameLabel;
	private javax.swing.JLabel middleNameValue;
	private javax.swing.JTable requestTable;
	private javax.swing.JScrollPane requestScrollPanel;
	private javax.swing.JLabel roleLabel;
	private javax.swing.JLabel roleValue;
	private javax.swing.JLabel statusLabel;
	private javax.swing.JPanel statusPanel;
	private javax.swing.JLabel userIconLabel;
	private javax.swing.JPanel userInfoPanel;
	private User currentUser;
	private UserCreditRequestTableModel requestTableModel;
	private CreditsTableModel creditTableModel;
	// End of variables declaration
}
