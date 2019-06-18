package com.goboosoft.company.busininspect.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.goboosoft.common.service.impl.CrudServiceImpl;
import com.goboosoft.company.busininspect.dao.BusinInspectionCorrectionDao;
import com.goboosoft.company.busininspect.dto.BusinInspectionCorrectionDTO;
import com.goboosoft.company.busininspect.dto.InspectionCorrectionDetailsDTO;
import com.goboosoft.company.busininspect.dto.InspectionCorrectionListDTO;
import com.goboosoft.company.busininspect.entity.BusinInspectionCorrectionEntity;
import com.goboosoft.company.busininspect.service.BusinInspectionCorrectionService;
import com.goboosoft.system.security.user.SecurityUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 业务 - 整改基本信息表
 *
 * @author DMXUAN
 * @since 1.0.0 2019-03-19
 */
@Service
public class BusinInspectionCorrectionServiceImpl extends CrudServiceImpl<BusinInspectionCorrectionDao, BusinInspectionCorrectionEntity, BusinInspectionCorrectionDTO> implements BusinInspectionCorrectionService {

    @Override
    public QueryWrapper<BusinInspectionCorrectionEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<BusinInspectionCorrectionEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }

    @Override
    public Long getChild(Long id) {
        return baseDao.getChild(id);
    }

    @Override
    public List<Integer> getStatus(Long id) {
        return baseDao.getStatus(id);
    }

    @Override
    public Integer getInspectionCorrectionCountByCompanyId(Long companyId) {
        int correctionCount = baseDao.getInspectionCorrectionListByCompanyId(companyId).size();
        return correctionCount;
    }

    @Override
    public List<InspectionCorrectionListDTO> getInspectionCorrectionListByCompanyId(Map<String, Object> params) {
        Long companyId;
        if ("".equals(((String) params.get("companyId")))){
            companyId = SecurityUser.getUser().getCompanyId();
            params.put("companyId",companyId);
        }
        List<InspectionCorrectionListDTO> correctionList = baseDao.getInspectionCorrectionListByParams(params);
        return correctionList;
    }

    @Override
    public InspectionCorrectionDetailsDTO getInspectionCorrectionDetailsById(Long id) {
        InspectionCorrectionDetailsDTO correctionDetails = baseDao.getInspectionCorrectionDetailsById(id);
        return correctionDetails;
    }
}