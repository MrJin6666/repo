package com.goboosoft.company.wastemanagement.utils;

import com.goboosoft.common.utils.DateUtils;
import com.goboosoft.company.wastemanagement.dto.*;
import com.goboosoft.company.wastemanagement.entity.WasteTypeEntity;
import org.apache.catalina.LifecycleState;

import java.util.Date;
import java.util.List;

public class WasteManageUtil {

   private static final String separator = ",";//分割符

    /**
     * 入库 内容类型 拼接
     * @param dto
     * @param typeEntity
     * @return
     */
    public static WasteTypeDTO inSpliceWasteType(InWasteTypeAdditionDTO dto, WasteTypeEntity typeEntity){

        WasteTypeDTO wasteTypeDTO = new WasteTypeDTO();
        if (typeEntity.getNameDir()!=null && !typeEntity.getNameDir().equals("") ){
            wasteTypeDTO.setNameDir(typeEntity.getNameDir() + separator + dto.getNameDir());
        }else {
            wasteTypeDTO.setNameDir(dto.getNameDir());
        }
        if (typeEntity.getWasteTypeDir()!=null && !typeEntity.getWasteTypeDir().equals("") ){
            wasteTypeDTO.setWasteTypeDir(typeEntity.getWasteTypeDir() + separator + dto.getWasteTypeDir());
        }else {
            wasteTypeDTO.setWasteTypeDir(dto.getWasteTypeDir());
        }
        if (typeEntity.getUnitDir()!=null && !typeEntity.getUnitDir().equals("") ){
            wasteTypeDTO.setUnitDir(typeEntity.getUnitDir() + separator + dto.getUnitDir());
        }else {
            wasteTypeDTO.setUnitDir(dto.getUnitDir());
        }
        if (typeEntity.getSourceDir()!=null && !typeEntity.getSourceDir().equals("") ){
            wasteTypeDTO.setSourceDir(typeEntity.getSourceDir() + separator + dto.getSourceDir());
        }else {
            wasteTypeDTO.setSourceDir(dto.getSourceDir());
        }
        if (typeEntity.getSaveDir()!=null && !typeEntity.getSaveDir().equals("") ){
            wasteTypeDTO.setSaveDir(typeEntity.getSaveDir() + separator + dto.getSaveDir());
        }else {
            wasteTypeDTO.setSaveDir(dto.getSaveDir());
        }
        return wasteTypeDTO;
    }

    /**
     * 出库 内容类型 拼接
     * @param dto
     * @param typeEntity
     * @return
     */
    public static WasteTypeDTO outSpliceWasteType(OutWasteTypeAdditionDTO dto, WasteTypeEntity typeEntity){

        WasteTypeDTO wasteTypeDTO = new WasteTypeDTO();
        if (typeEntity.getNameDir()!=null && !typeEntity.getNameDir().equals("") ){
            wasteTypeDTO.setNameDir(typeEntity.getNameDir() + separator + dto.getNameDir());
        }else {
            wasteTypeDTO.setNameDir(dto.getNameDir());
        }
        if (typeEntity.getWasteTypeDir()!=null && !typeEntity.getWasteTypeDir().equals("") ){
            wasteTypeDTO.setWasteTypeDir(typeEntity.getWasteTypeDir() + separator + dto.getWasteTypeDir());
        }else {
            wasteTypeDTO.setWasteTypeDir(dto.getWasteTypeDir());
        }
        if (typeEntity.getUnitDir()!=null && !typeEntity.getUnitDir().equals("") ){
            wasteTypeDTO.setUnitDir(typeEntity.getUnitDir() + separator + dto.getUnitDir());
        }else {
            wasteTypeDTO.setUnitDir(dto.getUnitDir());
        }
        if (typeEntity.getReceiveUnitDir()!=null && !typeEntity.getReceiveUnitDir().equals("") ){
            wasteTypeDTO.setReceiveUnitDir(typeEntity.getReceiveUnitDir() + separator + dto.getReceiveUnitDir());
        }else {
            wasteTypeDTO.setReceiveUnitDir(dto.getReceiveUnitDir());
        }
        if (typeEntity.getDeptDir()!=null && !typeEntity.getDeptDir().equals("") ){
            wasteTypeDTO.setDeptDir(typeEntity.getDeptDir() + separator + dto.getDeptDir());
        }else {
            wasteTypeDTO.setDeptDir(dto.getDeptDir());
        }
        if (typeEntity.getDirectionDir()!=null && !typeEntity.getDirectionDir().equals("") ){
            wasteTypeDTO.setDirectionDir(typeEntity.getDirectionDir() + separator + dto.getDirectionDir());
        }else {
            wasteTypeDTO.setDirectionDir(dto.getDirectionDir());
        }
        return wasteTypeDTO;
    }


    /**
     * 类型数据（数据库内容类型是以“,”分割的，所以返回时以“,”分割转为数组返回）
     * @param typeDTO
     * @return
     */
    public static WasteTypeResultDTO wasteContentTypeList(WasteTypeDTO typeDTO){

        WasteTypeResultDTO resultDTO = new WasteTypeResultDTO();

        if (typeDTO.getNameDir()!=null && !typeDTO.getNameDir().equals("") ){
            resultDTO.setNameDir(typeDTO.getNameDir().split(separator));
        }
        if (typeDTO.getWasteTypeDir()!=null && !typeDTO.getWasteTypeDir().equals("") ){
            resultDTO.setWasteTypeDir(typeDTO.getWasteTypeDir().split(separator));
        }
        if (typeDTO.getUnitDir()!=null && !typeDTO.getUnitDir().equals("") ){
            resultDTO.setUnitDir(typeDTO.getUnitDir().split(separator));
        }
        if (typeDTO.getSourceDir()!=null && !typeDTO.getSourceDir().equals("") ){
            resultDTO.setSourceDir(typeDTO.getSourceDir().split(separator));
        }
        if (typeDTO.getSaveDir()!=null && !typeDTO.getSaveDir().equals("") ){
            resultDTO.setSaveDir(typeDTO.getSaveDir().split(separator));
        }
        if (typeDTO.getReceiveUnitDir()!=null && !typeDTO.getReceiveUnitDir().equals("") ){
            resultDTO.setReceiveUnitDir(typeDTO.getReceiveUnitDir().split(separator));
        }
        if (typeDTO.getDeptDir()!=null && !typeDTO.getDeptDir().equals("") ){
            resultDTO.setDeptDir(typeDTO.getDeptDir().split(separator));
        }
        if (typeDTO.getDirectionDir()!=null && !typeDTO.getDirectionDir().equals("") ){
            resultDTO.setDirectionDir(typeDTO.getDirectionDir().split(separator));
        }
        return resultDTO;
    }


    /**
     * 判断危废协议是否过期
     * @param dtos
     * @return
     */
    public static List<WasteProtocolListDTO> isOverdue(List<WasteProtocolListDTO> dtos){

        for (int i = 0; i < dtos.size(); i++) {
            String endDate = dtos.get(i).getEndDate();
            Date date = DateUtils.parse(endDate, "yyyy-MM-dd");
            Date currentDate = new Date();
            if (date.before(currentDate)) {
                dtos.get(i).setStatus(1);
            }
        }
        return dtos;
    }


}
