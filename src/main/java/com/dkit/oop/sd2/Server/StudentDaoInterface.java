package com.dkit.oop.sd2.Server;

import com.dkit.oop.sd2.DTOs.Student;
import com.dkit.oop.sd2.Exceptions.DaoException;

import java.util.List;

public interface StudentDaoInterface {
    public List<Student> findAllStudents() throws DaoException;

    public Student findStudentByCaoNumberPassword(int caoNumber, String pword) throws DaoException;

    public String register(int caoNumber, String dob, String password) throws DaoException;

    public  boolean login(int caoNumber, String password) throws DaoException;
}
