package com.rest.restapi.controller;

import com.rest.RestApiApplicationTests;
import com.rest.restapi.utils.AesUtils;
import com.rest.restapi.utils.Constants;
import com.rest.restapi.utils.GZipUtils;
import org.junit.Test;

/**
 * UserController Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>һ�� 3, 2017</pre>
 */
public class OrderControllerTest extends RestApiApplicationTests {

    private OrderControllerByXzc orderController = new OrderControllerByXzc();

    /**
     * Method: queryOrderGzip()
     */
    @Test
    public void testQueryOrderGzip() throws Exception {

        byte[] resultBytes = (byte[]) orderController.queryOrderGzip();

        System.out.println("接口字节长度: " + resultBytes.length);

        byte[] decompressResultBytes = GZipUtils.decompress(resultBytes);

        String strRead = new String(decompressResultBytes);
        strRead = String.copyValueOf(strRead.toCharArray(), 0, decompressResultBytes.length);

        String result = AesUtils.Decrypt(strRead, Constants.SPACE_SECRET_KEY);

        System.out.println("解密后数据是\n" + result);
    }


} 
