package com.misssyc.seed.common.core.web;

import com.misssyc.seed.common.core.constants.Constants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author 33992
 * @since 2024/2/21
 **/
@ApiModel("通用泛型结果返回")
@Accessors(chain = true)
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("是否成功")
    private boolean success = true;

    @ApiModelProperty("状态码")
    private int code = Constants.SUCCESS;

    @ApiModelProperty("返回内容")
    private String msg;

    @ApiModelProperty("数据对象")
    private T data;

    protected static <T> Result<T> buildResult(T data, int code, String msg) {
        return new Result<T>().setCode(code).setData(data).setMsg(msg).setSuccess(code == Constants.SUCCESS);
    }
}
