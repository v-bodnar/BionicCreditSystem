package userinterface;

import javax.swing.JTable;
import entity.CreditProgram;

/**
 * 
 * @author bodnar
 */
public class CreditProgramFrame extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2848100919151605088L;
	/**
	 * Creates new form CreditRequestForm
	 */
	public CreditProgramFrame(CreditProgram currentProgram) {
		this.currentProgram = currentProgram;
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;

		programsTablePanel = new javax.swing.JScrollPane();
		// tableModel = new CreditProgramTableModel();
		
		descriptionPanel = new javax.swing.JPanel();
		descriptionLabel = new javax.swing.JLabel();
		descriptionScrollPanel = new javax.swing.JScrollPane();
		descriptionText = new javax.swing.JTextArea();
		// buttonPanel = new javax.swing.JPanel();

		Object[][] tableData = new Object[][] { { currentProgram.getId(), currentProgram.getTitle(), currentProgram.getMinSum(), currentProgram.getMaxSum(), currentProgram.getPeriod(), currentProgram.getYearPercent(),
				null } };
		String[] colNames = { "Номер", "Название", "Минимальная сумма, грн",
				"Максимальная сумма, грн", "Срок, мес.", "Процентная ставка" };
		programTable = new JTable(tableData, colNames);
		descriptionText.setLineWrap(true);
		descriptionText.setWrapStyleWord(true);
		descriptionText.setText(currentProgram.getFullDescription());
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Кредитные программы");
		setPreferredSize(new java.awt.Dimension(600, 600));
		getContentPane().setLayout(new java.awt.GridBagLayout());

		programsTablePanel.setPreferredSize(new java.awt.Dimension(580, 225));

		programsTablePanel.setViewportView(programTable);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		getContentPane().add(programsTablePanel, gridBagConstraints);
		setLocation(360, 100);

		descriptionPanel.setPreferredSize(new java.awt.Dimension(580, 250));
		descriptionPanel.setLayout(new java.awt.GridBagLayout());

		descriptionLabel
				.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		descriptionLabel.setText("Описание");
		descriptionLabel.setPreferredSize(new java.awt.Dimension(580, 25));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		descriptionPanel.add(descriptionLabel, gridBagConstraints);

		descriptionScrollPanel
				.setPreferredSize(new java.awt.Dimension(555, 143));

		descriptionScrollPanel.setViewportView(descriptionText);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		descriptionPanel.add(descriptionScrollPanel, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		getContentPane().add(descriptionPanel, gridBagConstraints);

		// buttonPanel.setPreferredSize(new java.awt.Dimension(580, 40));

		// gridBagConstraints = new java.awt.GridBagConstraints();
		// gridBagConstraints.gridx = 0;
		// gridBagConstraints.gridy = 2;
		// gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		// gridBagConstraints.weightx = 1.0;
		// gridBagConstraints.weighty = 1.0;
		// getContentPane().add(buttonPanel, gridBagConstraints);



		pack();
	}// </editor-fold>

	// Variables declaration - do not modify
	// private javax.swing.JPanel buttonPanel;
	private javax.swing.JLabel descriptionLabel;
	private javax.swing.JPanel descriptionPanel;
	private javax.swing.JScrollPane descriptionScrollPanel;
	private javax.swing.JTextArea descriptionText;
	private javax.swing.JTable programTable;
	private javax.swing.JScrollPane programsTablePanel;
	private CreditProgram currentProgram;
	//private CreditProgramTableModel tableModel;
	// End of variables declaration
}