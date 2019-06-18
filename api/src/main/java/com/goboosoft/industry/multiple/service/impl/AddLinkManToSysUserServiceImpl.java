package com.goboosoft.industry.multiple.service.impl;

import com.goboosoft.common.utils.Result;
import com.goboosoft.industry.multiple.dao.LinkManDao;
import com.goboosoft.industry.multiple.dao.QuerySysUserDao;
import com.goboosoft.industry.multiple.dto.AddLinkManDTO;
import com.goboosoft.industry.multiple.dto.UpdateUserDTO;
import com.goboosoft.industry.multiple.service.AddLinkManToSysUserService;
import com.goboosoft.system.message.service.SysSmsService;
import com.goboosoft.system.security.user.SecurityUser;
import com.goboosoft.system.sys.dao.SysUserDao;
import com.goboosoft.system.sys.dto.SysUserDTO;
import com.goboosoft.system.sys.entity.SysUserEntity;
import com.goboosoft.system.sys.service.SysUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 添加联系人
 * @author jinxin
 * created time 2019/3/8
 */
@Service
@Transactional
public class AddLinkManToSysUserServiceImpl implements AddLinkManToSysUserService {
    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private QuerySysUserDao querySysUserDao;
    @Autowired
    private LinkManDao linkManDao;
    @Autowired
    private SysSmsService sysSmsService;
    @Value("${SMS.signName}")
    private String signName;
    @Value("${SMS.template}")
    private String template;
    @Override
    @Transactional
    public Result<SysUserEntity> addLinkManToSysUser(AddLinkManDTO addLinkManDTO) {
        Long userId = SecurityUser.getUserId();
        if(userId==null||"".equals(userId)){
            return new Result<SysUserEntity>().error("用户未登录，不允许添加用户");
        }
        SysUserEntity sysUserEntity1 = linkManDao.selectById(userId);
        Integer superAdmin = sysUserEntity1.getSuperAdmin();
        if(superAdmin==0){
            return new Result<SysUserEntity>().error("不是管理员，没有权限添加联系人");
        }
        String mobile = addLinkManDTO.getMobile();
        SysUserEntity sysUserEntity = querySysUserDao.queryById(mobile);
        System.out.println(sysUserEntity);
        if(sysUserEntity!=null){
            return new Result<SysUserEntity>().error("用户已存在，不允许添加此用户");
        }
        //随机生成6位数字，并用MD5加密
        String passwordStr = RandomStringUtils.randomNumeric(6);
        String password = DigestUtils.md5Hex(passwordStr);
        SysUserEntity user=new SysUserEntity();
        user.setRealName(addLinkManDTO.getRealName());
        user.setMobile(addLinkManDTO.getMobile());
        user.setDeptId(addLinkManDTO.getDeptId());
        user.setIsDeptHead(  addLinkManDTO.getIsDeptHead());
        user.setCreator(userId);
        user.setCreateDate(new Date());
        user.setUsername(addLinkManDTO.getMobile());
        user.setPassword(password);
        user.setCount(0);
        int insert = sysUserDao.insert(user);
        if(insert==0){
            return new Result<SysUserEntity>().error("增加失败");
        }

       /* LinkedHashMap<String,String> map=new LinkedHashMap<>();
        map.put("username",user.getUsername());
        map.put("password",passwordStr);
        sysSmsService.send(user.getMobile(),map,signName,template);*/
        return new Result<SysUserEntity>().ok(user);
    }

    /**
     * 修改人员信息
     * @param updateUserDTO
     * @return
     */
    @Override
    public Result<SysUserDTO> updateUserToSysUser(UpdateUserDTO updateUserDTO) {
        Long userId = SecurityUser.getUserId();
        SysUserEntity sysUserEntity = linkManDao.selectById(userId);
        Integer isDeptHead = sysUserEntity.getIsDeptHead();
        if(isDeptHead==1){
            return new Result<SysUserDTO>().error("不是部门领导，没有修改人员信息权限");
        }
        SysUserDTO user =sysUserService.get(Long.parseLong(updateUserDTO.getId()));
        user.setRealName(updateUserDTO.getRealName());
        user.setDeptId(Long.parseLong(updateUserDTO.getDeptId()));
        user.setIsDeptHead(Integer.parseInt(updateUserDTO.getIsDeptHead()));
        sysUserService.update(user);
        return new Result<SysUserDTO>().ok(user);

    }

}
