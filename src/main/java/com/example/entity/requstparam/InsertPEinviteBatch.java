package com.example.entity.requstparam;

import com.example.entity.ProjectauditExpertInvite;

import java.util.Map;

/**
 * 批量添加项目审核邀请(专家)
 */
public class InsertPEinviteBatch extends ProjectauditExpertInvite {
    private Map[]experInfos;

    public Map[] getExperInfos() {
        return experInfos;
    }

    public void setExperInfos(Map[] experInfos) {
        this.experInfos = experInfos;
    }
}
