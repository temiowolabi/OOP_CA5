//package com.dkit.oop.sd2.Server;
//
//import com.dkit.oop.sd2.DTOs.Course;
//import com.dkit.oop.sd2.DTOs.CourseChoice;
//import com.dkit.oop.sd2.Exceptions.DaoException;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class MySqlCourseChoiceDao extends MySqlDao implements CourseChoiceInterface{
//
//    @Override
//    public CourseChoice displayCurrentChoice (int cNumber, String pword) throws DaoException
//    {
//        Connection con = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        List<CourseChoice> courses = new ArrayList<>();
//
//        try
//        {
//            //Get connection object using the methods in the super class (MySqlDao.java)...
//            con = this.getConnection();
//
//            String query = "SELECT * FROM STUDENT_COURSE WHERE CAONUMBER = ? AND COURSEID = ?";
//            ps = con.prepareStatement(query);
//            ps.setInt(1, cNumber);
//            ps.setString(2, pword);
//
//            //Using a PreparedStatement to execute SQL...
//            rs = ps.executeQuery();
//            while (rs.next())
//            {
//                int caoNumber = rs.getInt("CAO Number");
//                String courseId = rs.getString("Course ID");
//                CourseChoice c = new CourseChoice(caoNumber, courseId);
//            }
//        } catch (SQLException e)
//        {
//            throw new DaoException("findAllCourses() " + e.getMessage());
//        } finally
//        {
//            try
//            {
//                if (rs != null)
//                {
//                    rs.close();
//                }
//                if (ps != null)
//                {
//                    ps.close();
//                }
//                if (con != null)
//                {
//                    freeConnection(con);
//                }
//            } catch (SQLException e)
//            {
//                throw new DaoException("findAllCourses() " + e.getMessage());
//            }
//        }
//        return courses;     // may be empty
//    }
//
//
//}
