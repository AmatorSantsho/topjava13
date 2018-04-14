package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import ru.javawebinar.topjava.repository.JpaUtil;

/**
 * Created by 123 on 12.04.2018.
 */
 abstract public class AbstractUserJpaServiceTest extends AbstractUserServiceTest {
    @Autowired
    protected JpaUtil jpaUtil;
    @Autowired
    private CacheManager cacheManager;

    @Before
    public void setUp() throws Exception {
        cacheManager.getCache("users").clear();
        jpaUtil.clear2ndLevelHibernateCache();
    }
}
