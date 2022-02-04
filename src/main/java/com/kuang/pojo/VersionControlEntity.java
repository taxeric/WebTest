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
@TableName(value = "_version_controller")
public class VersionControlEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private int hasUpdate;
    private String updateUrl;
    private String updateDesc;
    private String updateId;
    private long fileSize;
    private int versionCode;
}
