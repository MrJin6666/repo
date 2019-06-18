package com.goboosoft.modules.industrytasklist.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.goboosoft.common.constant.Constant;
import com.goboosoft.common.page.PageData;
import com.goboosoft.common.service.impl.CrudServiceImpl;
import com.goboosoft.common.utils.ConvertUtils;
import com.goboosoft.common.utils.GenerateOrderUtil;
import com.goboosoft.common.validator.ValidatorUtils;
import com.goboosoft.common.validator.group.AddGroup;
import com.goboosoft.common.validator.group.DefaultGroup;
import com.goboosoft.modules.industrytasklist.dao.IndustryTaskListDao;
import com.goboosoft.modules.industrytasklist.dto.IndustryTaskListDTO;
import com.goboosoft.modules.industrytasklist.dto.IndustryTaskLogDTO;
import com.goboosoft.modules.industrytasklist.entity.IndustryTaskListEntity;
import com.goboosoft.modules.industrytasklist.service.IndustryTaskListService;
import com.goboosoft.modules.industrytasklist.service.IndustryTaskLogService;
import com.goboosoft.modules.security.user.SecurityUser;
import com.goboosoft.modules.sys.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

/**
 * 行业 - 新任务发布表
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-12
 */
@Service
public class IndustryTaskListServiceImpl extends CrudServiceImpl<IndustryTaskListDao, IndustryTaskListEntity, IndustryTaskListDTO> implements IndustryTaskListService {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private IndustryTaskLogService industryTaskLogService;

    @Override
    public QueryWrapper<IndustryTaskListEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");
        Long receiverId = (Long)params.get("receiverId");

        QueryWrapper<IndustryTaskListEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);
        wrapper.eq(receiverId != null, "receiver_id", receiverId);

        return wrapper;
    }

    @Override
    public PageData<IndustryTaskListDTO> pageList(Map<String, Object> params) {
        IPage<IndustryTaskListDTO> pageList = baseDao.findPageList(getPage(params, Constant.CREATE_DATE, false),params);
        PageData<IndustryTaskListDTO> objectPageData = new PageData<IndustryTaskListDTO>(pageList.getRecords(), pageList.getTotal());
        return objectPageData;
    }

    @Override
    @Transactional
    public void industry(IndustryTaskListDTO dto) {
        IndustryTaskListDTO industryTaskListDTO = this.get(dto.getId());
        industryTaskListDTO.setReceiverId(dto.getReceiverId());
        IndustryTaskLogDTO industryTaskLogDTO = new IndustryTaskLogDTO();
        String realName = sysUserService.get(SecurityUser.getUserId()).getRealName();
        String realNameRe = sysUserService.get(dto.getReceiverId()).getRealName();
        industryTaskLogDTO.setRemark(realName + "将任务指派给" + realNameRe);
        industryTaskLogDTO.setStatus(industryTaskListDTO.getStatus());
        industryTaskLogDTO.setReceiverId(dto.getReceiverId());
        industryTaskLogDTO.setIndustryTaskId(industryTaskListDTO.getId());
        industryTaskLogDTO.setCreateDate(new Date());
        industryTaskLogService.save(industryTaskLogDTO);
        this.update(industryTaskListDTO);
    }

    @Override
    @Transactional
    public void saveDTO(IndustryTaskListDTO dto) {
        dto.setReceiverId(dto.getReceiverId());
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        dto.setStatus(0);
        dto.setOrderNumber(GenerateOrderUtil.doOrderNum());
        IndustryTaskListEntity industryTaskListEntity = ConvertUtils.sourceToTarget(dto, IndustryTaskListEntity.class);
        industryTaskListEntity.setCreateDate(new Date());
        this.insert(industryTaskListEntity);
        IndustryTaskLogDTO industryTaskLogDTO = new IndustryTaskLogDTO();
        // 查询当前人
        String realName = sysUserService.get(SecurityUser.getUserId()).getRealName();
        String realNameRe = sysUserService.get(dto.getReceiverId()).getRealName();
        industryTaskLogDTO.setRemark(realName+"创建了任务并将任务指派给"+ realNameRe);
        industryTaskLogDTO.setStatus(0);
        industryTaskLogDTO.setReceiverId(dto.getReceiverId());
        industryTaskLogDTO.setIndustryTaskId(industryTaskListEntity.getId());
        industryTaskLogDTO.setCreateDate(new Date());
        industryTaskLogService.save(industryTaskLogDTO);
    }
}