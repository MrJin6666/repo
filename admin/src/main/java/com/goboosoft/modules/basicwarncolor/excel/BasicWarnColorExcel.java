package com.goboosoft.modules.basicwarncolor.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 过期警告颜色
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-12
 */
@Data
public class BasicWarnColorExcel {
    @Excel(name = "")
    private Long id;
    @Excel(name = "超期时间 天")
    private Long beyond;
    @Excel(name = "提醒颜色")
    private String color;
    @Excel(name = "")
    private Date createDate;
    @Excel(name = "创建人")
    private Long creator;

}