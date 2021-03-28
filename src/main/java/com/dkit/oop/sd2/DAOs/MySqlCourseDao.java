package com.dkit.oop.sd2.DAOs;

import com.dkit.oop.sd2.DTOs.Course;
import com.dkit.oop.sd2.DTOs.Student;
import com.dkit.oop.sd2.Exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlCourseDao extends MySqlDao implements CourseDaoInterface{
    @Override
    public List<Course> findAllCourses() throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Course> courses = new ArrayList<>();

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "SELECT * FROM COURSE";
            ps = con.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next())
            {
                String courseId = rs.getString("courseId");
                String title = rs.getString("title");
                int level = rs.getInt("level");
                String institution = rs.getString("institution");
                Course c = new Course(courseId, level, title, institution);
                courses.add(c);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findAllCourses() " + e.getMessage());
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
                throw new DaoException("findAllCourses() " + e.getMessage());
            }
        }
        return courses;     // may be empty
    }

    @Override
    public Course findCoursesByCourseID(String courseId) throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Course c = null;
        try
        {
            con = this.getConnection();

            String query = "SELECT * FROM COURSE WHERE COURSEID = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, courseId);

            rs = ps.executeQuery();
            if (rs.next())
            {
                String courseID = rs.getString("COURSE_ID");
                int level = rs.getInt("LEVEL");
                String institution = rs.getString("INSTITUTION");
                String title = rs.getString("TITLE");
                c = new Course(courseID, level, institution, title);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findCoursesByCourseID() " + e.getMessage());
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
                throw new DaoException("findCoursesByCourseID() " + e.getMessage());
            }
        }
        return c;     // u may be null
    }
}
