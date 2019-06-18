package com.goboosoft.company.busininspect.utils;

import com.goboosoft.company.busininspect.dto.InspectionCorrectionListDTO;

import java.util.Date;
import java.util.List;

public class StatusChangeUtil {

    public static List<InspectionCorrectionListDTO> statusChangeByDate(List<InspectionCorrectionListDTO> dtoList){
        for (int i = 0; i < dtoList.size(); i++) {
            Integer status = dtoList.get(i).getCorrectionStatus();
            Date date = dtoList.get(i).getLastCorrectionDate();
            Date currentDate = new Date();
            switch (status){//0新建 1待审核 2审核通过 3审核失败  5超期
                case 0://新建
                    if (date.before(currentDate)){
                        dtoList.get(i).setInspectionStatus("超期");
                        dtoList.get(i).setCorrectionStatus(5);
                    }else {
                        dtoList.get(i).setInspectionStatus("待整改");
                    }
                    break;
                case 1://待审核
                    if (date.before(currentDate)){
//                        dtoList.get(i).setInspectionStatus("超期");
                    }else {
                        dtoList.get(i).setInspectionStatus("待审核");
                    }
                    break;
                case 2://审核通过
                    dtoList.get(i).setInspectionStatus("正常");
                    break;
                case 3://审核失败
                    if (date.before(currentDate)){
//                        dtoList.get(i).setInspectionStatus("超期");
                    }else {
                        dtoList.get(i).setInspectionStatus("待整改");
                    }
                    break;
                default:
                    break;
            }
        }
        return dtoList;

    }

}
