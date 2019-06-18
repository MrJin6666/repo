package com.goboosoft.industry.statistics.service;

import com.goboosoft.common.service.BaseService;
import com.goboosoft.industry.statistics.dto.MajorRegulatoryCompanyListDTO;
import com.goboosoft.industry.statistics.dto.MajorRegulatoryDTO;
import com.goboosoft.industry.supervisionandinspection.entity.BusinInspectionTaskEntity;

import java.util.List;
import java.util.Map;

/**
 * 统计-重点监管
 *
 * @author jinxin
 */
public interface MajorRegulatoryService extends BaseService<BusinInspectionTaskEntity> {
    List<MajorRegulatoryDTO> queryCompanyList(Map<String, Object> params);

    /**
     * 查询重点监管企业名称
     * @param params
     * @return
     */
    List<MajorRegulatoryCompanyListDTO> queryMajorRegulatoryCompanyList(Map<String,Object> params);
}
