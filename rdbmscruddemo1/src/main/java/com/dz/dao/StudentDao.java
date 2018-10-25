package com.dz.dao;

import com.dz.model.Student;

import java.io.IOException;
import java.util.List;

public interface StudentDao {
    public boolean addstudent(Student student);
    public List display();
    public boolean removeById(int id);
    public boolean updateById(int id,String name ,int age) ;

}
