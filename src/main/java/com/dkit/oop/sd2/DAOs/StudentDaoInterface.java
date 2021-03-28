package com.dkit.oop.sd2.DAOs;

import com.dkit.oop.sd2.DTOs.Student;
import com.dkit.oop.sd2.DTOs.User;
import com.dkit.oop.sd2.Exceptions.DaoException;

import java.util.List;

public interface StudentDaoInterface {
    public List<Student> findAllStudents() throws DaoException;

    public Student findStudentByCaoNumberPassword(int caoNumber, String pword) throws DaoException;
}
