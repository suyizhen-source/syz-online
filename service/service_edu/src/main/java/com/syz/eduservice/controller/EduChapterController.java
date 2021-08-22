package com.syz.eduservice.controller;


import com.syz.commonutils.R;
import com.syz.eduservice.entity.EduChapter;
import com.syz.eduservice.entity.vo.ChapterQuery;
import com.syz.eduservice.service.EduChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "获取章节")
    @GetMapping("/{courseId}")
    public R getAllChapter(@PathVariable String courseId){
        List<ChapterQuery> chapterList= eduChapterService.getChapterAndVideo(courseId);
        return R.success().data("items",chapterList);
    }

    @ApiOperation(value = "新增章节")
    @PostMapping("/addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter){
        boolean save = eduChapterService.save(eduChapter);
        if (save){
            return R.success();
        }
        return R.error().code(20090).message("添加章节失败");
    }

    @ApiOperation(value = "根据ID查询章节")
    @GetMapping("getChapterInfoById/{chapterId}")
    public R getChapterInfoById(@PathVariable String chapterId){
        EduChapter eduChapter = eduChapterService.getById(chapterId);
        return R.success().data("item",eduChapter);
    }

    @ApiOperation(value = "根据ID修改章节")
    @PostMapping("/updateChapter")
    public R updateChapter(@RequestBody EduChapter eduChapter){
        boolean update = eduChapterService.updateById(eduChapter);
        if (update){
            return R.success();
        }
        return R.error().code(20091).message("修改章节失败");
    }

    @ApiOperation(value = "根据ID删除章节")
    @DeleteMapping("/removeChapterById/{chapterId}")
    public R removeChapterById(@PathVariable String chapterId){
        Boolean remove = eduChapterService.removeChapterById(chapterId);
        if (remove){
            return R.success();
        }
        return R.error().code(20092).message("删除章节失败");
    }
}
