package com.curriculo.uprb.models.student;

import com.curriculo.uprb.models.course.Course;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AcademicProgress {

    private Long id;
    private Student student;
    private Course course;
    private Grade grade;

}
