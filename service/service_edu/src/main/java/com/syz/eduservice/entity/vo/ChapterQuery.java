package com.syz.eduservice.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class ChapterQuery {
    private String id;
    private String title;
    private List<VideoQuery> children;
}
