package com.syz.eduservice.controller;


import com.syz.commonutils.R;
import com.syz.eduservice.entity.vo.ChapterQuery;
import com.syz.eduservice.service.EduChapterService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-08-07
 */
@Api(tags="章节管理")
@RestController
@RequestMapping("/eduservice/edu-chapter")
@CrossOrigin
public class EduChapterController {

    @Resource
    EduChapterService eduChapterService;

    @GetMapping("/{courseId}")
    public R getAllChapter(@PathVariable String courseId){
        List<ChapterQuery> chapterList= eduChapterService.getChapterAndVideo(courseId);
        return R.success().data("items",chapterList);
    }

}
