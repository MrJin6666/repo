package com.goboosoft.company.wastemanagement.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class WasteTypeResultDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "危废类型id")
    private Long id;

    @ApiModelProperty(value = "危废类型集合")
    private String[] wasteTypeDir;

    @ApiModelProperty(value = "危废名称集合")
    private String[] nameDir;

    @ApiModelProperty(value = "计量单位集合")
    private String[] unitDir;

    @ApiModelProperty(value = "废物来源集合")
    private String[] sourceDir;

    @ApiModelProperty(value = "危废保存位置集合")
    private String[] saveDir;

    @ApiModelProperty(value = "接收单位集合")
    private String[] receiveUnitDir;

    @ApiModelProperty(value = "所属部门集合")
    private String[] deptDir;

    @ApiModelProperty(value = "危废去向集合")
    private String[] directionDir;

}
