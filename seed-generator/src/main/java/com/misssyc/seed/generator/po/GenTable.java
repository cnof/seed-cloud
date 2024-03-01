package com.misssyc.seed.generator.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 代码生成业务表
 * </p>
 *
 * @author 
 * @since 2024-03-01
 */
@Data
@TableName("generator_gen_table")
@ApiModel(value = "GenTable对象", description = "代码生成业务表")
public class GenTable implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("编号")
    @TableId(value = "table_id", type = IdType.AUTO)
    private Long tableId;

    @ApiModelProperty("表名称")
    private String tableName;

    @ApiModelProperty("表描述")
    private String tableComment;

    @ApiModelProperty("关联子表的表名")
    private String subTableName;

    @ApiModelProperty("子表关联的外键名")
    private String subTableFkName;

    @ApiModelProperty("实体类名称")
    private String className;

    @ApiModelProperty("使用的模板（crud单表操作 tree树表操作）")
    private String tplCategory;

    @ApiModelProperty("前端模板类型（element-ui模版 element-plus模版）")
    private String tplWebType;

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

    @ApiModelProperty("生成代码方式（0zip压缩包 1自定义路径）")
    private String genType;

    @ApiModelProperty("生成路径（不填默认项目路径）")
    private String genPath;

    @ApiModelProperty("其它生成选项")
    private String options;

    @ApiModelProperty("创建者")
    private String createBy;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新者")
    private String updateBy;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("备注")
    private String remark;
}
