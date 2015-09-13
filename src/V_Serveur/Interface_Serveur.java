package V_Serveur;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.ScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import DataBase.Connexion_BD;
import V_Serveur.SimpleLogger;
import V_Serveur.Serveur;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import javax.swing.JTable;
import java.awt.event.WindowAdapter;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.util.ArrayList;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JScrollBar;

public class Interface_Serveur extends JFrame {

	private JPanel contentPane;
    static JTextArea log;
    private Serveur serveur;
    static JTable table;
    static DefaultTableModel model;
   static ArrayList<JTextField> tab;
    JScrollPane scrollPane_1;
    private JTextField textField;
    private JTextField textField_1;
    private JScrollPane scrollPane;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface_Serveur frame = new Interface_Serveur();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Interface_Serveur() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				 Connexion_BD b=new Connexion_BD();
		          Object col[] = { "login"};
			      Object cont[][] = b.listeAbonnapp();
			      tab=new ArrayList<JTextField> (); int x=5;
			    for(int j=0;j<cont.length;j++)	
			    	 {  
			    	    textField = new JTextField();
					     // textField.setForeground(Color.GREEN);
					    textField.setEditable(false);
					    textField.setFont(new Font("Times New Roman", Font.BOLD, 12));
						textField.setBounds(5, x, 100, 20);
					    textField.setText(cont[j][0].toString());
					    scrollPane.add(textField);
			         	x+=25;
			         	
			    	    tab.add(textField);
			    	 }
			    }
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\Nouveau dossier\\VoIP_App\\src\\Images\\serv.png"));
		setTitle("Serveur");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 525, 448);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnStart = new JButton("Start");
		btnStart.setBounds(11, 9, 89, 28);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				 serveur = new Serveur();
				 int port=9999;
		          serveur.setListenPort(port);
		          SimpleLogger.init();
		          new Thread () {
		        	  @Override public void run () {
		        		  serveur.demarerServeur();
		        	  }
		        	}.start();
		        	
		          
		          
			}
		         
		});
		contentPane.setLayout(null);
		contentPane.add(btnStart);
		
		
		
		 log = new JTextArea();
		
		log.setEditable(false);
		log.setBounds(11, 48, 266, 204);
	    scrollPane_1 = new JScrollPane(log);
	    scrollPane_1.setBounds(5, 43, 299, 319);
		contentPane.add(scrollPane_1);
		
		
		
		JSeparator separator = new JSeparator();
		separator.setBounds(337, 43, 5, 324);
		separator.setOrientation(SwingConstants.VERTICAL);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(314, 43, 5, 324);
		separator_1.setOrientation(SwingConstants.VERTICAL);
		contentPane.add(separator_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(352, 43, 134, 319);
		contentPane.add(scrollPane);
			
		
		
		
		
		
		
		
	}
}
