package com.syz.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.syz.eduservice.entity.EduSubject;
import com.syz.eduservice.entity.excel.SubjectData;
import com.syz.eduservice.service.EduSubjectService;
import com.syz.servicebase.exceptionhandler.CustomizeException;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    public EduSubjectService eduSubjectService;
    public SubjectExcelListener() {}
    //创建有参数构造，传递subjectService用于操作数据库
    public SubjectExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null){
            throw new CustomizeException(20001,"文件数据为空");
        }
        //添加一级分类
        EduSubject oneEduSubject = existOneEduSubject(eduSubjectService, subjectData.getOneSubjectName());
        if (oneEduSubject ==null){
            oneEduSubject = new EduSubject();
            oneEduSubject.setTitle(subjectData.getOneSubjectName());
            oneEduSubject.setParentId("0");
            eduSubjectService.save(oneEduSubject);
        }
        //获取一级分类id值
        String pId = oneEduSubject.getId();
        //添加二级分类
        EduSubject twoEduSubject = existTwoEduSubject(eduSubjectService, subjectData.getTwoSubjectName(), pId);
        if (twoEduSubject == null){
            twoEduSubject = new EduSubject();
            twoEduSubject.setTitle(subjectData.getTwoSubjectName());
            twoEduSubject.setParentId(pId);
            eduSubjectService.save(twoEduSubject);
        }

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    //判断一级分类是否重复
    public EduSubject existOneEduSubject(EduSubjectService eduSubjectService,String name){
        QueryWrapper<EduSubject> wrapper =new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        EduSubject oneSubject = eduSubjectService.getOne(wrapper);
        return oneSubject;
    }

    //判断二级分类是否重复
    public EduSubject existTwoEduSubject(EduSubjectService eduSubjectService,String name,String pId){
        QueryWrapper<EduSubject> wrapper =new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pId);
        EduSubject twoSubject = eduSubjectService.getOne(wrapper);
        return twoSubject;
    }

}
