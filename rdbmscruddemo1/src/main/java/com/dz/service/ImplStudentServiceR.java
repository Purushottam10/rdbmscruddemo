package com.dz.service;

import com.dz.config.DBConfig;
import com.dz.dao.ImplStudentDao;
import com.dz.model.Student;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.List;

public class ImplStudentServiceR implements StudentServiceR {
        private Connection connection=null;
        private  PreparedStatement statement=null;
        private BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
        private Student student;
        private DBConfig dbConfig;
        private ImplStudentDao dbOperation;

    /**
     *
     * @return true if data inserted
     * to add data in the data base
     * @throws IOException
     */


    public boolean addStudent() throws  IOException {
        // TODO Auto-generated method stub
        student=new Student();
        dbConfig =new DBConfig();
        boolean status=false;
        connection=dbConfig.getConnection();

         dbOperation =new ImplStudentDao();
        String name=null;
        int age=0;
        try {
            System.out.println("enter the name ");
            name = bufferedReader.readLine();
            System.out.println("enter the age");
            age = Integer.parseInt(bufferedReader.readLine());
        }catch (NumberFormatException e)
        {
            e.printStackTrace();

        }
        try {
            statement = connection.prepareStatement("select max(Id) from student ");
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                student.setRoll_no( rs.getInt(1) + 1);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
     //   student.setRoll_no(id);
        student.setStudentName(name);
        student.setAge(age);
        if(age==0){
            System.out.println("Invalid Input ");
            return  false;
        }
         try{

           status = dbOperation.addstudent( student);
         }catch (NullPointerException e){
             e.printStackTrace();
         }
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

        dbOperation=new ImplStudentDao();

       // dbOperation.display();
     List<Student>  studentList=dbOperation.display();
     if(studentList.isEmpty()){
         System.out.println("table is empty");
     }
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
    public boolean displayById(int id ) {
        // TODO Auto-generated method stub
        dbOperation=new ImplStudentDao();

        // dbOperation.display();
        List<Student>  studentList=dbOperation.display();
        for(Student stu:studentList){
            if(stu.getRoll_no()==id){
                System.out.println(stu.toString());
                return  true;
            }
        }

 return false ;
    }//method end

    /**
     *
     * @param
     * @return
     */

    public void  removeById() throws  IOException {
        // TODO Auto-generated method stub
       int roll_no =0;
        dbOperation=new ImplStudentDao();

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
        dbOperation=new ImplStudentDao();
        dbConfig=new DBConfig();
        connection =dbConfig.getConnection();

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

            System.out.println("enter the age");
            age = Integer.parseInt(bufferedReader.readLine());
        }
        catch (NumberFormatException e){
            e.printStackTrace();
        }if(age==0){
            System.out.println("Not valid Input ");
        }else
       if( dbOperation.updateById(roll_no,name,age)){
           System.out.println("data upadted");
       }
       else {
           System.err.println("Student with ID "+roll_no +" not Exist in Table ");
       }
    }//method end

}//end class
