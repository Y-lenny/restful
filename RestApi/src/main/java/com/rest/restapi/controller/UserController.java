package com.rest.restapi.controller;

import com.rest.restapi.util.JsonUtil;
import com.rest.restapi.util.query.QueryConstants;
import com.rest.restapi.util.query.QueryOrderUtil;
import com.rest.restapi.util.query.QuerySearchUtil;
import com.rest.restapi.util.url.UrlMappings;
import com.rest.restapi.bean.vo.OrderVo;
import com.rest.restapi.bean.vo.UserVo;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <br></br>
 *
 * api.dev.hawk.com/v1/users
 * api.test.hawk.com/v1/users
 * api.pro.hawk.com/v1/users
 *
 * @class   UserController
 * @author  lennylv
 * @date    2017-1-16 15:11
 * @version 1.0
 * @since   1.0
 */
@RestController
@RequestMapping(UrlMappings.USERS)
public class UserController extends AbstractController {

    /**
     * 创建用户
     * 并且资源放在 LocationHeader 中
     *
     * @param userVo
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void insertUser(@RequestBody(required = true) UserVo userVo) {
        System.out.println(userVo);
    }

    /**
     * 删除用户
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable(value = "id") int id) {
        System.out.println(id);
    }

    /**
     * 更新用户
     *
     * @param userVo
     */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public void updateUser(@RequestBody(required = true) UserVo userVo) {
        System.out.println(userVo);
    }

    /**
     * 通过id查询用户
     *
     * @param id
     * @return
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserVo queryUserById(@PathVariable(value = "id") int id) {
        return new UserVo(id, "zhangsan", "zhangsan@tcl.com", "1234567890");
    }

    /**
     * 通过username过滤用户
     * api.dev.clean.com/v1/users?username=zhangsan/ 直接映射RequestParam 对应参数;然后进行参数校验
     *
     * @param username
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserVo queryUserByFilter(@RequestParam(value = "username") String username) {
        if ("zhangsan".equalsIgnoreCase(username)) {
            return new UserVo(0, "zhangsan", "zhangsan@tcl.com", "1234567890");
        } else {
            return null;
        }
    }

    /**
     * 通过username过滤用户
     * ?sort=-id,username
     *
     * @return
     */

    @RequestMapping(params = {QueryConstants.SORT}, method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<UserVo> queryUserSort(@RequestParam(QueryConstants.SORT) String sorts) {

        // 转换成排序列表
        QueryOrderUtil.parseSort(sorts);
        return null;
    }

    /**
     * 通过username过滤用户
     * ?q=zhangsan
     *
     * @param searches
     * @return
     */

    @RequestMapping(params = {QueryConstants.Q_PARAM}, method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<UserVo> queryUserSearch(@RequestParam(QueryConstants.Q_PARAM) final String searches) {

        // 转换成搜索列表
        QuerySearchUtil.parseSearch(searches);
        return null;
    }

    /**
     * 通过username过滤用户
     * ?page=3&size=100
     *
     * @param page
     * @param size
     * @return
     */

    @RequestMapping(params = {QueryConstants.PAGE, QueryConstants.SIZE}, method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<UserVo> queryUserPaging(@RequestParam(QueryConstants.PAGE) final int page, @RequestParam(QueryConstants.SIZE) final int size) {
        return null;
    }

    /**
     * 通过用户id查询订单
     *
     * @param id
     * @return
     */
    @RequestMapping(path = "/{id}/orders", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<OrderVo> queryOrderByUserId(@PathVariable(value = "id") int id) {
        System.out.println(JsonUtil.obj2json(getCommonParameter()));
        ArrayList orders = new ArrayList<OrderVo>();
        orders.add(new OrderVo(1, "order", id, null));
        System.out.println(JsonUtil.obj2json(orders));
        return orders;
    }

}
