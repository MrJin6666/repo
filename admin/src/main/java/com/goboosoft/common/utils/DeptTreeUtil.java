package com.goboosoft.common.utils;

import com.goboosoft.modules.sys.dto.SysDeptDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * Description:
 *
 * @author cy
 * @date 2019年03月07日 8:56
 * version 1.0
 */
public class DeptTreeUtil {

    public static List<SysDeptDTO> createTreeDept(String id,List<SysDeptDTO> trees) {
        List<SysDeptDTO> treeMenus = null;
        if (null != trees && !trees.isEmpty()) {
            // 创建根节点
            SysDeptDTO root = new SysDeptDTO();
            root.setName("组织架构");
            // 组装Map数据
            Map<String, SysDeptDTO> dataMap = new HashMap<String, SysDeptDTO>();
            for (SysDeptDTO tree : trees) {
                dataMap.put(tree.getId()+"", tree);
            }
            // 组装树形结构
            Set<Map.Entry<String, SysDeptDTO>> entrySet = dataMap.entrySet();
            for (Map.Entry<String, SysDeptDTO> entry : entrySet) {
                SysDeptDTO tree = entry.getValue();
                if (null == tree.getPid() || "0".equals(String.valueOf(tree.getPid()))) {
                    root.getChildren().add(tree);
                } else {
                    dataMap.get(tree.getPid() +"").getChildren().add(tree);
                }
            }
            if(StringUtils.isBlank(id)){
                treeMenus = root.getChildren();
            }else {
                treeMenus = dataMap.get(id).getChildren();
            }
        }
        return treeMenus;
    }

    public static List<SysDeptDTO> createTreeDeptParent(String id,List<SysDeptDTO> trees) {
        List<SysDeptDTO> treeMenus = new ArrayList<SysDeptDTO>();
        if (null != trees && !trees.isEmpty()) {
            // 创建根节点
            SysDeptDTO root = new SysDeptDTO();
            root.setName("组织架构");
            // 组装Map数据
            Map<String, SysDeptDTO> dataMap = new HashMap<String, SysDeptDTO>();
            for (SysDeptDTO tree : trees) {
                dataMap.put(tree.getId()+"", tree);
            }
            // 组装树形结构
            Set<Map.Entry<String, SysDeptDTO>> entrySet = dataMap.entrySet();
            for (Map.Entry<String, SysDeptDTO> entry : entrySet) {
                SysDeptDTO tree = entry.getValue();
                if (null == tree.getPid() || "0".equals(String.valueOf(tree.getPid()))) {
                    root.getChildren().add(tree);
                } else {
                    dataMap.get(tree.getPid() +"").getChildren().add(tree);
                }
            }
            if(StringUtils.isBlank(id)){
                treeMenus.add(root);
            }else {
                treeMenus.add(dataMap.get(id));
            }
        }
        return treeMenus;
    }

}
