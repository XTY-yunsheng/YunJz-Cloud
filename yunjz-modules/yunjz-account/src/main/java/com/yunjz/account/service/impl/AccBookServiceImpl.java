package com.yunjz.account.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunjz.account.domain.AccBook;
import com.yunjz.account.domain.UserBookRelation;
import com.yunjz.account.feign.MemberFeignService;
import com.yunjz.account.mapper.AccBookMapper;
import com.yunjz.account.mapper.UserBookRelationMapper;
import com.yunjz.account.service.IAccBookService;
import com.yunjz.account.service.IUserBookRelationService;
import com.yunjz.account.vo.*;
import com.yunjz.common.core.utils.StringUtils;
import com.yunjz.common.core.web.domain.AjaxResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import com.yunjz.common.core.myconstant.ShowStatusEnum;
import com.yunjz.common.core.myconstant.DefaultStatusEnum;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author yunjz
 * @date 2022-09-18
 */
@Service
public class AccBookServiceImpl extends ServiceImpl<AccBookMapper, AccBook> implements IAccBookService
{
    @Autowired
    private AccBookMapper accBookMapper;

    @Autowired
    IUserBookRelationService userBookRelationService;

    @Autowired
    UserBookRelationMapper userBookRelationMapper;

    @Autowired
    MemberFeignService memberFeignService;

    /**
     * 查询【请填写功能名称】列表
     * @param bookId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public AccBook selectAccBookByBookId(Long bookId)
    {
        return accBookMapper.selectAccBookByBookId(bookId);
    }

    /**
     * 条件查询账本列表
     * @param bookQueryVo 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<BookRespVo> selectAccBookList(BookQueryVo bookQueryVo)
    {
        long l = System.currentTimeMillis();

        QueryWrapper<AccBook> bookQueryWrapper = new QueryWrapper<>();
        String bookName = bookQueryVo.getBookName();
        if (!StringUtils.isEmpty(bookName)) {
            bookQueryWrapper.and(w -> {
                w.like("book_name", bookName);
            });
        }
        if (!StringUtils.isEmpty(bookQueryVo.getGmtCreate())) {
            Date min = bookQueryVo.getGmtCreate().get("min");
            if (min != null) {
                System.out.println("开始时间min: " + min);
                bookQueryWrapper.ge("gmt_create", min);
            }
            Date max = bookQueryVo.getGmtCreate().get("max");
            if (max != null) {
                System.out.println("结束时间max: " + max);
                bookQueryWrapper.le("gmt_create", max);
            }
        }
        List<AccBook> accBooks = accBookMapper.selectList(bookQueryWrapper.eq("show_status", ShowStatusEnum.Enable.getCode()));

        QueryWrapper<UserBookRelation> userBookRelationQueryWrapper = new QueryWrapper<>();
        String userName = bookQueryVo.getUserName();
        /*if (!StringUtils.isEmpty(userName)) {
            userBookRelationQueryWrapper.and( w -> {
                w.like("user_name",userName);
            });
            List<UserBookRelation> userBookRelations = userBookRelationMapper.selectList(userBookRelationQueryWrapper);
            List<Long> userIds = userBookRelations.stream().map(UserBookRelation::getUserId).distinct().collect(Collectors.toList());
            UserIdTo userIdTo = new UserIdTo();
            userIdTo.setUserIds(userIds);
            System.out.println("userIdTo===: "+ userIdTo.toString());
            AjaxResult vaildUserList = memberFeignService.getVaildUserList(userIdTo);
            List<Integer> validIds = (List<Integer>) vaildUserList.get("data");

            List<Long> arrayList = new ArrayList<>();
            for (Integer validId : validIds) {
                arrayList.add(validId.longValue());
            }
            List<Long> bookIds = userBookRelations.stream().filter(relation -> (arrayList).contains(relation.getUserId())).map(UserBookRelation::getBookId).collect(Collectors.toList());
            System.out.println("bookIds===: "+bookIds);
            List<BookRespVo> bookRespVos = accBooks.stream()
                    .filter(book -> bookIds.contains(book.getBookId()))
                    .map(accBook -> {
                        BookRespVo bookRespVo = new BookRespVo();
                        BeanUtils.copyProperties(accBook, bookRespVo);
                        UserBookRelation selectOne = userBookRelationMapper.selectOne(new QueryWrapper<UserBookRelation>().eq("book_id", accBook.getBookId()));
                        bookRespVo.setUserName(selectOne.getUserName());
                        return bookRespVo;
            }).collect(Collectors.toList());
            long e = System.currentTimeMillis();
            System.out.println("远程所花时间："+(e - l));
            return bookRespVos;
        }*/

        if (!StringUtils.isEmpty(accBooks)) {
            List<Long> bookIds = accBooks.stream().map(AccBook::getBookId).collect(Collectors.toList());
            if (!StringUtils.isEmpty(userName)) {
                userBookRelationQueryWrapper.and( w -> {
                    w.like("user_name",userName);
                });
                List<UserBookRelation> userBookRelations = userBookRelationMapper.selectList(userBookRelationQueryWrapper);
                bookIds = userBookRelations.stream().map(UserBookRelation::getBookId).collect(Collectors.toList());
            }
            List<Long> finalBookIds = bookIds;
            List<BookRespVo> vos = accBooks.stream().filter(accBook -> finalBookIds.contains(accBook.getBookId())).map(book -> {
                BookRespVo bookRespVo = new BookRespVo();
                BeanUtils.copyProperties(book, bookRespVo);
                UserBookRelation selectOne = userBookRelationMapper.selectOne(new QueryWrapper<UserBookRelation>().eq("book_id", book.getBookId()));
                bookRespVo.setUserName(selectOne.getUserName());
                return bookRespVo;
            }).collect(Collectors.toList());
            long e = System.currentTimeMillis();
            System.out.println();
            System.out.println("账本信息查询完成!!!\t\t所花时间: "+(e - l));
            return vos;
        }

        return new ArrayList<>();
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param accBook 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertAccBook(AccBook accBook)
    {
        return accBookMapper.insertAccBook(accBook);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param accBook 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateBookWithDefault(AccBook accBook)
    {
        if (accBook.getDefaultStatus() == DefaultStatusEnum.Default.getCode()) {
            Integer count = accBookMapper.selectCount(new QueryWrapper<AccBook>().eq("default_status", DefaultStatusEnum.Default.getCode()).eq("user_id", accBook.getUserId()).ne("book_id", accBook.getBookId()));
            if (count > 0) {
                List<AccBook> list = accBookMapper.selectList(new QueryWrapper<AccBook>().eq("default_status", DefaultStatusEnum.Default.getCode()).eq("user_id", accBook.getUserId()).ne("book_id", accBook.getBookId()));
                list.forEach(e -> accBookMapper.updateDefaultStatus(e.getBookId()));
            }
        }
        return accBookMapper.updateById(accBook);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param bookIds 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteAccBookByBookIds(Long[] bookIds)
    {
        return accBookMapper.deleteAccBookByBookIds(bookIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param bookId 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteAccBookByBookId(Long bookId)
    {
        return accBookMapper.deleteAccBookByBookId(bookId);
    }

    /**
     * 新增账本，和关联关系
     */
    @Transactional
    @Override
    public void insertBookWithRelation(AccBook accBook) {
        accBook.setGmtCreate(new Date());
        accBook.setGmtModified(new Date());
        //如果是默认账本
        if (accBook.getDefaultStatus() == DefaultStatusEnum.Default.getCode()) {
            Integer count = accBookMapper.selectCount(new QueryWrapper<AccBook>().eq("default_status", DefaultStatusEnum.Default.getCode()).eq("user_id", accBook.getUserId()));
            if (count > 0) {
                List<AccBook> list = accBookMapper.selectList(new QueryWrapper<AccBook>().eq("default_status", DefaultStatusEnum.Default.getCode()).eq("user_id", accBook.getUserId()));
                list.forEach(e-> accBookMapper.updateDefaultStatus(e.getBookId()));
            }
        }
        this.insertAccBook(accBook);

        UserBookRelation userBookRelation = new UserBookRelation();
        UserIdTo userIdTo = new UserIdTo();
        userIdTo.setUserId(accBook.getUserId());
        AjaxResult userName = memberFeignService.getUserName(userIdTo);
        String name = (String) userName.get("userName");
        userBookRelation.setUserId(accBook.getUserId()).setBookId(accBook.getBookId()).setUserName(name).setBookName(accBook.getBookName());
        userBookRelationService.save(userBookRelation);
    }

    /**
     * 根据bookIds删除账本，和关联关系
     * @param bookIds
     */
    @Transactional
    @Override
    public void deleteBookWithRelationByBookIds(Long[] bookIds) {
        accBookMapper.deleteAccBookByBookIds(bookIds);
        //int i = 10/0;
        userBookRelationMapper.delete(new QueryWrapper<UserBookRelation>().in("book_id", bookIds));

    }

    /**
     * 根据用户id查询账本名称列表
     * @param userId
     * @return
     */
    @Override
    public List<BookNameVo> getBookNameListByUserId(Long userId) {
        if (userId != null && userId != 0) {
            List<AccBook> accBooks = accBookMapper.selectList(new QueryWrapper<AccBook>().eq("user_id", userId).eq("show_status", ShowStatusEnum.Enable.getCode()));
            List<BookNameVo> bookNameVos = accBooks.stream().map(book -> {
                BookNameVo bookNameVo = new BookNameVo();
                BeanUtils.copyProperties(book, bookNameVo);
                return bookNameVo;
            }).collect(Collectors.toList());
            return bookNameVos;
        }
        return new ArrayList<>();
    }

    /**
     * 根据用户id和status修改当前用户所有账本状态(远程)
     * @param userStatusTo
     */
    @Override
    public void updateStatusFeign(UserStatusTo userStatusTo) {
        Integer count = this.baseMapper.selectCount(new QueryWrapper<AccBook>().eq("user_id", userStatusTo.getUserId()));
        if (count > 0){
            this.baseMapper.updateStatusFeign(userStatusTo);
        }
    }

}
