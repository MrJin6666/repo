package com.goboosoft.modules.sysnotice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 公告
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-11
 */
@Data
@ApiModel(value = "公告")
public class SysNoticeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "标题")
	private String title;

	@ApiModelProperty(value = "内容")
	private String content;

	@ApiModelProperty(value = "上传人")
	private String uploadPerson;

	@ApiModelProperty(value = "图片")
	private String picture;

	@ApiModelProperty(value = "部门id")
	private Long depId;

	@ApiModelProperty(value = "创建人id")
	private Long creator;

	@ApiModelProperty(value = "发布时间")
	private Date createDate;

	@ApiModelProperty(value = "更新者")
	private Long updater;

	@ApiModelProperty(value = "更新时间")
	private Date updateDate;


}