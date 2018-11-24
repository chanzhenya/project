package com.bgy.robot.accesscontrol.kafka;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

public class StringTest {

    @Test
    public void test() {
        String str = "{\"BranchId\":\"1\",\"AGV\":[{\"Id\":\"001\",\"X\":1,\"Y\":2,\"Theta\":180,\"Status\":1,\"Error\":0,\"Battery\":13.0,\"Vel\":1.0,\"RotVel\":1.0,\"LineAcc\":0.1,\"RotAcc\":0.1}],\"Time\":\"1542872497\"}";
        JSONObject object = JSONObject.parseObject(str);

        System.out.println("JsonObject: "+object.toJSONString());
    }
}
