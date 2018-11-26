package com.curriculo.uprb.models.curriculum;

import lombok.Data;

import java.util.List;

@Data
public class Curriculum {
    private String code;
    private List<Term> termList;
    private int startYear;
    private boolean isActive;
}
