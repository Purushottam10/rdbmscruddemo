package com.dz;

import com.dz.config.DBConfig;
import com.dz.model.Student;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class DBOperation {
    private DBConfig dbConfig;
    private PreparedStatement statement=null;

    public boolean addstudent(Connection connection ,Student student) {

        int id =0;
        int count =0;

        if (student.getAge() == 0) {
            return false;
        }
        try{
            statement=connection.prepareStatement("insert into student (id ,name,age)values (?,?,?)");

            statement.setInt(1, id);
            statement.setString(2, student.getStudentName());
            statement.setInt(3, student.getAge());
            count=  statement.executeUpdate();
            // System.out.println("data inserted successfully");
            if(count>0){
                return true;
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
    /*   finally {
            connection.close();
        }*/

        return false;

    }//method end

    /**
     * Display record
     */
    public  List display(){
        dbConfig=new DBConfig();
        Connection connection= dbConfig.getConnection();
        Student student;
        List<Student> studentList=new ArrayList();
        try{
            statement= connection.prepareStatement("select * from student") ;
         ResultSet   rs=statement.executeQuery();

            while (rs.next())  {
                student =new Student();
                student.setRoll_no(rs.getInt(1));
                student.setStudentName(rs.getString(2));
                student.setAge(rs.getInt(3));

              studentList.add(student);

            }
            return  studentList;
        }catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param id
     * @return
     */

    public boolean removeById(int id) {
        // TODO Auto-generated method stub
        int count =0;
        dbConfig=new DBConfig();
        Connection connection= dbConfig.getConnection();
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
    public boolean updateById(int id,String name ,int age) throws IOException {
        // TODO Auto-generated method stub
        dbConfig=new DBConfig();
        Connection connection= dbConfig.getConnection();
        int count =0;

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

}//class end
