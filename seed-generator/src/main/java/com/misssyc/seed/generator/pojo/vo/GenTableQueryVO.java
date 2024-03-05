package com.misssyc.seed.generator.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 代码生成业务表
 * </p>
 *
 * @author 
 * @since 2024-03-01
 */
@Data
public class GenTableQueryVO implements Serializable {

    @ApiModelProperty("编号")
    private Long tableId;

    @ApiModelProperty("表名称")
    private String tableName;

    @ApiModelProperty("实体类名称")
    private String className;

    @ApiModelProperty("生成包路径")
    private String packageName;

    @ApiModelProperty("生成模块名")
    private String moduleName;

    @ApiModelProperty("生成业务名")
    private String businessName;

    @ApiModelProperty("生成功能名")
    private String functionName;

    @ApiModelProperty("生成功能作者")
    private String functionAuthor;
}
