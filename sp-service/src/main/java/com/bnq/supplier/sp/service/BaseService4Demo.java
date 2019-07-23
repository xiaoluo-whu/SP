package com.bnq.supplier.sp.service;

import com.bnq.supplier.sp.dao.mapper.DemoMapper;
import com.bnq.supplier.sp.dao.model.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseService4Demo extends BaseService{

    @Autowired
    private DemoMapper demoMapper;

    public int baseCreateDemo(Demo demo) {
        return demoMapper.insert(demo);
    }
}
