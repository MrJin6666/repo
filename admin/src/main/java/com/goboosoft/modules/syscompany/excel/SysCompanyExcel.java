package com.goboosoft.modules.syscompany.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 基础 - 公司信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-05
 */
@Data
public class SysCompanyExcel {
    @Excel(name = "主键")
    private Long id;
    @Excel(name = "公司/单位名称")
    private String name;
    @Excel(name = "企业唯一编码")
    private String code;
    @Excel(name = "地址 - 省")
    private String adrProvince;
    @Excel(name = "地址 - 市")
    private String adrCity;
    @Excel(name = "区")
    private String adrArea;
    @Excel(name = "地址 - 街道")
    private String adrStreet;
    @Excel(name = "地址 - 社区")
    private String adrCommunity;
    @Excel(name = "地址 - 路，门牌号")
    private String adrRoad;
    @Excel(name = "")
    private String lng;
    @Excel(name = "纬度")
    private String lat;
    @Excel(name = "状态 0待审核 1审核通过")
    private String status;
    @Excel(name = "公司类型 0行业 1企业")
    private String companyType;
    @Excel(name = "现管单位")
    private String xgdw;
    @Excel(name = "创建人主键")
    private Long creator;
    @Excel(name = "创建时间")
    private Date createDate;
    @Excel(name = "修改人主键")
    private Long updater;
    @Excel(name = "修改时间")
    private Date updateDate;

}