package com.goboosoft.modules.busininspect.dao;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.modules.busininspect.entity.BusinInspectionCorrectionEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * 业务 - 整改基本信息表
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-15
 */
@Mapper
public interface BusinInspectionCorrectionDao extends BaseDao<BusinInspectionCorrectionEntity> {

    IPage<Map<String, Object>> companyScreenRe(IPage page, Map<String, Object> params);
}