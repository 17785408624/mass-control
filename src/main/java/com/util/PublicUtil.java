package com.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Map;

public class PublicUtil {
    /**数组转换
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
        if (map != null && map.containsKey(mapKey) && map.get(mapKey) != null && !map.get(mapKey).equals(null) && !map.get(mapKey).equals(" ") && !map.get(mapKey).equals("")) {
            return false;
        }
        return true;
    }

    /**
     * 判断相同类型的几个对象中属性值是否相等
     * 只支持属性基本类型、基本类型封装类和String类型的对比
     * @param
     * @return 相等返回true
     */
    public static boolean isEntityEquality(Object... o) {
        try {
            Object o1=o[0];
            Object o2=o[1];
            Field[]fs=o1.getClass().getDeclaredFields();//获取所有属性
            for(int i=0;i<fs.length;i++){
                Field f=fs[i];//
                f.setAccessible(true);
                Object fValue=f.get(o1);//属性值
                Class fType=f.getType();//属性类型
                if(isClassBase(fType)){//判断是否为基本类型、String类型或者是基本类型的封装类
                    Field fTo=o2.getClass().getDeclaredField(f.getName());
                    fTo.setAccessible(true);
                    Object fValueTo=fTo.get(o2);
                    if((fValue!=null&&fValueTo==null)||(fValue!=null&&!fValueTo.equals(fValue))){
                        return false;
                    }
                }
            }
        }catch ( IllegalArgumentException | IllegalAccessException|NoSuchFieldException| SecurityException e){
            e.printStackTrace();
        }

        return true;
    }

    /**
     * 判断是否为基本类型、String类型或者是基本类型的封装类
     * @param c 需要判断的属性
     * @return
     */
    private static Boolean isClassBase(Class c){
        if(c.equals(String.class)||c.equals(Integer.class)||c.equals(Byte.class)||c.equals(Boolean.class)||
                c.equals(Short.class)||c.equals(Character.class)||c.equals(Long.class)||c.equals(Float.class)||
                c.equals(Double.class)){
            return true;
        }
        else if(c.getName().equals("int")||c.getName().equals("byte")||c.getName().equals("boolean")||c.getName().equals("short")||
                c.getName().equals("char")||c.getName().equals("long")||c.getName().equals("float")||c.getName().equals("double"))
        {
            return true;
        }

        return false;
    }



}
