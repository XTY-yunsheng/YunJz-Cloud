package com.yunjz.account.feign;

import com.yunjz.account.vo.UserIdTo;
import com.yunjz.common.core.web.domain.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author yunsheng
 * @create 2022-09-20 18:28
 */
@FeignClient("yunjz-member")
public interface MemberFeignService {

    @PostMapping("/user/list/feign")
    AjaxResult getVaildUserList(@RequestBody UserIdTo userIdTo);

    @PostMapping("/user/getNameById")
    AjaxResult getUserName(@RequestBody UserIdTo userIdTo);
}
