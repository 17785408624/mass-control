package com.example.service.vice;

import com.example.common.exceptiondefine.ServiceException;
import com.example.entity.CodeMap.DomainTypeEnum;
import com.util.PublicUtil;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExpertInfo {
    /**
     * 合并专家信息中公司名相似的信息
     *
     * @param expertCompanyList 专家公司信息
     * @return
     */
    public List mergeExpertCompany(List expertCompanyList) {
        List mergeList = new ArrayList();//
        for (int i = 0; i < expertCompanyList.size(); i++) {
            Map expertCompanyInfo = (Map) expertCompanyList.get(i);
            if (expertCompanyInfo == null || !expertCompanyInfo.containsKey("expert_info_companyname") ||
                    !expertCompanyInfo.containsKey("expert_info_id") || expertCompanyInfo.get("expert_info_companyname") == null
                    || expertCompanyInfo.get("expert_info_id") == null) {
                continue;
            }
            String conpanyName = expertCompanyInfo.get("expert_info_companyname").toString();//获取专家公司名信息
            int expert_info_id = Integer.valueOf(expertCompanyInfo.get("expert_info_id").toString());//专家信息id
            String toConpanyName = null;
            String conpanyNameReplace = dislodgeConpanyNameRepetitionKeywords(conpanyName);//去除公司名中重复判定的关键字
            Map mergeInfo = new HashMap();
            Integer mergeExpertIds[] = new Integer[]{expert_info_id};//合并数据的id
            String mergeExpertName = conpanyName;//合并后的公司名
            for (int i1 = i + 1; i1 < expertCompanyList.size(); i1++) {
                Map toExpertCompanyInfo = (Map) expertCompanyList.get(i1);
                if (toExpertCompanyInfo == null) {
                    continue;
                }
                toConpanyName = toExpertCompanyInfo.get("expert_info_companyname").toString();//获取公司名信息
                String toConpanyNameReplace = dislodgeConpanyNameRepetitionKeywords(toConpanyName);//去除公司名中重复判定的关键字
                if (isSimilarityCompanyname(conpanyName, toConpanyName)) {//如果公司名相似，将专家信息中的id添加进合并后相似的重复id数组
                    mergeExpertIds = java.util.Arrays.copyOf(mergeExpertIds, mergeExpertIds.length + 1);
                    mergeExpertIds[mergeExpertIds.length - 1] = Integer.valueOf(toExpertCompanyInfo.get("expert_info_id").toString());
                    expertCompanyList.remove(i1);
                    i1--;
                }
            }
            mergeInfo.put("mergeExpertName", mergeExpertName);
            mergeInfo.put("mergeExpertIds", mergeExpertIds);
            mergeList.add(mergeInfo);
        }
        return mergeList;
    }

    public List mergeExpertCompanyStr(List expertCompanyList) {
        List mergeList = new ArrayList();//
        for (int i = 0; i < expertCompanyList.size(); i++) {
            Map expertCompanyInfo = (Map) expertCompanyList.get(i);
            if (expertCompanyInfo == null) {
                continue;
            }
            String conpanyName = expertCompanyInfo.get("expert_info_companyname").toString();//获取专家公司名信息
            int expert_info_id = Integer.valueOf(expertCompanyInfo.get("expert_info_id").toString());//专家信息id
            String toConpanyName = null;
            String conpanyNameReplace = dislodgeConpanyNameRepetitionKeywords(conpanyName);//去除公司名中重复判定的关键字
            Map mergeInfo = new HashMap();
            StringBuilder mergeExpertIds = new StringBuilder(String.valueOf(expert_info_id));//合并数据的id
            String mergeExpertName = conpanyName;//合并后的公司名
            for (int i1 = i + 1; i1 < expertCompanyList.size(); i1++) {
                Map toExpertCompanyInfo = (Map) expertCompanyList.get(i1);
                if (toExpertCompanyInfo == null) {
                    continue;
                }
                toConpanyName = toExpertCompanyInfo.get("expert_info_companyname").toString();//获取公司名信息
                String toConpanyNameReplace = dislodgeConpanyNameRepetitionKeywords(toConpanyName);//去除公司名中重复判定的关键字

                if (isSimilarityCompanyname(conpanyName, toConpanyName)) {//如果公司名相似，将专家信息中的id添加进合并后相似的重复id数组
                    mergeExpertIds.append("," + toExpertCompanyInfo.get("expert_info_id"));
                    expertCompanyList.remove(i1);
                    i1--;

                }
            }
            mergeInfo.put("mergeExpertName", mergeExpertName);
            mergeInfo.put("mergeExpertIds", mergeExpertIds);
            mergeList.add(mergeInfo);
        }
        return mergeList;
    }

    /**
     * 去除公司名中重复判定的关键字及特定字符
     *
     * @param conpanyName 公司名
     * @return
     */
    private String dislodgeConpanyNameRepetitionKeywords(String conpanyName) {//去除公司名中重复判定的关键字
        boolean trimBlank = true;//是否去除判定相似公司的名字的特定字符
        String[] nameRepetitionCs = new String[]{" ", "，", ","};////判定相似公司的名字去除的字符
        if (trimBlank) {
            for (String nameRepetitionC : nameRepetitionCs) {
                conpanyName = conpanyName.replace(nameRepetitionC, "");//将关键字从公司名字中去除
            }
        }
        String conpanyNameReplace = null;//去除重复判定关键字后的公司名
        String[] nameRepetitionKeywords = new String[]{"公司", "有限公司"};//判定相似公司的名字去除的关键字
        for (String nameRepetitionKeyword : nameRepetitionKeywords) {
            conpanyNameReplace = conpanyName.replace(nameRepetitionKeyword, "");//将关键字从公司名字中去除
        }
        return conpanyNameReplace;
    }

    /**
     * 判断公司名是否相似
     *
     * @param companyname   进行对比的公司名
     * @param toCompanyname 进行对比的公司名
     * @return true为相似，false为不相似
     */
    boolean isSimilarityCompanyname(String companyname, String toCompanyname) {
        int denySimilarityLength = 1;//两个公司名不相似的字符判断最大长度，超过长度则视为不相似
        char[] companynameC = companyname.toCharArray();
        char[] toCompanynameC = toCompanyname.toCharArray();
        for (int i = 0; i < companynameC.length; i++) {
            if (companynameC[i] != toCompanynameC[i]) {
                denySimilarityLength--;
                if (denySimilarityLength < 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 获取随机抽取到的专家id
     *
     * @param userGroupDomains 专业现有专家信息
     * @param majorRequires    抽取专业条件及人数
     * @param majorTypeCode    专业条件类型
     * @return
     */
    public Object[] drawRandomExpert(List userGroupDomains, List majorRequires, String majorTypeCode) throws ServiceException {
        majorTypeCode = majorTypeCode == "declaredesign_design" || majorTypeCode == "declaredesign_safety" ? "declaredesign" : majorTypeCode;
        majorTypeCode = majorTypeCode == "learnmajor" || majorTypeCode == "workmajor" ? "major" : majorTypeCode;
        Object[] drawUids = new String[0];//抽取到的用户id
        forRmajorRequires:
        for (int i = 0; i < majorRequires.size(); i++) {//遍历抽取的专业条件及人数
            Map majorRequire = (Map) majorRequires.get(i);//抽取条件
            Integer requireMajorCode = Integer.valueOf(majorRequire.get("majorCode").toString());//抽取专业条件
            forUserGroupDomains:
            for (int i1 = 0; i1 < userGroupDomains.size(); i1++) {//遍历现有专家的专业信息
                Map userGroupDomain = ((Map) userGroupDomains.get(i1));//现有信息
                Object[] userIds = userGroupDomain.get("user_id").toString().split(",");//得到符合专业条件的专家id
                Object[] userIdsValid = new Object[0];//未被抽取过符合专业条件的专家id
                if (userGroupDomain.get("domain").equals(requireMajorCode.toString())) {//是否遍历到传入的某个专业条件
                    if(drawUids.length>0){
                        for (int num = 1; num < userIds.length; num++) {
                            for (Object o : drawUids) {
                                if (!userIds[i].equals(o)) {
                                    userIdsValid=Arrays.copyOf(userIdsValid, userIdsValid.length + 1);
                                    userIdsValid[userIdsValid.length - 1] = userIds[i];
                                }
                            }

                        }
                    }else{
                        userIdsValid=userIds;
                    }


                    Integer majorNum = Integer.valueOf(majorRequire.get("majorNum").toString());//当前专业需要抽取的人数
                    if (majorNum >= userIdsValid.length) {//抽取专业的人数与现有符合条件的人数相同或多于符合条件的人数
                        String domainValue =
                                DomainTypeEnum.getDomainTypeEnumByTypeCode(majorTypeCode).
                                        getMapValueByMapCode(requireMajorCode.toString());//获取专业名称
                        if (majorNum > userIdsValid.length) {//
                            throw new ServiceException(domainValue + " 抽取人数超出此专业最大人数，因为有专家为多专业。" +
                                    "请重新选择此专业人数");
                        } else {
                            drawUids = Arrays.copyOf(drawUids, userIdsValid.length);
                            System.arraycopy(userIdsValid, 0, drawUids, drawUids.length - userIdsValid.length, userIdsValid.length);//将现有符合条件的人直接添加进抽取到的用户id数组
                            //drawUids=PublicUtil.arraycopy(drawUids,  drawUids.length-userIdsValid.length, userIdsValid, 0, userIdsValid.length);//将现有符合条件的人直接添加进抽取到的用户id数组
                            continue forRmajorRequires;//跳过符合本次专业条件的专家抽取
                        }

                    }
                    //获取随机数作为获取专家id数组元素的下标
                    int[] randomIndex = PublicUtil.randomArray(0, userIdsValid.length , majorNum);
                    Object[] randomDrawUids;//随机抽取到的专家用户id
                    randomDrawUids = new Object[randomIndex.length];
                    for (int count = 0; count < randomIndex.length; count++) {
                        randomDrawUids[count] = userIdsValid[randomIndex[count]];
                    }

                    drawUids = Arrays.copyOf(drawUids, randomDrawUids.length);//抽取到的用户id数组扩容
                    //drawUids=PublicUtil.arraycopy(drawUids,drawUids.length-1,randomDrawUids,0,randomDrawUids.length);
                    System.arraycopy(randomDrawUids, 0, drawUids, drawUids.length - randomDrawUids.length, randomDrawUids.length);
                    //drawUids[drawUids.length - 1] = userIdsValid[random - 1];//将抽中的用户id添加进已抽取的用户id数组中



                }
            }
        }

        return drawUids;
    }

    /**
     * 获取优先随机抽取到的专家id
     *
     * @param userGroupDomains 优先抽取的专家现有信息
     * @param majorRequires    抽取专业条件及人数
     * @param majorTypeCode    专业条件类型
     * @param drawNum          抽取人数
     * @return
     * @throws ServiceException
     */
    public Object[] drawRandomExpert(List userGroupDomains, List majorRequires, String majorTypeCode, Integer drawNum) throws ServiceException {
        List majorRequiresSurplus;//剩余的抽取专业条件及人数
        majorTypeCode = majorTypeCode == "declaredesign_design" || majorTypeCode == "declaredesign_safety" ? "declaredesign" : majorTypeCode;
        majorTypeCode = majorTypeCode == "learnmajor" || majorTypeCode == "workmajor" ? "major" : majorTypeCode;
        Object[] drawUids = new String[0];//抽取到的用户id
        forRmajorRequires:
        for (int i = 0; i < majorRequires.size(); i++) {//遍历抽取的专业条件及人数
            Integer requireMajorCode = Integer.valueOf(((Map) majorRequires.get(i)).get("majorCode").toString());//抽取专业条件
            forUserGroupDomains:
            for (int i1 = 0; i1 < userGroupDomains.size(); i1++) {//遍历现有专家的专业信息
                Map userGroupDomain = ((Map) userGroupDomains.get(i1));//现有信息
                Object[] userIds = userGroupDomain.get("user_id").toString().split(",");//得到符合专业条件的专家id
                Object[] userIdsValid = new Object[0];//未被抽取过符合专业条件的专家id
                for (int num = 1; num < userIds.length; i++) {
                    for (Object o : drawUids) {
                        if (!userIds[i].equals(o)) {
                            Arrays.copyOf(userIdsValid, userIdsValid.length + 1);
                            userIdsValid[userIdsValid.length - 1] = userIds[i];
                        }
                    }

                }
                if (userGroupDomain.get("domain").equals(requireMajorCode.toString())) {//是否遍历到传入的某个专业条件
                    Map majorRequire = (Map) majorRequires.get(i1);//抽取条件
                    Map majorRequireNot = majorRequire;//未完成的抽取条件
                    Integer majorNum = Integer.valueOf(majorRequire.get("majorNum").toString());//当前专业需要抽取的人数
                    if (majorNum >= userIdsValid.length) {//抽取专业的人数与现有符合条件的人数相同或多于符合条件的人数
                        String domainValue =
                                DomainTypeEnum.getDomainTypeEnumByTypeCode(majorTypeCode).
                                        getMapValueByMapCode(requireMajorCode.toString());//获取专业名称
                        if (majorNum > userIdsValid.length) {//

                        } else {
                            drawUids = Arrays.copyOf(drawUids, userIdsValid.length);
                            for (int count = 0; count < userIdsValid.length; count++) {


                            }
                            System.arraycopy(userIdsValid, 0, drawUids, drawUids.length - userIdsValid.length, userIdsValid.length);//将现有符合条件的人直接添加进抽取到的用户id数组
                            //drawUids=PublicUtil.arraycopy(drawUids,  drawUids.length-userIdsValid.length, userIdsValid, 0, userIdsValid.length);//将现有符合条件的人直接添加进抽取到的用户id数组
                            continue forRmajorRequires;//跳过符合本次专业条件的专家抽取
                        }

                    }
                    //获取随机数作为获取专家id数组元素的下标
                    int[] randomIndex = PublicUtil.randomArray(0, userIdsValid.length - 1, userIdsValid.length);
                    Object[] randomDrawUids;//随机抽取到的专家用户id
                    boolean isUidRuepeat = true;//抽取到的用户id中是否有重复的userid
                    randomDrawUids = new Object[randomIndex.length];
                    for (int count = 0; count < randomIndex.length; count++) {
                        randomDrawUids[count] = userIdsValid[randomIndex[count]];
                    }

                    drawUids = Arrays.copyOf(drawUids, randomDrawUids.length);//抽取到的用户id数组扩容
                    //drawUids=PublicUtil.arraycopy(drawUids,drawUids.length-1,randomDrawUids,0,randomDrawUids.length);
                    System.arraycopy(randomDrawUids, 0, drawUids, drawUids.length - randomDrawUids.length, randomDrawUids.length);
                    //drawUids[drawUids.length - 1] = userIdsValid[random - 1];//将抽中的用户id添加进已抽取的用户id数组中
                    majorNum--;
                    isUidRuepeat = false;

                } else if (i1 >= userGroupDomains.size()) {
                    String domainValue =
                            DomainTypeEnum.getDomainTypeEnumByTypeCode(majorTypeCode).
                                    getMapValueByMapCode(requireMajorCode.toString());//获取专业名称
                    throw new ServiceException("没有" + domainValue + "专业符合条件的人数");
                }
            }
        }
        return drawUids;
    }
}
