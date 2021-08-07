package com.syz.eduservice.service;

import com.syz.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.syz.eduservice.entity.vo.CourseInfoVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-08-07
 */
public interface EduCourseService extends IService<EduCourse> {

    //添加课程基本信息
    String saveCourseInfo(CourseInfoVo courseInfoVo);
}
