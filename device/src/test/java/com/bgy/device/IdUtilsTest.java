package com.bgy.device;

import com.bgy.device.utils.IdUtil;
import org.junit.Test;

/**
 * @author Judith
 * @date 2019/1/14 11:11
 * @description
 */
public class IdUtilsTest {

    @Test
    public void generateStringTest() {
        String str = IdUtil.generateString();
        System.out.println("[----------- IdUtil Test -----------]"+str);
    }
}
