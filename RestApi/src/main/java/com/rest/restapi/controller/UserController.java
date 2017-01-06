package com.rest.restapi.controller;

import com.rest.restapi.utils.query.QueryConstants;
import com.rest.restapi.utils.query.QueryOrderUtil;
import com.rest.restapi.utils.query.QuerySearchUtil;
import com.rest.restapi.vo.OrderVo;
import com.rest.restapi.vo.UserVo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Created by lennylv on 2017-1-3.
 * <p>
 *     api.dev.hawk.com/v1/users
 *     api.test.hawk.com/v1/users
 *     api.pro.hawk.com/v1/users
 * <p>
 * v1版本API
 */
@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

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
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Object queryUserById(@PathVariable(value = "id") int id) {
        return new UserVo(id, "zhangsan", "zhangsan@tcl.com", "1234567890");
    }

    /**
     * 通过username过滤用户
     *api.dev.clean.com/v1/users?username=zhangsan/ 直接映射RequestParam 对应参数;然后进行参数校验
     *
     * @param username
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Object queryUserByFilter(@RequestParam(value = "username") String username) {
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

    @RequestMapping(params = {QueryConstants.SORT}, method = RequestMethod.GET)
    public Object queryUserSort(@RequestParam(QueryConstants.SORT) String sorts) {

        // 转换成排序列表
        return QueryOrderUtil.parseSort(sorts);
    }

    /**
     * 通过username过滤用户
     * ?q=zhangsan
     *
     * @param searches
     * @return
     */

    @RequestMapping(params = {QueryConstants.Q_PARAM}, method = RequestMethod.GET)
    public Object queryUserSearch(@RequestParam(QueryConstants.Q_PARAM) final String searches) {

        // 转换成搜索列表
        return QuerySearchUtil.parseSearch(searches);
    }

    /**
     * 通过username过滤用户
     * ?page=3&size=100
     *
     * @param page
     * @param size
     * @return
     */

    @RequestMapping(params = {QueryConstants.PAGE, QueryConstants.SIZE}, method = RequestMethod.GET)
    public Object queryUserPaging(@RequestParam(QueryConstants.PAGE) final int page, @RequestParam(QueryConstants.SIZE) final int size) {
        return null;
    }

    /**
     * 通过用户id查询订单
     *
     * @param id
     * @return
     */
    @RequestMapping(path = "/{id}/orders", method = RequestMethod.GET)
    public Object queryOrderByUserId(@PathVariable(value = "id") int id) {
        ArrayList orders = new ArrayList<OrderVo>();
        orders.add(new OrderVo(1, "order", id, null));
        System.out.println(orders);
        return orders;
    }

}
