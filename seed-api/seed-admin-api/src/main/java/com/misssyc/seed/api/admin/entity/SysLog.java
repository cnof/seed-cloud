package com.misssyc.seed.api.admin.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 33992
 * @since 2024/2/21
 **/
@Data
public class SysLog {

    @ApiModelProperty(value = "所属模块")
    private String module;

    @ApiModelProperty(value = "中文标题")
    private String title;

    @ApiModelProperty(value = "方法")
    private String method;

    @ApiModelProperty(value = "请求类型")
    private String reqType;

    @ApiModelProperty(value = "请求路径")
    private String reqUri;

    @ApiModelProperty(value = "请求参数")
    private String reqParam;

    @ApiModelProperty(value = "请求来源（0其它 1管理端 2手机端）")
    private Integer reqSource;

    @ApiModelProperty(value = "操作类型（0其它 1查询 2新增 3修改 4删除 5授权 6导入 7导出 8登录...）")
    private String operType;

    @ApiModelProperty(value = "操作IP")
    private String operIp;

    @ApiModelProperty(value = "操作状态（0正常 1异常）")
    private Integer operStatus;

    @ApiModelProperty(value = "返回信息")
    private String remark;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createAt;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateAt;
}
