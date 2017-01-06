package com.rest.restapi.controller;

import com.rest.restapi.utils.query.QueryConstants;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by lennylv on 2017-1-4.
 * TODO
 1. api的URI设计实现
     * 域名：api.company.com
     * 版本：/v1/users/1
     * 查询：分页、过滤、搜索、排序、关联查询
     * 状态码：Error/Status

 2. api的缓存实现
 3. Rate-Limit限流限速
 4. api的HATEOAS
 5. api的安全实现

     * https：保证数据传输加密
     * HMAC：保证数据的一致性，防止DDOS攻击
     * Header、Body

 6. 一致性(Accept/Content-Type、Action、ETag、Status)
 7. api的管理swagger
 8. 幂等操作


 * https://zhuanlan.zhihu.com/p/20034107?columnSlug=prattle
 */
@RequestMapping(path = "/orders")
public class OrderController {

    /**
     * 通过username过滤用户
     * TODO ?embed=userVo.username
     *
     * @param
     * @return
     */

    @RequestMapping(path = "/{id}", params = {QueryConstants.EMBED}, method = RequestMethod.GET)
    public Object queryUserRelate(@PathVariable(value = "id") String id, @RequestParam(QueryConstants.EMBED) String embed) {
        return null;
    }

    /**
     * 通过username过滤用户
     * TODO ?fields=orderId,number
     *
     * @param fields
     * @return
     */

    @RequestMapping(params = {QueryConstants.FIELD}, method = RequestMethod.GET)
    public Object queryOrderFields(@RequestParam(QueryConstants.FIELD) String fields) {
        return null;
    }

    /**
     * 通过username过滤用户
     * TODO gzip压缩
     *
     * @param
     * @return
     */

    @RequestMapping(method = RequestMethod.GET)
    public Object queryOrderGzip() {
        return null;
    }
}
