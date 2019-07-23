package com.bnq.supplier.sp.web.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bnq.supplier.sp.api.service.IDemoService;
import org.springframework.stereotype.Service;

@Service
public class RpcCommonService {

    @Reference(timeout = 10000, retries = 0)
    public IDemoService rpcDemoService;

}
