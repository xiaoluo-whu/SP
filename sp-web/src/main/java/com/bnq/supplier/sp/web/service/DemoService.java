package com.bnq.supplier.sp.web.service;

import com.alibaba.fastjson.JSONObject;
import com.bnq.supplier.sp.common.util.MyLog;
import com.bnq.supplier.sp.common.util.RpcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DemoService {

    private static final MyLog _log = MyLog.getLog(DemoService.class);

    @Autowired
    private RpcCommonService rpcCommonService;

    public int create(JSONObject item) {
        String jsonParam = item.toJSONString();
        Map<String, Object> result = rpcCommonService.rpcDemoService.create(jsonParam);
        String s = RpcUtil.mkRet(result);
        if(s == null) return 0;
        return Integer.parseInt(s);
    }
}
