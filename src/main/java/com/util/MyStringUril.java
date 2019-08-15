package com.util;

public class MyStringUril {
    /**
     * 返回字符串中字符c第num次出现的位置，从尾部开始计算
     * @param c
     * @param str
     * @param num 第几次出现
     * @return 返回位置下标 从0开始
     */
    public static int lastIndexOf(String str,char c,int num){
        int index=str.length();
        for(int i=0;i<num;i++){
            index=str.lastIndexOf(c,index-1);
        }
        return index;
    }
    /**
     * 返回字符串中字符c第num次出现的位置
     * @param c
     * @param str
     * @param num 第几次出现
     * @return 返回位置下标 从0开始
     */
    public static int indexOf(String str,char c,int num){
        int index=-1;
        for(int i=0;i<num;i++){
            index=str.indexOf(c,index+1);
        }
        return index;
    }
}
