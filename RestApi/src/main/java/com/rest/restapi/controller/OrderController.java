package com.rest.restapi.controller;

import com.rest.restapi.util.query.QueryConstants;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * <br>订单</br>
 * TODO
 * 1. api的URI设计实现
 * 域名：api.company.com
 * 版本：/v1/users/1
 * 查询：分页、过滤、搜索、排序、关联查询
 * 状态码：Error/Status
 * <p>
 * 2. api的缓存实现
 * 3. Rate-Limit限流限速
 * 4. api的HATEOAS
 * 5. api的安全实现
 * <p>
 * https：保证数据传输加密
 * HMAC：保证数据的一致性，防止DDOS攻击
 * Header、Body
 * <p>
 * 6. 一致性(Accept/Content-Type、Action、ETag、Status)
 * 7. api的管理swagger
 * 8. 幂等操作
 * https://zhuanlan.zhihu.com/p/20034107?columnSlug=prattle
 *
 * @author lennylv
 * @version 1.0
 * @class OrderController
 * @date 2017-1-16 15:12
 * @since 1.0
 */
@RequestMapping(path = "/orders")
@RestController
public class OrderController extends AbstractController {

    /**
     * 通过username过滤用户
     * TODO ?embed=userVo.username
     *
     * @param
     * @return
     */

    @RequestMapping(path = "/{id}", params = {QueryConstants.EMBED}, method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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

    @RequestMapping(params = {QueryConstants.FIELD}, method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object queryOrderGzip() {
        return null;
    }
}
