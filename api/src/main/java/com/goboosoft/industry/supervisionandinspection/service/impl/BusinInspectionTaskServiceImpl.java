package com.goboosoft.industry.supervisionandinspection.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.goboosoft.common.page.PageData;
import com.goboosoft.common.service.impl.CrudServiceImpl;
import com.goboosoft.common.utils.Base64;
import com.goboosoft.common.utils.ConvertUtils;
import com.goboosoft.common.utils.DateUtils;
import com.goboosoft.company.busininspect.dto.BusinInspectionCorrectionDTO;
import com.goboosoft.company.busininspect.dto.BusinInspectionCorrectionMxDTO;
import com.goboosoft.company.busininspect.entity.BusinInspectionCorrectionEntity;
import com.goboosoft.company.busininspect.entity.BusinInspectionCorrectionMxEntity;
import com.goboosoft.company.busininspect.service.BusinInspectionCorrectionMxService;
import com.goboosoft.company.busininspect.service.BusinInspectionCorrectionService;
import com.goboosoft.company.companymanagelog.dto.CompanyManageLogDTO;
import com.goboosoft.company.companymanagelog.service.CompanyManageLogService;
import com.goboosoft.industry.govern.dto.GuideCardItemDTO;
import com.goboosoft.industry.govern.dto.GuideExceedDTO;
import com.goboosoft.industry.govern.service.GovernService;
import com.goboosoft.industry.govern.service.GuideCardItemService;
import com.goboosoft.industry.multiple.dto.BusinessItemDTO;
import com.goboosoft.industry.multiple.dto.SpotCheckListDTO;
import com.goboosoft.industry.multiple.service.BusinessManagerService;
import com.goboosoft.industry.multiple.service.SpotCheckService;
import com.goboosoft.industry.supervisionandinspection.dto.CorrectionListDTO;
import com.goboosoft.industry.supervisionandinspection.dao.BusinInspectionTaskDao;
import com.goboosoft.industry.supervisionandinspection.dto.*;
import com.goboosoft.industry.supervisionandinspection.entity.BusinInspectionTaskEntity;
import com.goboosoft.industry.supervisionandinspection.entity.BusinInspectionTaskItemContentEntity;
import com.goboosoft.industry.supervisionandinspection.entity.BusinInspectionTaskItemEntity;
import com.goboosoft.industry.supervisionandinspection.service.BusinInspectionTaskItemContentService;
import com.goboosoft.industry.supervisionandinspection.service.BusinInspectionTaskItemService;
import com.goboosoft.industry.supervisionandinspection.service.BusinInspectionTaskService;
import com.goboosoft.system.security.user.SecurityUser;
import com.goboosoft.system.security.user.UserDetail;
import com.goboosoft.system.sys.dto.SysCompanyDTO;
import com.goboosoft.system.sys.dto.SysFileDTO;
import com.goboosoft.system.sys.service.SysCompanysService;
import com.mongodb.DB;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * 业务 - 检查任务信息表
 *
 * @author DMXUAN
 * @since 1.0.0 2019-03-14
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BusinInspectionTaskServiceImpl extends CrudServiceImpl<BusinInspectionTaskDao, BusinInspectionTaskEntity, BusinInspectionTaskDTO> implements BusinInspectionTaskService {

    @Autowired
    private BusinInspectionTaskItemService businInspectionTaskItemService;
    @Autowired
    private BusinInspectionTaskItemContentService businInspectionTaskItemContentService;
    @Autowired
    private BusinInspectionCorrectionMxService businInspectionCorrectionMxService;
    @Autowired
    private SpotCheckService spotCheckService;
    @Autowired
    private BusinessManagerService businessManagerService;
    @Autowired
    private BusinInspectionCorrectionService businInspectionCorrectionService;
    @Autowired
    private GovernService governService;
    @Autowired
    private SysCompanysService sysCompanysService;
    @Autowired
    private CompanyManageLogService companyManageLogService;
    @Autowired
    private GuideCardItemService guideCardItemService;
    @Autowired
    private MongoDbFactory mongoDbFactory;
    @Value("filePath")
    private String filePath;

    @Override
    public QueryWrapper<BusinInspectionTaskEntity> getWrapper(Map<String, Object> params) {
        String id = (String) params.get("id");
        Long userId = (Long) params.get("userId");

        QueryWrapper<BusinInspectionTaskEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);
        wrapper.eq("inspect_user_id", userId);

        return wrapper;
    }

    @Override
    public Long saveBusinInspectionTask(BusinInspectionTaskDTO dto) {
        UserDetail user = SecurityUser.getUser();
        dto.setInspectUserId(user.getId());
        dto.setInspectUserName(user.getRealName());
        dto.setDeptId(user.getDeptId());
        dto.setInspectDate(new Date());
        dto.setTickets("ddjc" + DateUtils.format(new Date(), DateUtils.DATETIMEPATTERN));
        BusinInspectionTaskEntity businInspectionTaskEntity = ConvertUtils.sourceToTarget(dto, BusinInspectionTaskEntity.class);
        baseDao.insert(businInspectionTaskEntity);
        List<InspectionDTO> inspectionListByCompanyId = baseDao.getInspectionListByCompanyId(dto.getCompanyId());
        for (InspectionDTO inspectionDTO : inspectionListByCompanyId) {
            BusinInspectionTaskItemEntity businInspectionTaskItemEntity = new BusinInspectionTaskItemEntity();
            businInspectionTaskItemEntity.setInspectionTaskId(businInspectionTaskEntity.getId());
            businInspectionTaskItemEntity.setInspectionListId(inspectionDTO.getId());
            businInspectionTaskItemEntity.setInspectionListName(inspectionDTO.getName());
            businInspectionTaskItemEntity.setTotalScore(inspectionDTO.getTotalScore());
            businInspectionTaskItemService.insert(businInspectionTaskItemEntity);
            List<InspectionDTO> inspectionListByPid = baseDao.getInspectionListByPid(inspectionDTO.getId());
            for (InspectionDTO inspection : inspectionListByPid) {
                BusinInspectionTaskItemContentEntity businInspectionTaskItemContentEntiy = new BusinInspectionTaskItemContentEntity();
                businInspectionTaskItemContentEntiy.setInspectionTaskItemId(businInspectionTaskItemEntity.getId());
                businInspectionTaskItemContentEntiy.setInspectionListId(inspection.getId());
                businInspectionTaskItemContentEntiy.setInspectionListName(inspection.getName());
                businInspectionTaskItemContentEntiy.setTotalScore(inspection.getTotalScore());
                businInspectionTaskItemContentService.insert(businInspectionTaskItemContentEntiy);
            }
        }
        return businInspectionTaskEntity.getId();
    }

    @Override
    public CompanyGuideDTO getCompanyGuide(Long companyId) {
        CompanyGuideDTO companyGuideDTO = new CompanyGuideDTO();
        Map<String, Object> params = new HashMap<>();
        params.put("companyId", companyId);
        companyGuideDTO.setCompanyId(companyId);
        List<SpotCheckListDTO> spotCheckList = spotCheckService.getspotCheckList(params);
        companyGuideDTO.setSpotCheckNum(spotCheckList.size());
        BusinessItemDTO businessItemDTO = businessManagerService.queryCompanyItemByCompanyId(String.valueOf(companyId));
        companyGuideDTO.setCompanyStatus(businessItemDTO.getStatus());
        Integer rectificationNum = businInspectionCorrectionService.getInspectionCorrectionCountByCompanyId(companyId);
        companyGuideDTO.setRectificationNum(rectificationNum);
        Integer superviseNum = this.getInspectionCountByCompanyId(companyId);
        companyGuideDTO.setSuperviseNum(superviseNum);
        params.put("status", 4);
        List<GuideExceedDTO> GuideExceedList = governService.queryGuideExceedListByStatus(params);
        companyGuideDTO.setGovernanceNum(GuideExceedList.size());
        Map<String, Object> params1 = new HashMap<>();
        params1.put("companyId", companyId);
        params1.put("checkStatus", 0);
        List<GuideCardItemDTO> redCardList = guideCardItemService.queryGuideCardItemBycheckStatus(params1);
        companyGuideDTO.setRedNum(redCardList.size());
        Map<String, Object> params2 = new HashMap<>();
        params2.put("companyId", companyId);
        params2.put("checkStatus", 1);
        List<GuideCardItemDTO> yellowCardList = guideCardItemService.queryGuideCardItemBycheckStatus(params2);
        companyGuideDTO.setYellowNum(yellowCardList.size());
        return companyGuideDTO;
    }

    @Override
    public List<BusinInspectionTaskListDTO> getList(Map<String, Object> params) {
        return baseDao.getList(params);
    }

    @Override
    public List<CorrectionListDTO> getCorrectionList(Map<String, Object> params) {
        return baseDao.getCorrectionList(params);
    }

    @Override
    public List<CorrectionInfoDTO> getCorrectionContent(Long id, Integer type) {
        return baseDao.getCorrectionContent(id, type);
    }

    @Override
    public CorrectionDTO getCorrectionContentInfo(Long id) {
        return baseDao.getCorrectionContentInfo(id);
    }

    @Override
    public List<CheckListDTO> getCheckList(Map<String, Object> params) {
        return baseDao.getCheckList(params);
    }

    @Override
    public CheckDTO getCheckContentInfo(Long id) {
        return baseDao.getCheckContentInfo(id);
    }

    @Override
    public List<ReportDTO> getReportList(Map<String, Object> params) {
        return baseDao.getReportList(params);
    }

    @Override
    public List<ReportDTO> getReportListByCompanyId(Map<String, Object> params) {
        return baseDao.getReportListByCompanyId(params);
    }

    @Override
    public ReportInfoDTO getReportInfo(Long id) {
        return baseDao.getReportInfo(id);
    }

    @Override
    public Integer getInspectionCountByCompanyId(Long companyId) {
        return baseDao.getInspectionCountByCompanyId(companyId);
    }

    @Override
    public Integer getCheckCountByUserId() {
        return baseDao.getCheckCountByUserId(SecurityUser.getUserId());
    }

    @Override
    public String getIdByFiles(List<SysFileDTO> fileDTOS) {
        StringBuffer photos = new StringBuffer();
        for (SysFileDTO fileDTO : fileDTOS) {
//                ObjectId store = gridFsTemplate.store(new ByteArrayInputStream(Base64.decode(fileDTO.getInputStream())), fileDTO.getOriginalFilename(), fileDTO.getContentType());
            try {
                String[] split = fileDTO.getOriginalFilename().split("\\.");
                String contentType = fileDTO.getContentType();
                String suffix = split[split.length - 1];
                DB legacyDb = mongoDbFactory.getLegacyDb();
                GridFS gfsPhoto = new GridFS(legacyDb);
                GridFSInputFile gfsFile = gfsPhoto.createFile(new ByteArrayInputStream(Base64.decode(fileDTO.getInputStream())));
                String id = DateUtils.format(new Date(), DateUtils.DATETIMEPATTERN) + RandomStringUtils.randomAlphanumeric(6);
                gfsFile.setId(id);
                gfsFile.setFilename(id + "." + suffix);
                gfsFile.setContentType(contentType);
                gfsFile.save();
                photos.append(",").append(id + "." + suffix);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return photos.length() == 0 ? "" : photos.substring(1);
    }

    @Override
    public Integer getCorrectionCountByUserId() {
        return baseDao.getCorrectionCountByUserId(SecurityUser.getUserId());
    }

    /**
     * dmxuan
     * 文件路径拼接
     * @Date 2019/3/29 15:12
     * @Param [photos]
     * @return java.lang.String
     **/
    @Override
    public String addPhotoPath(String photos) {
        if (StringUtils.isEmpty(photos)){
            return null;
        }
        StringBuffer photoPaths = new StringBuffer();
        String[] split = photos.split(",");
        for (String photo : split) {
            photoPaths.append(",").append(filePath).append(photo);
        }
        return photos.substring(1);
    }

    @Override
    public PageData<BusinInspectionTaskItemContentDTO> getInspectContent(Map<String, Object> params) {
        params.put("page", "1");
        params.put("limit", "1000");
        PageData<BusinInspectionTaskItemContentDTO> page = businInspectionTaskItemContentService.page(params);
        List<BusinInspectionTaskItemContentDTO> list = page.getList();
        for (BusinInspectionTaskItemContentDTO businInspectionTaskItemContentDTO : list) {
            String photos = businInspectionTaskItemContentDTO.getPhotos();
            String photoPaths = this.addPhotoPath(photos);
            businInspectionTaskItemContentDTO.setPhotos(photoPaths);
        }
        page.setList(list);
        return page;
    }

    @Override
    public InspectionTaskInfoDTO getInspectionTaskInfo(Long id) {

        BusinInspectionTaskDTO data = this.get(id);
        InspectionTaskInfoDTO inspectionTaskInfoDTO = ConvertUtils.sourceToTarget(data, InspectionTaskInfoDTO.class);
        Map<String, Object> params = new HashMap<>();
        params.put("businInspectionTaskId", id.toString());
        params.put("page", "1");
        params.put("limit", "1000");
        List<InspectionInfoDTO> inspectionInfoDTOS = ConvertUtils.sourceToTarget(businInspectionTaskItemService.page(params).getList(), InspectionInfoDTO.class);
        inspectionTaskInfoDTO.setInspectionInfoDTOS(inspectionInfoDTOS);
        String underwrite = inspectionTaskInfoDTO.getUnderwrite();
        String photoPath = this.addPhotoPath(underwrite);
        inspectionTaskInfoDTO.setUnderwrite(photoPath);
        return inspectionTaskInfoDTO;

    }

    @Override
    public PageData<BusinInspectionTaskItemListDTO> getCompanyinspect(Map<String, Object> params) {
        params.put("page", "1");
        params.put("limit", "1000");
        PageData<BusinInspectionTaskItemDTO> page = businInspectionTaskItemService.page(params);
        PageData<BusinInspectionTaskItemListDTO> resultPage = new PageData(ConvertUtils.sourceToTarget(page.getList(), BusinInspectionTaskItemListDTO.class), page.getTotal());
        return resultPage;
    }

    @Override
    public PageData<InspectionTaskItemContentListDTO> getCompanyInspectContent(Map<String, Object> params) {
        params.put("page", "1");
        params.put("limit", "1000");
        PageData<BusinInspectionTaskItemContentDTO> page = businInspectionTaskItemContentService.page(params);
        PageData<InspectionTaskItemContentListDTO> resultPage = new PageData(ConvertUtils.sourceToTarget(page.getList(), InspectionTaskItemContentListDTO.class), page.getTotal());
        return resultPage;
    }

    @Override
    public void saveInspectContent(List<InspectionContentDTO> dtos) {
        //检测项目总分
        Integer totalScore = 0;
        Long inspectionTaskItemId = 0L;
        //获取检查项id
        if (!dtos.isEmpty()) {
            inspectionTaskItemId = dtos.get(0).getInspectionTaskItemId();
        }
        for (InspectionContentDTO dto : dtos) {
            //如果没有id则是新建   保存
            if (null == dto.getId() || Long.valueOf(0).equals(dto.getId())) {
                String idByFiles = this.getIdByFiles(dto.getFiles());
                BusinInspectionTaskItemContentDTO businInspectionTaskItemContentDTO = ConvertUtils.sourceToTarget(dto, BusinInspectionTaskItemContentDTO.class);
                businInspectionTaskItemContentDTO.setPhotos(idByFiles);
                businInspectionTaskItemContentService.save(businInspectionTaskItemContentDTO);
                //如果存在id则为修改
            } else {
                totalScore = totalScore + dto.getScore();
                businInspectionTaskItemContentService.saveInspectContent(dto);
            }
        }
        //修改检查项信息
        if (!inspectionTaskItemId.equals(0)) {
            BusinInspectionTaskItemDTO businInspectionTaskItemDTO = businInspectionTaskItemService.get(inspectionTaskItemId);
            //总分
            businInspectionTaskItemDTO.setScore(totalScore);
            //状态
            businInspectionTaskItemDTO.setStatus(1);
            //是否合格
            if (businInspectionTaskItemDTO.getTotalScore() > totalScore) {
                businInspectionTaskItemDTO.setPassStatus(0);
            } else {
                businInspectionTaskItemDTO.setPassStatus(1);
            }
            businInspectionTaskItemService.update(businInspectionTaskItemDTO);
        }
    }

    @Override
    public ResultDTO getInspectResult(Map<String, Object> params) {
        BusinInspectionTaskDTO businInspectionTaskId = this.get(Long.valueOf((String) params.get("businInspectionTaskId")));
        if (businInspectionTaskId.getStatus() == 1) {
            params.put("page", "1");
            params.put("limit", "1000");
            //总分
            Integer num = 0;
            //检查项数
            Integer sum = 0;
            List<BusinInspectionTaskItemDTO> list = businInspectionTaskItemService.page(params).getList();
            for (BusinInspectionTaskItemDTO businInspectionTaskItemDTO : list) {
                sum += businInspectionTaskItemDTO.getScore();
                num++;
            }
            BigDecimal d = new BigDecimal(sum);
            BigDecimal r = new BigDecimal(num);
            BigDecimal i = d.divide(r, 0, RoundingMode.HALF_UP);
            businInspectionTaskId.setScore(i.intValue());
            businInspectionTaskId.setStatus(2);
            this.update(businInspectionTaskId);
        }
        Integer noQualifiedCount = 0;
        params.put("page", "1");
        params.put("limit", "1000");
        ResultDTO resultDTO = new ResultDTO();
        PageData<BusinInspectionTaskItemDTO> page = businInspectionTaskItemService.page(params);
        List<ResultItemListDTO> resultItemListDTOS = ConvertUtils.sourceToTarget(page.getList(), ResultItemListDTO.class);
        resultDTO.setInspectionCount(resultItemListDTOS.size());
        for (ResultItemListDTO resultItemListDTO : resultItemListDTOS) {
            params.put("page", "1");
            params.put("inspectionTaskItemId", resultItemListDTO.getId().toString());
            PageData<BusinInspectionTaskItemContentDTO> page1 = businInspectionTaskItemContentService.page(params);
            List<ResultItemContentDTO> resultItemContentDTOS = ConvertUtils.sourceToTarget(page1.getList(), ResultItemContentDTO.class);
            resultItemListDTO.setResultItemContentDTO(resultItemContentDTOS);
            if (resultItemListDTO.getScore() < resultItemListDTO.getTotalScore()) {
                noQualifiedCount++;
            }
        }
        resultDTO.setResultItemListDTO(resultItemListDTOS);
        resultDTO.setNoQualifiedCount(noQualifiedCount);
        resultDTO.setId(Long.valueOf((String) params.get("businInspectionTaskId")));
        return resultDTO;
    }

    @Override
    public void saveBusinInspectionReport(InspectionTaskReportDTO dto) {
        BusinInspectionTaskDTO businInspectionTaskDTO = this.get(dto.getId());
        businInspectionTaskDTO.setUnderwrite(dto.getUnderwrite());
        if (null != dto.getLastCorrectionDate()) {
            businInspectionTaskDTO.setStatus(4);
            businInspectionTaskDTO.setLastCorrectionDate(dto.getLastCorrectionDate());
            businInspectionTaskDTO.setPassStatus("0");
            BusinInspectionCorrectionEntity businInspectionCorrectionEntity = new BusinInspectionCorrectionEntity();
            businInspectionCorrectionEntity.setCompanyId(businInspectionTaskDTO.getCompanyId());
            businInspectionCorrectionEntity.setCompanyName(businInspectionTaskDTO.getCompanyName());
            businInspectionCorrectionEntity.setInspectionTaskId(dto.getId());
            businInspectionCorrectionEntity.setInspectionUserName(businInspectionTaskDTO.getInspectUserName() + "," + businInspectionTaskDTO.getAccompanyUserNames());
            businInspectionCorrectionEntity.setInspectionDate(businInspectionTaskDTO.getInspectDate());
            businInspectionCorrectionEntity.setLastCorrectionDate(dto.getLastCorrectionDate());
            businInspectionCorrectionEntity.setPid(0L);
            businInspectionCorrectionService.insert(businInspectionCorrectionEntity);

            Map<String, Object> params = new HashMap<>();
            params.put("businInspectionTaskId", dto.getId().toString());
            params.put("page", "1");
            params.put("limit", "1000");
            List<BusinInspectionTaskItemDTO> list = businInspectionTaskItemService.page(params).getList();
            for (BusinInspectionTaskItemDTO businInspectionTaskItemDTO : list) {
                if (businInspectionTaskItemDTO.getPassStatus() == 0) {
                    params.put("inspectionTaskItemId", businInspectionTaskItemDTO.getId().toString());
                    params.put("page", "1");
                    List<BusinInspectionTaskItemContentDTO> list1 = businInspectionTaskItemContentService.page(params).getList();
                    for (BusinInspectionTaskItemContentDTO businInspectionTaskItemContentDTO : list1) {
                        if (businInspectionTaskItemContentDTO.getPassStatus().equals("0")) {
                            BusinInspectionCorrectionMxEntity businInspectionCorrectionMxEntity = new BusinInspectionCorrectionMxEntity();
                            businInspectionCorrectionMxEntity.setInspectionCorrectionId(businInspectionCorrectionEntity.getId());
                            businInspectionCorrectionMxEntity.setInspectionTaskItemId(businInspectionTaskItemDTO.getInspectionListId());
                            businInspectionCorrectionMxEntity.setInspectionTaskItemName(businInspectionTaskItemDTO.getInspectionListName());
                            businInspectionCorrectionMxEntity.setProblemDescription(businInspectionTaskItemContentDTO.getRemark());
                            businInspectionCorrectionMxEntity.setPhotos(businInspectionTaskItemContentDTO.getPhotos());
                            businInspectionCorrectionMxEntity.setInspectionListId(businInspectionTaskItemContentDTO.getInspectionListId());
                            businInspectionCorrectionMxEntity.setInspectionListName(businInspectionTaskItemContentDTO.getInspectionListName());
                            businInspectionCorrectionMxService.insert(businInspectionCorrectionMxEntity);
                        }
                    }
                }
            }
        } else {
            businInspectionTaskDTO.setPassStatus("1");
            businInspectionTaskDTO.setFinishDate(new Date());
            businInspectionTaskDTO.setStatus(5);
        }
        businInspectionTaskDTO.setResultIsClosed(dto.getIsClosed());
        businInspectionTaskDTO.setCheckStatus(Integer.valueOf(dto.getCheckStatus()));
        this.update(businInspectionTaskDTO);
        CompanyManageLogDTO companyManageLogDTO = new CompanyManageLogDTO();
        companyManageLogDTO.setResult(dto.getCheckStatus() + 3);
        companyManageLogDTO.setCause("检查不通过");
        companyManageLogDTO.setCompanyId(businInspectionTaskDTO.getCompanyId());
        companyManageLogDTO.setType("3");
        companyManageLogService.save(companyManageLogDTO);
        SysCompanyDTO sysCompanyDTO = sysCompanysService.get(businInspectionTaskDTO.getCompanyId());
        sysCompanyDTO.setCheckStatus(dto.getCheckStatus());
        sysCompanyDTO.setResultIsClosed(dto.getIsClosed());
        sysCompanysService.update(sysCompanyDTO);
    }

    @Override
    public void saveCheckContent(CheckSaveDTO dto) {
        //获取整改明细
        BusinInspectionCorrectionMxDTO businInspectionCorrectionMxDTO = businInspectionCorrectionMxService.get(dto.getId());
        businInspectionCorrectionMxDTO.setCheckPhotos(dto.getPhotos());
        businInspectionCorrectionMxDTO.setCheckRemark(dto.getRemark());
        businInspectionCorrectionMxDTO.setCheckUnderwrite(dto.getUnderwrite());
        businInspectionCorrectionMxDTO.setCheckDate(new Date());
        //获取整改信息
        BusinInspectionCorrectionDTO businInspectionCorrectionDTO = businInspectionCorrectionService.get(businInspectionCorrectionMxDTO.getInspectionCorrectionId());
        //判断返回状态
        if (dto.getCheckResult().equals(1)) {
            businInspectionCorrectionMxDTO.setCorrectionStatus("2");
            //状态为0生成下次整改任务
        } else {
            businInspectionCorrectionMxDTO.setCorrectionStatus("3");
            //判断是否生成整改信息
            Long child = businInspectionCorrectionService.getChild(businInspectionCorrectionMxDTO.getInspectionCorrectionId());
            //未生成
            if (null == child) {
                BusinInspectionCorrectionEntity businInspectionCorrectionEntity = new BusinInspectionCorrectionEntity();
                businInspectionCorrectionEntity.setCompanyId(businInspectionCorrectionDTO.getCompanyId());
                businInspectionCorrectionEntity.setCompanyName(businInspectionCorrectionDTO.getCompanyName());
                businInspectionCorrectionEntity.setInspectionTaskId(businInspectionCorrectionDTO.getInspectionTaskId());
                businInspectionCorrectionEntity.setInspectionUserName(SecurityUser.getUser().getRealName());
                businInspectionCorrectionEntity.setInspectionDate(new Date());
                businInspectionCorrectionEntity.setLastCorrectionDate(DateUtils.addDateDays(new Date(), 3));
                businInspectionCorrectionEntity.setPid(businInspectionCorrectionDTO.getId());
                businInspectionCorrectionService.insert(businInspectionCorrectionEntity);
                child = businInspectionCorrectionEntity.getId();
            }
            BusinInspectionCorrectionMxEntity businInspectionCorrectionMxEntity = new BusinInspectionCorrectionMxEntity();
            businInspectionCorrectionMxEntity.setInspectionCorrectionId(child);
            businInspectionCorrectionMxEntity.setInspectionTaskItemId(businInspectionCorrectionMxDTO.getInspectionTaskItemId());
            businInspectionCorrectionMxEntity.setInspectionTaskItemName(businInspectionCorrectionMxDTO.getInspectionTaskItemName());
            businInspectionCorrectionMxEntity.setProblemDescription(dto.getRemark());
            businInspectionCorrectionMxEntity.setPhotos(dto.getPhotos());
            businInspectionCorrectionMxEntity.setInspectionListId(businInspectionCorrectionMxDTO.getInspectionListId());
            businInspectionCorrectionMxEntity.setInspectionListName(businInspectionCorrectionMxDTO.getInspectionListName());
            businInspectionCorrectionMxService.insert(businInspectionCorrectionMxEntity);
        }
        businInspectionCorrectionMxService.update(businInspectionCorrectionMxDTO);
        List<Integer> status = businInspectionCorrectionService.getStatus(businInspectionCorrectionMxDTO.getInspectionCorrectionId());
        List<Integer> saveStatus = new ArrayList<>();
        for (Integer integer : status) {
            if (integer.equals(1)) {
                break;
            } else {
                saveStatus.add(integer);
            }
        }
        if (saveStatus.size() == status.size()) {
            if (saveStatus.contains(3)) {
                businInspectionCorrectionDTO.setStatus(3);
            } else {
                businInspectionCorrectionDTO.setCheckUnderwrite(dto.getUnderwrite());
                businInspectionCorrectionDTO.setCheckUserId(SecurityUser.getUserId());
                businInspectionCorrectionDTO.setCheckUserName(SecurityUser.getUser().getRealName());
                businInspectionCorrectionDTO.setStatus(2);
                BusinInspectionTaskDTO businInspectionTaskDTO = this.get(businInspectionCorrectionDTO.getInspectionTaskId());
                businInspectionTaskDTO.setStatus(5);
                businInspectionTaskDTO.setFinishDate(new Date());
                SysCompanyDTO sysCompanyDTO = sysCompanysService.get(businInspectionTaskDTO.getCompanyId());
                sysCompanyDTO.setCheckStatus("3");
                sysCompanyDTO.setResultIsClosed(1);
                sysCompanysService.update(sysCompanyDTO);
                this.update(businInspectionTaskDTO);
            }
            businInspectionCorrectionService.update(businInspectionCorrectionDTO);
        }
    }

    @Override
    public ResultDTO getReportResult(Map<String, Object> params) {
        Integer noQualifiedCount = 0;
        params.put("page", "1");
        params.put("limit", "1000");
        ResultDTO resultDTO = new ResultDTO();
        PageData<BusinInspectionTaskItemDTO> page = businInspectionTaskItemService.page(params);
        List<ResultItemListDTO> resultItemListDTOS = ConvertUtils.sourceToTarget(page.getList(), ResultItemListDTO.class);
        resultDTO.setInspectionCount(resultItemListDTOS.size());
        for (ResultItemListDTO resultItemListDTO : resultItemListDTOS) {
            params.put("inspectionTaskItemId", resultItemListDTO.getId().toString());
            params.put("page", "1");
            PageData<BusinInspectionTaskItemContentDTO> page1 = businInspectionTaskItemContentService.page(params);
            List<ResultItemContentDTO> resultItemContentDTOS = ConvertUtils.sourceToTarget(page1.getList(), ResultItemContentDTO.class);
            resultItemListDTO.setResultItemContentDTO(resultItemContentDTOS);
            if (resultItemListDTO.getScore() < resultItemListDTO.getTotalScore()) {
                noQualifiedCount++;
            }
        }
        resultDTO.setResultItemListDTO(resultItemListDTOS);
        resultDTO.setNoQualifiedCount(noQualifiedCount);
        resultDTO.setId(Long.valueOf((String) params.get("businInspectionTaskId")));
        return resultDTO;
    }
}