import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ImplOperation operation=new ImplOperation();
		operation.insert();
		/*if (operation.removeById(2))
			System.out.println("data deleted ");
		else 
			System.out.println("not delete");*/
		}

}
