package com.goboosoft.company.wastemanagement.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class InWasteTypeAdditionDTO  implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "危废类型集合")
    private String wasteTypeDir;

    @ApiModelProperty(value = "危废名称集合")
    private String nameDir;

    @ApiModelProperty(value = "计量单位集合")
    private String unitDir;

    @ApiModelProperty(value = "废物来源集合")
    private String sourceDir;

    @ApiModelProperty(value = "危废保存位置集合")
    private String saveDir;


}
