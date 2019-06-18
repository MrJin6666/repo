package com.goboosoft.industry.multiple.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 抽查清单列表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-02-11
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class SpotCheckListDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "标题")
	private String title;

	@ApiModelProperty(value = "单号")
	private String code;

	@ApiModelProperty(value = "抽查人")
	private String person;

	@ApiModelProperty(value = "抽查时间")
	private Date createDate;

	@ApiModelProperty(value = "上报时间")
	private Date seatDate;

	@ApiModelProperty(value = "上报状态")
	private Integer seatStatus;

	@ApiModelProperty(value = "页码")
	private Integer rownum;
}