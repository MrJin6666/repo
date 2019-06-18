package com.goboosoft.company.wastemanagement.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 企业危废管理协议
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-14
 */
@Data
public class WasteProtocolExcel {
    @Excel(name = "协议id")
    private Long id;
    @Excel(name = "协议名称")
    private String protocolName;
    @Excel(name = "开始时间")
    private Date startDate;
    @Excel(name = "结束时间")
    private Date endDate;
    @Excel(name = "协议内容")
    private String content;
    @Excel(name = "创建人")
    private Long creator;
    @Excel(name = "创建时间")
    private Date createdate;

}