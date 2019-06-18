package com.goboosoft.modules.industrytasklist.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 行业任务日志记录
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-12
 */
@Data
public class IndustryTaskLogExcel {
    @Excel(name = "主键")
    private Long id;
    @Excel(name = "任务id")
    private Long industryTaskId;
    @Excel(name = "状态码:0:创建 1:处理中 2:已完成")
    private Integer status;
    @Excel(name = "附件")
    private String accessory;
    @Excel(name = "描述")
    private String remark;
    @Excel(name = "接收者")
    private Long receiverId;
    @Excel(name = "处理描述")
    private String finishRemark;
    @Excel(name = "创建时间")
    private Date createDate;
    @Excel(name = "创建者")
    private String creator;

}