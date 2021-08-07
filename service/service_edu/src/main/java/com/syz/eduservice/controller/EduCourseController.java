package com.syz.eduservice.controller;


import com.syz.commonutils.R;
import com.syz.eduservice.entity.EduCourse;
import com.syz.eduservice.entity.vo.CourseInfoVo;
import com.syz.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-08-07
 */
@Api(tags = "课程管理")
@RestController
@RequestMapping("/eduservice/edu-course")
@CrossOrigin
public class EduCourseController {

    @Resource
    private EduCourseService eduCourseService;

    @ApiOperation(value = "添加课程基本信息")
    @PostMapping("/addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        String courseId = eduCourseService.saveCourseInfo(courseInfoVo);
        return R.success().data("courseId",courseId);
    }
}
