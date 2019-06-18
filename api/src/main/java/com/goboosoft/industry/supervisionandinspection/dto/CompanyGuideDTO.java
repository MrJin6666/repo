package com.goboosoft.industry.supervisionandinspection.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * @author yuzhao
 * @since 1.0.0 2019-03-14
 */
@Data
@ApiModel(value = "企业指导首页")
public class CompanyGuideDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "企业id")
    private Long companyId;

    @ApiModelProperty(value = "企业状态")
    private Integer companyStatus;

    @ApiModelProperty(value = "红牌数量")
    private Integer redNum;

    @ApiModelProperty(value = "黄牌数量")
    private Integer yellowNum;

    @ApiModelProperty(value = "督导检查数量")
    private Integer superviseNum;

    @ApiModelProperty(value = "治理超期数量")
    private Integer GovernanceNum;

    @ApiModelProperty(value = "抽查清单数量")
    private Integer spotCheckNum;

    @ApiModelProperty(value = "整改清单数量")
    private Integer rectificationNum;
}