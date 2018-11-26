package com.curriculo.uprb.models.curriculum;

import com.curriculo.uprb.models.course.Course;
import lombok.Data;

import java.util.List;

@Data
public class Term {


    private int year;
    private char term;
    private List<Course> courseList;




}
