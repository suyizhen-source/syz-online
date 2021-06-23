package com.syz.oss.controller;

import com.syz.commonutils.R;
import com.syz.oss.service.OssService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/eduoss/upload")
@CrossOrigin
public class OssController {

    @Resource
    private OssService ossService;

    @PostMapping("/")
    public R uploadOssFile(MultipartFile file){
        //上传头像到oss
        String url = ossService.uploadAvatar(file);
        return R.success().data("url",url);
    }

}

