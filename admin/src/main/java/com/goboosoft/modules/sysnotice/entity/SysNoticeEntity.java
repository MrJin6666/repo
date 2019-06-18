package com.goboosoft.modules.sysnotice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.goboosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 公告
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-11
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sys_notice")
public class SysNoticeEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 标题
     */
	private String title;
    /**
     * 内容
     */
	private String content;
    /**
     * 上传人
     */
	private String uploadPerson;
    /**
     * 图片
     */
	private String picture;
    /**
     * 部门id
     */
	private Long depId;
    /**
     * 更新者
     */
	private Long updater;
    /**
     * 更新时间
     */
	private Date updateDate;
}