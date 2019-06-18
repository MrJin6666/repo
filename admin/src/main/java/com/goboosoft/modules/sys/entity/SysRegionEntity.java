package com.goboosoft.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.goboosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-14
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sys_region")
public class SysRegionEntity {
	private static final long serialVersionUID = 1L;

	private long id;
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