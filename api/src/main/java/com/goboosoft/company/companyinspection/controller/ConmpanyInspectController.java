package com.goboosoft.company.companyinspection.controller;


import com.goboosoft.common.constant.Constant;
import com.goboosoft.common.page.PageData;
import com.goboosoft.common.utils.DateUtils;
import com.goboosoft.common.utils.GenerateOrderUtil;
import com.goboosoft.common.utils.Result;
import com.goboosoft.common.validator.ValidatorUtils;
import com.goboosoft.common.validator.group.AddGroup;
import com.goboosoft.common.validator.group.DefaultGroup;
import com.goboosoft.company.busininspect.utils.PhotoJoint;
import com.goboosoft.company.companyinspection.dto.ConmpanyInspectDTO;
import com.goboosoft.company.companyinspection.service.ConmpanyInspectService;
import com.goboosoft.company.companymanagelog.dto.CompanyManageLogDTO;
import com.goboosoft.company.companymanagelog.service.CompanyManageLogService;
import com.goboosoft.company.companymenuuser.dto.CompanyMenuDTO;
import com.goboosoft.company.companymenuuser.entity.CompanyMenuEntity;
import com.goboosoft.company.companymenuuser.entity.CompanyUserMenuEntity;
import com.goboosoft.company.companymenuuser.service.CompanyMenuService;
import com.goboosoft.company.companymenuuser.service.CompanyUserMenuService;
import com.goboosoft.company.govern.entity.GovernPlanEntity;
import com.goboosoft.company.govern.service.GovernPlanService;
import com.goboosoft.industry.multiple.dto.BasicInspectionListDTO;
import com.goboosoft.industry.multiple.dto.SysCompanyDTO;
import com.goboosoft.industry.multiple.entity.SysCompanyEntity;
import com.goboosoft.industry.multiple.service.InspectionListService;
import com.goboosoft.industry.multiple.service.SysCompanyService;
import com.goboosoft.industry.supervisionandinspection.service.BusinInspectionTaskService;
import com.goboosoft.system.security.user.SecurityUser;
import com.goboosoft.system.security.user.UserDetail;
import com.goboosoft.system.sys.dto.SysDeptDTO;
import com.goboosoft.system.sys.dto.SysUserDTO;
import com.goboosoft.system.sys.enums.SuperAdminEnum;
import com.goboosoft.system.sys.service.SysDeptService;
import com.goboosoft.system.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;


/**
 * 企业检查项
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-06
 */
@RestController
@RequestMapping("conmpanyinspect")
@Api(tags="企业检查项")
public class ConmpanyInspectController {
    @Autowired
    private ConmpanyInspectService conmpanyInspectService;
    @Autowired
    private InspectionListService inspectionListService;
    @Autowired
    private SysDeptService sysDeptService;
    @Autowired
    private SysCompanyService sysCompanyService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private CompanyUserMenuService companyUserMenuService;
    @Autowired
    private CompanyManageLogService companyManageLogService;
    @Autowired
    private CompanyMenuService companyMenuService;

    @Autowired
    private GovernPlanService governPlanService;
    @Autowired
    private PhotoJoint photoJoint;

    @GetMapping("page")
    @ApiOperation("获取检查项列表")
    @ApiImplicitParams({

            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
            @ApiImplicitParam(name = "id", value = "检查分类id", paramType = "query",required = true, dataType="Long") ,
            @ApiImplicitParam(name = "companyId", value = "公司id", paramType = "query",required = false, dataType="Long") ,
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    //@RequiresPermissions("demo:conmpanyinspect:page")
    public Result<PageData<ConmpanyInspectDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        params.put("userId",SecurityUser.getUserId());
        UserDetail user = SecurityUser.getUser();
        if(params.get("companyId")==null){
            params.put("companyId",user.getCompanyId());
        }else{
            params.put("companyId",Long.valueOf((String)params.get("companyId")));
        }
        PageData<ConmpanyInspectDTO> page = conmpanyInspectService.page(params);
        List<ConmpanyInspectDTO> list = page.getList();
        if(list.size()>0){
            for(ConmpanyInspectDTO conmpanyInspectDTO : list){
                conmpanyInspectDTO.setPicture(photoJoint.addPhotoPath(conmpanyInspectDTO.getPicture()));
            }
        }
        return new Result<PageData<ConmpanyInspectDTO>>().ok(page);
    }

    @PostMapping("save")
    @ApiOperation("添加企业检查项")
    public Result save(@RequestBody ConmpanyInspectDTO dto){
        Result result = new Result();
        String message = null;
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        try {
            dto.setUserId(SecurityUser.getUserId());
            if(SecurityUser.getUser().getCompanyId()!=null) {
                dto.setCompanyId(SecurityUser.getUser().getCompanyId());
            }
            dto.setCreateDate(new Date());
            dto.setCreator(SecurityUser.getUserId());
            Integer exits = conmpanyInspectService.isExits(dto);
            if(exits > 0){
                return result.error("此检查项已存在！");
            }
            conmpanyInspectService.save(dto);
            message="保存成功！";
            result.ok(message);
        }catch (Exception e){
            e.printStackTrace();
            message="保存失败！";
            result.error(message);
        }
        return result;
    }

    /**
     * 检查分类的id及检查项的id
     * @return
     */
    @PostMapping("getListName")
    @ApiOperation("获取检查分类名称及数量")
    public Result<List<BasicInspectionListDTO>> getListName(@RequestBody Map<String, Object> params){
        Long companyId;
        String param = (String) params.get("companyId");
        if (StringUtils.isEmpty(param)){
            companyId = SecurityUser.getUser().getCompanyId();
        }else {
            companyId = Long.valueOf(param);
        }
        Long userId = SecurityUser.getUserId();
        List<BasicInspectionListDTO> listName = inspectionListService.getListName(companyId,userId);
        return new Result<List<BasicInspectionListDTO>>().ok(listName);
    }

    /**
     * 检查分类的id及检查项的id
     * @param pid
     * @return
     */
    @GetMapping("getListNameAndNum")
    @ApiOperation("获取检查分类名称")
    public Result<List<BasicInspectionListDTO>> getListNameAndNum(@RequestParam(value = "id",required = true) String pid){

        List<BasicInspectionListDTO> listNames = inspectionListService.getListByPid(pid);
        return new Result<List<BasicInspectionListDTO>>().ok(listNames);
    }

    @GetMapping("getDeptName")
    @ApiOperation("获取监管部门")
    public Result<List<SysDeptDTO>> getDept(@RequestParam(value = "deptId",required = false) String deptId){
        Long pid = Long.valueOf(0);;
        if(deptId!=null) {
            pid = Long.parseLong(deptId);
        }
        List<SysDeptDTO> deptList = sysDeptService.getDeptList(pid);
        return new Result<List<SysDeptDTO>>().ok(deptList);
    }

    @GetMapping("getUserDeptName")
    @ApiOperation("获取监管部门负责人")
    public Result<List<SysUserDTO>> getUserDeptName(@RequestParam(value = "id",required = true) String id){
        List<SysUserDTO> deptList = sysUserService.getUserDeptName(id);
        return new Result<List<SysUserDTO>>().ok(deptList);
    }

    @PostMapping("saveCompany")
    @ApiOperation("企业申请")
    public Result saveCompany(@RequestBody SysCompanyDTO sysCompanyDTO){
        Result result = new Result();
        String message = null;
        Long userId = SecurityUser.getUserId();
        Map map = new HashMap();
        try {

            Integer num = conmpanyInspectService.getByName(sysCompanyDTO.getName());
            if(num == 0) {
                sysCompanyDTO.setStatus(Constant.CompanyStatusService.PENDIND.value());
                Long companyId = conmpanyInspectService.saveCompany(sysCompanyDTO, userId);

                if (companyId != null) {
                    message = "保存成功！";
                    map.put("message", message);
                    map.put("companyId", companyId);
                    result.ok(map);
                } else {
                    message = "保存失败！";
                    result.error(message);
                }
            }else{
                return result.error("此企业名称已被注册！");
            }
        }catch (Exception e){
            e.printStackTrace();
            message="保存失败！";
            result.error(message);
        }

        return result;
    }


    @PostMapping("getCompanyById")
    @ApiOperation("获取企业信息")
    public Result<SysCompanyDTO> getCompanyById(@RequestBody Map<String, Object> params){
        Long companyId;
        String param = (String) params.get("companyId");
        if (StringUtils.isEmpty(param)){
            companyId = SecurityUser.getUser().getCompanyId();
        }else {
            companyId = Long.valueOf(param);
        }
        SysCompanyDTO sysCompanyDTO = sysCompanyService.getByCompanyId(companyId);
        SysDeptDTO sysDeptDTO = sysDeptService.get(sysCompanyDTO.getXgdw());
        SysUserDTO sysUserDTO = sysUserService.get(sysCompanyDTO.getUserId());
        sysCompanyDTO.setSysDeptDTO(sysDeptDTO);
        sysCompanyDTO.setSysUserDTO(sysUserDTO);
        return new Result<SysCompanyDTO>().ok(sysCompanyDTO);
    }

    @PutMapping("examineAndVerify")
    @ApiOperation("审核企业")
    public Result examineAndVerify(@RequestBody Map<String, Object> params){
        Long companyId = Long.valueOf((String)params.get("companyId"));
        String status= (String) params.get("status");
        String cause = (String) params.get("cause");
        Long userId= Long.parseLong(params.get("userId").toString());
        CompanyManageLogDTO companyManageLogDTO = new CompanyManageLogDTO();
        SysCompanyDTO sysCompanyDTO = sysCompanyService.get(companyId);
        if (String.valueOf(Constant.CompanyStatusService.PASS.value()).equals(status)){
            List<String> list = new ArrayList<>();
            Map map = new HashMap<>();
            SysUserDTO sysUserDTO = sysUserService.getCompanyId(companyId);
            sysUserService.updateSuperAdmin(sysUserDTO.getId(), SuperAdminEnum.YES.value());
            map.put("userId",sysUserDTO.getId());
            map.put("list",list);
            Integer count = companyUserMenuService.deleteByUserId(sysUserDTO.getId());
            List<CompanyMenuDTO> menuDTOList = companyMenuService.selectAll();
            for(CompanyMenuDTO companyMenuDTO : menuDTOList){
                CompanyUserMenuEntity companyUserMenuEntity = new CompanyUserMenuEntity();
                companyUserMenuEntity.setCompanyMenuModule(companyMenuDTO.getModule());
                companyUserMenuEntity.setUserId(sysUserDTO.getId());
                companyUserMenuService.insert(companyUserMenuEntity);
            }
            if(StringUtils.isBlank(cause)){
                cause="企业申请通过！";
                companyManageLogDTO.setResult(Constant.CompanyLogService.PASS.value());
            }

            Map<String,Object> governPlanMap = new HashMap<String,Object>();
            governPlanMap.put("companyId",companyId);
            governPlanMap.put("planStatus",Constant.PlanstatusService.grovern.value());
            //获取未生成的治理项
            //List<BasicInspectionListDTO> noProducePlan = governPlanService.getNoProducePlan(governPlanMap);

            // 计划的发放
            sysCompanyDTO.setCause(null);
            List<BasicInspectionListDTO> list1 = inspectionListService.findConmpanyInspectList(companyId);

            //判断此企业之前是否有生成治理计划
            /*if(noProducePlan != null){
                conmpanyInspectService.generateGovern(noProducePlan,companyId);
            }else{*/
                conmpanyInspectService.generateGovern(list1,companyId);
           /* }*/

        }

        if(String.valueOf(Constant.CompanyStatusService.FAIL.value()).equals(status) && StringUtils.isBlank(cause)){//企业审核不通过时
            cause="企业申请不通过！";
            companyManageLogDTO.setResult(Constant.CompanyLogService.FAIL.value());
        }
        //增加行业监管人
        sysCompanyDTO.setUserId(userId);
        sysCompanyDTO.setStatus(Integer.valueOf(status));
        sysCompanyDTO.setCause(cause);
        sysCompanyDTO.setResultIsClosed(1);
        sysCompanyService.update(sysCompanyDTO);
        companyManageLogDTO.setCompanyId(companyId);
        companyManageLogDTO.setCause(cause);
        companyManageLogDTO.setAuditUser(SecurityUser.getUserId());
        companyManageLogDTO.setType(Constant.CompanyLogService.PASS.value());
        companyManageLogService.save(companyManageLogDTO);
        return new Result<SysCompanyDTO>().ok(sysCompanyDTO);
    }

    @PostMapping("updateCompanyById")
    @ApiOperation("更新企业信息")
    public Result<SysCompanyDTO> updateCompanyById(@RequestBody SysCompanyDTO sysCompanyDTO){
        Result result = new Result();
        Map map = new HashMap();
        String message = null;
        try {
            sysCompanyDTO.setStatus(Constant.CompanyStatusService.PENDIND.value());
            sysCompanyService.update(sysCompanyDTO);
            CompanyManageLogDTO companyManageLogDTO = new CompanyManageLogDTO();
            companyManageLogDTO.setCompanyId(sysCompanyDTO.getId());
            companyManageLogDTO.setCause("企业信息更新");
            companyManageLogDTO.setAuditUser(SecurityUser.getUserId());
            companyManageLogService.save(companyManageLogDTO);
            message = "更新成功！";
            result.ok(message);
        }catch (Exception e){
            e.printStackTrace();
            message = "更新失败！";
            result.error(message);
        }
        return result;
    }
}