package com.goboosoft.system.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.goboosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-14
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sys_region")
public class SysRegionEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 
     */
	private String name;
    /**
     * 
     */
	private Integer level;
    /**
     * 
     */
	private Double parentId;
}