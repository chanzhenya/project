package com.bgy.robot.accesscontrol.service.impl;

import com.bgy.robot.accesscontrol.entity.Account;
import com.bgy.robot.accesscontrol.mapper.AccountMapper;
import com.bgy.robot.accesscontrol.service.AccountService;
import com.bgy.robot.accesscontrol.utils.CommonUtil;
import com.bgy.robot.accesscontrol.utils.FileUtil;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.Socket;
import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private static final double THRESHOLD = 0.79;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AsyncTask asyncTask;

    @Override
    public void saveAccount(Account account, MultipartFile photo) throws Exception {

        // 计算图像特征值
        String eigenvalue = asyncTask.getEigenvalue(photo, CommonUtil.SOCKET_HOST, CommonUtil.SOCKET_PORT);

        //获取图片后缀名
        String filename = photo.getOriginalFilename();
        String suffix = filename.substring(filename.lastIndexOf(".") + 1);

        //将图片保存至本地
        FileUtil.uploadFile(photo.getBytes(), CommonUtil.FILE_PATH, account.getJobNo() + "." + suffix);

        account.setEigenvalue(eigenvalue);
        account.setPhotoPath(CommonUtil.FILE_PATH+account.getJobNo() + "." + suffix);

        int result = accountMapper.insert(account);

    }

    /**
     * 特征值匹配
     * @param photo
     * @return
     */
    @Override
    public boolean matchEigenvalue(MultipartFile photo) {
        // 计算图像特征值
        String eigenvalue = asyncTask.getEigenvalue(photo, CommonUtil.SOCKET_HOST, CommonUtil.SOCKET_PORT);
        List<Account> accounts = accountMapper.selectAll();
        for(Account account:accounts) {
            boolean result = compareFeature(account.getEigenvalue(),eigenvalue);
            if(result) {
                return true;
            }
        }
        return false;
    }

    /**
     * 特征值对比，余弦公式
     * @param srcFeatureStr
     * @param tagFeatureStr
     * @return
     */
    private boolean compareFeature(String srcFeatureStr,String tagFeatureStr) {
        srcFeatureStr = srcFeatureStr.substring(1,srcFeatureStr.length()-1);
        tagFeatureStr = tagFeatureStr.substring(1,tagFeatureStr.length()-1);
        String[] srcFeature = srcFeatureStr.split(",");
        String[] tagFeature = tagFeatureStr.split(",");

        int idx=0;
        Double[] srcFeatureArray = new Double[513];
        for(String feature:srcFeature) {
            double tmp = Double.valueOf(feature);
            srcFeatureArray[idx++] = tmp;
        }

        idx=0;
        Double[] tagFeatureArray = new Double[513];
        for(String feature:tagFeature) {
            double tmp = Double.valueOf(feature);
            tagFeatureArray[idx++]=tmp;
        }

        RealVector srcVector = new ArrayRealVector(srcFeatureArray);
        RealVector tagVector = new ArrayRealVector(tagFeatureArray);
        double result = srcVector.dotProduct(tagVector)/(srcVector.getNorm()*tagVector.getNorm());
        return result > THRESHOLD ? true:false;
    }
}
