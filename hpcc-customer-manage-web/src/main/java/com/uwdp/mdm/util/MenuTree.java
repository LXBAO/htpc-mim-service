package com.uwdp.mdm.util;

import com.uwdp.module.api.vo.dto.MdmOrganizationDto;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *树形结构封装类
 */
public class MenuTree {
    private static Map<String,Object> mapArray = new LinkedHashMap<String, Object>();
    private List<MdmOrganizationDto> mdmOrganizationDtoCommon;
    private List<Object> list = new ArrayList<Object>();

    private Integer node_key = 1 ;

    public List<Object>  menuList(List<MdmOrganizationDto> mdmOrganizationDtos){
        this.mdmOrganizationDtoCommon = mdmOrganizationDtos;
        for (MdmOrganizationDto x : mdmOrganizationDtos) {
            Map<String,Object> mapArr = new LinkedHashMap<String, Object>();
            if(x.getGroupParentCode().equals("0120")){
                mapArr.put("groupCode", x.getGroupCode());
                mapArr.put("groupName", x.getGroupName());
                mapArr.put("node-key", node_key++);
//                mapArr.put("parentCode", x.getGroupParentCode());
                mapArr.put("children", menuChild(x.getGroupCode()));
                list.add(mapArr);
            }
        }
        return list;
    }


    private List<?> menuChild(String groupCode) {
        List<Object> lists = new ArrayList<Object>();
        for (MdmOrganizationDto x : mdmOrganizationDtoCommon) {
            Map<String, Object> childArray = new LinkedHashMap<String, Object>();
            if (x.getGroupParentCode().equals(groupCode)) {
                childArray.put("groupCode", x.getGroupCode());
                childArray.put("groupName", x.getGroupName());
                childArray.put("node-key", node_key++);
//                childArray.put("parentCode", x.getGroupParentCode());
                childArray.put("children", menuChild(x.getGroupCode()));
                lists.add(childArray);
            }
        }
        return lists;
    }
}
