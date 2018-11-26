package com.curriculo.uprb.models.course;

import lombok.Data;

import java.util.List;

@Data
public class Course {

    private String code;
    private String name;
    private int credits;
    private int difficulty;
    private List<Course> preRequisite;
    private List<Course> coRequisite;

}
