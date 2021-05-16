package com.syz.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.syz.commonutils.R;
import com.syz.eduservice.entity.EduTeacher;
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
@Api(tags="讲师管理")
@RestController
@RequestMapping("/eduservice/edu-teacher")
public class EduTeacherController {
    @Resource
    private EduTeacherService eduTeacherService;

    @ApiOperation(value = "所有讲师列表")
    @GetMapping("/findAll")
    public R findAllTeacher(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.success().data("items",list);
    }

    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("/delTeacher/{id}")
    public R delTeacherById(@ApiParam(name = "id", value = "讲师ID", required = true)@PathVariable String id){
        boolean flag = eduTeacherService.removeById(id);
        if (flag){
            return R.success();
        }else {
            return R.error();
        }
    }

    @ApiOperation(value = "分页查询讲师")
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(@ApiParam(name = "current", value = "当前页", required = true)@PathVariable long current,
                             @ApiParam(name = "limit", value = "每页记录数", required = true)@PathVariable long limit) {
        Page<EduTeacher> teacherPage=new Page<>(current,limit);
        eduTeacherService.page(teacherPage,null);
        long total = teacherPage.getTotal();
        List<EduTeacher> records = teacherPage.getRecords();
        return R.success().data("total",total).data("rows",records);
    }
}
