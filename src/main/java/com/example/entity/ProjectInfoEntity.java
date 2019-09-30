package com.example.entity;

public class ProjectInfoEntity {
    /**
     * 数据主键
     */
    private Integer projectInfoId;

    /**
     * 项目名字
     */
    private String projectInfoName;

    /**
     * 项目地区
     */
    private String projectInfoArea;

    /**
     * 项目性质 1新建 2改扩建
     */
    private Integer projectInfoNature;

    /**
     * 投资额 单位（万元）
     */
    private Double projectInfoInvest;

    /**
     * 规模 (单位 万吨/年)
     */
    private Double projectInfoScale;

    /**
     * 工期 (单位 月)
     */
    private Double projectInfoDuration;

    /**
     * 服务年限(单位/年)
     */
    private Double projectInfoServe;

    /**
     * 保有储量 (单位 万吨)
     */
    private Double projectInfoReservesquantity;

    /**
     * 可采储量(单位 万吨)
     */
    private Double projectInfoUsequantity;

    /**
     * 瓦斯等级(1 低瓦斯矿井 2高瓦斯矿井 3煤（岩）与瓦斯（二氧化碳）突出矿井)
     */
    private Integer projectInfoGas;

    /**
     * 项目资料 文件id(本地)
     */
    private Integer projectInfoDatalocal;

    /**
     * 项目资料(外部链接)
     */
    private String projectInfoDataurl;

    /**
     * 项目资料描述
     */
    private String projectInfoDatedescribe;

    /**
     * 项目进程(1等待选择机构 2等待选择组长 3等待选择专家组 4等待项目评审 5等待批复 6完成)
     */
    private Integer projectInfoProgress;

    /**
     * 项目状态(1待操作 2通过 3不通过)
     */
    private Integer projectInfoState;

    /**
     * 项目进程信息
     */
    private String projectInfoProgressmessage;

    /**
     * 项目类型 (1初设，2安设)
     */
    private Integer projectInfoType;

    /**
     * 添加时间(13位时间戳)
     */
    private Long projectInfoAddtime;

    /**
     * 添加人id
     */
    private Integer projectInfoAdduserid;
    /**
     * 项目审核开始时间
     */
    private Long projectInfoAuditstartdate;
    /**
     * 项目审核过期时间
     */
    private Long projectInfoExpiration;







    /**
     * 数据主键
     * @return project_info_id 数据主键
     */
    public Integer getProjectInfoId() {
        return projectInfoId;
    }

    /**
     * 数据主键
     * @param projectInfoId 数据主键
     */
    public void setProjectInfoId(Integer projectInfoId) {
        this.projectInfoId = projectInfoId;
    }

    /**
     * 项目名字
     * @return project_info_name 项目名字
     */
    public String getProjectInfoName() {
        return projectInfoName;
    }

    /**
     * 项目名字
     * @param projectInfoName 项目名字
     */
    public void setProjectInfoName(String projectInfoName) {
        this.projectInfoName = projectInfoName == null ? null : projectInfoName.trim();
    }

    /**
     * 项目地区
     * @return project_info_area 项目地区
     */
    public String getProjectInfoArea() {
        return projectInfoArea;
    }

    /**
     * 项目地区
     * @param projectInfoArea 项目地区
     */
    public void setProjectInfoArea(String projectInfoArea) {
        this.projectInfoArea = projectInfoArea == null ? null : projectInfoArea.trim();
    }

    /**
     * 项目性质 1新建 2改扩建
     * @return project_info_nature 项目性质 1新建 2改扩建
     */
    public Integer getProjectInfoNature() {
        return projectInfoNature;
    }

    /**
     * 项目性质 1新建 2改扩建
     * @param projectInfoNature 项目性质 1新建 2改扩建
     */
    public void setProjectInfoNature(Integer projectInfoNature) {
        this.projectInfoNature = projectInfoNature;
    }

    /**
     * 投资额 单位（万元）
     * @return project_info_invest 投资额 单位（万元）
     */
    public Double getProjectInfoInvest() {
        return projectInfoInvest;
    }

    /**
     * 投资额 单位（万元）
     * @param projectInfoInvest 投资额 单位（万元）
     */
    public void setProjectInfoInvest(Double projectInfoInvest) {
        this.projectInfoInvest = projectInfoInvest;
    }

    /**
     * 规模 (单位 万吨/年)
     * @return project_info_scale 规模 (单位 万吨/年)
     */
    public Double getProjectInfoScale() {
        return projectInfoScale;
    }

    /**
     * 规模 (单位 万吨/年)
     * @param projectInfoScale 规模 (单位 万吨/年)
     */
    public void setProjectInfoScale(Double projectInfoScale) {
        this.projectInfoScale = projectInfoScale;
    }

    /**
     * 工期 (单位 月)
     * @return project_info_duration 工期 (单位 月)
     */
    public Double getProjectInfoDuration() {
        return projectInfoDuration;
    }

    /**
     * 工期 (单位 月)
     * @param projectInfoDuration 工期 (单位 月)
     */
    public void setProjectInfoDuration(Double projectInfoDuration) {
        this.projectInfoDuration = projectInfoDuration;
    }

    /**
     * 服务年限(单位/年)
     * @return project_info_serve 服务年限(单位/年)
     */
    public Double getProjectInfoServe() {
        return projectInfoServe;
    }

    /**
     * 服务年限(单位/年)
     * @param projectInfoServe 服务年限(单位/年)
     */
    public void setProjectInfoServe(Double projectInfoServe) {
        this.projectInfoServe = projectInfoServe;
    }

    /**
     * 保有储量 (单位 万吨)
     * @return project_info_reservesquantity 保有储量 (单位 万吨)
     */
    public Double getProjectInfoReservesquantity() {
        return projectInfoReservesquantity;
    }

    /**
     * 保有储量 (单位 万吨)
     * @param projectInfoReservesquantity 保有储量 (单位 万吨)
     */
    public void setProjectInfoReservesquantity(Double projectInfoReservesquantity) {
        this.projectInfoReservesquantity = projectInfoReservesquantity;
    }

    /**
     * 可采储量(单位 万吨)
     * @return project_info_usequantity 可采储量(单位 万吨)
     */
    public Double getProjectInfoUsequantity() {
        return projectInfoUsequantity;
    }

    /**
     * 可采储量(单位 万吨)
     * @param projectInfoUsequantity 可采储量(单位 万吨)
     */
    public void setProjectInfoUsequantity(Double projectInfoUsequantity) {
        this.projectInfoUsequantity = projectInfoUsequantity;
    }

    /**
     * 瓦斯等级(1 低瓦斯矿井 2高瓦斯矿井 3煤（岩）与瓦斯（二氧化碳）突出矿井)
     * @return project_info_gas 瓦斯等级(1 低瓦斯矿井 2高瓦斯矿井 3煤（岩）与瓦斯（二氧化碳）突出矿井)
     */
    public Integer getProjectInfoGas() {
        return projectInfoGas;
    }

    /**
     * 瓦斯等级(1 低瓦斯矿井 2高瓦斯矿井 3煤（岩）与瓦斯（二氧化碳）突出矿井)
     * @param projectInfoGas 瓦斯等级(1 低瓦斯矿井 2高瓦斯矿井 3煤（岩）与瓦斯（二氧化碳）突出矿井)
     */
    public void setProjectInfoGas(Integer projectInfoGas) {
        this.projectInfoGas = projectInfoGas;
    }

    /**
     * 项目资料 文件id(本地)
     * @return project_info_datalocal 项目资料 文件id(本地)
     */
    public Integer getProjectInfoDatalocal() {
        return projectInfoDatalocal;
    }

    /**
     * 项目资料 文件id(本地)
     * @param projectInfoDatalocal 项目资料 文件id(本地)
     */
    public void setProjectInfoDatalocal(Integer projectInfoDatalocal) {
        this.projectInfoDatalocal = projectInfoDatalocal;
    }

    /**
     * 项目资料(外部链接)
     * @return project_info_dataurl 项目资料(外部链接)
     */
    public String getProjectInfoDataurl() {
        return projectInfoDataurl;
    }

    /**
     * 项目资料(外部链接)
     * @param projectInfoDataurl 项目资料(外部链接)
     */
    public void setProjectInfoDataurl(String projectInfoDataurl) {
        this.projectInfoDataurl = projectInfoDataurl == null ? null : projectInfoDataurl.trim();
    }

    /**
     * 项目资料描述
     * @return project_info_datedescribe 项目资料描述
     */
    public String getProjectInfoDatedescribe() {
        return projectInfoDatedescribe;
    }

    /**
     * 项目资料描述
     * @param projectInfoDatedescribe 项目资料描述
     */
    public void setProjectInfoDatedescribe(String projectInfoDatedescribe) {
        this.projectInfoDatedescribe = projectInfoDatedescribe == null ? null : projectInfoDatedescribe.trim();
    }

    /**
     * 项目进程(1选择机构 2选择组长 3选择专家组 4项目评审 5批复 6完成)
     * @return project_info_progress 项目进程(1选择机构 2选择组长 3选择专家组 4项目评审 5批复 6完成)
     */
    public Integer getProjectInfoProgress() {
        return projectInfoProgress;
    }

    /**
     * 项目进程(1选择机构 2选择组长 3选择专家组 4项目评审 5批复 6完成)
     * @param projectInfoProgress 项目进程(1选择机构 2选择组长 3选择专家组 4项目评审 5批复 6完成)
     */
    public void setProjectInfoProgress(Integer projectInfoProgress) {
        this.projectInfoProgress = projectInfoProgress;
    }

    /**
     * 项目状态(1待操作 2通过 3不通过)
     * @return project_info_state 项目状态(1待操作 2通过 3不通过)
     */
    public Integer getProjectInfoState() {
        return projectInfoState;
    }

    /**
     * 项目状态(1待操作 2通过 3不通过)
     * @param projectInfoState 项目状态(1待操作 2通过 3不通过)
     */
    public void setProjectInfoState(Integer projectInfoState) {
        this.projectInfoState = projectInfoState;
    }

    /**
     * 项目进程信息
     * @return project_info_progressmessage 项目进程信息
     */
    public String getProjectInfoProgressmessage() {
        return projectInfoProgressmessage;
    }

    /**
     * 项目进程信息
     * @param projectInfoProgressmessage 项目进程信息
     */
    public void setProjectInfoProgressmessage(String projectInfoProgressmessage) {
        this.projectInfoProgressmessage = projectInfoProgressmessage == null ? null : projectInfoProgressmessage.trim();
    }

    /**
     * 项目类型 (1初设，2安设)
     * @return project_info_type 项目类型 (1初设，2安设)
     */
    public Integer getProjectInfoType() {
        return projectInfoType;
    }

    /**
     * 项目类型 (1初设，2安设)
     * @param projectInfoType 项目类型 (1初设，2安设)
     */
    public void setProjectInfoType(Integer projectInfoType) {
        this.projectInfoType = projectInfoType;
    }

    /**
     * 添加时间(13位时间戳)
     * @return project_info_addtime 添加时间(13位时间戳)
     */
    public Long getProjectInfoAddtime() {
        return projectInfoAddtime;
    }

    /**
     * 添加时间(13位时间戳)
     * @param projectInfoAddtime 添加时间(13位时间戳)
     */
    public void setProjectInfoAddtime(Long projectInfoAddtime) {
        this.projectInfoAddtime = projectInfoAddtime;
    }

    /**
     * 添加人id
     * @return project_info_adduserid 添加人id
     */
    public Integer getProjectInfoAdduserid() {
        return projectInfoAdduserid;
    }

    /**
     * 添加人id
     * @param projectInfoAdduserid 添加人id
     */
    public void setProjectInfoAdduserid(Integer projectInfoAdduserid) {
        this.projectInfoAdduserid = projectInfoAdduserid;
    }

    public Long getProjectInfoAuditstartdate() {
        return projectInfoAuditstartdate;
    }

    public void setProjectInfoAuditstartdate(Long projectInfoAuditstartdate) {
        this.projectInfoAuditstartdate = projectInfoAuditstartdate;
    }

    public Long getProjectInfoExpiration() {
        return projectInfoExpiration;
    }

    public void setProjectInfoExpiration(Long projectInfoExpiration) {
        this.projectInfoExpiration = projectInfoExpiration;
    }
}