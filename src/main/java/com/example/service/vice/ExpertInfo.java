package com.example.service.vice;

import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
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
     * @param userGroupDomains 专业现有专家信息
     * @param majorRequires    抽取专业条件及人数
     * @return
     */
    public String[] drawRandomExpert(List userGroupDomains, List majorRequires) {
        String[] drawUid = new String[0];//抽取到的用户id
        for (int i = 0; i < majorRequires.size(); i++) {//遍历抽取的专业条件及人数
            Integer requireMajorCode = Integer.valueOf(((Map) majorRequires.get(i)).get("majorCode").toString());//抽取专业条件
            for (int i1 = 0; i1 < userGroupDomains.size(); i1++) {//遍历现有专业信息
                Map userGroupDomain = ((Map) userGroupDomains.get(i1));//现有信息
                //Object o=userGroupDomain.get("domain");
                if (userGroupDomain.get("domain").equals(requireMajorCode.toString())) {//传入的专业条件中现有的信息
                    Map majorRequire = (Map) majorRequires.get(i1);//抽取条件
                    Integer majorNum = Integer.valueOf(majorRequire.get("majorNum").toString());//需要抽取的人数
                    while (majorNum != null && majorNum > 0) {
                        String[] userId = new String[0];
                        if (majorNum == Integer.valueOf(majorRequire.get("majorNum").toString())) {//初始符合专业条件的专家id
                            userId = userGroupDomain.get("user_id").toString().split(",");//得到符合专业条件的专家id
                        }
                        Integer random = (int) Math.random() * (userId.length )+ 1;//得到专家人数总数中的一个随机数
                        //将随机获取到的用户id加入抽取的用户id数组中
                        drawUid = Arrays.copyOf(drawUid, drawUid.length + 1);
                        drawUid[drawUid.length - 1] = userId[random - 1];
                        String[] copyUtil1 = new String[random - 1];//随机元素前的元素数组
                        String[] copyUtil2 = new String[userId.length - random];//随机元素后的元素数组
                        System.arraycopy(userId, 0, copyUtil1, 0, random-1);//获取随机元素前的元素
                        System.arraycopy(userId, random - 1, copyUtil2, random - 1, userId.length-1);//获取随机元素后面的元素
                        String[] copyUtil= Arrays.copyOf(copyUtil1,copyUtil1.length+copyUtil1.length);
                        System.arraycopy(copyUtil1, 0, copyUtil, 0, copyUtil1.length);
                        System.arraycopy(copyUtil2, 0, copyUtil, copyUtil1.length-1, copyUtil2.length);
                        userId=copyUtil;
                        drawUid= Arrays.copyOf(drawUid,drawUid.length+userId.length);
                        System.arraycopy(userId, 0, drawUid, drawUid.length-userId.length, userId.length);
                        majorNum--;
                    }


                }

            }

        }

        return drawUid;
    }

    ;


}
