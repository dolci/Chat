package DataBase;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Connexion_BD {

	/**
	 * @param args
	 */
	 private static String  nomBase="abonne_app";
	 private static String  hostname="localhost";
	 private static String  urlBd = "jdbc:mysql://"+hostname+"/"+nomBase;
	 private static String  user="root";
	 private static String  password="";
     private  Connection connect;
	 
	 
	public  Connection connecterBd()
	{
		if (connect==null)
		{
		    try { //chargement dynamique du driver
			      Class.forName("com.mysql.jdbc.Driver");
			     connect=DriverManager.getConnection(urlBd,user,password );
				 System.out.println("Connexion réalisée !");
			   }
			catch (ClassNotFoundException e)
			{
			System.out.println("Vérifier que la classe du  driver est dans le classpath");e.printStackTrace( );
			} 
		   catch (SQLException e){ 
			System.out.println("Connexion non effectuée !"); e.printStackTrace( ); 
		    }
		}
		return connect;
	   }
	public ResultSet executeRequete(String req) throws SQLException
	{ 
		ResultSet rs=null;
			
		    Statement stmt= connect.createStatement();
		    rs =stmt.executeQuery(req);
		    
		return rs;
	}	
	public Object [][] listeAbonnapp()
	{
		 String rq="SELECT login FROM  `users`";
		 Object [][]tab=null;
		
		 
		 connecterBd();
		 try{
			 PreparedStatement prepare1 = connect.prepareStatement(rq);
	            ResultSet rs=prepare1.executeQuery();
	            rs.last();
	            int n=rs.getRow();
	            System.out.println(n);
	            rs.beforeFirst();
	             tab=new Object[n][1];
	    	 //int rs1=b.updateRequete(rq1);
	        // System.out.println(rs1);
	          int   i=0;
	    	 while(rs.next())
	         {	
	    		 tab[i][0]=rs.getString("login");i++;
	          }
	          
	        }
	     catch (SQLException e1){  e1.printStackTrace( ); }
		 return tab;
	  }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 String rq="SELECT login FROM  `users`";
		 
	      Connexion_BD b=new Connexion_BD();
		
		   
	}

}
