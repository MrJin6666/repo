package com.goboosoft.industry.multiple.controller;

import com.goboosoft.common.utils.DateUtils;
import com.goboosoft.common.utils.Result;
import com.goboosoft.industry.multiple.dto.IndustryTaskListDTO;
import com.goboosoft.industry.multiple.dto.IndustryTaskListSelectDTO;
import com.goboosoft.industry.multiple.dto.IndustryTaskLogDTO;

import com.goboosoft.industry.multiple.entity.IndustryTaskListEntity;
import com.goboosoft.industry.multiple.service.IndustryTaskListSelectService;
import com.goboosoft.industry.multiple.service.IndustryTaskListService;
import com.goboosoft.industry.multiple.service.IndustryTaskLogService;
import com.goboosoft.industry.multiple.service.impl.GenerateOrderUtil;
import com.goboosoft.system.security.user.SecurityUser;
import com.goboosoft.system.sys.dto.SysDeptDTO;
import com.goboosoft.system.sys.service.SysDeptService;
import com.goboosoft.system.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;
import java.util.*;


/**
 * 行业 - 新任务发布表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-05
 */
@RestController
@RequestMapping("demo/industrytasklist")
@Api(tags="行业 - 新任务发布表")
public class ApiIndustryTaskListController {
    @Autowired
    private IndustryTaskListService industryTaskListService;
    @Autowired
    private IndustryTaskLogService industryTaskLogService;
    @Autowired
    private IndustryTaskListSelectService industryTaskListSelectService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysDeptService sysDeptService;
    private static final int CREATESTATUS =0;
    private static final int ASSIGNSTATUS =1;
    private static final int DISPOSESTATUS =2;
    private static final int FINISHSTATUS =3;
    private static final int BACKTRACK=4;
    @PostMapping("create")
    @ApiOperation("行业新任务创建")
    public Result save(@RequestBody IndustryTaskListEntity industryTaskListEntity)throws IOException {
        String orderNumber = GenerateOrderUtil.doOrderNum();
        industryTaskListEntity.setOrderNumber("HYRW"+orderNumber);
        industryTaskListEntity.setStatus(CREATESTATUS);
        Long receiverId = industryTaskListEntity.getReceiverId();
        //添加接收人部门
        Long userId = SecurityUser.getUserId();
        Long fcDeptId = sysUserService.get(userId).getDeptId();
        Long jsDeptId = sysUserService.get(receiverId).getDeptId();
        industryTaskListEntity.setFcDeptId(fcDeptId);
        industryTaskListEntity.setJsDeptId(jsDeptId);
        industryTaskListService.insert(industryTaskListEntity);
        //创建日志
        IndustryTaskLogDTO industryTaskLogDTO = new IndustryTaskLogDTO();
        industryTaskLogDTO.setStatus(CREATESTATUS);
        industryTaskLogDTO.setRemark(SecurityUser.getUser().getRealName()+"创建了任务并将任务指派给"+sysUserService.get(receiverId).getRealName());
        industryTaskLogDTO.setIndustryTaskId(industryTaskListEntity.getId());
        industryTaskLogDTO.setReceiverId(industryTaskListEntity.getReceiverId());

        industryTaskLogService.save(industryTaskLogDTO);
        return new Result();
    }
    /**
     * 查询发出列表
     * created by yuzhao
     * @param
     * @return
     */
    @GetMapping("getSendList")
    @ApiOperation("新任务发布列表查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lastRowNum", value = "当前页码，从0开始", paramType = "query", required = true, dataType="int") ,
            @ApiImplicitParam(name = "pageSize", value = "显示记录数", paramType = "query",required = true, dataType="int")
    })
    public Result<IndustryTaskListSelectDTO> getSendList(@ApiIgnore @RequestParam Map<String, Object> params){
        Long userId=SecurityUser.getUserId();
        params.put("userId",userId);
        List<IndustryTaskListSelectDTO> dataList = industryTaskListSelectService.getSendList(params);
        return new Result().ok(dataList);
    }
    /**
     * 查询接收列表
     * created by yuzhao
     * @param
     * @return
     */
    @GetMapping("getReceiveList")
    @ApiOperation("新任务接收列表查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lastRowNum", value = "当前页码，从0开始", paramType = "query", required = true, dataType="int") ,
            @ApiImplicitParam(name = "pageSize", value = "显示记录数", paramType = "query",required = true, dataType="int")
    })
    public Result<IndustryTaskListSelectDTO> getReceiveList(@ApiIgnore @RequestParam Map<String, Object> params){
        Long userId=SecurityUser.getUserId();
        params.put("userId",userId);
        List<IndustryTaskListSelectDTO> dataList = industryTaskListSelectService.getReceiveList(params);
        return new Result().ok(dataList);
    }
    /**
     * 查询待确认列表
     * created by yuzhao
     * @param
     * @return
     */
    @GetMapping("getBeConfirmedList")
    @ApiOperation("新任务待确认列表查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lastRowNum", value = "当前页码，从0开始", paramType = "query", required = true, dataType="int") ,
            @ApiImplicitParam(name = "pageSize", value = "显示记录数", paramType = "query",required = true, dataType="int")
    })
    public Result<IndustryTaskListSelectDTO> getBeConfirmedList(@ApiIgnore @RequestParam Map<String, Object> params){
        Long userId=SecurityUser.getUserId();
        params.put("userId",userId);
        List<IndustryTaskListSelectDTO> dataList = industryTaskListSelectService.getBeConfirmedList(params);
        return new Result().ok(dataList);
    }
    /**
     * 查询接收列表个数
     * created by yuzhao
     * @param
     * @return
     */
    @GetMapping("getReceiveListNum")
    @ApiOperation("新任务接收列表数量查询")
    public Result<Integer> getReceiveListNum(@ApiIgnore @RequestParam Map<String, Object> params){
        Long userId=SecurityUser.getUserId();
        params.put("userId",userId);
        Integer dataListNum = industryTaskListSelectService.getReceiveListNum(params);
        return new Result().ok(dataListNum);
    }
    /**
     * 根据任务ID查询任务详情
     * created by yuzhao
     * @return
     */
    @GetMapping("{id}")
    @ApiOperation("查询新任务详情")
    public Result<IndustryTaskListDTO> get(@PathVariable("id") Long id){
        final String filePath= "http://files.goboosoft.com/zwjm/";
        //任务当前状态
        Integer status=0;
        IndustryTaskListDTO data = industryTaskListService.get(id);
        status =data.getStatus();
        //附件转换
        String accessory = data.getAccessory();
        if (StringUtils.isNotEmpty(accessory)){
            String[] split = accessory.split(",");
            List<String> strings = Arrays.asList(split);
            List<String> strings1 = new ArrayList<>();
            for (String photo : strings) {
                photo=filePath+photo;
                strings1.add(photo);
            }
            data.setAccessoryList(strings1);
        }
        //获取log
        Map<String,Object> params = new HashMap();
        params.put("industryTaskId",id.toString());
        List<IndustryTaskLogDTO> list = industryTaskLogService.page(params).getList();
        //log附件转换
        for (IndustryTaskLogDTO industryTaskLogDTO : list) {
            //获取最终状态
           // status =industryTaskLogDTO.getStatus()>status?industryTaskLogDTO.getStatus():status;
            if (StringUtils.isNotEmpty(industryTaskLogDTO.getAccessory())){
                String[] split = industryTaskLogDTO.getAccessory().split(",");
                List<String> strings = Arrays.asList(split);
                List<String> strings1 = new ArrayList<>();
                for (String photo : strings) {
                    photo=filePath+photo;
                    strings1.add(photo);
                }
                industryTaskLogDTO.setAccessoryList(strings1);
            }
        }
        data.setStatus(status);
        data.setLogList(list);
        return new Result<IndustryTaskListDTO>().ok(data);
    }
    /**
     * 任务指派，更换接收人和状态
     * created by yuzhao
     * @return
     */
    @PutMapping("assign")
    @ApiOperation("任务指派")
    public Result assign(@RequestBody Map<String, Object> params){
        Long id = Long.valueOf((String)params.get("id"));
        Long receivedPersonId = Long.valueOf((String)params.get("receivedPersonId"));
        //更新执行者
        IndustryTaskListDTO industryTaskListDTO = industryTaskListService.get(id);
        industryTaskListDTO.setReceiverId(receivedPersonId);
        industryTaskListDTO.setStatus(ASSIGNSTATUS);
        industryTaskListService.update(industryTaskListDTO);
        //创建日志
        IndustryTaskLogDTO industryTaskLogDTO = new IndustryTaskLogDTO();
        industryTaskLogDTO.setStatus(ASSIGNSTATUS);
        industryTaskLogDTO.setIndustryTaskId(id);
        industryTaskLogDTO.setRemark(SecurityUser.getUser().getRealName()+"将任务指派给"+sysUserService.get(receivedPersonId).getRealName());
        industryTaskLogDTO.setReceiverId(receivedPersonId);
        industryTaskLogService.save(industryTaskLogDTO);

        return new Result();
    }
    /**
     * 任务处理，更改状态代码
     * created by yuzhao
     * @return
     */
    @PutMapping("dispose")
    @ApiOperation("任务处理")
    public Result dispose(@RequestBody Map<String, Object> params){
        Long id = Long.valueOf((String)params.get("id"));
        IndustryTaskListDTO industryTaskListDTO = industryTaskListService.get(id);
        industryTaskListDTO.setStatus(DISPOSESTATUS);
        industryTaskListService.update(industryTaskListDTO);
        String accessory =(String) params.get("accessory");
        String finishRemark =(String)params.get("finishRemark");
        //创建日志
        IndustryTaskLogDTO industryTaskLogDTO = new IndustryTaskLogDTO();
        industryTaskLogDTO.setStatus(DISPOSESTATUS);
        industryTaskLogDTO.setIndustryTaskId(id);
        industryTaskLogDTO.setAccessory(accessory);
        industryTaskLogDTO.setFinishRemark(finishRemark);
        industryTaskLogDTO.setRemark(SecurityUser.getUser().getRealName()+"处理了任务");
        industryTaskLogService.save(industryTaskLogDTO);
        return new Result();
    }
    /**
     * 任务驳回，更改状态代码，记录日志
     * created by yuzhao
     * @return
     */
    @PutMapping("backtrack")
    @ApiOperation("任务驳回")
    public Result backtrack(@RequestBody Map<String, Object> params){
        Long id = Long.valueOf((String)params.get("id"));
        IndustryTaskListDTO industryTaskListDTO = industryTaskListService.get(id);
        industryTaskListDTO.setStatus(BACKTRACK);
        industryTaskListService.update(industryTaskListDTO);
        String accessory =(String) params.get("accessory");
        String finishRemark =(String)params.get("finishRemark");
        //创建日志
        IndustryTaskLogDTO industryTaskLogDTO = new IndustryTaskLogDTO();
        industryTaskLogDTO.setStatus(BACKTRACK);
        industryTaskLogDTO.setIndustryTaskId(id);
        industryTaskLogDTO.setAccessory(accessory);
        industryTaskLogDTO.setFinishRemark(finishRemark);
        industryTaskLogDTO.setRemark(SecurityUser.getUser().getRealName()+"驳回了任务");
        industryTaskLogService.save(industryTaskLogDTO);
        return new Result();
    }
    /**
     * 任务确认完成，更改状态代码
     * created by yuzhao
     * @return
     */
    @PutMapping("verify")
    @ApiOperation("任务确认完成")
    public Result verify(@RequestBody Map<String, Object> params){
        //TODO
        Long id = Long.valueOf((String)params.get("id"));
        IndustryTaskListDTO industryTaskListDTO = industryTaskListService.get(id);
        industryTaskListDTO.setStatus(FINISHSTATUS);
        industryTaskListService.update(industryTaskListDTO);
        //String finishRemark =(String)params.get("finishRemark");
        //创建日志
        IndustryTaskLogDTO industryTaskLogDTO = new IndustryTaskLogDTO();
        industryTaskLogDTO.setStatus(FINISHSTATUS);
        industryTaskLogDTO.setIndustryTaskId(id);
        //industryTaskLogDTO.setFinishRemark(finishRemark);
        industryTaskLogDTO.setRemark(SecurityUser.getUser().getRealName()+"确认任务已完成");
        industryTaskLogService.save(industryTaskLogDTO);
        return new Result();
    }
    /**
     * 查询任务待处理和超期及已完成列表
     * created by yuzhao
     * @param
     * @return
     */
    @GetMapping("getTaskHomePage")
    @ApiOperation("查询任务待处理和超期及已完成列表 0 待处理 5 超期 3已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lastRowNum", value = "当前页码，从0开始", paramType = "query", required = true, dataType="int") ,
            @ApiImplicitParam(name = "pageSize", value = "显示记录数", paramType = "query",required = true, dataType="int"),
            @ApiImplicitParam(name = "status", value = "状态码", paramType = "query",required = true, dataType="int")
    })
    public Result<IndustryTaskListSelectDTO> getHomePageList(@ApiIgnore @RequestParam Map<String, Object> params){
        Long userId=SecurityUser.getUserId();
        params.put("userId",userId);
        List<IndustryTaskListSelectDTO> dataList = industryTaskListSelectService.getHomePageList(params);
        return new Result().ok(dataList);
    }
}