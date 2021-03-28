package com.dkit.oop.sd2.DAOs;

import com.dkit.oop.sd2.DTOs.Student;
import com.dkit.oop.sd2.Exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlStudentDao extends MySqlDao implements StudentDaoInterface{

    @Override
    public List<Student> findAllStudents() throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Student> students = new ArrayList<>();

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "SELECT * FROM STUDENT";
            ps = con.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next())
            {
                int caoNumber = rs.getInt("caonumber");
                String dateOfBirth = rs.getString("dob");
                String password = rs.getString("password");
                Student s = new Student(caoNumber, dateOfBirth, password);
                students.add(s);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findAllStudents() " + e.getMessage());
        } finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (con != null)
                {
                    freeConnection(con);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findAllStudents() " + e.getMessage());
            }
        }
        return students;     // may be empty
    }

    @Override
    public Student findStudentByCaoNumberPassword(int caoNumber, String password) throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Student s = null;
        try
        {
            con = this.getConnection();

            String query = "SELECT * FROM STUDENT WHERE CAONUMBER = ? AND PASSWORD = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, caoNumber);
            ps.setString(2, password);

            rs = ps.executeQuery();
            if (rs.next())
            {
                int cao_number = rs.getInt("CAO_NUMBER");
                String dateOfBirth = rs.getString("DATE_OF_BIRTH");
                String pword = rs.getString("PASSWORD");
                s = new Student(cao_number, dateOfBirth, pword);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findUserByUsernamePassword() " + e.getMessage());
        } finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (con != null)
                {
                    freeConnection(con);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findUserByUsernamePassword() " + e.getMessage());
            }
        }
        return s;     // u may be null
    }

}
