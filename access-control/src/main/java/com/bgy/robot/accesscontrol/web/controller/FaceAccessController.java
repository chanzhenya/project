package com.bgy.robot.accesscontrol.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.bgy.robot.accesscontrol.entity.Device;
import com.bgy.robot.accesscontrol.service.AccountService;
import com.bgy.robot.accesscontrol.service.DeviceService;
import com.bgy.robot.accesscontrol.utils.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
public class FaceAccessController {

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private AccountService accountService;

    @Autowired
    private DeviceService deviceService;

    @PostMapping("/upload")
    public String uploadPhoto(@RequestParam("imageFile")MultipartFile multipartFile, HttpServletRequest request) {
        String clientIp = getIpAddress(request);
        boolean flag = false;
        try {
            boolean marchResult = accountService.matchEigenvalue(multipartFile);
            if(marchResult) {
                flag = unlockEntranceGuard(clientIp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Exception";
        }
        return flag?"success":"fail";
    }

    /**
     * 解锁门禁
     * @return
     */
    private boolean unlockEntranceGuard(String ip) {
        Device device = deviceService.findByIp(ip);
        String gid = device.getGid();
        String devNo = device.getDevNo();
        //10.8.103.74
        JSONObject params = new JSONObject();
        params.put("SubSystem",2);
        params.put("Order",1);
        params.put("OrderDate",new Date());
        params.put("DevId",devNo);
        params.put("Parameter","1");
        params.put("OrderRemark","Test Test");
        params.put("Gid",gid);
        params.put("IsLegal",true);

        ResponseEntity<JSONObject> responseEntity = restTemplate.postForEntity(CommonUtil.API_REMOTE_ORDER_URL,params,JSONObject.class);

        JSONObject result = responseEntity.getBody();

        boolean isSuccess = result.getBoolean("IsSucess");
        return isSuccess;
    }

    /**
     * 获取IP地址
     * @return
     */
    private String getIpAddress(HttpServletRequest request) {
        String Xip = request.getHeader("X-Real-IP");
        String XFor = request.getHeader("X-Forwarded-For");
        if(StringUtils.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = XFor.indexOf(",");
            if(index != -1){
                return XFor.substring(0,index);
            }else{
                return XFor;
            }
        }
        XFor = Xip;
        if(StringUtils.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)){
            return XFor;
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getRemoteAddr();
        }
        return XFor;
    }
}
