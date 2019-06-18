package com.goboosoft.industry.statistics.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 统计-重点监管
 *
 * @author jinxin
 */
@Data
@ApiModel(value = "统计—重点监管")
public class MajorRegulatoryDTO implements Comparable<MajorRegulatoryDTO> {

    @ApiModelProperty(value = "公司id")
    private Long companyId;

    @ApiModelProperty(value = "公司名称")
    private String companyName;

    @ApiModelProperty(value = "红牌总个数")
    private Integer redCardCount;

    @ApiModelProperty(value = "黄牌总个数")
    private Integer yellowCardCount;

    @ApiModelProperty(value = "一般总个数")
    private Integer ordinaryCardCount;

    @ApiModelProperty(value = "页码")
    private String rownum;

    @Override
    public int compareTo(MajorRegulatoryDTO o) {
        //先按照红牌排序
        int i = o.getRedCardCount() - this.getRedCardCount();
        int j = o.getYellowCardCount() - this.getYellowCardCount();

        if (i == 0) {
            //如果红牌相等了再用黄牌进行排序
            return o.getYellowCardCount() - this.getYellowCardCount();
        }
        if (i == 0 && j == 0) {
            return o.getOrdinaryCardCount() - this.getOrdinaryCardCount();
        }
        return i;

    }
}
