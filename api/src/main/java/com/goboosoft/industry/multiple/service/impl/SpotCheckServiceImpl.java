package com.goboosoft.industry.multiple.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.goboosoft.common.utils.ConvertUtils;
import com.goboosoft.common.utils.DateUtils;
import com.goboosoft.company.companyinspection.dto.ConmpanyInspectDTO;
import com.goboosoft.company.companyinspection.service.ConmpanyInspectService;
import com.goboosoft.company.govern.dto.GovernPlanDTO;
import com.goboosoft.company.govern.dto.GovernProcessPlanDTO;
import com.goboosoft.company.govern.entity.GovernPlanEntity;
import com.goboosoft.company.govern.service.GovernPlanService;
import com.goboosoft.company.govern.service.GovernProcessPlanService;
import com.goboosoft.industry.multiple.dao.SpotCheckDao;
import com.goboosoft.industry.multiple.dto.*;
import com.goboosoft.industry.multiple.service.BasicInspectionListService;
import com.goboosoft.industry.multiple.service.SpotCheckService;
import com.goboosoft.industry.multiple.service.SysCompanyService;
import com.goboosoft.system.security.user.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.goboosoft.industry.multiple.service.impl.GenerateOrderUtil.doOrderNum;

/**
 * &#x884c;&#x4e1a; - &#x65b0;&#x4efb;&#x52a1;&#x53d1;&#x5e03;&#x8868;
 *
 * @author yuzhao
 * @since 1.0.0 2019-03-05
 */
@Service
public class SpotCheckServiceImpl implements SpotCheckService {
    @Autowired
    private SpotCheckDao spotCheckDao;
    @Autowired
    private SysCompanyService sysCompanyService;
    @Autowired
    private BasicInspectionListService basicInspectionListService;
    @Autowired
    private GovernPlanService governPlanService;
    @Autowired
    private GovernProcessPlanService governProcessPlanService;
    @Autowired
    private ConmpanyInspectService conmpanyInspectService;

    /**
     * 企业抽查样本建立
     */
    @Override
    public void saveSample(JSONObject jsonObject) {
       List<Map<String,String>> spotCheckCompanyDtoList =( List<Map<String,String>>)  jsonObject.get("spotCheckCompanyDtoList");
        List<Map<String,Object>> jsonObjects = (List<Map<String,Object>>)jsonObject.get("inspectionProjectDtoList");
        for (Map<String,String> map : spotCheckCompanyDtoList) {
            for (Map inspectionProjectDTO : jsonObjects) {
                //抽查项处理
                Long companyId = Long.parseLong(map.get("companyId"));
                Long inspectionId = Long.parseLong((String)inspectionProjectDTO.get("id"));
                List<ConmpanyInspectDTO> inspectNum= conmpanyInspectService.getInspectCountById(companyId, inspectionId);
                if (inspectNum != null) {
                    for (ConmpanyInspectDTO conmpanyInspectDTO :inspectNum) {
                        List<Map<String,Object>>  items = (List<Map<String,Object>>)inspectionProjectDTO.get("inspectionProjicts");
                        //处理抽查内容
                        for (Map inspectionItemsDTO : items) {
                            //生成计划
                            GovernPlanDTO governPlanDTO = new GovernPlanDTO();
                            //单号
                            String orderNumber = doOrderNum();
                            governPlanDTO.setCode("hycc" + orderNumber);
                            //企业id
                            governPlanDTO.setCompanyId(companyId);
                            //抽查内容项id
                            governPlanDTO.setInspectionListId(Long.parseLong((String)inspectionItemsDTO.get("id")));
                            //项目名称
                            governPlanDTO.setCompanyInspectionId(conmpanyInspectDTO.getId());
                            governPlanDTO.setName(conmpanyInspectDTO.getName());
                            //抽查为1
                            governPlanDTO.setPlanStatus("1");
                            //0.待处理
                            governPlanDTO.setStatus("0");
                            governPlanDTO.setCreatorDeptId(SecurityUser.getDeptId());
                            GovernPlanEntity governPlanEntity =ConvertUtils.sourceToTarget(governPlanDTO, GovernPlanEntity.class);
                            governPlanService.insert(governPlanEntity);
                           //过程记录
                            GovernProcessPlanDTO governProcessPlanDTO = new GovernProcessPlanDTO();
                            //抽查计划id
                            governProcessPlanDTO.setGovernPlanId(governPlanEntity.getId());
                            //抽查时间（当前时间)
                            governProcessPlanDTO.setSelectDate(governPlanEntity.getCreateDate());
                            //抽查时限(当前时间+子项目时间)TODO
                            Long cycle=basicInspectionListService.getCycleByInspectionId(inspectionId);
                            int cycle1=Integer.parseInt(String.valueOf(cycle));
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(governPlanEntity.getCreateDate());
                            calendar.add(Calendar.DAY_OF_YEAR,cycle1);
                            Date selectLimitDate=calendar.getTime();
                            governProcessPlanDTO.setSelectLimitDate(selectLimitDate);
                            governPlanEntity.setEndDate(selectLimitDate);
                            governPlanService.updateById(governPlanEntity);
                            governProcessPlanService.save(governProcessPlanDTO);
                        }
                    }
                }
            }
        }
    }

    /**
     * 选择检查项
     *
     * @return
     */
    @Override
    public List<InspectionProjectDTO> getinspectionProject() {
        List<InspectionProjectDTO> inspectionProjectDtoList = spotCheckDao.getinspectionProject();
        List<InspectionItemsDTO> InspectionItemsDtoList = spotCheckDao.getinspectionitems();
        List<InspectionProjectDTO> aab = new ArrayList<InspectionProjectDTO>();
        for (InspectionProjectDTO inspectionProjectDTO : inspectionProjectDtoList) {
            List<InspectionItemsDTO> ips = new ArrayList<InspectionItemsDTO>();
            for (InspectionItemsDTO InspectionItemsDTO : InspectionItemsDtoList) {
                if (InspectionItemsDTO.getPid() != null && inspectionProjectDTO.getId() != null && InspectionItemsDTO.getPid().equals(inspectionProjectDTO.getId())) {
                    InspectionItemsDTO inspectionItems = new InspectionItemsDTO();
                    inspectionItems.setId(InspectionItemsDTO.getId());
                    inspectionItems.setName(InspectionItemsDTO.getName());
                    ips.add(inspectionItems);
                }
            }
            inspectionProjectDTO.setInspectionProjicts(ips);
            aab.add(inspectionProjectDTO);
        }
        return aab;
    }

    /**
     * 企业抽查获取已上报
     *
     * @return
     */
    @Override
    public List<SpotCheckSampleListDTO> getNotReportedList(Map<String, Object> params) {
        List<SpotCheckSampleListDTO> sendList = spotCheckDao.getNotReportedList(params);
        return sendList;
    }

    /**
     * 企业抽查获取未上报
     *
     * @return
     */
    @Override
    public List<SpotCheckSampleListDTO> getReportedList(Map<String, Object> params) {
        List<SpotCheckSampleListDTO> receiveList = spotCheckDao.getReportedList(params);
        return receiveList;
    }

    /**
     * 根据订单id查询抽查订单详情
     */
    @Override
    public SpotCheckSampleListDTO getSpotCheckDetails(Long id) {
        String filePath= "http://files.goboosoft.com/zwjm/";
        SpotCheckSampleListDTO spotCheckSampleListDTO = spotCheckDao.getSpotCheckDetails(id);
        //获取公司名称
        if (spotCheckSampleListDTO.getCompanyId() != null) {
            spotCheckSampleListDTO.setCompanyName(sysCompanyService.get(spotCheckSampleListDTO.getCompanyId()).getName());
        } else {
            spotCheckSampleListDTO.setCompanyName(null);
        }
        //获取抽查项
        if (spotCheckSampleListDTO.getName() != null && spotCheckSampleListDTO.getInspectionListId()!= null) {
            String name = spotCheckSampleListDTO.getName();
            Long InspectionListid = spotCheckSampleListDTO.getInspectionListId();
            String InspectionListName = basicInspectionListService.get(InspectionListid).getName();
            String spotCheckName = name + "(" + InspectionListName + ")";
            spotCheckSampleListDTO.setSpotCheckName(spotCheckName);
        }
        //获取抽查前照片
        if(spotCheckSampleListDTO.getCompanyId()!=null && spotCheckSampleListDTO.getCompanyInspectionId()!=null) {
            Date now = spotCheckSampleListDTO.getSelectDate();
            String beforePicture = basicInspectionListService.getBeforeAccessory(spotCheckSampleListDTO.getCompanyId(), spotCheckSampleListDTO.getCompanyInspectionId(), now);
            List<String> pictureBefore = new ArrayList<String>();
            if (beforePicture != null) {
                pictureBefore = Arrays.asList(beforePicture.split(","));
                List<String> strings1 = new ArrayList<>();
                for (String photo : pictureBefore) {
                    photo=filePath+photo;
                    strings1.add(photo);
                }
                spotCheckSampleListDTO.setPictureBefore(strings1);
            } else {
                spotCheckSampleListDTO.setPictureBefore(null);
            }
        }
        //解析抽样后照片
        List<String> pictureAfter = new ArrayList<String>();
        if (spotCheckSampleListDTO.getPicture() != null) {
            pictureAfter = Arrays.asList(spotCheckSampleListDTO.getPicture().split(","));
            List<String> strings1 = new ArrayList<>();
            for (String photo : pictureAfter) {
                photo=filePath+photo;
                strings1.add(photo);
            }
            spotCheckSampleListDTO.setPictureAfter(strings1);
        } else {
            spotCheckSampleListDTO.setPictureAfter(null);
        }
        return spotCheckSampleListDTO;
    }
    /**
     * 根据企业id查询抽查清单
     */
    public List<SpotCheckListDTO> getspotCheckList(Map<String, Object> params){
        return spotCheckDao.getspotCheckList(params);
    }
}