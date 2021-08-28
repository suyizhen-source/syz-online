package com.syz.eduservice.controller;


import com.syz.commonutils.R;
import com.syz.eduservice.entity.EduVideo;
import com.syz.eduservice.service.EduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-08-07
 */
@Api(tags="小节管理")
@RestController
@RequestMapping("/eduservice/edu-video")
@CrossOrigin
public class EduVideoController {

    @Resource
    EduVideoService eduVideoService;

    @GetMapping("/getVideoInfoById/{videoId}")
    public R getVideoInfoById(@PathVariable String videoId){
        EduVideo video = eduVideoService.getById(videoId);
        return R.success().data("item",video);
    }

    @ApiOperation(value = "新增小节")
    @PostMapping("/addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){
        boolean save = eduVideoService.save(eduVideo);
        if (save){
            return R.success();
        }
        return R.error().code(20010).message("添加小节失败");
    }

    @ApiOperation(value = "根据ID修改小节")
    @PostMapping("/updateVideo")
    public R updateVideo(@RequestBody EduVideo eduVideo){
        boolean update = eduVideoService.updateById(eduVideo);
        if (update){
            return R.success();
        }
        return R.error().code(20011).message("修改小节失败");
    }

    @ApiOperation(value = "根据ID删除小节")
    @DeleteMapping("/removeVideoById/{videoId}")
    public R removeVideoById(@PathVariable String videoId){
        boolean remove = eduVideoService.removeById(videoId);
        if (remove){
            return R.success();
        }
        return R.error().code(20092).message("删除小节失败");
    }
}
