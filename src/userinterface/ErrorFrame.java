package userinterface;

import java.awt.datatransfer.*;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author bodnar
 */
public class ErrorFrame extends javax.swing.JFrame {


    // Variables declaration - do not modify                     
    private javax.swing.JButton copyButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JTextArea errorText;
    private javax.swing.JScrollPane scrollPane;
    private Throwable error;
	private static Logger log;
	private static final long serialVersionUID = 5468007955256782358L;
	// End of variables declaration   

	
    public ErrorFrame(Throwable error) {
    	log = Logger.getLogger(error.getClass().getName());
    	createLogFiles();
    	this.error = error;
    	log.info(""+error);
        initComponents();
        this.setVisible(true);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        scrollPane = new javax.swing.JScrollPane();
        errorText = new javax.swing.JTextArea();
        copyButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ошибка!");
        setAlwaysOnTop(true);
        getContentPane().setLayout(new java.awt.GridBagLayout());
        

        scrollPane.setPreferredSize(new java.awt.Dimension(380, 280));

        errorText.setText(error.toString() + error.getMessage());
        errorText.setEditable(false);
        errorText.setColumns(20);
        errorText.setLineWrap(true);
        errorText.setRows(5);
        errorText.setWrapStyleWord(true);
        errorText.setPreferredSize(new java.awt.Dimension(380, 270));
        scrollPane.setViewportView(errorText);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(scrollPane, gridBagConstraints);

        copyButton.setText("Копировать");
        copyButton.setPreferredSize(new java.awt.Dimension(100, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(copyButton, gridBagConstraints);

        closeButton.setText("Закрыть");
        closeButton.setPreferredSize(new java.awt.Dimension(100, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(closeButton, gridBagConstraints);
        
        copyButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				StringSelection stringSelection = new StringSelection (error.getMessage());	
				Clipboard clpbrd = Toolkit.getDefaultToolkit ().getSystemClipboard ();
				clpbrd.setContents (stringSelection, null);
			}
		});
        
        closeButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ErrorFrame.this.dispose();
			}
		});

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>                        
	private static void createLogFiles(){
		String home = System.getProperty("user.home");
		File dir = new File(home + "\\Bionic");
		if (!dir.exists()) {
			dir.mkdirs();
		}
		FileHandler fh;
		try {
			
			fh = new FileHandler(home + "\\Bionic\\Error.log");
			log.addHandler(fh);
		} catch (SecurityException | IOException e1) {
			e1.printStackTrace();
			new ErrorFrame(e1);
		}  
		log.setLevel(Level.ALL);
	}
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	Throwable e = new Throwable();
                new ErrorFrame(e);
            }
        });
    }
        
}
