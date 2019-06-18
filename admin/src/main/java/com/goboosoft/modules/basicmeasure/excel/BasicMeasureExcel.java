package com.goboosoft.modules.basicmeasure.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 计量单位
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-12
 */
@Data
public class BasicMeasureExcel {
    @Excel(name = "")
    private Long id;
    @Excel(name = "计量单位名称")
    private String name;
    @Excel(name = "单位简写")
    private String logogram;
    @Excel(name = "说明")
    private String instructions;
    @Excel(name = "创建时间")
    private Date createDate;
    @Excel(name = "创建人")
    private Long creator;

}