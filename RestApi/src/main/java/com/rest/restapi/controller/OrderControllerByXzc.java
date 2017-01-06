package com.rest.restapi.controller;

import com.rest.restapi.aspect.AbstractController;
import com.rest.restapi.utils.ReponseMsg;
import com.rest.restapi.utils.TokenUtils;
import com.rest.restapi.utils.query.QueryConstants;
import com.rest.restapi.vo.OrderVo;
import com.rest.restapi.vo.UserVo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by lennylv on 2017-1-4.
 * TODO Http缓存、Cache、Rate-Limit、Hateoas、安全性（Header、Body）、Error、一致性(Accept/Content-Type、Action、ETag、Status)
 * https://zhuanlan.zhihu.com/p/20034107?columnSlug=prattle
 */
@RequestMapping(path = "/ordersxzc")
public class OrderControllerByXzc extends AbstractController {

    /**
     * 通过username过滤用户
     * TODO ?embed=userVo.username
     *
     * @param
     * @return
     */
    @RequestMapping(path = "/{id}", params = {QueryConstants.EMBED}, method = RequestMethod.GET)
    public Object queryUserRelate(@PathVariable(value = "id") String id, @RequestParam(QueryConstants.EMBED) String embed) {

        //取到token值
        String token = getCommonParameter().getToken();

        //token校验
        if (!TokenUtils.checkToken(token)) {
            return this.serviceFail(ReponseMsg.REQUEST_IS_ERROR);
        }

        //具体业务操作，实际需要调用server
        UserVo userVo = new UserVo(1, "test", "111@qq.com", "110");
        OrderVo vo = new OrderVo(123, "13", 12, userVo);

        return this.getSuccessBytesByAes(vo);
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
        //取到token值
        String token = getCommonParameter().getToken();

        //token校验
        if (!TokenUtils.checkToken(token)) {
            return this.serviceFail(ReponseMsg.REQUEST_IS_ERROR);
        }

        //具体业务操作，实际需要调用server
        UserVo userVo = new UserVo(1, "test", "111@qq.com", "110");
        OrderVo vo = new OrderVo(123, "13", 12, userVo);

        return this.getSuccessBytesByAes(vo);
    }

    /**
     * 通过username过滤用户
     * TODO gzip压缩
     *
     * @param
     * @return
     */

    @RequestMapping(method = RequestMethod.GET)
    public byte[] queryOrderGzip() {

        //取到token值
        String token = "142556325669:OGUiRDpWHv6B7cW&";

        //token校验
        if (!TokenUtils.checkToken(token)) {
            return this.getServiceFailBytesByAes(ReponseMsg.REQUEST_IS_ERROR);
        }

        //具体业务操作，实际需要调用server
        UserVo userVo = new UserVo(1, "test", "111@qq.com", "110");
        OrderVo vo = new OrderVo(123, "13", 12, userVo);

        return this.getSuccessBytesByAes(vo);
    }
}
