package com.goboosoft.industry.company.dao;

import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.industry.multiple.entity.SysCompanyEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface QueryProblemCompanyNumberDao extends BaseDao<SysCompanyEntity> {
    /**
     * 获取红牌总个数
     * @param userId
     * @return
     */
    Integer queryRedCard(@Param("userId") Long userId);

    /**
     * 获取黄牌总个数
     * @param userId
     * @return
     */
    Integer queryYellowCard(@Param("userId") Long userId);

    /**
     * 获取一般总个数
     * @param userId
     * @return
     */
    Integer queryOrdinaryCard(@Param("userId") Long userId);
}
