package com.goboosoft.industry.multiple.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 基础 - 检查项/检查内容信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-02-14
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("basic_inspection_list")
public class BasicInspectionListEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Long id;
    /**
     * 检查项/检查内容名称
     */
	private String name;
    /**
     * 级别 0检查项 1检查内容
     */
	private String level;
    /**
     * 上级主键
     */
	private Long pid;
    /**
     * 总分
     */
	private Integer totalScore;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Integer getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}
}