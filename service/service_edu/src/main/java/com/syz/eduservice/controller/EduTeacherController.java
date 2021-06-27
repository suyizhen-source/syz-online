package com.syz.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.syz.commonutils.R;
import com.syz.eduservice.entity.EduTeacher;
import com.syz.eduservice.entity.vo.TeacherQuery;
import com.syz.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-05-16
 */
@Api(tags = "讲师管理")
@RestController
@RequestMapping("/eduservice/edu-teacher")
@CrossOrigin
public class EduTeacherController {
    @Resource
    private EduTeacherService eduTeacherService;

    @ApiOperation(value = "所有讲师列表")
    @GetMapping("/findAll")
    public R findAll() {
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.success().data("items", list);
    }

    @ApiOperation(value = "根据ID获取讲师")
    @GetMapping("/findById/{id}")
    public R findById(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable String id) {
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return eduTeacher == null ? R.error() : R.success().data("teacher", eduTeacher);
    }

    @ApiOperation(value = "新增讲师")
    @PostMapping("/save")
    public R save(@ApiParam(name = "teacher", value = "讲师对象", required = true) @RequestBody EduTeacher eduTeacher) {
        boolean flag = eduTeacherService.save(eduTeacher);
        return flag ? R.success() : R.error();
    }

    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("/delete/{id}")
    public R deleteById(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable String id) {
        boolean flag = eduTeacherService.removeById(id);
        return flag ? R.success() : R.error();
    }

    @ApiOperation(value = "根据ID修改讲师")
    @PostMapping("/update")
    public R updateById(@ApiParam(name = "teacherQuery", value = "修改对象") @RequestBody EduTeacher eduTeacher) {
        boolean flag = eduTeacherService.updateById(eduTeacher);
        return flag ? R.success() : R.error();
    }

    @ApiOperation(value = "分页查询讲师")
    @PostMapping("/{page}/{limit}")
    public R pageList(
            @ApiParam(name = "page", value = "当前页码", required = true) @PathVariable long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true) @PathVariable long limit,
            @ApiParam(name = "teacherQuery", value = "查询对象") @RequestBody TeacherQuery teacherQuery) {
        Page<EduTeacher> teacherPage = new Page<>(page, limit);
        eduTeacherService.pageQuery(teacherPage, teacherQuery);
        long total = teacherPage.getTotal();
        List<EduTeacher> records = teacherPage.getRecords();
        return R.success().data("total", total).data("rows", records);
    }

}
