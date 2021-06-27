package com.syz.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syz.eduservice.entity.EduTeacher;
import com.syz.eduservice.entity.vo.TeacherQuery;
import com.syz.eduservice.mapper.EduTeacherMapper;
import com.syz.eduservice.service.EduTeacherService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-05-16
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery) {
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_create");
        if (teacherQuery==null){
            baseMapper.selectPage(pageParam,queryWrapper);
        }
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if (StringUtils.isNotBlank(name)){
            queryWrapper.like("name",name);
        }
        if (level!=null){
            queryWrapper.eq("level",level);
        }
        if (StringUtils.isNotBlank(begin)){
            queryWrapper.ge("gmt_create",begin);
        }
        if (StringUtils.isNotBlank(end)){
            queryWrapper.le("gmt_create",end);
        }
        baseMapper.selectPage(pageParam,queryWrapper);
    }
}
