package com.yunjz.member.feign;

import com.yunjz.common.core.web.domain.AjaxResult;
import com.yunjz.member.vo.UserStatusTo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author yunsheng
 * @create 2022-10-17 18:09
 */
@FeignClient("yunjz-account")
public interface AccountFeignService {

    @PostMapping("/book/status/feign")
    AjaxResult updateStatusByUserId(@RequestBody UserStatusTo userStatusTo);
}
