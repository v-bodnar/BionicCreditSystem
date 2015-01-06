package userinterface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import rmi.DAOStubFactory;
import tablemodels.CreditRequestTableModel;
import dao.GenericDao;
import dao.PersistException;
import entity.CreditProgram;
import entity.CreditRequest;
import entity.User;

public class CreditRequestFrame extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4070202082702233797L;
	/**
	 * Creates new form CreditManager
	 */
	public CreditRequestFrame(User currentUser) {
		this.currentUser = currentUser;
		initComponents();
	}

	private void initComponents() {
		scrollPanel = new javax.swing.JScrollPane();
		creditTableModel = new CreditRequestTableModel(currentUser);
		requestTable = new JTable(creditTableModel);

		requestTable.setAutoCreateRowSorter(true);
		headPanel = new javax.swing.JPanel();
		searchField = new javax.swing.JTextField();
		hideConfirmed = new javax.swing.JCheckBox();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Запросы на кредит");
		setBounds(new java.awt.Rectangle(0, 0, 0, 0));
		setPreferredSize(new java.awt.Dimension(600, 600));
		getContentPane().setLayout(new java.awt.BorderLayout(5, 5));
		setLocation(360, 100);

		scrollPanel.setViewportView(requestTable);

		getContentPane().add(scrollPanel, java.awt.BorderLayout.CENTER);

		headPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5,
				5, 5));
		headPanel.setPreferredSize(new java.awt.Dimension(600, 35));
		headPanel.setLayout(new java.awt.BorderLayout());

		searchField.setText("Поиск");
		searchField.setMargin(new java.awt.Insets(5, 5, 5, 5));
		searchField.setMaximumSize(new java.awt.Dimension(200, 25));
		searchField.setMinimumSize(new java.awt.Dimension(200, 25));
		searchField.setPreferredSize(new java.awt.Dimension(195, 25));
		searchField.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				creditTableModel.search(searchField.getText());
			}
		});
		searchField.addFocusListener(new FocusListener() {
		      public void focusGained(FocusEvent e) {
		    	  searchField.setText("");
		      }

			@Override
			public void focusLost(FocusEvent arg0) {
				creditTableModel.search(searchField.getText());
			}
		});
		headPanel.add(searchField, java.awt.BorderLayout.WEST);

		hideConfirmed.setText("Скрыть подтвержденные");
		hideConfirmed
				.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
		hideConfirmed.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				creditTableModel.hideConfirmed(hideConfirmed.isSelected());
			}
		});

		headPanel.add(hideConfirmed, java.awt.BorderLayout.EAST);

		getContentPane().add(headPanel, java.awt.BorderLayout.PAGE_START);

		pack();
		
		   MouseListener tableMouseListener = new MouseAdapter() {
				@Override
			      public void mouseClicked(MouseEvent event) {
				    int row = requestTable.rowAtPoint(event.getPoint());//get mouse-selected row
				    int col = requestTable.columnAtPoint(event.getPoint());//get mouse-selected col 
					if (col == 1) {
						processMouseMotionEvent(event);
			            	Integer requestId = (Integer)requestTable.getValueAt(row, 0);
			            	try {
			            		final User userClicked = userDao.getByPK(creditRequestDao.getByPK(requestId).getIdUser());
			            		java.awt.EventQueue.invokeLater(new Runnable() {
				            		public void run() {
				            			new UserProfile(userClicked).setVisible(true);
				            		}
			            		});
							} catch (RemoteException | PersistException e) {
								new ErrorFrame(e);
							}
			            };
			            if (col == 3){
			            	Integer requestId = (Integer)requestTable.getValueAt(row, 0);
			            	try {
			            		final CreditProgram creditProgramClicked = creditProgramDao.getByPK(creditRequestDao.getByPK(requestId).getIdCreditProgram());
			            		java.awt.EventQueue.invokeLater(new Runnable() {
				            		public void run() {
				            			new CreditProgramFrame(creditProgramClicked).setVisible(true);
				            		}
			            		});
							} catch (RemoteException | PersistException e) {
								new ErrorFrame(e);
							}
			            };
			      }
			   };
			   requestTable.addMouseListener(tableMouseListener);
			   for (int i = 0; i < creditTableModel.getColumnCount(); i++ )
				requestTable.setDefaultRenderer(requestTable.getColumnClass(i), new CellRenderer());
			    requestTable.requestFocusInWindow();
			   
			   
			   

			   
	}// </editor-fold>

    public static class CellRenderer implements TableCellRenderer {

        private static final Border noFocusBorder = new EmptyBorder(1, 1, 1, 1);

        public CellRenderer() {

        }
        

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        	
        	JCheckBox cbox = new JCheckBox();
        	cbox.setLayout(new GridBagLayout());
        	cbox.setMargin(new Insets(0, 0, 0, 0));
        	cbox.setHorizontalAlignment(JLabel.CENTER);
        	cbox.setCursor(new Cursor(Cursor.HAND_CURSOR));
        	
        	JLabel cell = new JLabel();
        	cell.setLayout(new GridBagLayout());
        	cell.setHorizontalAlignment(JLabel.CENTER);
        	
        	if (value instanceof Boolean) {
        		cbox.setSelected((Boolean) value);
                if (!isSelected) {
                	if(table.getValueAt(row,7) == null)cbox.setBackground(Color.PINK);
                } else {
                	cbox.setForeground(table.getSelectionForeground());
                	cbox.setBackground(table.getSelectionBackground());
                }
                return cbox;
            }else if (value instanceof String){
            	cell.setText((String)value);
           	 	cell.setOpaque(true);
            	if (!isSelected) {
            		if(table.getValueAt(row,7) == null)cell.setBackground(Color.PINK);
                } else {
                	 cell.setForeground(table.getSelectionForeground());
                	 cell.setBackground(table.getSelectionBackground());
                }
            	return cell;
            }else if (value instanceof Number){
            	cell.setText(value.toString());
           	 	cell.setOpaque(true);
            	if (!isSelected) {
            		if(table.getValueAt(row,7) == null)cell.setBackground(Color.PINK);
                } else {
                	 cell.setForeground(table.getSelectionForeground());
                	 cell.setBackground(table.getSelectionBackground());
                }
            	return cell;
            }else if (value instanceof Date){
            	DateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm");
            	cell.setText(df.format(value));
           	 	cell.setOpaque(true);
            	if (!isSelected) {
                 	if(table.getValueAt(row,7) == null)cell.setBackground(Color.PINK);
                } else {
                	 cell.setForeground(table.getSelectionForeground());
                	 cell.setBackground(table.getSelectionBackground());
                }
            	return cell;
            }else{
            	cell.setOpaque(true);
            	if (!isSelected) {
                 	if(table.getValueAt(row,7) == null)cell.setBackground(Color.PINK);
                } else {
                	 cell.setForeground(table.getSelectionForeground());
                	 cell.setBackground(table.getSelectionBackground());
                }
            	return cell;
            }
        	

        }
    }

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {


		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				User currentUser = null;
				try {
					currentUser = userDao.getByPK(2);
				} catch (RemoteException | PersistException e) {
					// TODO Auto-generated catch block
					new ErrorFrame(e);
				}
				new CreditRequestFrame(currentUser).setVisible(true);
				
			}
		});
	}

	// Variables declaration - do not modify
	private javax.swing.JPanel headPanel;
	private javax.swing.JCheckBox hideConfirmed;
	private javax.swing.JTable requestTable;
	private javax.swing.JScrollPane scrollPanel;
	private javax.swing.JTextField searchField;
	private User currentUser;
	private CreditRequestTableModel creditTableModel;
	private static DAOStubFactory daoStubFactory = MainFrame.getDaoStubFactory();
	//private GenericDao<Account> accountDao = daoStubFactory.getAccountDao();
	private GenericDao<CreditProgram> creditProgramDao = daoStubFactory.getCreditProgramDao();
	private GenericDao<CreditRequest> creditRequestDao = daoStubFactory.getCreditRequestDao();
	//private GenericDao<Credits> creditsDao = daoStubFactory.getCreditsDao();
	//private GenericDao<Currency> currencyDao = daoStubFactory.getCurrencyDao();
	//private GenericDao<Role> roleDao = daoStubFactory.getRoleDao();
	//private GenericDao<Transaction> transactionDao = daoStubFactory.getTransactionDao();
	private static GenericDao<User> userDao = daoStubFactory.getUserDao();
	
	// End of variables declaration
	
}
