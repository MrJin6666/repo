package com.goboosoft.company.busininspect.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

@Data
@ApiModel(value = "开始整改")
public class BeginCorrectionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "整改描述")
    private String finishRemark;

    @ApiModelProperty(value = "整改图片")
    private String finishPhotos;

    @ApiModelProperty(value = "整改人签名")
    private String underwrite;
}
