package com.bgy.device.service;

import com.bgy.device.entity.Branch;

import java.util.List;

public interface BranchService {

    public List<Branch> findAll();

    public void save(Branch branch);

    public void delete(List<Branch> branches) throws Exception;
}
