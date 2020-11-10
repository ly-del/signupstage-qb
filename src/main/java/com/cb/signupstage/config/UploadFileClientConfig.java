package com.cb.signupstage.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author wwn
 * @create_time 2020/9/2
 * @description 上传oss
 */
@Data
@Component
public class UploadFileClientConfig implements Serializable {
    private static final long serialVersionUID = -3554994322100227664L;

    /**
     * 地区
     */
    @Value("${upload.region}")
    private String region;

    /**
     *
     */
    @Value("${upload.accessKeyId}")
    private String accessKeyId;

    /**
     *
     */
    @Value("${upload.accessKeySecret}")
    private String accessKeySecret;

    /**
     *
     */
    @Value("${upload.bucket}")
    private String bucket ;

    /**
     * 文件路径，eq a/b.txt
     */
    private String filePath;

    private String url = "http://oss-cn-shenzhen.aliyuncs.com";

    private String policy;

    private String signaturecom;
}
