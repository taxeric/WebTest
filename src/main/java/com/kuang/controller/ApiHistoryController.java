package com.kuang.controller;

import com.kuang.dto.ApiHistoryEntity;
import com.kuang.dto.HttpResponse;
import com.kuang.pojo.ApiBaseEntity;
import com.kuang.pojo.ApiParamsEntity;
import com.kuang.service.ApiHistoryService;
import com.kuang.utils.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiHistoryController {

    @Autowired
    private ApiHistoryService apiHistoryService;

    @RequestMapping("/getAllApi")
    public HttpResponse<List<ApiHistoryEntity>> selectAllApi(){
        return new HttpResponse<>(apiHistoryService.selectAllApis());
    }

    @RequestMapping("/slById")
    public List<ApiParamsEntity> selectListByUrlId(Integer id){
        return apiHistoryService.selectListByUrlId(id);
    }

    @RequestMapping("/insertApi")
    public HttpResponse<Object> insertApi(
            String apiDesc,
            String apiUrl,
            Integer apiRequestMethods
    ){
        if (TextUtils.isNullOrEmpty(apiUrl)){
            return new HttpResponse<>(101, "路径不合法");
        }
        ApiBaseEntity entity = new ApiBaseEntity();
        entity.setApiDesc(TextUtils.isNotNullAndEmpty(apiDesc) ? "" : apiDesc);
        entity.setApiUrl(apiUrl);
        entity.setApiRequestMethods(apiRequestMethods);
        int result = apiHistoryService.insertApi(entity);
        return buildResponse(result);
    }

    @RequestMapping("/insertParam")
    public HttpResponse<Object> insertParams(
            String paramName,
            String paramDesc,
            String paramType,
            Integer parentUrlId
    ){
        if (parentUrlId <= 0){
            return new HttpResponse<>(101, "父路径ID不合法");
        }
        if (TextUtils.isNullOrEmpty(paramName)){
            return new HttpResponse<>(101, "参数名不合法");
        }
        ApiParamsEntity entity = new ApiParamsEntity();
        entity.setParamName(paramName);
        entity.setParamDesc(paramDesc);
        entity.setParamType(paramType);
        entity.setParentUrlId(parentUrlId);
        entity.setParamValue("");
        int result = apiHistoryService.insertParam(entity);
        return buildResponse(result);
    }

    private HttpResponse<Object> buildResponse(int result){
        HttpResponse<Object> response = new HttpResponse<>(null);
        response.setCode(result == 1 ? 200 : 102);
        response.setMsg(result == 1 ? "ok" : "un know error");
        return response;
    }
}
