package org.hswebframework.web.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hswebframework.web.authorization.Permission;
import org.hswebframework.web.authorization.annotation.Authorize;
import org.hswebframework.web.controller.message.ResponseMessage;
import org.hswebframework.web.demo.pojo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

import static org.hswebframework.web.controller.message.ResponseMessage.ok;

@RestController
@RequestMapping("/test")
@Api(value = "数据字典", tags = "数据字典-字典配置")
public class TestController {

    @Authorize(action = Permission.ACTION_QUERY)
    @GetMapping
    @ApiOperation(value = "不分页动态查询", responseReference = "get")
    public ResponseMessage<List<Result>> list(String name, String password) {

        return ok( Arrays.asList(new Result(name,password)));
    }
}
