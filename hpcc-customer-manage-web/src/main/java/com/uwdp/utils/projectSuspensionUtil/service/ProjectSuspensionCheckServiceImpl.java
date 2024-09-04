package com.uwdp.utils.projectSuspensionUtil.service;

import com.uwdp.jdbcutils.DatabaseReaderUtil;
import com.uwdp.utils.StringUtils;
import com.uwdp.utils.projectSuspensionUtil.service.iservice.ProjectSuspensionCheckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
@Service
public class ProjectSuspensionCheckServiceImpl implements ProjectSuspensionCheckService {

    @Autowired
    DatabaseReaderUtil databaseReaderUtil;

    @Autowired
    StringUtils stringUtils;
    /**
     * 中止时校验项目关联流程是否全部审批通过
     * switch (endState){
     *                         case "1":returnStr="仅登记";break;
     *                         case "2":returnStr="已跟踪";break;
     *                         case "3":returnStr="已投标";break;
     *                         case "4":returnStr="已中标";break;
     *                         case "5":returnStr="已签约";break;
     *                         case "6":returnStr="已实施";break;
     *                         case "7":returnStr="已中止";break;
     *                     }
     * @param id 项目id
     * @return Map{code:false时不可中止，true时可中止；msg:返回信息}
     */
    @Override
    public Map checkWorkflow(String id) {
        log.info("进入checkWorkflow方法！！！");
        Map returnMap=new HashMap();
        if(id!=null&&!id.isEmpty()){
            log.info("~~~~~~~~~~~~项目id：{}~~~~~~~~~~~~~！",id);
            //校验是否有过中止操作
            boolean checkIsNull=checkIsNull(id);
            if(checkIsNull){
                returnMap.put("code",false);
                returnMap.put("msg","当前项目已经有过中止操作，请点击项目中止菜单查看");
                return returnMap;
            }
            //先查项目登记看项目当前所属阶段
            String sql="select * from T_PROJECTRECORDS where id='"+id+"'";
            List<Map> list=databaseReaderUtil.findData("local",sql);
            if(list!=null&&!list.isEmpty()){
                Map map=list.get(0);
                //当前项目所属阶段
                String projectPhase=stringUtils.getStr(map.get("projectphase"));
                String hideState=stringUtils.getStr(map.get("hidestate"));
                log.info("~~~~~~~~~~~~projectPhase：{}~~~~~~~~~~~~~！",projectPhase);
                log.info("~~~~~~~~~~~~hideState：{}~~~~~~~~~~~~~！",hideState);
                String endState;
                if(!projectPhase.equals(hideState)){//不相等说明流程未走完
                    endState=hideState==""?projectPhase:hideState;
                    String sql1="";
                    if("1".equals(endState)){
                        sql1="select * from T_PROJECTRECORDS p left join t_lmcworkflow l " +
                                "on p.ID =  l.BIZID WHERE p.PROJECTNUMBER='"+id+"'";
                        List<Map> list1=databaseReaderUtil.findData("local",sql1);
                        if(list1!=null&&!list1.isEmpty()){
                            Map map1=list1.get(0);
                            //流程状态
                            String workflowStatus=stringUtils.getStr(map1.get("workflowstatus"));
                            if(!"COMPLETED".equals(workflowStatus)){
                                returnMap.put("code",false);
                                returnMap.put("msg","当前项目在项目登记流程中，不可中止!");
                            }else{
                                returnMap.put("code",true);
                                returnMap.put("msg","可中止");
                            }
                        }
                    }else if("2".equals(endState)){
                        sql1="select * from t_projecttracking p left join t_lmcworkflow l " +
                                "on p.ID =  l.BIZID WHERE p.PROJECTNUMBER='"+id+"'";
                        List<Map> list1=databaseReaderUtil.findData("local",sql1);
                        if(list1!=null&&!list1.isEmpty()){
                            Map map1=list1.get(0);
                            //流程状态
                            String workflowStatus=stringUtils.getStr(map1.get("workflowstatus"));
                            if("ACTIVE".equals(workflowStatus)){
                                returnMap.put("code",false);
                                returnMap.put("msg","当前项目在项目跟踪流程中，不可中止!");
                            }else{
                                returnMap.put("code",true);
                                returnMap.put("msg","可中止");
                            }
                        }
                    }else if("3".equals(endState)){
                        sql1="select * from t_projectbidding p left join t_lmcworkflow l " +
                                "on p.ID =  l.BIZID WHERE p.PROJECTNUMBER='"+id+"'";
                        List<Map> list1=databaseReaderUtil.findData("local",sql1);
                        if(list1!=null&&!list1.isEmpty()){
                            Map map1=list1.get(0);
                            //流程状态
                            String workflowStatus=stringUtils.getStr(map1.get("workflowstatus"));
                            if("ACTIVE".equals(workflowStatus)){
                                returnMap.put("code",false);
                                returnMap.put("msg","当前项目在项目投标流程中，不可中止!");
                            }else{
                                returnMap.put("code",true);
                                returnMap.put("msg","可中止");
                            }
                        }
                    }else if("4".equals(endState)){
                        returnMap.put("code",false);
                        returnMap.put("msg","当前项目在项目中标中有数据，不可中止！");
                    }else if("5".equals(endState)){
                        returnMap.put("code",false);
                        returnMap.put("msg","当前项目在项目签约中有数据，不可中止！");
                    }else if("6".equals(endState)){
                        returnMap.put("code",false);
                        returnMap.put("msg","当前项目在项目实施中有数据，不可中止！");
                    }else if("7".equals(endState)){
                        returnMap.put("code",false);
                        returnMap.put("msg","当前项目已被中止，不可再次中止！");
                    }else{
                        returnMap.put("code",false);
                        returnMap.put("msg","未知状态的项目！！");
                    }
                }else{//所有程都走完
                    endState=projectPhase;
                    switch (endState){
                        case "1":returnMap.put("code",true);
                            returnMap.put("msg","可中止");break;
                        case "2":returnMap.put("code",true);
                            returnMap.put("msg","可中止");break;
                        case "3":returnMap.put("code",true);
                            returnMap.put("msg","可中止");break;
                        case "4":returnMap.put("code",false);
                            returnMap.put("msg","当前项目在项目中标中有数据，不可中止！");break;
                        case "5":returnMap.put("code",false);
                            returnMap.put("msg","当前项目在项目签约中有数据，不可中止！");break;
                        case "6":returnMap.put("code",false);
                            returnMap.put("msg","当前项目在项目实施中有数据，不可中止！");break;
                        case "7":returnMap.put("code",false);
                            returnMap.put("msg","当前项目已被中止，不可再次中止！");break;
                            default:returnMap.put("code",false);
                                returnMap.put("msg","未知状态的项目！！");
                    }
                }
            }else{
                returnMap.put("code",false);
                returnMap.put("msg","该项目在项目登记中不存在！！");
            }
        }
        return returnMap;
    }

    /**
     * 选择中止时校验该项目是否已经发起过中止
     * @param id 项目id
     * @return true/false
     */
    @Override
    public boolean checkIsNull(String id) {
        if(id!=null&&!id.isEmpty()){
            String sql="select * from t_projectsuspension where projectId='"+id+"'";
            List list=databaseReaderUtil.findData("local",sql);
            if(list==null||list.isEmpty()){
                return false;
            }
            return true;
        }
        return true;
    }

    /**
     * 选择中止时根据项目id获取项目登记数据
     * @param id 项目id
     * @return true/false
     */
    public Map getProjectNum(String id) {
        if(id!=null&&!id.isEmpty()){
            String sql="select * from T_PROJECTRECORDS where id='"+id+"'";
            List<Map> list=databaseReaderUtil.findData("local",sql);
            if(list==null||list.isEmpty()){
                return null;
            }
            return list.get(0);
        }
        return null;
    }
}
