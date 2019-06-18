package com.goboosoft.industry.statistics.service.impl;

import com.goboosoft.common.service.impl.BaseServiceImpl;
import com.goboosoft.industry.company.dto.QueryCompanyListDTO;
import com.goboosoft.industry.company.service.QueryCompanyListService;
import com.goboosoft.industry.statistics.dao.MajorRegulatoryDao;
import com.goboosoft.industry.statistics.dto.MajorRegulatoryCompanyListDTO;
import com.goboosoft.industry.statistics.dto.MajorRegulatoryDTO;
import com.goboosoft.industry.statistics.service.MajorRegulatoryService;
import com.goboosoft.industry.supervisionandinspection.entity.BusinInspectionTaskEntity;
import com.goboosoft.system.security.user.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 统计-重点监管
 *
 * @author jinxin
 */
@Service
public class MajorRegulatoryServiceImpl extends BaseServiceImpl<MajorRegulatoryDao, BusinInspectionTaskEntity> implements MajorRegulatoryService {
    @Autowired
    private MajorRegulatoryDao majorRegulatoryDao;
    @Autowired
    private QueryCompanyListService queryCompanyListService;

    @Override
    public List<MajorRegulatoryDTO> queryCompanyList(Map<String, Object> params) {
        Long userId = SecurityUser.getUserId();
        params.put("userId", userId);
        List<MajorRegulatoryCompanyListDTO> majorRegulatoryCompanyListDTOS = majorRegulatoryDao.queryMajorRegulatoryCompanyList(params);
        List<MajorRegulatoryDTO> list = new ArrayList<>();
        for (MajorRegulatoryCompanyListDTO majorRegulatoryCompanyListDTO : majorRegulatoryCompanyListDTOS) {
            MajorRegulatoryDTO majorRegulatoryDTO = new MajorRegulatoryDTO();
            String companyId = majorRegulatoryCompanyListDTO.getCompanyId();
            String companyName = majorRegulatoryCompanyListDTO.getCompanyName();
            String rownum = majorRegulatoryCompanyListDTO.getRownum();
            params.put("companyId", companyId);
            params.put("checkStatus", 0);
            Integer redCardCount = majorRegulatoryDao.queryCompanyList(params);
            majorRegulatoryDTO.setRedCardCount(redCardCount);
            params.put("checkStatus", 1);
            Integer yellowCardCount = majorRegulatoryDao.queryCompanyList(params);
            majorRegulatoryDTO.setYellowCardCount(yellowCardCount);
            params.put("checkStatus", 2);
            Integer ordinaryCardCount = majorRegulatoryDao.queryCompanyList(params);
            majorRegulatoryDTO.setCompanyName(companyName);
            majorRegulatoryDTO.setOrdinaryCardCount(ordinaryCardCount);
            majorRegulatoryDTO.setCompanyId(Long.parseLong(companyId));
            majorRegulatoryDTO.setRownum(rownum);
            list.add(majorRegulatoryDTO);
        }
        Collections.sort(list);
        return list;
    }

    /**
     * 重点监管企业名称
     * @param params
     * @return
     */
    @Override
    public List<MajorRegulatoryCompanyListDTO> queryMajorRegulatoryCompanyList(Map<String, Object> params) {
        Long userId = SecurityUser.getUserId();
        params.put("userId",userId);
        return majorRegulatoryDao. queryMajorRegulatoryCompanyList(params);
    }
}
