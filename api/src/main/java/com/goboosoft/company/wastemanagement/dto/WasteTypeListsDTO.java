package com.goboosoft.company.wastemanagement.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 * 企业危废管理内容类型
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-15
 */
@Data
@ApiModel(value = "企业类型")
public class WasteTypeListsDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	private List<WasteSelectDTO> wasteSelectTypes;

	private List<WasteSelectDTO> wasteSelectNames;

	private List<WasteSelectDTO> wasteSelectUnits;

	private List<WasteSelectDTO> wasteSelectSources;

	private List<WasteSelectDTO> wasteSelectLocals;

	private List<WasteSelectDTO> wasteSelectRevices;

	private List<WasteSelectDTO> wasteSelectGones;

	private List<WasteSelectDTO> wasteSelectDepts;

}