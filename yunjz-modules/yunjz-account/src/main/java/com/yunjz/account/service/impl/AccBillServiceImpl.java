package com.yunjz.account.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunjz.account.domain.AccBill;
import com.yunjz.account.domain.AccBook;
import com.yunjz.account.domain.UserBookRelation;
import com.yunjz.account.mapper.AccBillMapper;
import com.yunjz.account.mapper.AccBookMapper;
import com.yunjz.account.service.IAccBillService;
import com.yunjz.account.service.IAccCategoryService;
import com.yunjz.account.service.IAccTypeService;
import com.yunjz.account.service.IUserBookRelationService;
import com.yunjz.account.vo.BillQueryVo;
import com.yunjz.account.vo.BillRespVo;
import com.yunjz.common.core.myconstant.ShowStatusEnum;
import com.yunjz.common.core.myutils.SnowIdUtils;
import com.yunjz.common.core.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author yunjz
 * @date 2022-09-18
 */
@Service
public class AccBillServiceImpl extends ServiceImpl<AccBillMapper, AccBill> implements IAccBillService
{
    @Autowired
    private AccBillMapper accBillMapper;

    @Autowired
    IAccTypeService accTypeService;

    @Autowired
    IAccCategoryService accCategoryService;

    @Autowired
    IUserBookRelationService userBookRelationService;

    @Autowired
    AccBookMapper accBookMapper;


    /**
     * 查询【请填写功能名称】
     * 
     * @param billId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public AccBill selectAccBillByBillId(Long billId)
    {
        return accBillMapper.selectAccBillByBillId(billId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param billQueryVo 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<BillRespVo> selectAccBillList(BillQueryVo billQueryVo)
    {
        QueryWrapper<AccBill> wrapper = new QueryWrapper<>();
        String title = billQueryVo.getTitle();
        if (!StringUtils.isEmpty(title)) {
            wrapper.and(w -> {
                w.like("title", title);
            });
        }
        Long userId = billQueryVo.getUserId();
        if (userId != null) {
            wrapper.eq("user_id", userId);
            Long bookId = billQueryVo.getBookId();
            if (bookId != null) {
                wrapper.eq("book_id", bookId);
            }
        }
        Long typeId = billQueryVo.getTypeId();
        if (typeId != null) {
            wrapper.eq("type_id", typeId);
            Long categoryId = billQueryVo.getCategoryId();
            if (categoryId != null) {
                wrapper.eq("category_id", categoryId);
            }
        }
        if (!StringUtils.isEmpty(billQueryVo.getGmtCreate())) {
            Date min = billQueryVo.getGmtCreate().get("min");
            if (min != null) {
                wrapper.ge("gmt_create", min);
            }
            Date max = billQueryVo.getGmtCreate().get("max");
            if (max != null) {
                wrapper.le("gmt_create", max);
            }
        }
        List<AccBill> accBills = accBillMapper.selectList(wrapper);

        if (!StringUtils.isEmpty(accBills)) {
            List<BillRespVo> billRespVos = accBills.stream()
                    .filter(bill -> accBookMapper.selectCount(new QueryWrapper<AccBook>().eq("book_id", bill.getBookId()).eq("show_status", ShowStatusEnum.Enable.getCode())) > 0)
                    .map(bill -> {
                        BillRespVo billRespVo = new BillRespVo();
                        BeanUtils.copyProperties(bill, billRespVo);
                        String typeName = accTypeService.getById(bill.getTypeId()).getTypeName();
                        String categoryName = accCategoryService.getById(bill.getCategoryId()).getCategoryName();
                        UserBookRelation userBookRelation = userBookRelationService.getOne(new QueryWrapper<UserBookRelation>().eq("user_id", bill.getUserId()).eq("book_id", bill.getBookId()));
                        billRespVo.setTypeName(typeName);
                        billRespVo.setCategoryName(categoryName);
                        billRespVo.setUserName(userBookRelation.getUserName());
                        billRespVo.setBookName(userBookRelation.getBookName());
                        return billRespVo;
                    }).collect(Collectors.toList());
            return billRespVos;
        }
        return new ArrayList<BillRespVo>();
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param accBill 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertAccBill(AccBill accBill)
    {
        SnowIdUtils snowIdUtils = new SnowIdUtils(0);
        accBill.setOrderSn(String.valueOf(snowIdUtils.getId()));
        accBill.setGmtCreate(new Date());
        accBill.setGmtModified(new Date());
        return accBillMapper.insertAccBill(accBill);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param accBill 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateAccBill(AccBill accBill)
    {
        accBill.setGmtModified(new Date());
        return accBillMapper.updateAccBill(accBill);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param billIds 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteAccBillByBillIds(Long[] billIds)
    {
        return accBillMapper.deleteAccBillByBillIds(billIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param billId 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteAccBillByBillId(Long billId)
    {
        return accBillMapper.deleteAccBillByBillId(billId);
    }
}
