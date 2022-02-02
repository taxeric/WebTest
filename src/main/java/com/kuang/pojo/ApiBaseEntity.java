package com.kuang.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "_ApiHistory")
public class ApiBaseEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String apiDesc;

    private String apiUrl;

    private Integer apiRequestMethods;

}
