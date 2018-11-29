package com.bgy.device.service.impl;

import com.bgy.device.entity.Branch;
import com.bgy.device.mapper.BranchMapper;
import com.bgy.device.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchMapper branchMapper;

    @Override
    public List<Branch> findAll() {
        return branchMapper.selectAll();
    }

    @Override
    public void save(Branch branch) {
        if(branch.getBranchId() != null) {
            branchMapper.updateByPrimaryKeySelective(branch);
        } else {
            branchMapper.insert(branch);
        }
    }

    @Override
    public boolean delete(List<Branch> branches) {
        try {
            for(Branch branch:branches) {
                branchMapper.deleteByPrimaryKey(branch);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
