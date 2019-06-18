package com.goboosoft.modules.sys.controller;


import com.goboosoft.common.utils.Result;
import com.mongodb.client.gridfs.model.GridFSFile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;


/**
 * 文件
 *
 * @author DMXUAN
 * @since 1.0.0 2019-01-11
 */
@RestController
@RequestMapping("sys/file")
@Api(tags="文件服务")
public class SysFileController {
    @Autowired
    private GridFsTemplate gridFsTemplate;


    @GetMapping("getFile")
    @ApiOperation(value = "获取文件")
    public void getFile(HttpServletResponse response, String fileId)throws IOException {
        Query query = Query.query(Criteria.where("_id").is(fileId));
        GridFSFile one = gridFsTemplate.findOne(query);
        GridFsResource file = gridFsTemplate.getResource(one);
        response.setContentType(file.getContentType());
        InputStream inputStream = file.getInputStream();
        ServletOutputStream outputStream = response.getOutputStream();
        IOUtils.copy(inputStream,outputStream);
        outputStream.close();
        inputStream.close();
}

    @PostMapping("uploadFile")
    @ApiOperation(value = "上传文件")
    public Result getFile(@RequestParam("file") MultipartFile file)throws IOException {
        ObjectId store = gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename(), file.getContentType());
          return new Result().ok(store.toString());
    }

}