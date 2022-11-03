package com.yunjz.account.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yunjz.account.domain.AccBook;
import com.yunjz.account.vo.UserStatusTo;
import org.apache.ibatis.annotations.Param;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author yunjz
 * @date 2022-09-18
 */
public interface AccBookMapper extends BaseMapper<AccBook>
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param bookId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public AccBook selectAccBookByBookId(Long bookId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param accBook 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<AccBook> selectAccBookList(AccBook accBook);

    /**
     * 新增【请填写功能名称】
     * 
     * @param accBook 【请填写功能名称】
     * @return 结果
     */
    public int insertAccBook(AccBook accBook);

    /**
     * 修改【请填写功能名称】
     * 
     * @param accBook 【请填写功能名称】
     * @return 结果
     */
    public int updateAccBook(AccBook accBook);

    /**
     * 删除【请填写功能名称】
     * 
     * @param bookId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteAccBookByBookId(Long bookId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param bookIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAccBookByBookIds(Long[] bookIds);

    void updateDefaultStatus(@Param("bookId") Long bookId);

    void updateStatusFeign(@Param("userStatusTo") UserStatusTo userStatusTo);
}
