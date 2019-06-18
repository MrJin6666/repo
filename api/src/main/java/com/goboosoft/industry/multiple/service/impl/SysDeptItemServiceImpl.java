package com.goboosoft.industry.multiple.service.impl;


import com.goboosoft.industry.multiple.dao.SysDeptItemDao;
import com.goboosoft.industry.multiple.dto.SysDeptItemDTO;
import com.goboosoft.industry.multiple.dto.SysSameLevelDeptDTO;
import com.goboosoft.industry.multiple.service.QuerySysDeptService;
import com.goboosoft.industry.multiple.service.SysDeptItemService;
import com.goboosoft.system.sys.entity.SysDeptEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 部门详情列表
 * @author jinxin
 * created time 2019/3/6
 */
@Service
public class SysDeptItemServiceImpl implements SysDeptItemService {
    @Autowired
    private SysDeptItemDao sysDeptItemDao;
    @Autowired
    private QuerySysDeptService querySysDeptService;

    /**
     * 通过部门id查询部门详情列表
     * @param id
     * @return
     */
    @Override
    public List<SysDeptItemDTO> getSysDeptItemListBySysDeptId(Long id) {
        List<SysDeptItemDTO> list = sysDeptItemDao.getSysDeptItemListBySysDeptId(id);
        return list;
    }

    @Override
    public List<SysSameLevelDeptDTO> getSysDeptAndSameLevelListBySysDeptId(Long id) {
        SysDeptEntity sysDeptEntity = querySysDeptService.selectById(id);
        Long pid = sysDeptEntity.getPid();
        return sysDeptItemDao.getSysDeptAndSameLevelListBySysDeptId(id,pid);

    }

    @Override
    public List<SysSameLevelDeptDTO> getSysDeptNextLevelListBySysDeptId(Long id) {
        return sysDeptItemDao.getSysDeptNextLevelListBySysDeptId(id);
    }
}
