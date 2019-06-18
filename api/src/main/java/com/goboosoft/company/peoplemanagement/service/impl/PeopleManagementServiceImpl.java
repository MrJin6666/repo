package com.goboosoft.company.peoplemanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.goboosoft.common.page.PageData;
import com.goboosoft.common.service.impl.BaseServiceImpl;
import com.goboosoft.common.utils.ConvertUtils;
import com.goboosoft.common.utils.Result;
import com.goboosoft.company.companymenuuser.entity.CompanyUserMenuEntity;
import com.goboosoft.company.companymenuuser.service.CompanyMenuService;
import com.goboosoft.company.companymenuuser.service.CompanyUserMenuService;
import com.goboosoft.company.peoplemanagement.dao.PeopleManagementDao;
import com.goboosoft.company.peoplemanagement.dto.PeopleAddManageDTO;
import com.goboosoft.company.peoplemanagement.dto.PeopleAuthorityDTO;
import com.goboosoft.company.peoplemanagement.dto.PeopleManagementDTO;
import com.goboosoft.company.peoplemanagement.entity.PeopleManagementEntity;
import com.goboosoft.company.peoplemanagement.service.PeopleManagementService;
import com.goboosoft.system.security.user.SecurityUser;
import com.goboosoft.system.sys.dto.SysUserDTO;
import com.goboosoft.system.sys.entity.SysDictEntity;
import com.goboosoft.system.sys.entity.SysUserEntity;
import com.goboosoft.system.sys.service.SysUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询 人员管理 实现类
 * @author yangfl
 * created time 2019/3/6
 */
@Service
@Transactional
public class PeopleManagementServiceImpl extends BaseServiceImpl<PeopleManagementDao,PeopleManagementEntity> implements PeopleManagementService {

    @Autowired
    private PeopleManagementDao peopleManagementDao;

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private CompanyMenuService companyMenuService;
    @Autowired
    private CompanyUserMenuService companyUserMenuService;

    /**
     * 返回的页数据11
     * @param companyId
     * @return
     */
    @Override
    public PageData<PeopleManagementDTO> getUserManage(Long companyId) {
        QueryWrapper<PeopleManagementEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("company_id", companyId);
        IPage iPage = baseDao.selectPage(new Page<>(), wrapper);
        List<PeopleManagementDTO> targetList = ConvertUtils.sourceToTarget( iPage.getRecords(), PeopleManagementDTO.class);
        return  new PageData(targetList,iPage.getTotal());
    }


    /**
     * 返回人员管理数据列表
     * @param params
     * @return
     */
    @Override
    public List<PeopleManagementDTO> peopleList(Map<String, Object> params) {

        List<PeopleManagementDTO> peopleManagement = peopleManagementDao.peopleList(params);
        for(PeopleManagementDTO peopleManagementDTO : peopleManagement){
            List<CompanyUserMenuEntity> userMenuEntities = companyUserMenuService.getByUserId(peopleManagementDTO.getId());
            List<String> list = new ArrayList<>();
            for(CompanyUserMenuEntity companyUserMenuEntity : userMenuEntities) {
                list.add(companyUserMenuEntity.getCompanyMenuModule());
            }
            peopleManagementDTO.setManuList(list);
            System.out.println();
        }
        return peopleManagement;
    }

    /**
     * 返回人员权限列列表
     * @param
     * @return
     */
    @Override
    public List<PeopleAuthorityDTO> peopleAuthorityList(String dictType) {
        List<SysDictEntity> peopleManagement = peopleManagementDao.authorityManageList(dictType);
        List<PeopleAuthorityDTO> targetList = ConvertUtils.sourceToTarget(peopleManagement, PeopleAuthorityDTO.class);
        return targetList;
    }

    /**
     * 人员管理 --- 添加用户
     * @param peopleAddManageDTO
     * @return
     */
    @Override
    // TODO: yfl 2019/3/11 事务
    public Result peopleAdd(PeopleAddManageDTO peopleAddManageDTO) {
        Result result = new Result();
        Long userId = SecurityUser.getUserId();
        String mobile = peopleAddManageDTO.getMobile();
        Boolean exist = sysUserService.isExist(mobile);
        if(!exist){
            // TODO:  yfl 2019/3/11 判断是否本公司员工
            return result.error("用户已存在，不允许添加此用户");
        }
        //随机生成6位数字，并用MD5加密
        String passwordStr = RandomStringUtils.randomNumeric(6);
        // TODO:  yfl 2019/3/11 短信发送

        String password = DigestUtils.sha256Hex(passwordStr);
        SysUserEntity user=new SysUserEntity();
        user.setRealName(peopleAddManageDTO.getUserName());
        user.setUsername(peopleAddManageDTO.getMobile());
        user.setMobile(peopleAddManageDTO.getMobile());
        user.setDeptId(null);
        user.setSuperAdmin(0);
        user.setCreator(SecurityUser.getUserId());
        user.setCompanyId(SecurityUser.getUser().getCompanyId());
        user.setPassword(password);
        user.setStatus(1);
        user.setCount(0);
        user.setIsCompany(0);
        boolean insert = sysUserService.insert(user);
        if(!insert){
            return result.error("增加失败");
        }
        //添加权限
        Map map = new HashMap<>();
        map.put("userId",user.getId());
        map.put("list",peopleAddManageDTO.getManuList());
        companyUserMenuService.saveModule(map);
        return result.ok(user);
    }


}
