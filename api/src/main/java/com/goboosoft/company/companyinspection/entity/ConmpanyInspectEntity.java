package com.goboosoft.company.companyinspection.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.goboosoft.common.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 企业检查项
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-06
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("conmpany_inspect")
public class ConmpanyInspectEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 检查项id
     */
	private Long listId;
    /**
     * 用户id
     */
	private Long userId;
    /**
     * 图片
     */
	private String picture;
    /**
     * 检查项实例
     */
	private String roomId;
    /**
     * 
     */
	private Date createDate;

	/**
	 *公司id
	 */
	private Long companyId;
	/**
	 *名称
	 */
	private String name;
}