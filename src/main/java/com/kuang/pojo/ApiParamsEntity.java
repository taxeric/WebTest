package com.kuang.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "_ApiParams")
public class ApiParamsEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String paramName;

    private String paramValue;
    private String paramDesc;
    private String paramType;

    private Integer parentUrlId;

}
