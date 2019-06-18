package com.goboosoft.company.manageread.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 公告记录
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-20
 */
@Data
public class ManageReadExcel {
    @Excel(name = "")
    private Long id;
    @Excel(name = "已读人id")
    private Long finshRead;
    @Excel(name = "")
    private Long creator;
    @Excel(name = "")
    private Date createDate;
    @Excel(name = "公告id")
    private Long manageSystemId;
    @Excel(name = "已读人姓名")
    private String finshReadName;
    @Excel(name = "0 企业 1 行业")
    private Integer isCompany;

}