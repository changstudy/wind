package com.chang.controller;

import com.chang.component.MinioUtil;
import com.chang.configuration.MinioProperties;
import com.chang.pojo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/minio")
public class MinioController {

    @Autowired
    private MinioProperties minioProperties;

    @PostMapping(value = "/upload")
    public ResultVo upload(@RequestParam(name = "file") MultipartFile multipartFile) throws Exception {
        String fileName = multipartFile.getOriginalFilename();
        MinioUtil.createBucket(minioProperties.getBucket());
        MinioUtil.uploadFile(minioProperties.getBucket(), multipartFile, fileName);
        String objectUrl = MinioUtil.getPreSignedObjectUrl(minioProperties.getBucket(), fileName);
        return new ResultVo().defineResult(200, "上传成功", objectUrl);
    }
}