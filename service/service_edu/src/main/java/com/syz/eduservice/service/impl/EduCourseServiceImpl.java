package com.syz.eduservice.service.impl;

import com.syz.eduservice.entity.EduCourse;
import com.syz.eduservice.entity.EduCourseDescription;
import com.syz.eduservice.entity.vo.CourseInfoVo;
import com.syz.eduservice.mapper.EduCourseMapper;
import com.syz.eduservice.service.EduCourseDescriptionService;
import com.syz.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syz.servicebase.exceptionhandler.CustomizeException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-08-07
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Resource
    EduCourseDescriptionService eduCourseDescriptionService;

    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //添加课程基本信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if (insert == 0){
            throw new CustomizeException(20040,"添加课程基本信息失败");
        }
        //添加课程简介
        String courseId=eduCourse.getId();
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(courseId);
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescriptionService.save(eduCourseDescription);
        return courseId;
    }
}
