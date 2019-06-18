package com.goboosoft.modules.companymanagesystem.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 企业管理制度
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-13
 */
@Data
public class CompanyManageSystemExcel {
    @Excel(name = "通告id")
    private Long id;
    @Excel(name = "通告名称")
    private String noticeName;
    @Excel(name = "创造者名称")
    private String creatorName;
    @Excel(name = "通告内容")
    private String noticeContent;
    @Excel(name = "附件（上传图片）")
    private String attachment;
    @Excel(name = "创造者ID")
    private Long creator;
    @Excel(name = "上传时间")
    private Date createDate;
    @Excel(name = "是否是企业（0：企业，1：部门）")
    private Integer isCompany;
    @Excel(name = "0：正常 1：不可用（删除数据时标记为“1”）")
    private Integer state;
    @Excel(name = "图标")
    private String iconUri;
    @Excel(name = "部门id")
    private Long deptId;

}