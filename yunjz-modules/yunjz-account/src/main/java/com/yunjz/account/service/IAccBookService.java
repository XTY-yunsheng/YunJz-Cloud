package com.yunjz.account.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yunjz.account.domain.AccBook;
import com.yunjz.account.vo.BookNameVo;
import com.yunjz.account.vo.BookRespVo;
import com.yunjz.account.vo.BookQueryVo;
import com.yunjz.account.vo.UserStatusTo;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author yunjz
 * @date 2022-09-18
 */
public interface IAccBookService extends IService<AccBook>
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param bookId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public AccBook selectAccBookByBookId(Long bookId);

    /**
     * 条件查询账本列表
     * 
     * @param bookQueryVo 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<BookRespVo> selectAccBookList(BookQueryVo bookQueryVo);

    /**
     * 新增【请填写功能名称】
     * 
     * @param accBook 【请填写功能名称】
     * @return 结果
     */
    public int insertAccBook(AccBook accBook);

    /**
     * 修改账本
     * 
     * @param accBook 【请填写功能名称】
     * @return 结果
     */
    public int updateBookWithDefault(AccBook accBook);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param bookIds 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteAccBookByBookIds(Long[] bookIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param bookId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteAccBookByBookId(Long bookId);

    /**
     * 新增账本，和关联关系
     * @param accBook
     */
    void insertBookWithRelation(AccBook accBook);

    /**
     * 根据bookIds删除账本，和关联关系
     * @param bookIds
     */
    void deleteBookWithRelationByBookIds(Long[] bookIds);

    /**
     * 根据用户id查询账本名称列表
     * @param userId
     * @return
     */
    List<BookNameVo> getBookNameListByUserId(Long userId);

    /**
     * 根据用户id和status修改当前用户所有账本状态(远程)
     * @param userStatusTo
     */
    void updateStatusFeign(UserStatusTo userStatusTo);
}
