package com.dkit.oop.sd2.DTOs;

import com.dkit.oop.sd2.Server.MySqlCourseDao;
import com.dkit.oop.sd2.Server.MySqlStudentDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseChoice {

    private MySqlStudentDao studentManager;

    private MySqlCourseDao courseManager;

    private Map <Integer, List<Course>> studentChoices = new HashMap<>();


    public CourseChoice(MySqlStudentDao studentManager, MySqlCourseDao courseManager) {
        this.studentManager = studentManager;
        this.courseManager = courseManager;
    }

    public Map<Integer, List<Course>> getStudentChoicesMap()
    {
        return studentChoices;
    }

    public void setStudentChoices(Map<Integer, List<Course>> studentChoices)
    {
        this.studentChoices = studentChoices;
    }

    public MySqlStudentDao getStudentManager()
    {
        return studentManager;
    }

    public MySqlCourseDao getCourseManager()
    {
        return courseManager;
    }

    public void updateChoices(int caoNumber, List<String> choices)
    {
        ArrayList<Course> courseList = new ArrayList<>();

        for(String courseId : choices)
        {
            Course course = courseManager.getCoursesMap().get(courseId);
            courseList.add(course);
        }

        studentChoices.put(caoNumber, courseList);
    }

    public List<Course> getAllCourses()
    {
        ArrayList<Course> list = new ArrayList<>();
        for(Map.Entry<String, Course> entry : this.courseManager.getCoursesMap().entrySet())
        {
            Course course = entry.getValue();
            list.add(course);
        }
        return list;
    }
}
