import com.dz.com.dz.DBOperation;
import com.dz.config.DBConfig;
import com.dz.model.Student;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.List;

public class Operations {
        private Connection connection=null;
        private  PreparedStatement statement=null;
        private BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
        private Student student=new Student();
        private   ResultSet rs=null;
        private DBConfig dbConfig;
        private DBOperation dbOperation;

    /**
     *
     * @return true if data inserted
     * to add data in the data base
     * @throws SQLException
     */

    public boolean addStudent() throws  IOException {
        // TODO Auto-generated method stub
        dbConfig =new DBConfig();

        connection=dbConfig.getConnection();
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
        try {
            statement = connection.prepareStatement("select max(Id) from student ");
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1) + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        student.setRoll_no(id);
        student.setStudentName(name);
        student.setAge(age);
          boolean status= dbOperation.addstudent( connection,student);
          if(status){
              return true;
          }
             return false;
    }//method end

    /**
     * to display the record
     */
    public void display() {
        // TODO Auto-generated method stub
        dbConfig=new DBConfig();
        connection =dbConfig.getConnection();
        dbOperation=new DBOperation();

        List<Student> studentList=dbOperation.display(connection);
try {

        for(Student cursor:studentList){

        System.out.println(cursor.toString());
    }
}catch (NullPointerException e){
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
    public boolean updateById(int id) throws  IOException {
        // TODO Auto-generated method stub
        dbConfig=new DBConfig();
        connection =dbConfig.getConnection();
        System.out.println(connection);
        String name =null;
        int age =0;
        int count=0;
        System.out.println("enter the name ");
        try {
            name = bufferedReader.readLine();

            System.err.println("enter the age");
            age = Integer.parseInt(bufferedReader.readLine());
        }
        catch (NumberFormatException e){
            e.printStackTrace();
        }
        if(age==0||name.equals(null)){
            return false;
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
