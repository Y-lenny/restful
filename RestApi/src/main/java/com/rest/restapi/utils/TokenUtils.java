package com.rest.restapi.utils;

/**
 * @author : feelingxu@tcl.com
 * @DESC 请求参数 token检验工具类
 * @department : 应用产品中心/JAVA工程师
 * @date : 2017-1-5
 * @since : 1.0.0
 */
public class TokenUtils {

    /**
     * token校验
     * true 表示检验通过，flase表示检验失败
     *
     * @param token ,   请求时间戳:公钥
     * @return
     */
    public static boolean checkToken(String token) {
        if (null == token) {
            return false;
        }
        String[] arr = StringUtils.splitStr(token);
        if (null == arr || arr.length < 2) {
            return false;
        }

        //取到公钥，判断公钥是否我们下发客户端的公钥
        String accessKey = arr[1];

        System.out.println("*****  accessKey: " + accessKey);

        if (null != accessKey && Constants.SPACE_ACCESS_KEY.equals(accessKey)) {
            return true;
        }
        return false;
    }

}
