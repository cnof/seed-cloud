package com.misssyc.seed.generator.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;

import java.util.List;

/**
 * @author 33992
 * @since 2024/3/4
 **/
@Data
public class GenTableAddDTO {

    @ApiModelProperty("数据库中的表名称")
    private List<String> tableNames;

    /** 作者 */
    @ApiModelProperty("作者")
    public String author;

    /** 生成包路径 */
    @ApiModelProperty("生成包路径")
    public String packageName;

    /** 自动去除表前缀，默认是false */
    @ApiModelProperty("自动去除表前缀")
    public boolean autoRemovePre;

    /** 表前缀(类名不会包含表前缀) */
    @ApiModelProperty("表前缀(类名不会包含表前缀)")
    public String tablePrefix;
}
