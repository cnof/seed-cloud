package com.misssyc.seed.admin.pojo.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * @author 李升平
 * @since  2024-03-25 23:30:54
 */
@Data
public class UserAddOrUpdateVO {

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("部门ID")
    private Long deptId;

    @ApiModelProperty("用户账号")
    private String userName;

    @ApiModelProperty("用户姓名")
    private String fullName;

    @ApiModelProperty("用户昵称")
    private String nickName;

    @ApiModelProperty(value = "用户类型（00系统用户）", example = "00")
    private String userType;

    @ApiModelProperty("用户邮箱")
    private String email;

    @ApiModelProperty("手机号码")
    private String phoneNumber;

    @ApiModelProperty(value = "用户性别（0男 1女 2未知）", example = "0")
    private String sex;

    @ApiModelProperty("头像地址")
    private String avatar;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty(value = "帐号状态（0正常 1停用）", example = "0")
    private String status;

    @ApiModelProperty("最后登录IP")
    private String loginIp;

    @ApiModelProperty("最后登录时间")
    private Date loginDate;

    @ApiModelProperty("备注")
    private String remark;
}