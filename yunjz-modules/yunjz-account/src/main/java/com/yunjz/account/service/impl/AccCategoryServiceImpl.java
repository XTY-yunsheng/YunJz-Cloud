package com.yunjz.account.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunjz.account.domain.AccType;
import com.yunjz.account.domain.TypeCategoryRelation;
import com.yunjz.account.mapper.AccTypeMapper;
import com.yunjz.account.mapper.TypeCategoryRelationMapper;
import com.yunjz.account.service.IAccTypeService;
import com.yunjz.account.service.ITypeCategoryRelationService;
import com.yunjz.account.vo.CategoryNameVo;
import com.yunjz.account.vo.CategoryRespVo;
import com.yunjz.account.mapper.AccCategoryMapper;
import com.yunjz.account.service.IAccCategoryService;
import com.yunjz.common.core.myconstant.ShowStatusEnum;
import com.yunjz.common.core.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yunjz.account.domain.AccCategory;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author yunjz
 * @date 2022-09-18
 */
@Service
public class AccCategoryServiceImpl extends ServiceImpl<AccCategoryMapper, AccCategory> implements IAccCategoryService
{
    @Autowired
    private AccCategoryMapper accCategoryMapper;

    @Autowired
    IAccTypeService accTypeService;

    @Autowired
    ITypeCategoryRelationService typeCategoryRelationService;

    @Autowired
    TypeCategoryRelationMapper typeCategoryRelationMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param categoryId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public AccCategory selectAccCategoryByCategoryId(Long categoryId)
    {
        return accCategoryMapper.selectAccCategoryByCategoryId(categoryId);
    }

    /**
     * 查询账单类别列表
     * 
     * @param accCategory 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<CategoryRespVo> selectAccCategoryList(AccCategory accCategory)
    {
        QueryWrapper<AccCategory> wrapper = new QueryWrapper<>();
        String categoryName = accCategory.getCategoryName();
        if (!StringUtils.isEmpty(categoryName)) {
            wrapper.and(w -> {
                w.like("category_name",categoryName);
            });
        }
        Long typeId = accCategory.getTypeId();
        if (typeId != null) {
            wrapper.eq("type_id", typeId);
        }
        List<AccCategory> categoryList = this.list(wrapper);
        List<CategoryRespVo> vos = categoryList.stream().map(category -> {
            CategoryRespVo categoryRespVo = new CategoryRespVo();
            BeanUtils.copyProperties(category, categoryRespVo);
            Long typeId2 = category.getTypeId();
            if (typeId2 != null) {
                AccType accType = accTypeService.getById(typeId2);
                categoryRespVo.setTypeName(accType.getTypeName());
            }
            return categoryRespVo;
        }).collect(Collectors.toList());
        return vos;
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param accCategory 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertAccCategory(AccCategory accCategory)
    {
        return accCategoryMapper.insertAccCategory(accCategory);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param accCategory 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateAccCategory(AccCategory accCategory)
    {
        return accCategoryMapper.updateAccCategory(accCategory);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param categoryIds 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteAccCategoryByCategoryIds(Long[] categoryIds)
    {
        return accCategoryMapper.deleteAccCategoryByCategoryIds(categoryIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param categoryId 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteAccCategoryByCategoryId(Long categoryId)
    {
        return accCategoryMapper.deleteAccCategoryByCategoryId(categoryId);
    }

    /**
     * 新增账单类别，并添加关联关系
     * @param accCategory
     */
    @Override
    public void addCategoryWithRelation(AccCategory accCategory) {
        this.save(accCategory);

        Long typeId = accCategory.getTypeId();
        if (typeId != null) {
            TypeCategoryRelation typeCategoryRelation = new TypeCategoryRelation();
            Long categoryId = accCategory.getCategoryId();
            String categoryName = accCategory.getCategoryName();
            typeCategoryRelation.setTypeId(typeId).setCategoryId(categoryId).setCategoryName(categoryName);
            typeCategoryRelationService.save(typeCategoryRelation);
        }
    }

    /**
     * 删除账单类别，和关联关系
     * @param categoryIds
     */
    @Override
    public void deleteCategoryWithRelationByCategoryIds(Long[] categoryIds) {
        this.deleteAccCategoryByCategoryIds(categoryIds);
        typeCategoryRelationService.remove(new QueryWrapper<TypeCategoryRelation>().in("category_id", categoryIds));
    }

    /**
     * 根据typeId查询关联类别列表
     * @param typeId
     * @return
     */
    @Override
    public List<AccCategory> getRelationCategroy(Long typeId) {
        Integer count = typeCategoryRelationMapper.selectCount(new QueryWrapper<TypeCategoryRelation>().eq("type_id", typeId));
        if (count > 0) {
            List<TypeCategoryRelation> relations = typeCategoryRelationMapper.selectList(new QueryWrapper<TypeCategoryRelation>().eq("type_id", typeId));
            List<Long> categoryIds = relations.stream().map(TypeCategoryRelation::getCategoryId).collect(Collectors.toList());
            return this.listByIds(categoryIds);
        }
        return null;
    }

    /**
     * 根据typeId查询可以关联的类别列表
     * @param accCategory
     * @param typeId
     * @return
     */
    @Override
    public List<AccCategory> getNoRelationCategroy(AccCategory accCategory, Long typeId) {
        //1. 当前类型只能关联别的类型没有引用的类别
        List<AccType> accTypes = accTypeService.list(new QueryWrapper<AccType>().eq("show_status", ShowStatusEnum.Enable.getCode()));
        List<Long> typeIds = accTypes.stream().map(AccType::getTypeId).collect(Collectors.toList());
        List<TypeCategoryRelation> relations = typeCategoryRelationMapper.selectList(new QueryWrapper<TypeCategoryRelation>().in("type_id", typeIds));
        if (relations != null && relations.size() > 0) {
            List<Long> categoryIds = relations.stream().map(TypeCategoryRelation::getCategoryId).collect(Collectors.toList());
            //2. 移除已经被应用的这些类别
            List<AccCategory> categoryList = this.baseMapper.selectList(new QueryWrapper<AccCategory>().notIn("category_id", categoryIds));
            //3. 条件查询
            if (!StringUtils.isEmpty(categoryList)) {
//                List<Long> allowCatIds = categoryList.stream().map(AccCategory::getCategoryId).collect(Collectors.toList());
//                List<AccCategory> allowCategorys = accCategoryMapper.selectAccCategoryList(accCategory).stream().filter(category -> allowCatIds.contains(category.getCategoryId())).collect(Collectors.toList());
//                System.out.println("==========");
//                allowCategorys.forEach(System.out::println);
//                return allowCategorys;
            }
            return categoryList;
        }
        return new ArrayList<>();

    }

    /**
     * 根据typeId获取类别名称列表
     * @param typeId
     * @return
     */
    @Override
    public List<CategoryNameVo> getCategoryNameListByTypeId(Long typeId) {
        if (typeId != null && typeId != 0) {
            List<AccCategory> accCategories = accCategoryMapper.selectList(new QueryWrapper<AccCategory>().eq("type_id", typeId).eq("show_status", ShowStatusEnum.Enable.getCode()));
            List<CategoryNameVo> categoryNameVos = accCategories.stream().map(category -> {
                CategoryNameVo categoryNameVo = new CategoryNameVo();
                BeanUtils.copyProperties(category, categoryNameVo);
                return categoryNameVo;
            }).collect(Collectors.toList());
            return categoryNameVos;
        }
        return new ArrayList<>();
    }
}
