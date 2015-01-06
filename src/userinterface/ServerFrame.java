package userinterface;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.Date;

import javax.swing.ImageIcon;

import dao.GenericDao;
import dao.PersistException;
import entity.User;
import rmi.DAOStubFactory;
import entity.Account;
import entity.CreditProgram;
import entity.CreditRequest;
import entity.Credits;
import entity.Currency;
import entity.Role;
import server.Server;
import entity.Transaction;

public class ServerFrame extends javax.swing.JFrame{
	
	private static final long serialVersionUID = 5781121447898929200L;
	private static final String SEP = "/";
	private final ImageIcon dbDataAddIcon = new ImageIcon(getClass().getResource(SEP + "images" + SEP + "db_create_icon.png"));
	private final ImageIcon dbUploadIcon = new ImageIcon(getClass().getResource(SEP + "images" + SEP + "db_upload_icon.png"));
	private final ImageIcon prefIcon = new ImageIcon(getClass().getResource(SEP + "images" + SEP + "pref_icon.png"));
	private final ImageIcon logoIcon = new ImageIcon(getClass().getResource(SEP + "images" + SEP + "credit.png"));

	/**
	 * Creates new form MainFrame
	 */
	public ServerFrame() {
		initComponents();
	}
	
	public void setStatusLabel(final String status) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	statusLabel.setText(status);
            }
        });
	}



	private void initComponents() {
		logoImage = new javax.swing.JLabel();
		authPanel = new javax.swing.JPanel();
		createTablesButton = new javax.swing.JLabel();
		prefButton = new javax.swing.JLabel();
		addDataButton = new javax.swing.JLabel();
		statusPanel = new javax.swing.JPanel();
		statusLabel = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Credit Manager");
		setBounds(new java.awt.Rectangle(1, 1, 1, 1));
		setMinimumSize(new java.awt.Dimension(200, 600));
		setLocation(100,100);
		setResizable(false);

		logoImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		logoImage.setIcon(logoIcon); // NOI18N
		logoImage.setToolTipText("");
		getContentPane().add(logoImage, java.awt.BorderLayout.PAGE_START);

		authPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"Главное меню",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION, null,
				new java.awt.Color(153, 153, 153)));
		authPanel.setMaximumSize(new java.awt.Dimension(230, 300));
		authPanel.setMinimumSize(new java.awt.Dimension(230, 300));
		authPanel.setPreferredSize(new java.awt.Dimension(230, 250));
		authPanel.setLayout(new java.awt.GridLayout(7, 1, 5, 5));

		createTablesButton.setIcon(dbDataAddIcon); // NOI18N
		createTablesButton
				.setText("<html><a href=\"\"><h3>Создать структуру</h3></a></html>");
		createTablesButton.setToolTipText("");
		createTablesButton.setCursor(new java.awt.Cursor(
				java.awt.Cursor.HAND_CURSOR));
		createTablesButton.setMinimumSize(new java.awt.Dimension(230, 35));
		createTablesButton.setPreferredSize(new java.awt.Dimension(230, 35));
		createTablesButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
        		Thread createTables = new Thread(new Runnable() {
        			@Override
        			public void run() {
        				if(Server.getDaoStubFactory() != null) Server.createTables();
        			}
        		});
        		createTables.start();
            }
        });
		authPanel.add(createTablesButton);

		prefButton.setIcon(prefIcon); // NOI18N
		prefButton.setText("<html><a href=\"\"><h3>Настройки</h3></a></html>");
		prefButton.setToolTipText("");
		prefButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		authPanel.add(prefButton);

		addDataButton.setIcon(dbUploadIcon); // NOI18N
		addDataButton
				.setText("<html><a href=\"\"><h3>Наполнить таблицы</h3></a></html>");
		addDataButton.setToolTipText("");
		addDataButton
				.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		addDataButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
        		Thread createTables = new Thread(new Runnable() {
        			@Override
        			public void run() {
        				if(Server.getDaoStubFactory() != null) Server.addTestData();
        			}
        		});
        		createTables.start();
            }
        });
		authPanel.add(addDataButton);

		getContentPane().add(authPanel, java.awt.BorderLayout.CENTER);

		statusPanel
				.setBorder(javax.swing.BorderFactory.createTitledBorder(" "));
		statusPanel.setMaximumSize(null);
		statusPanel.setPreferredSize(new java.awt.Dimension(230, 200));
		statusPanel.setLayout(new java.awt.GridLayout(1, 1, 5, 5));
		statusPanel.add(statusLabel);
		getContentPane().add(statusPanel, java.awt.BorderLayout.PAGE_END);
		
        prefButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                    	new ServerPrefFrame().setVisible(true);
                    }
                });
            }
        });
        
		pack();
	}// </editor-fold>

	// Variables declaration - do not modify
	private javax.swing.JLabel addDataButton;
	private javax.swing.JPanel authPanel;
	private javax.swing.JLabel createTablesButton;
	private javax.swing.JLabel logoImage;
	private javax.swing.JLabel prefButton;
	private javax.swing.JLabel statusLabel;
	private javax.swing.JPanel statusPanel;
	// End of variables declaration
}
