package com.goboosoft.company.managesystem.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UploadingNoticeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "公告名称")
    private String noticeName;

    @ApiModelProperty(value = "公告内容")
    private String noticeContent;

    @ApiModelProperty(value = "附件（上传图片）")
    private String attachment;

    @ApiModelProperty(value = "区分管理制度与公告（0：是制度 1：公告）")
    private Integer type;
}
