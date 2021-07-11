package com.syz.eduservice.controller;


import com.syz.commonutils.R;
import com.syz.eduservice.entity.EduSubject;
import com.syz.eduservice.entity.vo.SubjectNestedQuery;
import com.syz.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-06-27
 */
@Api(tags="课程分类管理")
@RestController
@RequestMapping("/eduservice/edu-subject")
@CrossOrigin
public class EduSubjectController {

    @Resource
    private EduSubjectService eduSubjectService;

    @ApiOperation(value = "Excel批量导入")
    @PostMapping("/addSubject")
    public R addSubject(MultipartFile file){
        eduSubjectService.importSubjectData(file,eduSubjectService);
        return R.success();
    }

    @ApiOperation(value = "嵌套数据列表")
    @GetMapping("/")
    public R getAllSubject(){
        List<SubjectNestedQuery> subjectNestedVoList= eduSubjectService.getAllSubject();
        return R.success().data("items",subjectNestedVoList);
    }

}
