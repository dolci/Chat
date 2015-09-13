package V_Client;

import java.net.*;
import java.util.Date;
import java.util.Vector;
import java.io.*;
import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.LEADING;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseAdapter;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.synth.SynthLookAndFeel;
import javax.swing.table.DefaultTableModel;





import java.awt.*;
import java.awt.event.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
//import java.awt.event.MouseAdapter;
public class InterfaceClient extends JFrame {
	
	private JPanel jpPrincipale;
	private JPanel jp1;
	private JTextArea jtAffichage;
	private JScrollPane jtaScrollPane;
	private JTextArea jtaSaisie;
	
	private JScrollPane jtaSScrollPane;
	private JButton jbEnvoier;

	private JPanel jp2;
	private JPanel jp3;
	static JTextField jtLogin;
	private CustumComboBoxDemo jstatut;
	private JButton jbConnecter;
	private JList jlClientConnecter;
	private DefaultListModel listModel;
	private JScrollPane listScrollPane;
	JTable jtabClientConnecter;
    static int sta;
	private JSplitPane js;
	JLabel jlEntete;
	static Client client;
	

	
	public InterfaceClient(){
		setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\Nouveau dossier\\VoIP_App\\src\\Images\\app.jpg"));
		setForeground(new Color(255, 105, 180));
		setFont(new Font("Times New Roman", Font.BOLD, 16));
		initialisation();
		Dimension d=new Dimension();
		this.setContentPane(jpPrincipale);
		d=this.getPreferredSize();
		this.setSize(new Dimension(505, 344));
		this.setTitle("Client");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		this.setVisible(true);
	}

	public void initialisation(){
		
		
		jpPrincipale=new JPanel();
		
		jp1=new JPanel();
		jp1.setBackground(Color.BLACK);
		jtAffichage=new JTextArea(10,27);
		jtaScrollPane=new JScrollPane(jtAffichage);
		//jtAffichage.setEditable(false);
		jtAffichage.setForeground(Color.blue);
		
		
		jtaSaisie=new JTextArea(5,5);
		jtaSScrollPane =new JScrollPane(jtaSaisie);
		jtaSaisie.setForeground(Color.BLACK);
		
		
		jbEnvoier=new JButton("envoyer");
		
		
		jp2=new JPanel();
		jp2.setBackground(Color.BLACK);
		jbConnecter=new JButton("Se connecter");
		jtLogin=new JTextField();
		jtLogin.setFont(new Font("Segoe Script", Font.BOLD, 16));
		jtLogin.setEditable(false);
		jtLogin.selectAll();
		jtLogin.setBounds(5, 5, 121, 30);	
		jtLogin.setColumns(10);
		
		//jstatut.setBounds(136, 5, 53, 20);
		jtLogin.setSelectionStart(0);
		jtaSaisie.setBackground(jtLogin.getSelectionColor());
		
		remplissageTable(null);
		listScrollPane = new JScrollPane(jtabClientConnecter);
        
		remplissageJP1();
        
		remplissageJP2();
		
		
		
		
		js =new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,jp2,jp1);
		
		jstatut = new CustumComboBoxDemo();
	//    ActionEvent e=null;
	//	jstatut.actionPerformed(e);
		GroupLayout gl_jp2 = new GroupLayout(jp2);
		gl_jp2.setHorizontalGroup(
			gl_jp2.createParallelGroup(Alignment.LEADING)
				.addComponent(jp3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_jp2.createSequentialGroup()
					.addGap(6)
					.addComponent(jtLogin, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jstatut, 0, 42, 170)
					.addGap(14))
		);
		gl_jp2.setVerticalGroup(
			gl_jp2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jp2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_jp2.createParallelGroup(Alignment.BASELINE)
						.addComponent(jtLogin, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(jstatut, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jp3, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE))
		);
		jp2.setLayout(gl_jp2);
		js.setOneTouchExpandable(true);
		//js.setBackground(Color.orange);
		//js.setBottomComponent(jp2);
		js.setRequestFocusEnabled(false);
		remplissageJPPrincipale();
		
		jp1.setVisible(false);
		//jbConnecter.setEnabled(false);
		
		jtLogin.addMouseListener(new TraitementSourie());
		
		jbConnecter.addActionListener(new TraitemmentButtonAction());
		jbEnvoier.addActionListener(new TraitemmentButtonAction());
		
		
		
		client=new Client();
		client.setPort(9999);
		client.setInetAddress("localhost");
		
		
		TimeThread t=new TimeThread();
		t.start();
	}
	
	
	public void remplissageJP1(){
		GroupLayout layout = new GroupLayout(jp1);
		jp1.setLayout(layout);
		//jp1.setBackground(Color.orange);
		jp1.setOpaque(true);
		layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
        		.addGroup(layout.createParallelGroup(LEADING)
	        		.addComponent(jtaScrollPane)
	        		.addComponent(jtaSScrollPane)
	        		.addComponent(jbEnvoier))
        		);
        //layout.linkSize(SwingConstants.HORIZONTAL,jtaSScrollPane, jtaScrollPane,jbEnvoier );
        layout.setVerticalGroup(layout.createSequentialGroup()
        		.addComponent(jtaScrollPane)
	        	.addComponent(jtaSScrollPane)
	            .addComponent(jbEnvoier)
                );		
	}//Fin Remplissage jp1
	
	public void remplissageJP2(){
		jp3=new JPanel();
		jp3.setBackground(Color.BLACK);
		GroupLayout layout = new GroupLayout(jp3);
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
					.addComponent(jbConnecter, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(listScrollPane, Alignment.LEADING))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addComponent(jbConnecter)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(listScrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		jp3.setLayout(layout);
		//jp3.setBackground(Color.orange);
		jp3.setOpaque(true);
		layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
	}//Fin Remplissage jp2
	
	public void remplissageJPPrincipale(){
		jlEntete=new JLabel();
		ImageIcon image = new ImageIcon(Toolkit.getDefaultToolkit().getImage("src/images/entete.png"));
		jlEntete.setPreferredSize(new Dimension(image.getIconWidth(), image.getIconHeight()));
		jlEntete.setIcon(image);
		jlEntete.setOpaque(true);
		jlEntete.setBackground(Color.WHITE);
		jlEntete.setForeground(Color.blue);
		jpPrincipale.setLayout(new BorderLayout());
		jpPrincipale.add(jlEntete,BorderLayout.NORTH);
		jpPrincipale.add(js,BorderLayout.CENTER);
	}//Fin Remplissage jp2
		
	public void remplissageTable(String [][]tab){
		
		
		String [][] tDonnee=tab;
		String [] tNom={"Login de Client","Adresse IP"};
		jtabClientConnecter=new JTable(new MonTabModel(tNom,tDonnee));
		jtabClientConnecter.getSelectionModel().setSelectionInterval(0, 0);
		jtabClientConnecter.setPreferredScrollableViewportSize(new Dimension(100, 100));
		jtabClientConnecter.setFillsViewportHeight(true);
		jtabClientConnecter.setSelectionBackground(Color.cyan);
		jtabClientConnecter.setBackground(jtLogin.getSelectionColor());
				
	}
	
	public static void main(String []args){
		
		
		InterfaceClient mi=new InterfaceClient();
		
	}//Fin main
	
	
	class MonTabModel extends DefaultTableModel{
		String []columnNames;
		Object[][]data;
		public MonTabModel(Object []n,Object[][]d){
			super(d,n);
			columnNames=(String[])n;
			data=d;
		}
	}
	
	public class TraitementSourie extends MouseAdapter {

		public void mouseClicked(MouseEvent e) {
			if(e.getSource().equals(jtLogin)){
				jtLogin.selectAll();
			}
		}

	}

	class TraitemmentButtonAction implements ActionListener{
	
		public void actionPerformed(ActionEvent e){
			if(e.getSource().equals(jbConnecter)){
				
				if (e.getActionCommand().indexOf("Se conn")!=-1){
					ImageIcon image = new ImageIcon(Toolkit.getDefaultToolkit().getImage("src/images/edit_f2.png"));
					
					client.setInetAddress("192.168.1.2");
					client.setPort(9999);
					client.setLogin(jtLogin.getText());	
					client.etablissementDeConnexion();
					jtabClientConnecter.getSelectionModel().setSelectionInterval(0, 0);
					System.out.println("1");
				}
				else{
					System.out.println("2");
					client.deconnecter();
					
				}
			}
			else{
				if(e.getSource().equals(jbEnvoier)){
				 //	System.out.println(sta);
					int pos=jtabClientConnecter.getSelectedRow();
					
					if(pos==0){
						client.setAdrIPDestination("tout");
						client.setLoginDestination("tout");
					}
					else
					{
						client.setAdrIPDestination(String.valueOf(pos));
						client.setLoginDestination(String.valueOf(pos));
					}
					if(pos>=0){
						
						client.setMsgE1(jtaSaisie.getText());
						client.setTypeMsgE("s");
						client.envoieMessage();
						
					}
					else{
						ImageIcon image = new ImageIcon(Toolkit.getDefaultToolkit().getImage("src/images/optionpane.warningicon.png"));
						JOptionPane.showMessageDialog(null,"Il faut choisir un client","Attention...",JOptionPane.YES_OPTION,image);
					}
				}
			}
			
				
		}
		
	}//Fin class TraitementClavier
	
	
	
	
	class Client implements Runnable{

	// Déclaration des attributs de la classe 
	//
	   	private int port ;
	    private InetAddress inetAddress ;    
	    private Thread _t ;
		private Socket s ;
	    private String login ;
		
		private String msgRecue;
		private String typeMsgRecue;
		
		private String typeMsgE;
		private String msgE1;
		private String loginDestination="serveur";
		private String ipDestination="localhost";

		private Vector vClient=new Vector();


	    	/** Constructeur de la classe Client */
	    	public Client() {	}
		/**  lire et afficher les messages envoyés par le serveur*/
		public void run(){
			try{
				while(true){
					// Récupérer le flux d'entrée de la socket s (méthode getInputStream() de la classe Socket)
					InputStream is = s.getInputStream ();
	      	  		ObjectInputStream ois = new ObjectInputStream (is);

					
					Object obj = ois.readObject();                
					String msg = (String) obj;
					typeMsgRecue=msg;
					obj = ois.readObject();
	         		if(typeMsgRecue.equalsIgnoreCase("ac"))
	         		{
	         			msgRecue = "connecté à "+(((Date) obj)).toString().substring(11,20);
	         			jtAffichage.setText(jtAffichage.getText()+"\n"+msgRecue);
	         			
	         		}
	         		else
	         		{
	         			
	         			
	         			if(typeMsgRecue.charAt(0)=='i'){
	         				
		         			String[][]donnee = (String[][]) obj;
		         			Object[] objects=new Object[20];
		         			int nb=Integer.parseInt(typeMsgRecue.substring(1));
		         			if(jtabClientConnecter.getRowCount()<nb)
		         			{
			         			for(int i=jtabClientConnecter.getRowCount();i<nb;i++)
			         			{
			         				DefaultTableModel tab = (DefaultTableModel) jtabClientConnecter.getModel();
									objects[0] = (Object)donnee[i][0];
									objects[1] = donnee[i][1];
									tab.addRow(objects);
									
			         			}//fin for
		         			}//fin if
			         			
		         		}
	         			else{
	         				if (typeMsgRecue.charAt(0)=='a'){
			         			String[][]donnee = (String[][]) obj;
			         			Object[] objects=new Object[20];
			         			DefaultTableModel tab = (DefaultTableModel) jtabClientConnecter.getModel();
								objects[0] = (Object)donnee[0][0];
								objects[1] = donnee[0][1];
								tab.addRow(objects);
	         				}
	         				else{
	         					if (typeMsgRecue.charAt(0)=='s'){
				         			String[][]donnee = (String[][]) obj;
				         			Object[] objects=new Object[20];
				         			int nb=Integer.parseInt(typeMsgRecue.substring(1));
				         			DefaultTableModel tab = (DefaultTableModel) jtabClientConnecter.getModel();
									objects[0] = (Object)donnee[0][0];
									objects[1] = donnee[0][1];
									//tab.addRow(objects);
									tab.removeRow(nb);
		         				}
	         					else{
			         				String s=(String)obj;
			         				jtAffichage.setText(jtAffichage.getText()+"\n"+s);
			         			}//fin if
	         				}
	         				
	         			}
	         			
	         			jtabClientConnecter.setSelectionMode(0);
	         			
	         		}
	         		}
				
			}
			catch(Exception e){
				ImageIcon image = new ImageIcon(Toolkit.getDefaultToolkit().getImage("src/images/informationicon.png"));
				JOptionPane.showMessageDialog(null,"Vous etes deconnecter de la serveur","Deconnexion...",JOptionPane.YES_OPTION,image);
			
			}
		}
	 
	
		public void etablissementDeConnexion(){
			try{

				// 1. Créer la socket
		        s = new Socket(getInetAddress(),getPort());
		        
		        _t = new Thread(this);
				_t.start();
				
		        typeMsgE="dc";//demande de connexion
				loginDestination="serveur";
				ipDestination="localhost";
				msgE1="demande de connexion";
				
				envoieMessage();
				jbConnecter.setText("Se deconnecter");
				jp1.setVisible(true);
				js.resetToPreferredSizes();
				jtabClientConnecter.getSelectionModel().setSelectionInterval(0, 0);
				jtabClientConnecter.setSelectionMode(0);
				
			}catch(IOException e){
				ImageIcon image = new ImageIcon(Toolkit.getDefaultToolkit().getImage("src/images/erroricon.png"));
				JOptionPane.showMessageDialog(null,"Erreur de connexion avec le serveur","Connexion...",JOptionPane.YES_NO_OPTION,image);
				jbConnecter.setText("Se connecter");
	        	
			}
		}
		public void envoieMessage(){
			try{

		        OutputStream os;
				ObjectOutputStream oos ;
		        os = s.getOutputStream();
				oos = new ObjectOutputStream(os);
				
				oos.writeObject(typeMsgE);
				oos.writeObject(loginDestination);
				oos.writeObject(ipDestination);
				oos.writeObject(login);
				oos.writeObject(msgE1);
				      	
				jtAffichage.setText(jtAffichage.getText()+"\n"+login+">> "+msgE1);
				if(msgE1.equalsIgnoreCase("bye")){
					deconnecter();				
				}
				jtaSaisie.setText("");	
				
			}catch(IOException e){
				ImageIcon image = new ImageIcon(Toolkit.getDefaultToolkit().getImage("src/images/error.png"));
				JOptionPane.showMessageDialog(null,"Erreur d'emission de message","Erreur",JOptionPane.YES_NO_OPTION,image);
	        
			}
		}
		public void deconnecter(){
			try{
				typeMsgE="dd";//demande de deconnexion
				loginDestination="serveur";
				ipDestination="localhost";
				msgE1="demande de deconnexion";
				envoieMessage();
				
				//jbConnecter.setEnabled(true);
				//vider la table
				jbConnecter.setText("Se connecter");
				jtAffichage.setText(jtAffichage.getText()+"\n  Deconnexion etabli "+new Date().toString().substring(11,20));
				int taille=jtabClientConnecter.getRowCount();
				
				DefaultTableModel tab = (DefaultTableModel) jtabClientConnecter.getModel();
				for(int i=taille-1;i>=0;i--){
					tab.removeRow(i);
				}
				s.close();
			}catch(IOException e){
				ImageIcon image = new ImageIcon(Toolkit.getDefaultToolkit().getImage("src/images/error.png"));
				JOptionPane.showMessageDialog(null,"Erreur de deconnexion...","Erreur",JOptionPane.YES_NO_OPTION,image);
				jbConnecter.setText("Se connecter");
	        	
			}
			
		}
	      /** Méthode permettant d'introduire le numéro de port surveillé par le serveur */          
	   	public void setPort(int _port){
	   		this.port=_port;
	    	}

	   	/** Méthode permettant de retourner la message recue */          
		public String getMsgRecue(){
			return this.msgRecue;
		}
		/** Méthode permettant de mmodifier la message recue */          
		public void setMsgRecue(String s){
			this.msgRecue=s;
		}
		/** Méthode permettant de retourner la message recue */          
		public String getTypeMsgRecue(){
			return this.typeMsgRecue;
		}
		/** Méthode permettant de mmodifier la message recue */          
		public void setTypeMsgRecue(String s){
			this.typeMsgRecue=s;
		}
		/** Méthode permettant de retourner la message recue */          
		public String getTypeMsgE(){
			return this.typeMsgE;
		}
		/** Méthode permettant de mmodifier la message recue */          
		public void setTypeMsgE(String s){
			this.typeMsgE=s;
		}
		/** Méthode permettant de retourner la message envo */          
		public String getMsgE1(){
			return this.msgE1;
		}
		/** Méthode permettant de mmodifier la message  */          
		public void setMsgE1(String s){
			this.msgE1=s;
		}
		
		/** Méthode permettant de retourner le login du destinataire */          
		public String getLoginDestination(){
			return this.loginDestination;
		}
		/** Méthode permettant de mmodifier le login du destinataire */          
		public void setLoginDestination(String s){
			this.loginDestination=s;
		}/** Méthode permettant de retourner l @IP du destinataire */          
		public String getAdrIPDestination(){
			return this.ipDestination;
		}
		/** Méthode permettant de mmodifier l @IP du destinataire */          
		public void setAdrIPDestination(String s){
			this.ipDestination=s;
		}
		/** Méthode permettant de retourner le numéro de port du serveur */          
	    	public int getPort(){
			return this.port;
	    	}

	  	/** Méthode permettant d'introduire le nom ou l'IP du serveur */          
	    	public void setInetAddress (String  _machineName){
	    		try{
	    			inetAddress=InetAddress.getByName(_machineName);
	    		}
	    		catch(Exception e){System.out.println();}
	    		
	    	}


		/** Méthode permettant de retourner l'adresse IP du serveur */ 
	   	public InetAddress getInetAddress(){
	        return this.inetAddress;
	    	}

		/** Méthode permettant d'introduire le login de l'utilisateur */ 
		public void getLogin(){
			System.out.print("donner votre login SVP:");
			//try{
				//this.login=Lire.S();
			//}catch(IOException e){System.out.println(e.getMessage());}
			
		}
		/** Méthode permettant d'introduire le login du client login<>" " */          
		public void setLogin (String  l){
			this.login=l;
		}

	}//Fin Client
	
	class TimeThread extends Thread{
		public void run(){
			Color c= jtLogin.getSelectionColor();
			while(true){
				try{
					jlEntete.setText(new Date().toString());
					if(jtLogin.getText().equalsIgnoreCase("entrer votre login")){
						if(jtLogin.getSelectionColor().equals(c)){
							jtLogin.selectAll();
							jtLogin.setSelectionColor(Color.red);
						}
						else{
							jtLogin.setSelectionColor(c);
							jtLogin.selectAll();
						}
					}
					else{
						if(jtLogin.getText().indexOf(" ")>=0){
							if(jtLogin.getSelectionColor().equals(c)){
								jtLogin.select(jtLogin.getText().indexOf(" "),jtLogin.getText().length());
								jtLogin.setSelectionColor(Color.red);
							}
							else{
								jtLogin.select(jtLogin.getText().indexOf(" "),jtLogin.getText().length());
								jtLogin.setSelectionColor(c);
								
							}
						}
					}
					sleep(1000);
				}catch(Exception e){}
			}
			
		}
	}
}
