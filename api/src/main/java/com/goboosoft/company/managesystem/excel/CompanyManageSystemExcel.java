package com.goboosoft.company.managesystem.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-08
 */
@Data
public class CompanyManageSystemExcel {
    @Excel(name = "")
    private Long id;
    @Excel(name = "通告名称")
    private String noticeName;
    @Excel(name = "通告内容")
    private String noticeContant;
    @Excel(name = "附件（上传图片）")
    private String attachment;
    @Excel(name = "上传人ID")
    private Long updateId;
    @Excel(name = "上传时间")
    private Date updateTime;
    @Excel(name = "是否是企业（0：企业，1：部门）")
    private Integer isCompany;
    @Excel(name = "图标")
    private String iconUri;

}