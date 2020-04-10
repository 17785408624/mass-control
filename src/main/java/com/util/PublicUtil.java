package com.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class PublicUtil {
    /**
     * 数组转换
     *
     * @param targetType   要转成的类型
     * @param arrayObjects 被转的数组
     * @param <T>
     * @return
     */
    public static <T> T[] convertArray(Class<T> targetType, Object[] arrayObjects) {
        if (targetType == null) {
            return (T[]) arrayObjects;
        }
        if (arrayObjects == null) {
            return null;
        }
        T[] targetArray = (T[]) Array.newInstance(targetType, arrayObjects.length);
        try {
            System.arraycopy(arrayObjects, 0, targetArray, 0, arrayObjects.length);
        } catch (ArrayStoreException e) {
            for (int i = 0; i < arrayObjects.length; i++) {
                targetArray[i] = (T) Integer.valueOf(String.valueOf(arrayObjects[i]));
            }
        }
        return targetArray;
    }

    /**
     * 判断key为String的map中的键对应是否有值
     *
     * @param map    map对象
     * @param mapKey 键名
     * @return true为没有设置值
     */
    public static Boolean mapKeyIsNull_keyString(Map map, String mapKey) {
        if (map == null || !map.containsKey(mapKey) || map.get(mapKey) == null || map.get(mapKey).equals(null) ||
                map.get(mapKey).equals(" ") || map.get(mapKey).equals("")) {
            return true;
        }
        if (map.get(mapKey) instanceof List) {
            if (((List) map.get(mapKey)).size() < 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断相同类型的几个对象中属性值是否相等
     * 只支持属性基本类型、基本类型封装类和String类型的对比
     *
     * @param
     * @return 相等返回true
     */
    public static boolean isEntityEquality(Object... o) {
        try {
            Object o1 = o[0];
            Object o2 = o[1];
            Field[] fs = o1.getClass().getDeclaredFields();//获取所有属性
            for (int i = 0; i < fs.length; i++) {
                Field f = fs[i];//
                f.setAccessible(true);
                Object fValue = f.get(o1);//属性值
                Class fType = f.getType();//属性类型
                if (isClassBase(fType)) {//判断是否为基本类型、String类型或者是基本类型的封装类
                    Field fTo = o2.getClass().getDeclaredField(f.getName());
                    fTo.setAccessible(true);
                    Object fValueTo = fTo.get(o2);
                    if ((fValue != null && fValueTo == null) || (fValue != null && !fValueTo.equals(fValue))) {
                        return false;
                    }
                }
            }
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * 判断是否为基本类型、String类型或者是基本类型的封装类
     *
     * @param c 需要判断的属性
     * @return
     */
    private static Boolean isClassBase(Class c) {
        if (c.equals(String.class) || c.equals(Integer.class) || c.equals(Byte.class) || c.equals(Boolean.class) ||
                c.equals(Short.class) || c.equals(Character.class) || c.equals(Long.class) || c.equals(Float.class) ||
                c.equals(Double.class)) {
            return true;
        } else if (c.getName().equals("int") || c.getName().equals("byte") || c.getName().equals("boolean") || c.getName().equals("short") ||
                c.getName().equals("char") || c.getName().equals("long") || c.getName().equals("float") || c.getName().equals("double")) {
            return true;
        }

        return false;
    }

    /**
     * 获取随机数字字符串
     *
     * @param length 数字长度
     * @return
     */
    public static String getRandomNum(Integer length) {
        String rc = "";
        for (int i = 0; i < length; i++) {
            rc += (int) (Math.random() * 10);
        }

        return rc;
    }

    static String getRandomCh(Integer length) {

        return null;
    }

    /**
     * 移除数组中的元素
     *
     * @param originalArray 原数组
     * @param removeIndex   移除的元素下标
     * @return
     */
    public static Object[] arrayRemove(Object[] originalArray, Integer removeIndex) {
        String[] copyUtil1 = new String[removeIndex - 1];//随机元素前的元素数组
        String[] copyUtil2 = new String[originalArray.length - removeIndex];//随机元素后的元素数组
        System.arraycopy(originalArray, 0, copyUtil1, 0, removeIndex - 1);//获取随机元素前的元素
        System.arraycopy(originalArray, removeIndex - 1, copyUtil2, removeIndex - 1, originalArray.length - 1);//获取随机元素后面的元素
        Object[] copyUtil = new String[copyUtil1.length + copyUtil2.length];//原数组移除被抽中用户id
        System.arraycopy(copyUtil1, 0, copyUtil, 0, copyUtil1.length);
        System.arraycopy(copyUtil2, 0, copyUtil, copyUtil1.length, copyUtil2.length);
        return copyUtil;
    }
    public static Object[] arrayRemove(Object[] originalArray, Integer... removeIndexs) {
//        String[] copyUtil1 = new String[removeIndex - 1];//随机元素前的元素数组
//        String[] copyUtil2 = new String[originalArray.length - removeIndex];//随机元素后的元素数组
//        System.arraycopy(originalArray, 0, copyUtil1, 0, removeIndex - 1);//获取随机元素前的元素
//        System.arraycopy(originalArray, removeIndex - 1, copyUtil2, removeIndex - 1, originalArray.length - 1);//获取随机元素后面的元素
//        Object[] copyUtil = new String[copyUtil1.length + copyUtil2.length];//原数组移除被抽中用户id
//        System.arraycopy(copyUtil1, 0, copyUtil, 0, copyUtil1.length);
//        System.arraycopy(copyUtil2, 0, copyUtil, copyUtil1.length, copyUtil2.length);
//        return copyUtil;
        return originalArray;
    }

    /**
     * 随机指定范围内N个不重复的数
     * 最简单最基本的方法
     *
     * @param min 指定范围最小值
     * @param max 指定范围最大值
     * @param n   随机数个数
     */
    public static int[] randomCommon(int min, int max, int n) {
        if (n > (max - min + 1) || max < min) {
            return null;
        }
        int[] result = new int[n];
        int count = 0;
        while (count < n) {
            int num = (int) (Math.random() * ((max + 1) - min)) + min;
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if (num == result[j]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                result[count] = num;
                count++;
            }
        }
        return result;
    }

    /**
     * 随机指定范围内N个不重复的数
     * 在初始化的无重复待选数组中随机产生一个数放入结果中，
     * 将待选数组被随机到的数，用待选数组(len-1)下标对应的数替换
     * 然后从len-2里随机产生下一个随机数，如此类推
     * @param max  指定范围最大值
     * @param min  指定范围最小值
     * @param n  随机数个数
     * @return int[] 随机数结果集
     */
    public static int[] randomArray(int min,int max,int n){
        int len = max-min+1;//初始数组长度
        if(max < min || n > len){
            return null;
        }

        //初始化给定范围的待选数组
        int[] source = new int[len];
        for (int i = min; i < min+len; i++){
            source[i-min] = i;
        }

        int[] result = new int[n];
        Random rd = new Random();
        int index = 0;
        for (int i = 0; i < result.length; i++) {

            index = Math.abs(rd.nextInt() % len--);//随机一个int数组，取余初始数组长度后得到一个随机值
            //将随机到的数放入结果集
            result[i] = source[index];
            //将待选数组中被随机到的数，用待选数组(len-1)下标对应的数替换
            source[index] = source[len];
        }
        return result;
    }

    /**
     *
     * @param src
     * @param srcPos
     * @param dest
     * @param destPos
     * @param length
     * @return
     */
   public static Object[] arraycopy(Object[] src,  int  srcPos,
                      Object[] dest, int destPos,
                      int length){

        for(int i=0;i<=length;i++){
            src[srcPos+i]=dest[destPos+i];
        }
        return dest;

   }


}
