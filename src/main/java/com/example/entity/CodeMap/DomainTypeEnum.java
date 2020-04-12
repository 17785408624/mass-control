package com.example.entity.CodeMap;

public enum DomainTypeEnum {
    major("所学专业或从事专业", 1, "major"),
    declaredesign("申报专业", 2, "declaredesign");
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
                        "采矿", "露采", "选煤", "矿山机电", "机械", "电气-供配电", "电气-自动控制", "电气-通信",
                        "电气-信号", "建筑", "结构", "给排水", "暖通空调", "环保", "总图", "运输"
                });
                this.setMapCodes(new String[]{
                        "1", "2", "3", "4", "5", "6", "7", "8",
                        "9", "10", "11", "12", "13", "14", "15", "16"
                });
                break;
            case 2:
                this.setMapValues(new String[]{
                        "采矿工程", "通风安全", "供电", "四大件", "水文地质", "总平面工程", "造价", "环保节能",
                        "土建工程"
                });
                this.setMapCodes(new String[]{
                        "1", "2", "3", "4", "5", "6", "7", "8",
                        "9", "10", "11", "12", "13", "14", "15", "16"
                });
                break;

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
        majorTypeCode = majorTypeCode == "declaredesign_design" || majorTypeCode == "declaredesign_safety" ? "declaredesign" : majorTypeCode;
        majorTypeCode = majorTypeCode == "learnmajor" || majorTypeCode == "workmajor" ? "major" : majorTypeCode;
        switch (majorTypeCode) {
            case "major":
                return major;
            case "declaredesign":
                return declaredesign;
        }
        return null;
    }

}
