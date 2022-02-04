package com.kuang.controller;

import com.kuang.dto.HttpResponse;
import com.kuang.pojo.VersionControlEntity;
import com.kuang.service.VersionControlService;
import com.kuang.utils.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

    @Autowired
    private VersionControlService service;

    @RequestMapping("/updateVersionInfo")
    public HttpResponse<Object> updateInfo(
            String id,
            boolean hasUpdate,
            String url,
            String desc,
            int versionCode,
            long fileSize
    ){
        if (TextUtils.isNullOrEmpty(id)){
            return new HttpResponse<>(101, "app id must be not null");
        }
        VersionControlEntity entity = new VersionControlEntity();
        entity.setUpdateId(id);
        entity.setHasUpdate(hasUpdate ? 1 : 0);
        entity.setUpdateUrl(url);
        entity.setUpdateDesc(desc);
        entity.setVersionCode(versionCode);
        entity.setFileSize(fileSize);
        VersionControlEntity queryEntity = service.getAppInfoVersionById(id);
        int result;
        if (queryEntity != null){
            entity.setId(queryEntity.getId());
            result = service.updateAppVersion(entity);
        } else {
            result = service.insertAppVersion(entity);
        }
        return buildResponse(result);
    }

    @RequestMapping("/getVersionInfo")
    public HttpResponse<VersionControlEntity> getVersionInfo(String id){
        if (TextUtils.isNullOrEmpty(id)){
            return new HttpResponse<>(101, "app id must be not null");
        }
        VersionControlEntity entity = service.getAppInfoVersionById(id);
        if (entity != null){
            return new HttpResponse<>(entity);
        }
        return new HttpResponse<>(101, "Do you have information about of the app's version? id -> " + id);
    }

    private HttpResponse<Object> buildResponse(int result){
        HttpResponse<Object> response = new HttpResponse<>(null);
        response.setCode(result == 1 ? 200 : 102);
        response.setMsg(result == 1 ? "ok" : "Unknown error");
        return response;
    }
}
