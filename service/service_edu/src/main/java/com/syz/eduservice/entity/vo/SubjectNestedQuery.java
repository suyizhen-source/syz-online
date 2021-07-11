package com.syz.eduservice.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class SubjectNestedQuery {
    private String id;
    private String title;
    private List<SubjectQuery> children;
}
