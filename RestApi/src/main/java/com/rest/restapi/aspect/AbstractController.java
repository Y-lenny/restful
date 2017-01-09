package com.rest.restapi.aspect;

import com.rest.restapi.bean.CommonParameter;
import com.rest.restapi.utils.*;
import com.rest.restapi.vo.CommonParameterVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xiaowan.dang
 * @ClassName: AbstractController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2016年4月6日 下午2:00:12
 */
public class AbstractController {

    private final static Logger logger = LoggerFactory.getLogger(AbstractController.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private CommonParameterVo commonParameter;

    /**
     * 获取公共参数
     *
     * @return CommonParameter
     */
    public CommonParameterVo getCommonParameter() {
        commonParameter.setImei(request.getHeader("imei"));

        commonParameter.setLanguage(StringUtils.getLanguage(request));
        commonParameter.setLocale(request.getHeader("locale"));

        commonParameter.setScreenSize(request.getHeader("screenSize"));
        commonParameter.setNetwork(request.getHeader("network"));
        commonParameter.setPackageName(request.getHeader("pkgName"));
        commonParameter.setVersionCode(request.getHeader("versionCode"));
        commonParameter.setVersionName(request.getHeader("versionName"));
        commonParameter.setOsVersion(request.getHeader("osVersion"));
        commonParameter.setOsVersionCode(request.getHeader("osVersionCode"));
        commonParameter.setChannel(request.getHeader("channel"));
        commonParameter.setToken(request.getHeader("token"));
        return commonParameter;
    }

    public CommonParameter converterCommonParameter() {
        CommonParameterVo commonParameterVo = getCommonParameter();
        CommonParameter commonParameter = new CommonParameter();
        BeanUtils.copyProperties(commonParameterVo, commonParameter);
        return commonParameter;
    }

    // 组装 自定义错误
    public JsonResult serviceFail(ReponseMsg reponseMsg) {
        return new JsonResult(reponseMsg.getCode(), reponseMsg.getMsg());
    }


    /**
     * 得到  服务执行成功的 AES 加密、Gzip压缩 字节返回
     */
    public static byte[] getSuccessBytesByAes(Object data) {
        String code = ReponseMsg.SERVICE_SUCCESS.getCode();
        String msg = ReponseMsg.SERVICE_SUCCESS.getMsg();
        com.rest.restapi.vo.JsonResult result = new com.rest.restapi.vo.JsonResult(code, msg, data);
        logger.info("### getSuccessBytesByAes result is {}", JsonUtils.obj2json(result));
        byte[] resultBytes = null;
        try {
            String aesStr = AesUtils.Encrypt(JsonUtils.obj2json(result), Constants.SPACE_SECRET_KEY);
            logger.debug("### getSuccessBytesByAes aesStr is {}", aesStr);
            if (null != aesStr) {
                resultBytes = GZipUtils.compress(aesStr.getBytes());
            }
        } catch (Exception e) {
            logger.error("### getSuccessBytesByAes AES or GZip Exception,e is {}", e);
        }
        return resultBytes;
    }

    /**
     * 得到  服务执行成功的 base64加密、Gzip压缩 字节返回
     */
    public static byte[] getSuccessBytesByBase64(Object data) {
        String code = ReponseMsg.SERVICE_SUCCESS.getCode();
        String msg = ReponseMsg.SERVICE_SUCCESS.getMsg();
        com.rest.restapi.vo.JsonResult result = new com.rest.restapi.vo.JsonResult(code, msg, data);
        logger.info("### getSuccessBytesByBase64 result is {}", JsonUtils.obj2json(result));
        byte[] resultBytes = null;
        try {
            String base64Str = Base64Utils.encryptBase64(JsonUtils.obj2json(result));
            logger.debug("### getSuccessBytesByBase64 is {}", base64Str);
            if (null != base64Str) resultBytes = GZipUtils.compress(base64Str.getBytes());
        } catch (Exception e) {
            logger.error("### getSuccessBytesByBase64 base64 or GZip Exception,e is {}", e);
        }
        return resultBytes;
    }

    /**
     * 得到 自定义错误的 AES 加密、Gzip压缩 字节返回
     */
    public static byte[] getServiceFailBytesByAes(ReponseMsg reponseMsg) {
        com.rest.restapi.vo.JsonResult result = new com.rest.restapi.vo.JsonResult(reponseMsg.getCode(), reponseMsg.getMsg());
        logger.debug("### getServiceFailBytesByAes result is {}", JsonUtils.obj2json(result));
        byte[] serviceFailBytes = null;
        try {
            String base64Str = Base64Utils.encryptBase64(JsonUtils.obj2json(result));
            logger.debug("### getServiceFailBytesByAes base64Str is {}", base64Str);
            if (null != base64Str) serviceFailBytes = GZipUtils.compress(base64Str.getBytes());
        } catch (Exception e) {
            logger.error("getServiceFailBytesByAes base64 or GZip Exception,e is {}", e);
        }
        return serviceFailBytes;
    }

    /**
     * 得到 自定义错误的 base64加密、Gzip压缩 字节返回
     */
    public static byte[] getServiceFailBytesByBase64(ReponseMsg reponseMsg) {
        com.rest.restapi.vo.JsonResult result = new com.rest.restapi.vo.JsonResult(reponseMsg.getCode(), reponseMsg.getMsg());
        logger.debug("### getServiceFailBytesByBase64 result is {}", JsonUtils.obj2json(result));
        byte[] serviceFailBytes = null;
        try {
            String base64Str = Base64Utils.encryptBase64(JsonUtils.obj2json(result));
            logger.debug("### getServiceFailBytesByBase64 base64Str is {}", base64Str);
            if (null != base64Str) serviceFailBytes = GZipUtils.compress(base64Str.getBytes());
        } catch (Exception e) {
            logger.error("getServiceFailBytesByBase64 base64 or GZip Exception,e is {}", e);
        }
        return serviceFailBytes;
    }

    /**
     * 服务执行成功
     *
     * @param data
     * @return
     */
    public JsonResult serviceSuccess(Object data) {
        String code = ReponseMsg.SERVICE_SUCCESS.getCode();
        String msg = ReponseMsg.SERVICE_SUCCESS.getMsg();
        return new JsonResult(code, msg, data);
    }

    public class JsonResult {
        private String code;
        private String msg;
        private Object data;

        public JsonResult() {
        }

        public JsonResult(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public JsonResult(String retCode, String retMsg, Object data) {
            this.code = retCode;
            this.msg = retMsg;
            this.data = data;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }
    }
}
