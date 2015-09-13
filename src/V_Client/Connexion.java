package V_Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;



import java.awt.Toolkit;
import java.awt.Label;
import java.awt.Dialog.ModalExclusionType;

public class Connexion extends JFrame {

	private JPanel contentPane;
	private JTextField jtlogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Connexion frame = new Connexion();
					UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
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
	public Connexion() {
		setResizable(false);
		setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
		setTitle("Connexion ");
		setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\Nouveau dossier\\VoIP_App\\src\\Images\\app.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 284, 118);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 255));
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		jtlogin = new JTextField();
		jtlogin.setText("taper login");
		
		jtlogin.setBounds(10, 32, 124, 20);
		contentPane.add(jtlogin);
		jtlogin.setColumns(10);
		jtlogin.selectAll();
		jtlogin.setSelectionStart(0);
		//jtaSaisie.setBackground(jtlogin.getSelectionColor());
		JButton btnNewButton = new JButton("Connecter");
		btnNewButton.setIcon(null);
		jtlogin.setSelectionColor(Color.green);
		jtlogin.addMouseListener(new TraitementSourie());
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				InterfaceClient iclt=new InterfaceClient();
				iclt.setTitle(jtlogin.getText());
				iclt.client.setInetAddress("localhost");
				iclt.client.setPort(9999);
				iclt.client.setLogin(jtlogin.getText());
				iclt.client.etablissementDeConnexion();
				iclt.jtLogin.setText(jtlogin.getText());
				iclt.jtabClientConnecter.getSelectionModel().setSelectionInterval(0, 0);
				Connexion.this.setVisible(false);
				iclt.show();
				
			}
		});
		btnNewButton.setBounds(144, 31, 96, 23);
		contentPane.add(btnNewButton);
	}
	public class TraitementSourie extends MouseAdapter {

		public void mouseClicked(MouseEvent e) {
			if(e.getSource().equals(jtlogin)){
				jtlogin.selectAll();
			}
		}

	}
}
