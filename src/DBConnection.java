import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	 public static Connection getConnection()
	    {
	    	String driver="com.mysql.jdbc.Driver"; //1
	    	String url ="jdbc:mysql://localhost:3306/todolist";
	    	String user="root";
	    	String pass="";
	    	
	    	Connection con =null;
	    	try
	    	{		Class.forName(driver);//2
	    		con  = DriverManager.getConnection(url,user,pass);//3
	    	}
	    	catch(ClassNotFoundException ex)
	    	{    		ex.printStackTrace();
	    		
	    	}
	    	catch(SQLException ex)
	    	{    		ex.printStackTrace();
	    	}
	    	
	    	return con;
	    }

}
