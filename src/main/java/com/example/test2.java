package com.example;

import com.example.service.vice.ExpertInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test2 {
    public static void main(String[]args){
        Map map=new HashMap();
        map.put("expert_info_companyname","煤炭设计院");
        map.put("expert_info_id",0);
        Map map1=new HashMap();
        map1.put("expert_info_companyname","煤矿设计院");
        map1.put("expert_info_id",1);
        Map map2=new HashMap();
        map2.put("expert_info_companyname","士大夫公司");
        map2.put("expert_info_id",2);
        Map map3=new HashMap();
        map3.put("expert_info_companyname","士他夫公司");
        map3.put("expert_info_id",3);
        Map map4=new HashMap();
        map4.put("expert_info_companyname","士去夫公司");
        map4.put("expert_info_id",4);
        Map map5=new HashMap();
        map5.put("expert_info_companyname","士啊夫公司");
        map5.put("expert_info_id",5);
        Map map6=new HashMap();
        map6.put("expert_info_companyname","士发夫公司");
        map6.put("expert_info_id",6);
        Map map7=new HashMap();
        map7.put("expert_info_companyname","士是夫公司");
        map7.put("expert_info_id",7);
        Map map8=new HashMap();
        map8.put("expert_info_companyname","士是夫双方都是集团");
        map8.put("expert_info_id",8);
        Map map9=new HashMap();
        map9.put("expert_info_companyname","士是夫双请都是集团");
        map9.put("expert_info_id",9);
        List list=new ArrayList();
        list.add(map);
        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        list.add(map5);
        list.add(map6);
        list.add(map7);
        list.add(map8);
        list.add(map9);
        ExpertInfo ei=new ExpertInfo();
        List list1=ei.mergeExpertCompanyStr(list);
        System.out.println();

    }
}
