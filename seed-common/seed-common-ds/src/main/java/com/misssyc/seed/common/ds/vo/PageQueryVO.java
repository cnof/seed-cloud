package com.misssyc.seed.common.ds.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("分页查询对象")
public class PageQueryVO<T>{

    @ApiModelProperty("当前页")
    private int currentPage;

    @ApiModelProperty("每页数据条数")
    private int pageSize;

    private T data;
}
