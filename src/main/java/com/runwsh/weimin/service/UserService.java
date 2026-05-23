
package com.runwsh.weimin.service;

import com.runwsh.weimin.entity.User;
import com.runwsh.weimin.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    @Cacheable(value = "user", key = "#id", cacheManager = "redisCacheManager")
    public User getUserById(Long id) {
        log.info("查询数据库: 用户ID={}", id);
        return userMapper.selectById(id);
    }

    @Cacheable(value = "hotUser", key = "#phone", cacheManager = "localCacheManager")
    public User getUserByPhone(String phone) {
        log.info("查询数据库: 用户手机号={}", phone);
        return userMapper.selectByPhone(phone);
    }

    @CacheEvict(value = {"user", "hotUser"}, key = "#user.id")
    public void updateUser(User user) {
        log.info("更新用户: {}", user.getId());
    }
}
