package com.goboosoft.industry.multiple.service;

import com.goboosoft.common.utils.Result;
import com.goboosoft.industry.multiple.dto.AddLinkManDTO;
import com.goboosoft.industry.multiple.dto.UpdateUserDTO;
import com.goboosoft.system.sys.dto.SysUserDTO;
import com.goboosoft.system.sys.entity.SysUserEntity;

/**
 * 添加联系人
 * @author jinxin
 * created time 2019/3/7
 */
public interface AddLinkManToSysUserService  {
    /**
     * 增加联系人
     * @param addLinkManDTO
     * @return
     */
    Result<SysUserEntity> addLinkManToSysUser(AddLinkManDTO addLinkManDTO);

    /**
     * 修改人员信息
     * @param updateUserDTO
     * @return
     */
    Result<SysUserDTO> updateUserToSysUser(UpdateUserDTO updateUserDTO);
}
