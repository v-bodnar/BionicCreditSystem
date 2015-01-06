package userinterface;

import java.awt.GridBagConstraints;
import java.util.prefs.Preferences;

import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import service.FieldChecker;

public class ClientPrefFrame extends JFrame {
	private static final long serialVersionUID = 3517834437888246948L;
	private JPanel formPanel = new JPanel();
	private JLabel serverLabel = new JLabel();
	private JTextField serverField = new JTextField();
	private JButton saveButton = new JButton();
	private JLabel statusLabel = new JLabel();
	private GridBagConstraints gridBagConstraints;
	private Preferences root = Preferences.userRoot();
	private final Preferences NODE = root.node("/com/bionic/credit_program/client");
	private final String SERVER_IP = NODE.get("SERVER_IP", "localhost");
	
	public ClientPrefFrame(){
		initComponents();
	}
	
	private void initComponents() {
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Настройки");
		setPreferredSize(new java.awt.Dimension(400, 200));
		//getContentPane().setLayout(new java.awt.BorderLayout(5, 5));
		setLocation(360, 100);
		setSize(new java.awt.Dimension(400, 200 ));
		
		formPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
		formPanel.setPreferredSize(new java.awt.Dimension(450, 300));
		formPanel.setRequestFocusEnabled(false);
		formPanel.setLayout(new java.awt.GridBagLayout());
		
		serverLabel.setLabelFor(serverField);
		serverLabel.setText("Имя сервера или IP: ");
		serverLabel.setToolTipText("Введите доменное имя сервера или его ip");
		serverLabel.setName(""); // NOI18N
		serverLabel.setPreferredSize(new java.awt.Dimension(150, 25));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
		formPanel.add(serverLabel, gridBagConstraints);
		
		serverField.setText(SERVER_IP);
		serverField.setToolTipText("Введите название кредитной программы");
		serverField.setName(""); // NOI18N
		serverField.setPreferredSize(new java.awt.Dimension(130, 25));
		serverField.setInputVerifier(new serverFieldVerifier());
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
		formPanel.add(serverField, gridBagConstraints);
		
		
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
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
		formPanel.add(saveButton, gridBagConstraints);
		

		statusLabel.setName(""); // NOI18N
		statusLabel.setPreferredSize(new java.awt.Dimension(150, 25));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
		formPanel.add(statusLabel, gridBagConstraints);
		
		getContentPane().add(formPanel);
		pack();
	}
	
	private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {
		if (FieldChecker.checkIfNull(serverField.getText()) == "") {
			NODE.put("SERVER_IP",serverField.getText());
			JOptionPane.showMessageDialog(this,
					"Настройки сохранены!");
    		this.dispose();
		} else {
			statusLabel.setText(FieldChecker.checkIfNull(serverField.getText()));
		}
	}

	private class serverFieldVerifier extends InputVerifier {
		public boolean verify(JComponent input) {
			JTextField textField = (JTextField) input;
			statusLabel.setText(FieldChecker.checkIfNull(textField.getText()));
			return FieldChecker.checkIfNull(textField.getText()) == "";
		}
	}
	
	public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientPrefFrame().setVisible(true);
            }
        });
	}
}
