package com.bgy.robot;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Judith
 * @date 2018/12/6
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LogbackTest {

    @Test
    public void test() {
        log.debug("记录debug日志");
        log.info("记录info日志");
        log.error("记录error日志");
    }
}
