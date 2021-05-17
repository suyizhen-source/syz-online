package com.syz.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.syz.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.syz.eduservice.entity.vo.TeacherQuery;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-05-16
 */
public interface EduTeacherService extends IService<EduTeacher> {

    void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery);
}
