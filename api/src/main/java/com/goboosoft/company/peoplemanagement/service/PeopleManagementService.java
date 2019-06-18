package com.goboosoft.company.peoplemanagement.service;

import com.goboosoft.common.page.PageData;
import com.goboosoft.common.service.BaseService;
import com.goboosoft.common.utils.Result;
import com.goboosoft.company.peoplemanagement.dto.PeopleAddManageDTO;
import com.goboosoft.company.peoplemanagement.dto.PeopleAuthorityDTO;
import com.goboosoft.company.peoplemanagement.dto.PeopleManagementDTO;
import com.goboosoft.company.peoplemanagement.entity.PeopleManagementEntity;
import com.goboosoft.system.sys.entity.SysUserEntity;

import java.util.List;
import java.util.Map;

/**
 * 查询 联系人-人员管理 接口
 * @author yangfl
 * created time 2019/3/6
 */
public interface PeopleManagementService extends BaseService<PeopleManagementEntity> {

    PageData<PeopleManagementDTO> getUserManage(Long companyId);//11

    List<PeopleManagementDTO> peopleList(Map<String, Object> params);

    List<PeopleAuthorityDTO> peopleAuthorityList(String dictType);

    Result<SysUserEntity> peopleAdd(PeopleAddManageDTO peopleAddManageDTO);
}
