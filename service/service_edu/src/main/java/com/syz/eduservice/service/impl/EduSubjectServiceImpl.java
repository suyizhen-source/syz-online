package com.syz.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.syz.eduservice.entity.EduSubject;
import com.syz.eduservice.entity.excel.SubjectData;
import com.syz.eduservice.entity.vo.SubjectNestedQuery;
import com.syz.eduservice.entity.vo.SubjectQuery;
import com.syz.eduservice.listener.SubjectExcelListener;
import com.syz.eduservice.mapper.EduSubjectMapper;
import com.syz.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-06-27
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void importSubjectData(MultipartFile file, EduSubjectService eduSubjectService) {
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, SubjectData.class, new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<SubjectNestedQuery> getAllSubject() {
        List<SubjectNestedQuery> subjectNestedQueryList = new ArrayList<>();
        //获取一级分类数据记录
        QueryWrapper<EduSubject> parentWrapper = new QueryWrapper<>();
        parentWrapper.eq("parent_id", 0);
        List<EduSubject> parentSubjects = baseMapper.selectList(parentWrapper);
        //填充一级分类vo数据
        for (int i = 0; i < parentSubjects.size(); i++) {
            //创建一级类别vo对象
            SubjectNestedQuery subjectNestedQuery = new SubjectNestedQuery();
            BeanUtils.copyProperties(parentSubjects.get(i), subjectNestedQuery);
            subjectNestedQueryList.add(subjectNestedQuery);
            //获取二级分类数据记录
            List<SubjectQuery> subjectQueryList = new ArrayList<>();
            String parentId = parentSubjects.get(i).getId();
            QueryWrapper<EduSubject> childrenWrapper = new QueryWrapper<>();
            childrenWrapper.eq("parent_id", parentId);
            List<EduSubject> childrenSubjects = baseMapper.selectList(childrenWrapper);
            //填充二级分类vo数据
            for (int j = 0; j < childrenSubjects.size(); j++) {
                SubjectQuery subjectQuery = new SubjectQuery();
                BeanUtils.copyProperties(childrenSubjects.get(j), subjectQuery);
                subjectQueryList.add(subjectQuery);
            }
            subjectNestedQuery.setChildren(subjectQueryList);
        }
        return subjectNestedQueryList;
    }
}
