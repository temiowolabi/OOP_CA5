package com.dkit.oop.sd2.DAOs;

import com.dkit.oop.sd2.DTOs.Course;
import com.dkit.oop.sd2.Exceptions.DaoException;

import java.util.List;

public interface CourseDaoInterface {
    public List<Course> findAllCourses() throws DaoException;

    public Course findCoursesByCourseID(String courseId) throws DaoException;
}
