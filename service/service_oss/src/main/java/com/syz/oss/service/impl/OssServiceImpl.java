package com.syz.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.syz.oss.service.OssService;
import com.syz.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadAvatar(MultipartFile file) {
        // 获取阿里云存储相关常量
        String endpoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;
        String uploadUrl = null;

        // 创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            //获取上传文件流
            InputStream inputStream = file.getInputStream();
            //修改文件名
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String datePath = new DateTime().toString("yyyy/MM/dd");
            String fileName = datePath+"/"+uuid+file.getOriginalFilename();
            // 文件上传至阿里云
            ossClient.putObject(bucketName, fileName, inputStream);
            //获取url地址
            uploadUrl = "https://" + bucketName + "." + endpoint + fileName;
            //关闭文件流
            inputStream.close();
            return uploadUrl;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            // 关闭OSSClient。
            ossClient.shutdown();
        }
    }
}
