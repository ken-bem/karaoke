package com.curriculo.uprb.models.student;

import com.curriculo.uprb.models.curriculum.Curriculum;
import lombok.Data;

import java.util.List;

@Data
public class Student {

    private String name;
    private int studentNumber;
    private String email;
    private String password;
    private int yearAdmitted;

    //Curriculum (should we have a registry of all past curriculum?)
    private Curriculum curriculum;
    private List<AcademicProgress> academicProgress;


    public double getGPA(){

        double credits = 0;
        double grade = 0;


        for (AcademicProgress curr: academicProgress) {
           grade += ( curr.getGrade().getValue() * curr.getCourse().getCredits());
           credits += curr.getCourse().getCredits();
        }

        return grade/credits;
    }


}
