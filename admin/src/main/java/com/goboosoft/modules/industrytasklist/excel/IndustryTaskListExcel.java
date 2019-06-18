package com.goboosoft.modules.industrytasklist.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 行业 - 新任务发布表
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-12
 */
@Data
public class IndustryTaskListExcel {
    @Excel(name = "主键")
    private Long id;
    @Excel(name = "标题")
    private String title;
    @Excel(name = "接收人主键")
    private Long receiverId;
    @Excel(name = "接收人")
    private String receiver;
    @Excel(name = "最晚完成时间")
    private Date latestCompletionTime;
    @Excel(name = "描述")
    private String description;
    @Excel(name = "附件")
    private String accessory;
    @Excel(name = "订单号")
    private String orderNumber;
    @Excel(name = "创建时间")
    private Date createDate;
    @Excel(name = "创建人")
    private Long creator;
    @Excel(name = "状态码：0.创建1.过程中2.已完成")
    private Integer status;

}