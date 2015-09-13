package V_Serveur;

import java.awt.Color;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;



public class Serveur implements Runnable, Serializable {
	
	/**
	 *  Déclaration des attributs de la classe
	**/
	    private int numPort ;
		private InetAddress inetAddress ;
	    private ServerSocket serveurSocket;
	 	private Thread threadClient;
		Socket socketClient ;
		static String[] logins = new String[20];
	 	Vector clients = new Vector();
		int nbreClients = 0;
		int typeMsgEnvoye;
		String typeMsgRecue;
		private String loginDestination;
		private String ipDestination;
		private String login;
		private String msgRecue;
		
   /**                 ** 
    ** Getter & setter **
    **                 **
    **/	
      public void setListenPort(int numPort)
    	{
    		this.numPort=numPort;
     	}

	  public int getPort()
	    {
    		return numPort ;
	    }
	  public Vector getListeClients()
	  {
			return clients;
	  }
	  public String[] getClientlogin()
	  { return logins;}
	  /**                      ** 
	    **  Démarrer le serveur  **
	    **                      **
	    **/	
	  public void demarerServeur(){
			logins[0]="Tout";
			clients.add("Tout");
			nbreClients++;
			
			try{
				// création d'un objet de type serverSocket associe au numPort =>Le serveur prêt à recevoir des informations
				serveurSocket=new ServerSocket(numPort);
			   }
			catch(IOException e)
			   {
				System.out.println(e.getMessage());
			   }
	      	    System.out.println("Serveur en execution");
                Interface_Serveur.log.setText("Serveur en execution");
	        	try{
				    while(true)
				        {
				    	
					            // Une "socket" associée à cet objet, en utilisant la méthode accept 
					            socketClient=serveurSocket.accept();
					            // Ajouter la nouvelle connexion 
					            clients.add(clients.size(),socketClient);
	                            nbreClients++;
	                           
	                           //  Lancer threadclient 
				             	threadClient = new Thread(this);
				            	threadClient.start();
				         }
	        	   }
	        	catch(Exception e)
	        	{
	            	System.exit(0);
			    }
	 	}
	 
	 
	  public void initialisationTablelient(Socket socket){
			
				String [][]donnee=new String[20][2];
				donnee[0][0]=logins[0];
				donnee[0][1]="Tout";
				int j=1;
				int num=clients.indexOf(socketClient);
				for(int i=1;i<clients.size();i++){
	 				try{
	 					Socket soc;
	 					if(!(soc=(Socket)clients.get(i)).equals(socket)){
	 						
		 					donnee[j][0]=logins[i];
		 					
		 					String adr=soc.getInetAddress().toString();
		 					System.out.println("hello "+adr);
		 					donnee[j][1]=soc.getLocalAddress().toString();
		 					//System.out.println("ok2  "+nbClients);
		 					j++;
		 				
		 						
	 					}//fin if
	 					
	 					
	 				}catch(NullPointerException e){
	 					
	 				}
	 				
	 			}
				try{
					OutputStream os = socket.getOutputStream();
			        ObjectOutputStream oos = new ObjectOutputStream(os);
			        //msg1 (type initialisation du table client(liste des clients connecter))
			        Object obj = "i"+(Object)String.valueOf(nbreClients-1);
			        System.out.println("nbclient-1 = "+(nbreClients-1));
			        oos.writeObject(obj);
			        //msg2 (information)
			        obj = (Object)donnee;
			        oos.writeObject(obj);
			        System.out.println("fin for initialisationTablelient "+logins[clients.indexOf(socket)]+" j= "+j);
		           }
					catch(Exception e){
		        	    System.out.println("Erreur initialisationTableClient "+e);
		        	    
		            }
		}
		
		public void ajouterClient(Socket socket,int num){
			try{
				String [][]donnee=new String[1][2];
				donnee[0][0]=logins[num];
				donnee[0][1]=socket.getLocalAddress().toString();
				int j=1;
				num=clients.indexOf(socket);
				//System.out.println("debut  ajouter "+logins[clients.indexOf(socket)]);
				for(int i=1;i<clients.size();i++)
				{
	 				
	 					Socket soc;
	 					if(!(soc=(Socket)clients.get(i)).equals(socket)){
	 						try{
	 						OutputStream os = soc.getOutputStream();
	 				        ObjectOutputStream oos = new ObjectOutputStream(os);
	 				        Object obj = "a1";
	 				        oos.writeObject(obj);
	 				        //msg2 (information)
	 				        obj = (Object)donnee;
	 				        oos.writeObject(obj);
	 						}
	 						catch(SocketException e)
	 						{
	 							System.out.println("Suppression1 du client **ajouter** i= "+i+" "+logins[i]);
	 							
	 							clients.remove(i);
	 							if(i!=nbreClients-1){
	 								for(int x=i;x<nbreClients-1;x++){
	 									logins[x]=logins[x+1];
	 								}//fin for
	 							}
	 							nbreClients--;
	 							supprimerClient(soc,i);
	 							i--;
	 							
	 						}
	 					}	
	 			}
				
		      }catch(Exception e){System.out.println("Erreur h1 "+e);}
		      
		}
		
		public void supprimerClient(Socket socket,int num){
			try{
				String [][]donnee=new String[1][2];
				donnee[0][0]=logins[num];
				donnee[0][1]=socket.getLocalAddress().toString();
				System.out.println("debut supprimerClient"); 
				for(int i=1;i<clients.size();i++)
				{
	 				
	 					Socket soc;
	 					if(!(soc=(Socket)clients.get(i)).equals(socket))
	 					{
	 						Object obj;
	 						OutputStream os = soc.getOutputStream();
	 				        ObjectOutputStream oos = new ObjectOutputStream(os);
	 				        
	 				        if(i>num){
	 				        	obj = "s"+String.valueOf(num);
	 				        }
	 				        else{
	 				        	obj = "s"+String.valueOf(num-1);
	 				        	
	 				        }
	 				        oos.writeObject(obj);
	 				        //msg2 (information)
	 				        obj = (Object)donnee;
	 				        oos.writeObject(obj);	
	 					}//fin if
	 				
	 			}
				
		      }catch(Exception e){System.out.println("Erreur h1 "+e);}
		      System.out.println("Fin supprimerClient"); 
		}
		
		public void run(){
			try{
				// 1. Retirer la dernière socket du vecteur
				Socket sck=(Socket)clients.lastElement();
				int n=nbreClients;
				         
				while(true){
	           
     
	            	System.out.println("Debut reception");
	            	// 2. Récupérer le flux d'entrée de la socket (méthode getInputStream() de la classe Socket)
	    			InputStream is = sck.getInputStream ();
	    			ObjectInputStream  ois = new ObjectInputStream(is);
	    			Object obj;
	    			//msg1
	    			obj = ois.readObject();
	                typeMsgRecue = (String) obj;

	                //msg2
	                obj = ois.readObject();
	                loginDestination = (String) obj;

	                //msg3
	                obj = ois.readObject();
	                ipDestination = (String) obj;

	                //msg4
	                obj = ois.readObject();
	                login = (String) obj;
	                logins[n-1]=login;
	                
	                //msg5
	                obj = ois.readObject();
	                msgRecue = (String) obj;
	                System.out.println("Fin reception du serveur");
	                System.out.println("typeMsgRecue "+typeMsgRecue+" "+ipDestination);
	                
	                if(typeMsgRecue.equalsIgnoreCase("dc"))
	                {
	                	//demande de connexion
	                	OutputStream os = socketClient.getOutputStream();
	                    ObjectOutputStream oos = new ObjectOutputStream(os);
	                    //msg1 (type msg)
	                    obj = (Object)"ac";//acceptation de la connexion
	                    oos.writeObject(obj);
	                    //msg2 (information)
	                    obj = (Object)new Date();
	                    oos.writeObject(obj); 
	                   //initialisation de tt les table client
	                  /* for(int i=1;i<nbreClients;i++){
	                    	initialisationTablelient((Socket)clients.get(i));
	                    }*/
	                   initialisationTablelient(sck);
	                   ajouterClient (sck,clients.indexOf(sck));
	                    Interface_Serveur.log.setText( Interface_Serveur.log.getText()+"\n "+ login +" connecté "+(((Date) obj)).toString().substring(11,20));
	                    SimpleLogger.logger.log(Level.INFO,login +" connecté "+(((Date) obj)).toString().substring(11,20));
	                    for(int i=0;i<Interface_Serveur.tab.size();i++)
	                    if(Interface_Serveur.tab.get(i).getText().equals(login)==true)
	                    	Interface_Serveur.tab.get(i).setForeground(Color.GREEN);
	                }
	                else{
	                	
	                	if(typeMsgRecue.equalsIgnoreCase("dd"))
	                	 {//demande de deconnexion
	                		
	                		String l=logins[clients.indexOf(sck)];
	                		    System.out.println("debut deconnexion du client **dd** i= "+clients.indexOf(sck)+" "+logins[clients.indexOf(sck)]);
							
	                		    for(int i=0;i<Interface_Serveur.tab.size();i++)
	        	                    if(Interface_Serveur.tab.get(i).getText().equals(l)==true)
	        	                    	Interface_Serveur.tab.get(i).setForeground(Color.BLACK);
	                		    supprimerClient(sck,clients.indexOf(sck));
								if(clients.indexOf(sck)!=clients.size()-1)
								{
									for(int x=clients.indexOf(sck);x<clients.size()-1;x++)
									{
										logins[x]=logins[x+1];
									}
								}
								
								nbreClients--;
								clients.remove(clients.indexOf(sck));
								System.out.println("fin deconnexion du client **dd** ");
		                  Interface_Serveur.log.setText(Interface_Serveur.log.getText()+"\n "+l +" deconnecté "+new Date().toString().substring(11,20));
		                  SimpleLogger.logger.log(Level.INFO,l +" deconnecté "+new Date());
		                 System.out.println("fin deconnexion du client **dd** ");
	                	 }
	                	
	                	
	                		 else
	                		 {
		                	if(ipDestination.equalsIgnoreCase("tout")){
		                		
		                		 for (int i=1;i<nbreClients;i++){
		                             //if(!((Socket)clients.get(i)).equals(_s)){
		                			 if(i!=clients.indexOf(sck)){
		                            	 System.out.println("hello      tout login["+i+"]= "+logins[i]);
		                            	 Socket sck1=(Socket)clients.get(i);
		                                 OutputStream os = sck1.getOutputStream();
		                                 ObjectOutputStream oos = new ObjectOutputStream(os);
		                                 System.out.println("debut ecreture tout");
		                                 //msg1
		                                 obj = (Object)"msg";
		                                 oos.writeObject(obj);
		                                 
		                                 //msg2
		                                 obj = (Object)(login+">> "+msgRecue);
		                                 oos.writeObject(obj);
		                                 if(msgRecue.substring(0, 6).equalsIgnoreCase(" chang"))
		                                 { Interface_Serveur.log.setText(Interface_Serveur.log.getText()+"\n notification =>"+login+" "+msgRecue);
		       		                          SimpleLogger.logger.log(Level.INFO,"notification => "+msgRecue);
		                                 System.out.println("Fin ecreture tout");
		                             }
		                                 else{
		                                	 Interface_Serveur.log.setText(Interface_Serveur.log.getText()+"\n "+ login+">>envoie le << "+msgRecue+">> à tous les users");
				       		                   SimpleLogger.logger.log(Level.INFO,login+">>envoie le << "+msgRecue+">> à tous les users");
		                                 } 
		                			 }
		                		 }
		                	}
		                	
		                		 else{
		                		try{
		                			    int posClientDestination=Integer.parseInt(ipDestination);
		                			    int posClientSource=clients.indexOf(sck);
		                			    Socket sck1;
		                			    System.out.println("leclient "+logins[clients.indexOf(sck)]+" clients.indexOf(sck)= "+(clients.indexOf(sck)));
		                			    if(clients.indexOf(sck)>posClientDestination){
		                			    	sck1=(Socket)clients.get(posClientDestination);
		                			    }
		                			    else{
		                			    	sck1=(Socket)clients.get(posClientDestination+1);
		                			    }
		                			    
		                				OutputStream os = sck1.getOutputStream();
		                                ObjectOutputStream oos = new ObjectOutputStream(os);
		                                //msg1
		                                obj = (Object)"0";
		                                oos.writeObject(obj);
		                                //msg2
		                                obj = (Object)(login+">> "+msgRecue);
		                                oos.writeObject(obj);
		                                 
		                			
		                                Interface_Serveur.log.setText(Interface_Serveur.log.getText()+"\n "+ login+">>envoie le << "+msgRecue+">> à "+logins[ posClientSource+1]);
			       		                SimpleLogger.logger.log(Level.INFO,login+">>envoie le << "+msgRecue+">>  à "+logins[posClientDestination+1]);
		                		}catch(NullPointerException e){System.out.println("Socket null");}
		                	}
	                	}
	                }
	               
	            }


	     		}catch(Exception e){}
		}



	
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		Serveur serveur = new Serveur();
//		 int port=9999;
//         serveur.setListenPort(port);  
//        	serveur.demarerServeur();
//	}

}
/*
 * learn to forgive not to forget
 * 
 * */