import com.dz.model.Student;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class Operations {
        private Connection connection=null;
        private  PreparedStatement statement=null;
        private BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
        private Student student;
        private   ResultSet rs=null;
              Operations(){

                       try {
                             Class.forName("com.mysql.jdbc.Driver");
                            } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                              }

                          try {
                           connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?useSSL=false",
                                   "system", "admin123");
                             //statement = connection.createStatement();
                            } catch (SQLException e) {
                                e.printStackTrace();
                           }
                       }//constructor end
    /**
     *
     * @return true if data inserted
     * to add data in the data base
     * @throws SQLException
     */

    public boolean addStudent() throws  IOException {
        // TODO Auto-generated method stub
        int id=0;
        int count=0;
        String name=null;
        int age=0;
        try {
            System.out.println("enter the name ");
            name = bufferedReader.readLine();
            System.err.println("enter the age");
            age = Integer.parseInt(bufferedReader.readLine());
        }catch (NumberFormatException e)
        {
            e.printStackTrace();
        }
          try{
              statement=connection.prepareStatement("select max(Id) from student " );
            ResultSet rs=statement.executeQuery();
            if(rs.next()) {
                id=rs.getInt(1)+1;
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }

        if(age==0){
            return false;
        }

        try{
            statement=connection.prepareStatement("insert into student (id ,name,age)values (?,?,?)");

            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setInt(3, age);
          count=  statement.executeUpdate();
           // System.out.println("data inserted successfully");
        }catch(SQLException e) {
            e.printStackTrace();
        }
        if(count>0){
            return true;
        }
        return false;
    }//method end

    /**
     * to display the record
     */
    public void display() {
        // TODO Auto-generated method stub
        try{
            statement= connection.prepareStatement("select * from student") ;
            rs=statement.executeQuery();

            while(rs.next())  {
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getInt(3));
            }

        }catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }//method end

    /**
     *
     * @param id
     * @return
     */
    public void displayById(int id ) {
        // TODO Auto-generated method stub

        try {
           statement= connection.prepareStatement("select * from student where Id =?");
               statement.setInt(1, id);
            rs = statement.executeQuery();

            if (rs.first()) {

                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getInt(3));
            } else {
                System.out.println("no Id matched in Data Base ");
            }
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
        catch (SQLException  e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }//method end

    /**
     *
     * @param id
     * @return
     */

    public boolean removeById(int id) {
        // TODO Auto-generated method stub
       int count =0;

        try{
            statement=connection.prepareStatement("delete from  student  where id= ?");
            statement.setInt(1,id);
            count= statement.executeUpdate();

        }catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        catch(NullPointerException e){
            System.out.println("problem");
            e.printStackTrace();

        }
        if(count>0){
            return true;
        }

        return false;
    }//method end

    /**
     * \
     * @param id
     * @return
     */
    public boolean updateById(int id) {
        // TODO Auto-generated method stub
        String name ="purushottam";
        int age =32;
        int count=0;
        System.out.println("enter the name ");
        try {
            name = bufferedReader.readLine();

            System.err.println("enter the age");
            age = Integer.parseInt(bufferedReader.readLine());
        }
        catch (IOException e){
            e.printStackTrace();
        }
        try{

            statement=connection.prepareStatement("update  student set name=? ,age=? where id =?");

            statement.setString(1, name);
            statement.setInt(2, age);
            statement.setInt(3, id);
          count=  statement.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        if(count>0){
            return true ;
        }
        return false;
    }//method end

}//end class
