package com.goboosoft.industry.multiple.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * 查询部门
 * @author JINXIN
 */
@Data
@ApiModel(value = "部门")
public class QueryDeptAndChildDeptDTO {
    /**
     * 公司id
     */
    private String companyId;
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 部门id
     */
    private Long deptId;
    /**
     * 子部门
     */
    private List<QueryDeptAndChildDeptDTO> child;
}
