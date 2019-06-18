package com.goboosoft.modules.sysnotice.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 公告
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-11
 */
@Data
public class SysNoticeExcel {
    @Excel(name = "id")
    private Long id;
    @Excel(name = "标题")
    private String title;
    @Excel(name = "内容")
    private String content;
    @Excel(name = "上传人")
    private String uploadPerson;
    @Excel(name = "图片")
    private String picture;
    @Excel(name = "部门id")
    private Long depId;
    @Excel(name = "创建人id")
    private Long creator;
    @Excel(name = "发布时间")
    private Date createDate;
    @Excel(name = "更新者")
    private Long updater;
    @Excel(name = "更新时间")
    private Date updateDate;

}