package com.bnq.supplier.sp.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.bnq.supplier.sp.api.service.IDemoService;
import com.bnq.supplier.sp.dao.model.Demo;
import com.bnq.supplier.sp.common.enumm.RetEnum;
import com.bnq.supplier.sp.common.util.*;
import com.bnq.supplier.sp.service.BaseService4Demo;

import java.util.HashMap;
import java.util.Map;

@Service
public class DemoServiceImpl extends BaseService4Demo implements IDemoService {

    private static final MyLog _log = MyLog.getLog(DemoServiceImpl.class);

    @Override
    public Map create(String jsonParam) {
        Demo demo = BeanConvertUtils.map2Bean(JSONObject.parseObject(jsonParam), Demo.class);
        Map<String, String> ret = new HashMap<>();
        if(demo == null) {
            _log.warn("新增记录失败, {}. jsonParam={}", RetEnum.RET_PARAM_INVALID.getMessage(), jsonParam);
            ret.put("rpcRetCode", "1111");
            ret.put("bizResult", "0");
            return ret;
        }
        int result = super.baseCreateDemo(demo);

        if (result > 0) {
            ret.put("rpcRetCode", "0000");
            ret.put("bizResult", "1");
        } else {
            ret.put("rpcRetCode", "0000");
            ret.put("bizResult", "0");
        }
//        return RpcUtil.createBizResult(baseParam, result);
        return ret;
    }
}
