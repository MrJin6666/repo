package com.goboosoft.company.wastemanagement.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-12
 */
@Data
public class WasteManageExcel {
    @Excel(name = "危废id")
    private Long id;
    @Excel(name = "危废类型")
    private String type;
    @Excel(name = "危废名称")
    private String name;
    @Excel(name = "计数")
    private Integer count;
    @Excel(name = "计量单位")
    private String unit;
    @Excel(name = "危废来源")
    private String wasteSource;
    @Excel(name = "危废去向")
    private String wasteDirection;
    @Excel(name = "保存位置")
    private String saveLocation;
    @Excel(name = "更新人名称")
    private String creatorName;
    @Excel(name = "入库或出库")
    private Integer inboundOrOutbound;
    @Excel(name = "创建时间")
    private Date createDate;
    @Excel(name = "出库时间")
    private Date outbound;
    @Excel(name = "入库或出库人签字")
    private String signature;
    @Excel(name = "接收单位签字")
    private String unitSignature;
    @Excel(name = "接收单位")
    private String receiverUnit;
    @Excel(name = "更新人id")
    private Long creator;
    @Excel(name = "转移联单")
    private String transfer;
    @Excel(name = "是否确认（0：未确认 1：确认）")
    private Integer confirmed;

}