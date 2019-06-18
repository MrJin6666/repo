/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.goboosoft.system.security.controller;

import com.goboosoft.common.annotation.LogOperation;
import com.goboosoft.common.constant.Constant;
import com.goboosoft.common.exception.ErrorCode;
import com.goboosoft.common.exception.RenException;
import com.goboosoft.common.utils.IpUtils;
import com.goboosoft.common.utils.PushUtil;
import com.goboosoft.common.utils.Result;
import com.goboosoft.company.companymenuuser.entity.CompanyUserMenuEntity;
import com.goboosoft.company.companymenuuser.service.CompanyMenuService;
import com.goboosoft.company.companymenuuser.service.CompanyUserMenuService;
import com.goboosoft.industry.multiple.dto.RegisterDTO;
import com.goboosoft.system.log.entity.SysLogLoginEntity;
import com.goboosoft.system.log.enums.LoginOperationEnum;
import com.goboosoft.system.log.enums.LoginStatusEnum;
import com.goboosoft.system.log.service.SysLogLoginService;
import com.goboosoft.system.security.service.SysUserTokenService;
import com.goboosoft.system.security.user.SecurityUser;
import com.goboosoft.system.security.user.UserDetail;
import com.goboosoft.system.sys.dto.SysDeptDTO;
import com.goboosoft.system.sys.dto.SysUserDTO;
import com.goboosoft.system.sys.entity.SysUserEntity;
import com.goboosoft.system.sys.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 登录
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/loginManager")
@Api(tags = "登录管理")
public class LoginController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserTokenService sysUserTokenService;
    @Autowired
    private SysLogLoginService sysLogLoginService;
    @Autowired
    private SysDeptService sysDeptService;
    @Autowired
    private CompanyMenuService companyMenuService;
    @Autowired
    private CompanyUserMenuService companyUserMenuService;
    @Autowired
    private PushUtil pushUtil;


    @PostMapping("logout")
    @ApiOperation(value = "退出")
    public Result logout(HttpServletRequest request) {
        UserDetail user = SecurityUser.getUser();

        //退出
        sysUserTokenService.logout(user.getId());

        //用户信息
        SysLogLoginEntity log = new SysLogLoginEntity();
        log.setOperation(LoginOperationEnum.LOGOUT.value());
        log.setIp(IpUtils.getIpAddr(request));
        log.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
        log.setIp(IpUtils.getIpAddr(request));
        log.setStatus(LoginStatusEnum.SUCCESS.value());
        log.setCreator(user.getId());
        log.setCreatorName(user.getUsername());
        log.setCreateDate(new Date());
        sysLogLoginService.save(log);

        return new Result();
    }

    /**
     * 获取行业部门及负责人
     * @param deptId
     * @return
     */
    @GetMapping("getIndrutryAndUserName")
    @ApiOperation(value = "获取行业部门及负责人")
    public Result<List<SysDeptDTO>> getIndrutryAndUserName(@RequestParam(value = "deptId", required = false) String deptId) {
        Long pid = Long.valueOf(0);
        if (!(deptId == null)) {
            pid = Long.parseLong(deptId);
        }
        List<SysDeptDTO> deptList = sysDeptService.getDeptList(pid);

        return new Result<List<SysDeptDTO>>().ok(deptList);
    }

    @PostMapping("register")
    @ApiOperation(value = "企业/行业 注册")
    public Result indrustryRegister(HttpServletRequest request, @RequestBody RegisterDTO dto) {
        try {

            List<String> list = new ArrayList<String>();
            list.add(Constant.MenuService.QYGL.value());

            Boolean exist = sysUserService.isExist(dto.getMobile());
            if (!exist) {
                return new Result().error("此手机号已被注册，请登录！");
            }

            Boolean flag = sysUserService.checkCaptcha(dto.getMobile(), dto.getCaptcha());
            if (!flag) {
                return new Result().ok("验证码错误或失效！");
            }

            SysUserEntity user = new SysUserEntity();
            if(StringUtils.isBlank(dto.getRealName())){
                user.setRealName(dto.getMobile());
            }else{
                user.setRealName(dto.getRealName());
            }

            user.setMobile(dto.getMobile());
            user.setPassword(DigestUtils.sha256Hex(dto.getPassword()));
            user.setCreateDate(new Date());
            user.setIsCompany(dto.getIsCompany());
            user.setUsername(dto.getMobile());
            Map<String, Object> map = new HashMap<String, Object>();
            if (dto.getIsCompany() == Constant.CompanyService.COMPANY.value()) {
                user.setStatus(Constant.UserService.NORMAL.value());
                map.put("list", list);
            } else {
                user.setStatus(Constant.UserService.CHECK.value());
                user.setDeptId(dto.getDeptId());
            }
            sysUserService.insert(user);
            map.put("userId", user.getId());
            companyMenuService.setMenuByUserId(map);
            return new Result().ok("注册成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result().error("注册失败！");
        }

    }


    @GetMapping("/sendCaptcha")
    @ApiOperation(value = "发送验证码")
    public Result sendCaptcha(String mobile, int type) {
        return sysUserService.sendCaptcha(mobile, type);
    }

    @GetMapping("/updateSendCaptcha")
    @ApiOperation(value = "修改密码时发送验证码")
    public Result sendCaptcha(String mobile) {
        return sysUserService.sendCaptcha(mobile, 3);
    }



    @PostMapping(value = "/login")
    @ApiOperation("企业/行业 登录")
    public Result indrustruLogin(@RequestBody Map<String, String> map) {
        String mobile = map.get("mobile");
        String passwd = map.get("passwd");
        int isCompany = Integer.parseInt(map.get("isCompany"));
        String registrationId = map.get("registrationId");

        //用户信息
        SysUserDTO user = sysUserService.getByPhone(mobile);

        //用户不存在
        if (user == null) {
            return new Result().error("账号或密码错误！");
        }
        //密码不相等
        if (!user.getPassword().equals(DigestUtils.sha256Hex(passwd))) {
            return new Result().error("账号或密码错误！");
        }

        //验证用户身份
        if (user.getIsCompany() != isCompany) {
            return new Result().error("用户账号或密码错误！");
        }

        //账号停用
        if (user.getStatus() == Constant.UserService.FAIL.value()) {
            throw new RenException(ErrorCode.ACCOUNT_DISABLE);
        }

        //账号正在审核
        if (user.getStatus() == Constant.UserService.CHECK.value()) {
            return new Result().error("账号正在审核中，稍后重试！");
        }

        //账号审核不通过
        if (user.getStatus() == Constant.UserService.REJECT.value()) {
            return new Result().error("审核不通过！");
        }

        Integer num = sysUserService.updateRegistrationId(registrationId, user.getId());
        //根据查询的结果进行更新的结果判断是否成功
        if(num > 0) {
            return sysUserTokenService.createToken(user.getId());
        }else{
            return new Result().error("登录失败！");
        }
    }

    @PostMapping("/updatePassword")
    @ApiOperation("修改密码")
    public Result password(@RequestBody SysUserDTO dto) {
        Boolean flag = sysUserService.checkCaptcha(dto.getMobile(), dto.getCaptcha());
        if (!flag) {
            return new Result().error("验证码错误或失效！");
        }
        sysUserService.updatePassword(sysUserService.selectByMobile(dto.getMobile()), dto.getPassword());
        return new Result();
    }

    @PutMapping("getUser")
    @ApiOperation("获取用户详情")
    @LogOperation("获取用户详情")
    public Result<SysUserDTO> getUser() {
        Long userId = SecurityUser.getUserId();
        SysUserDTO sysUserDTO = sysUserService.getUser(userId);
        List<CompanyUserMenuEntity> list = companyUserMenuService.getByUserId(userId);
        sysUserDTO.setCompanyUserMenuEntityList(list);
        return new Result<SysUserDTO>().ok(sysUserDTO);
    }

    @PostMapping("/forgetPassword")
    @ApiOperation("忘记密码")
    public Result forgetPassword(@RequestBody SysUserDTO dto) {
        Boolean flag = sysUserService.checkCaptcha(dto.getMobile(), dto.getCaptcha());
        if (!flag) {
            return new Result().error("验证码错误或失效！");
        }
        sysUserService.updatePassword(sysUserService.selectByMobile(dto.getMobile()), dto.getPassword());
        return new Result();
    }

    @PostMapping("/updateSysUser")
    @ApiOperation("修改头像或昵称")
    public Result updateSysUser(@RequestBody Map<String, String> map) {
        try {

            sysUserService.updateHeadurlAndUserName(map,SecurityUser.getUserId());
            return new Result().ok("修改成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result().ok("修改失败！");
        }
    }

    @PostMapping("/test")
    @ApiOperation("测试推送")
    public Result test() {


        pushUtil.industryPushMessage(PushUtil.PushMsgType.TEST);

        return new Result().ok("");

    }
}