package com.dkit.oop.sd2.DTOs;

import java.util.HashMap;
import java.util.List;

public class CourseChoice {
//
//        // reference to constructor injected studentManager
//        private StudentManager studentManager;
//
//        // reference to constructor injected courseManager
//        private CourseManager courseManager;
//
//        // Store all the Course details -  fast access
//
//        // caoNumber, course selection list - for fast access
//        HashMap<Integer, List<String>> selectedChoices = new HashMap<>();
//
//        // CourseChoicesManager DEPENDS on both the StudentManager and CourseManager to access
//        // student details and course details.  So, we receive a reference to each via
//        // the constructor.
//        // This is called "Dependency Injection", meaning that we
//        // inject (or pass in) objects that this class requires to do its job.
//        //
//        CourseChoicesManager(StudentManager studentManager, CourseManager courseManager) {
//            this.studentManager = studentManager;
//            this.courseManager = courseManager;
//
//        }
//
//        public Student getStudentDetails(int caoNumber) {
//            return studentManager.getStudent(caoNumber);
//        }
//
//        public Course getCourseDetails(String courseId) {
//            return courseManager.getCourse(courseId);
//        }
//
//        public List<String> getStudentChoices(int caoNumber) {
//            return selectedChoices.get(caoNumber);
//        }
//
//        public void updateChoices(int caoNumber, List<String> choices) {
//            selectedChoices.put(caoNumber, choices);
//        }
//
//        public HashMap<Integer, List<String>> getAllCourses() {
//            return selectedChoices;
//        }
//
//        public boolean login(int caoNumber, String dateOfBirth, String password) {
//            return studentManager.isRegistered(caoNumber, dateOfBirth, password);
//
//        }
}
