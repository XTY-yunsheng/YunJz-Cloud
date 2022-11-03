package com.yunjz.account.vo;

import lombok.Data;

/**
 * @author yunsheng
 * @create 2022-09-22 15:55
 */
@Data
public class CategoryRespVo {

    private Long categoryId;
    private Long typeId;
    private String typeName;
    private String categoryName;
    private Integer showStatus;
    private Long sort;

}
