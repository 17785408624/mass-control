package com.example.entity.ExportExcel;



import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ExcelDataEntity implements Serializable {
    private static final long serialVersionUID = 6133772627258154184L;
    /**
     * 表头
     */
    private List<String> titles;

    /**
     * 数据
     */
    private List rows;

    /**
     * 页签名称
     */
    private String name;

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public ExcelDataEntity(List listM,ExcelType eet){
        init( listM, eet);
    }
    public ExcelDataEntity(List listM,String sheetName,String[]ExcelTitle){
        init(  listM, sheetName,ExcelTitle);
    }
    private void init(List listM,String sheetName,String[]ExcelTitle){
        setRows(listM);
        setName(sheetName);
        setTitles(Arrays.asList(ExcelTitle));//数组转换list
    }
    private void init(List listM,ExcelType eet){
         setRows(listM);
         setName(eet.getTypeName());
         setTitles(Arrays.asList(eet.getExcelTitle()));
    }



}
