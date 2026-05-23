
package com.runwsh.weimin.service;

import com.runwsh.weimin.entity.LoanApplication;
import com.runwsh.weimin.mapper.LoanApplicationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanApplicationMapper loanMapper;

    @Cacheable(value = "loan", key = "#id", cacheManager = "redisCacheManager")
    public LoanApplication getLoanById(Long id) {
        log.info("查询数据库: 贷款申请ID={}", id);
        return loanMapper.selectById(id);
    }

    @Cacheable(value = "hotLoan", key = "#userId", cacheManager = "localCacheManager")
    public List<LoanApplication> getLoansByUserId(Long userId) {
        log.info("查询数据库: 用户贷款申请 userId={}", userId);
        return loanMapper.selectByUserId(userId);
    }
}
