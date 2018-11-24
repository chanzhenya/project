package com.bgy.robot.accesscontrol.service;

import com.bgy.robot.accesscontrol.entity.Account;
import org.springframework.web.multipart.MultipartFile;

public interface AccountService {

    public void saveAccount(Account account, MultipartFile photo) throws Exception;

    public boolean matchEigenvalue(MultipartFile photo);
}
