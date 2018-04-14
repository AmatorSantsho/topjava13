package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;

/**
 * Created by 123 on 12.04.2018.
 */
 abstract public class AbstractUserJdbcServiceTest extends AbstractUserServiceTest  {
    @Autowired
    private CacheManager cacheManager;

    @Before
    public void setUp() throws Exception {
        cacheManager.getCache("users").clear();
            }
}
