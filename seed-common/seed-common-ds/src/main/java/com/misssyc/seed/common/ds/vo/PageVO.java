package com.misssyc.seed.common.ds.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页
 *
 * @author DP
 * @date 2023/11/02
 */
@ApiModel("分页")
@Data
@NoArgsConstructor
public class PageVO<T> {

    @ApiModelProperty("当前页的数据")
    private List<T> rows;

    @ApiModelProperty("分页信息")
    private Pagination pagination = new Pagination();

    public PageVO(List<T> rows, Page<?> pageInfo) {
        this.rows = rows;
        this.pagination.setTotalCount(pageInfo.getTotal());
        this.pagination.setPage(pageInfo.getCurrent());
        this.pagination.setPageSize(pageInfo.getSize());
    }

    @Data
    public static class Pagination {

        @ApiModelProperty("当前页")
        private Long page;

        @ApiModelProperty("每页显示的条数")
        private Long pageSize;

        @ApiModelProperty("总数量")
        private Long totalCount;
    }
}
