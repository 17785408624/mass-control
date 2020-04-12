package com.example.entity.CodeMap;

public enum DomainTypeEnum {
    major("所学专业或从事专业", 1, "major"),
    learnmajor("所学专业", 2, "learnmajor"),
    workmajor("从事专业", 3, "workmajor"),
    declaredesign("申报专业", 4, "declaredesign"),
    declaredesign_design("申报专业技术报告咨询审查类", 5, "declaredesign_design"),
    declaredesign_safety("申报专业安全生产检查类", 6, "declaredesign_safety");
    private String majorTypeName;
    private Integer majorTypeCodeNum;
    private String majorTypeCode;
    private String[] mapValues;
    private String[] mapCodes;

    DomainTypeEnum(String majorTypeName, Integer majorTypeCodeNum, String majorTypeCode) {
        this.majorTypeName = majorTypeName;
        this.majorTypeCodeNum = majorTypeCodeNum;
        this.majorTypeCode = majorTypeCode;
        initMap();
    }

    private String getMajorTypeName() {
        return majorTypeName;
    }

    private void setMajorTypeName(String majorTypeName) {
        this.majorTypeName = majorTypeName;
    }

    private Integer getMajorTypeCodeNum() {
        return majorTypeCodeNum;
    }

    private void setMajorTypeCodeNum(Integer majorTypeCodeNum) {
        this.majorTypeCodeNum = majorTypeCodeNum;
    }

    private String getMajorTypeCode() {
        return majorTypeCode;
    }

    private void setMajorTypeCode(String majorTypeCode) {
        this.majorTypeCode = majorTypeCode;
    }


    private String[] getMapValues() {
        return mapValues;
    }

    private void setMapValues(String[] mapName) {
        this.mapValues = mapName;
    }

    private String[] getMapCodes() {
        return mapCodes;
    }

    private void setMapCodes(String[] mapCode) {
        this.mapCodes = mapCode;
    }

    public void initMap() {
        switch (this.majorTypeCodeNum) {

            case 1:
                this.setMapValues(new String[]{
                        "采矿", "通风安全", "露采", "选煤", "矿山机电", "水文地质", "电气-供配电", "电气-自动控制",
                        "电气-通信", "电气-信号", "建筑", "结构","给排水", "暖通空调", "环保", "总图", "运输","造价","煤矿智能化"

                });
                this.setMapCodes(new String[]{
                        "1", "2", "3", "4", "5", "6", "7", "8",
                        "9", "10", "11", "12", "13", "14", "15", "16","17","18","19"
                });
                break;
            case 2:
                this.setMapValues(new String[]{
                        "采矿", "通风安全", "露采", "选煤", "矿山机电", "水文地质", "电气-供配电", "电气-自动控制",
                        "电气-通信", "电气-信号", "建筑", "结构","给排水", "暖通空调", "环保", "总图", "运输","造价","煤矿智能化"

                });
                this.setMapCodes(new String[]{
                        "1", "2", "3", "4", "5", "6", "7", "8",
                        "9", "10", "11", "12", "13", "14", "15", "16","17","18","19"
                });
                break;
            case 3:
                this.setMapValues(new String[]{
                        "采矿", "通风安全", "露采", "选煤", "矿山机电", "水文地质", "电气-供配电", "电气-自动控制",
                        "电气-通信", "电气-信号", "建筑", "结构","给排水", "暖通空调", "环保", "总图", "运输","造价","煤矿智能化"

                });
                this.setMapCodes(new String[]{
                        "1", "2", "3", "4", "5", "6", "7", "8",
                        "9", "10", "11", "12", "13", "14", "15", "16","17","18","19"
                });
                break;
            case 4:

            case 5:
                this.setMapValues(new String[]{
                        "采矿", "通风安全", "露采", "选煤", "矿山机电", "水文地质", "电气-供配电", "电气-自动控制",
                        "电气-通信", "电气-信号", "建筑", "结构","给排水", "暖通空调", "环保", "总图", "运输","造价","煤矿智能化"

                });
                this.setMapCodes(new String[]{
                        "1", "2", "3", "4", "5", "6", "7", "8",
                        "9", "10", "11", "12", "13", "14", "15", "16","17","18","19"
                });
            case 6:
                this.setMapValues(new String[]{
                        "采矿", "通风安全", "露采", "矿山机电", "水文地质", "电气-供配电", "电气-自动控制",
                        "电气-通信", "电气-信号", "建筑", "煤矿智能化","其他"

                });
                this.setMapCodes(new String[]{
                        "1", "2", "3", "4", "5", "6", "7", "8",
                        "9", "10", "11", "12"
                });
        }

    }

    /**
     * 通过映射code获取映射的值
     *
     * @param mapCode
     * @return
     */
    public String getMapValueByMapCode(String mapCode) {
        for (int i = 0; i < mapCodes.length; i++) {
            if (mapCodes[i].equals(mapCode)) {
                return mapValues[i];
            }

        }
        return null;
    }

    public static DomainTypeEnum getDomainTypeEnumByTypeCode(String majorTypeCode) {
        switch (majorTypeCode) {
            case "major":
                return major;
            case "declaredesign":
                return declaredesign;
            case "learnmajor":
                return learnmajor;
            case "workmajor":
                return workmajor;
            case "declaredesign_design":
                return declaredesign_design;
            case "declaredesign_safety":
                return declaredesign_safety;
        }
        return null;
    }

}
