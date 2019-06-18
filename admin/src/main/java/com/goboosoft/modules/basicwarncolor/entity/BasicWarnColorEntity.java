package com.goboosoft.modules.basicwarncolor.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.goboosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 过期警告颜色
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-12
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("basic_warn_color")
public class BasicWarnColorEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 超期时间 天
     */
	private Long beyond;
    /**
     * 提醒颜色
     */
	private String color;
}