import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
/**
 * 
 * @author PURUSHOTTAM
 *
 */
public class ImplOperation  {
      private ResultSet rs=null;
      private PreparedStatement stmt=null;
      private Connection connection;
     // private BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
     private Scanner scanner=new Scanner(System.in);
	ImplOperation(){
		
		try{  
			Class.forName("org.h2.Driver");  
			connection=DriverManager.getConnection(  
					"jdbc:h2:tcp://localhost/~/dz","sa" ,"");
					 
			
			}catch(Exception e){
				System.out.println(e);
				}  
			} 
	
/**
 * 
 * @return true if data inserted 
 * to add data in the data base 
 * @throws SQLException 
 */
	
	public boolean insert()  {
		// TODO Auto-generated method stub
	     int id=0;
	     String name=null;
	     int age=0;
	    		System.out.println("enter the name ");
	    		name=scanner.nextLine();
	    		System.err.println("enter the age");
	    		age=scanner.nextInt();
	    	try {	stmt=connection.prepareStatement("select max(Id) from student " );
	    		ResultSet rs=stmt.executeQuery();
	    		if(rs.next()) {
	    			id=rs.getInt(1)+1;
	    		}
	    	}catch(SQLException e) {
	    		e.printStackTrace();
	    	}
		try{
			stmt=connection.prepareStatement("insert into student (id ,name,age)values (?,?,?)");
		
		stmt.setInt(1, id);
		stmt.setString(2, name);
		stmt.setInt(3, age);
		stmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}//method end 

	/**
	 * to display the record 
	 */
	public void display() {
		// TODO Auto-generated method stub
		try{
			 stmt= connection.prepareStatement("select * from student") ;
			rs=stmt.executeQuery();  
		
		while(rs.next())  {
		System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getInt(3)); 
		}
		connection.close();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}//method end 

	
	public boolean displayById(int id ) {
		// TODO Auto-generated method stub
		try{
			 connection.prepareStatement("select * from student");  
			rs=stmt.executeQuery();  
		while(rs.next()) {
		if(rs.getInt(1)==id) {  
			
		System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getInt(3)); 
		}
		else {
			System.out.println("no Id matched in Data Base ");
		}
		connection.close();
		return true ;
		
		}
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}//method end 

	
	public boolean removeById(int id) {
		// TODO Auto-generated method stub
		
		try{
			connection.prepareStatement("delete from  student  where id="+id);
			stmt.executeUpdate();
		connection.close();
			return true ;
				}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}//method end 

	
	public boolean updateById(int id) {
		// TODO Auto-generated method stub
		String name =null;
		int age =0;
		try{
			connection.createStatement();
			 stmt.setString(1,name);  
			//.executeUpdate
			return true ;
				}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}//method end 

}//class end 
