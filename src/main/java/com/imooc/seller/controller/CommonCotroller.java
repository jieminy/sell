package com.imooc.seller.controller;

import com.imooc.common.VO.ResultVO;
import com.imooc.common.enums.ResultEnum;
import com.imooc.common.utils.QiNiuUtil;
import com.imooc.common.utils.ResultVOUtil;
import com.imooc.exception.SellException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author: JieMin
 * @Description:
 * @Date: created in 17:58 2018/4/14
 */
@RestController
@Slf4j
@RequestMapping("/common")
@Api(description = "公共接口")
public class CommonCotroller {
    @Autowired
    private QiNiuUtil qiNiuUtil;

    @PostMapping("/image/upload")
    @ApiOperation(value = "图片上传", notes = "图片上传", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultVO uploadImage(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        if (files == null) {
            throw new SellException(ResultEnum.IMAGE_NOT_EXIST);
        }
        List<String> urls = new ArrayList<>();
        for (MultipartFile multipartFile : files) {
            if (multipartFile.isEmpty() || StringUtils.isEmpty(multipartFile.getOriginalFilename())) {
                throw new SellException(ResultEnum.IMAGE_NOT_EXIST);
            }
            String contentType = multipartFile.getContentType();
            if (!contentType.contains("")) {
                throw new SellException(ResultEnum.IMAGE_FORMAT_ERROR);
            }
            String fileName = multipartFile.getOriginalFilename();
            String url;
            log.info("上传图片：name={},contentType={}", fileName, contentType);
            try {
                FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
                String suffix = fileName.substring(fileName.indexOf('.'), fileName.length());
                fileName = UUID.randomUUID().toString();
                url = qiNiuUtil.uploadImg(fileInputStream, fileName + suffix);
                if ("".equals(url)) {
                    throw new SellException(ResultEnum.IMAGE_UPLOAD_FAILED);
                }
                urls.add(url);
            } catch (IOException e) {
                throw new SellException(ResultEnum.IMAGE_UPLOAD_FAILED);
            }
        }
        return ResultVOUtil.success(urls);
    }
}
