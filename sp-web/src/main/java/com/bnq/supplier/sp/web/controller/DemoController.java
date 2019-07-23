package com.bnq.supplier.sp.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.bnq.supplier.sp.common.constant.Constant;
import com.bnq.supplier.sp.common.util.MyLog;
import com.bnq.supplier.sp.common.util.SPUtil;
import com.bnq.supplier.sp.web.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DemoController {
    private final MyLog _log = MyLog.getLog(DemoController.class);

    @Autowired
    private DemoService demoService;

    @RequestMapping(value = "/api/demo/create_item")
    public String createItem(@RequestBody Map<String, Object> params) {
        _log.info("createItem start.");
        try {
            JSONObject po = new JSONObject(params);
            JSONObject itemContext = new JSONObject();
            JSONObject item = null;
            // 验证参数有效性
//            Object object = validateParams(po, payContext);
//            if (object instanceof String) {
//                _log.info("{}参数校验不通过:{}", logPrefix, object);
//                return XXPayUtil.makeRetFail(XXPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, object.toString(), null, null));
//            }
//            if (object instanceof JSONObject) payOrder = (JSONObject) object;
//            if(payOrder == null) return XXPayUtil.makeRetFail(XXPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "支付中心下单失败", null, null));
            int result = demoService.create(po);
            _log.info("createItem result:{}", result);
            if(result != 1) {
                return SPUtil.makeRetFail(SPUtil.makeRetMap(Constant.RETURN_VALUE_FAIL, "创建记录失败", null, null, null));
            } else {
                Map<String, Object> map = SPUtil.makeRetMap(Constant.RETURN_VALUE_SUCCESS, "创建记录成功", null,null, null);
                return SPUtil.makeRetSuccess(map);
            }
        } catch (Exception e) {
            _log.error(e, "");
            return Constant.RETURN_VALUE_FAIL;
        }
    }
}
