package com.curriculo.uprb.models.course;

import lombok.Data;

import java.util.List;

@Data
public class Convalidation {

    private Long id;
    private Course requestedCourse;
    private List<Course> convalidatedCourses;


}
