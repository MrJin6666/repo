package com.goboosoft.company.govern.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.goboosoft.common.service.impl.CrudServiceImpl;
import com.goboosoft.common.utils.ConvertUtils;
import com.goboosoft.company.busininspect.utils.PhotoJoint;
import com.goboosoft.company.govern.dao.GovernProcessPlanDao;
import com.goboosoft.company.govern.dto.GovernProcessPlanDTO;
import com.goboosoft.company.govern.entity.GovernProcessPlanEntity;
import com.goboosoft.company.govern.service.GovernPlanService;
import com.goboosoft.company.govern.service.GovernProcessPlanService;
import com.goboosoft.system.sys.dto.SysUserDTO;
import com.goboosoft.system.sys.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 治理计划过程
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-08
 */
@Service
public class GovernProcessPlanServiceImpl extends CrudServiceImpl<GovernProcessPlanDao, GovernProcessPlanEntity, GovernProcessPlanDTO> implements GovernProcessPlanService {

    @Autowired
    private GovernPlanService  governPlanService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private PhotoJoint photoJoint;
    @Override
    public QueryWrapper<GovernProcessPlanEntity> getWrapper(Map<String, Object> params) {
        String id = (String) params.get("id");

        QueryWrapper<GovernProcessPlanEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }

    @Override
    @Transactional
    public Integer addGorvenPlanPicture(Long planId) {
        GovernProcessPlanEntity governProcessPlanEntity = new GovernProcessPlanEntity();
        governProcessPlanEntity.setGovernPlanId(planId);
        return baseDao.insert(governProcessPlanEntity);
    }

    @Override
    public Map<String,Object> getProcessPlan(Long id) {
        Map<String,Object> map = new HashMap<String,Object>();
        List<GovernProcessPlanEntity> governProcessPlanEntities = baseDao.selectListByProcess(id);
        for(GovernProcessPlanEntity governProcessPlanEntity : governProcessPlanEntities){
            map.put("finishDate",governProcessPlanEntity.getCreateDate());
            if(governProcessPlanEntity.getProcess()!=null) {
                if (governProcessPlanEntity.getProcess() == 6) {//处理前
                    String photoPath = photoJoint.addPhotoPath(governProcessPlanEntity.getPicture());
                    map.put("pictureBefore", photoPath);
                    SysUserDTO sysUserDTO = sysUserService.get(governProcessPlanEntity.getCreator());
                    map.put("personBefore",sysUserDTO.getRealName());
                    map.put("dateBefore",governProcessPlanEntity.getCreateDate());
                }
                if (governProcessPlanEntity.getProcess() == 2) {
                    String photoPath = photoJoint.addPhotoPath(governProcessPlanEntity.getPicture());
                    map.put("pictureAfter", photoPath);
                    map.put("describAfter", governProcessPlanEntity.getDescrib());
                    SysUserDTO sysUserDTO = sysUserService.get(governProcessPlanEntity.getCreator());
                    map.put("personAfter",sysUserDTO.getRealName());
                    map.put("dateAfter",governProcessPlanEntity.getCreateDate());
                }
                if (governProcessPlanEntity.getProcess() == 3) {
                    map.put("sign", photoJoint.addPhotoPath(governProcessPlanEntity.getPicture()));
                    map.put("dateFinish",governProcessPlanEntity.getCreateDate());
                }
                if (governProcessPlanEntity.getProcess() == 1) {//处理中
                    String photoPath = photoJoint.addPhotoPath(governProcessPlanEntity.getPicture());
                    map.put("pictureProcess", photoPath);
                    map.put("describProcess", governProcessPlanEntity.getDescrib());
                    SysUserDTO sysUserDTO = sysUserService.get(governProcessPlanEntity.getCreator());
                    map.put("personProcess",sysUserDTO.getRealName());
                    map.put("dateProcess",governProcessPlanEntity.getCreateDate());
                }
            }
        }
        return map;
    }

    @Override
    public GovernProcessPlanEntity selectLists(QueryWrapper wrapper){
        return baseDao.selectOne(wrapper);
    }
}