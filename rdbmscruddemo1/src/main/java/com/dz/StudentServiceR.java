package com.dz;

import com.dz.DBOperation;
import com.dz.config.DBConfig;
import com.dz.model.Student;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.List;

public class StudentServiceR {
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

        dbOperation=new DBOperation();

       // dbOperation.display();
     List<Student>  studentList=dbOperation.display();
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
        dbOperation=new DBOperation();

        // dbOperation.display();
        List<Student>  studentList=dbOperation.display();
        for(Student stu:studentList){
            if(stu.getRoll_no()==id){
                System.out.println(stu.toString());
            }
        }


    }//method end

    /**
     *
     * @param
     * @return
     */

    public void  removeById() throws  IOException {
        // TODO Auto-generated method stub
       int roll_no =0;
        dbOperation=new DBOperation();

        System.out.println("enter the student Roll No");
        try{
            roll_no=Integer.parseInt(bufferedReader.readLine());
               }catch (NumberFormatException ex){
               ex.printStackTrace();
           }
        if( dbOperation.removeById(roll_no)){
            System.out.println("data Removed to Table");
        }
        else System.out.println("not such id Found in table ");

    }//method end

    /**
     * \
     *
     * @return
     */
    public void updateById() throws  IOException {
        // TODO Auto-generated method stub
        dbOperation=new DBOperation();
        dbConfig=new DBConfig();
        connection =dbConfig.getConnection();
        System.out.println(connection);
        String name =null;
        int age =0;
        int roll_no=0;
        try{
            roll_no=Integer.parseInt(bufferedReader.readLine());
        }catch (NumberFormatException ex){
            ex.printStackTrace();
        }

        System.out.println("enter the name ");
        try {
            name = bufferedReader.readLine();

            System.err.println("enter the age");
            age = Integer.parseInt(bufferedReader.readLine());
        }
        catch (NumberFormatException e){
            e.printStackTrace();
        }
       if( dbOperation.updateById(roll_no,name,age)){
           System.out.println("data upadted");
       }
       else {
           System.out.println("not updated ");
       }
    }//method end

}//end class
