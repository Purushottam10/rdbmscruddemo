package com.dz.com.dz;

import com.dz.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class DBOperation {

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
        }catch(SQLException e) {
            e.printStackTrace();
        }
        if(count>0){
            return true;
        }
        return false;
    }//method end

    /**
     * Display record
     */
    public  List display(Connection connection){

        Student student=new Student();
        List<Student> studentList=new ArrayList<Student>();
        try{
            statement= connection.prepareStatement("select * from student") ;
         ResultSet   rs=statement.executeQuery();

            while(rs.next())  {
                //System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getInt(3));
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

}//class end
