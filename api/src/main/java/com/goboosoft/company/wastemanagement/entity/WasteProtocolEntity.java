package com.goboosoft.company.wastemanagement.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.goboosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

/**
 * 企业危废管理协议
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-14
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("company_waste_protocol")
public class WasteProtocolEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 协议名称
     */
    private String protocolName;
    /**
     * 开始时间
     */
    private String startDate;
    /**
     * 结束时间
     */
    private String endDate;
    /**
     * 企业id
     */
    private Long companyId;
    /**
     * 协议内容
     */
    private String content;
}
