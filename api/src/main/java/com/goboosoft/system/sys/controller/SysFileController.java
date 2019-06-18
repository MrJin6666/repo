package com.goboosoft.system.sys.controller;


import com.goboosoft.common.utils.DateUtils;
import com.goboosoft.common.utils.Result;
import com.mongodb.DB;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.MongoDbFactory;
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
import java.text.SimpleDateFormat;
import java.util.Date;


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
    @Autowired
    private MongoDbFactory mongoDbFactory;

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
        try {
            String[] split = file.getOriginalFilename().split("\\.");
            String contentType = file.getContentType();
            String suffix = split[split.length - 1];
            DB legacyDb = mongoDbFactory.getLegacyDb();
            GridFS gfsPhoto = new GridFS(legacyDb);
            GridFSInputFile gfsFile = gfsPhoto.createFile(file.getInputStream());
            String id = DateUtils.format(new Date(), DateUtils.DATETIMEPATTERN) + RandomStringUtils.randomAlphanumeric(6);
            gfsFile.setId(id);
            gfsFile.setFilename(id + "." + suffix);
            gfsFile.setContentType(contentType);
            gfsFile.save();
            return new Result().ok(id + "." + suffix);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result().error("网络异常，请重新提交！");
        }

//        ObjectId store = null;
//        try {
//            store = gridFsTemplate.store(file.getInputStream(),file.getOriginalFilename(),file.getContentType());
//            return new Result().ok(store.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new Result().error("网络异常，请重新提交！");
//        }

    }

}