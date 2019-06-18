package com.goboosoft.company.manageread.service;


import com.goboosoft.common.service.CrudService;
import com.goboosoft.company.manageread.dto.ManageReadDTO;
import com.goboosoft.company.manageread.entity.ManageReadEntity;

import java.util.List;

/**
 * 公告记录
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-20
 */
public interface ManageReadService extends CrudService<ManageReadEntity, ManageReadDTO> {

    List<ManageReadDTO> getLists(Long id, Integer isCompany);

    List<ManageReadDTO> selectLists(Long id, Long manageId);

    Integer deleteManageRead(Long id, Integer isCompany, Long userId);
}