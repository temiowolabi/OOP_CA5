package com.dkit.oop.sd2.Server;

import com.dkit.oop.sd2.DTOs.CourseChoice;
import com.dkit.oop.sd2.Exceptions.DaoException;

import java.util.List;

public interface CourseChoiceInterface {
    public CourseChoice displayCurrentChoice (int cNumber, String pword) throws DaoException;
}
