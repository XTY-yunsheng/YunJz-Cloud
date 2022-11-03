package com.yunjz.account.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;
import com.yunjz.common.core.annotation.Excel;

/**
 * 【请填写功能名称】对象 type_category_relation
 * 
 * @author yunjz
 * @date 2022-09-18
 */
@Data
@Accessors(chain = true)
public class TypeCategoryRelation
{
    private static final long serialVersionUID = 1L;

    /** id */
    @TableId
    private Long id;

    /** 账单类型id */
    @Excel(name = "账单类型id")
    private Long typeId;

    /** 账单类别id */
    @Excel(name = "账单类别id")
    private Long categoryId;

    /** 账单类型名称 */
    @Excel(name = "账单类型名称")
    private String typeName;

    /** 账单类别名称 */
    @Excel(name = "账单类别名称")
    private String categoryName;

}
