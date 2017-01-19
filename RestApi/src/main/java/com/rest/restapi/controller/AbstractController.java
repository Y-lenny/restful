package com.rest.restapi.controller;

import com.rest.restapi.bean.dto.CommonParameterDto;
import com.rest.restapi.bean.vo.CommonParameterVo;
import com.rest.restapi.convert.CommonParameterConvert;
import com.rest.restapi.exception.UnauthorizedException;
import com.rest.restapi.util.JsonUtil;
import com.rest.restapi.util.precondition.RestPrecondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * <br>v1 版本公共api 抽象controller 层</br>
 *
 * @author lennylv
 * @version 1.0
 * @class AbstractController
 * @date 2017-1-11 13:41
 * @since 1.0
 */
public abstract class AbstractController {

    private final static Logger LOGGER = LoggerFactory.getLogger(AbstractController.class);

    /**
     * 线程安全
     */
    @Autowired
    private HttpServletRequest request;


    /**
     * token校验
     */
    public void checkToken(CommonParameterDto common) throws UnauthorizedException {
//        if (!TokenUtils.checkToken(common.getToken())) {
//            throw new UnauthorizedException(ReponseMsg.TOKEN_IS_ERROR.getMsg());
//        }
    }

    /**
     * <br>封装公共参数</br>
     *
     * @return com.tcl.mig.cleanportal.bo.CommonParameterBo
     * @throws
     * @author lennylv
     * @date 2017-1-11 14:07
     * @version 1.0
     * @since 1.0
     */
    protected CommonParameterDto getCommonParameter() {

        CommonParameterVo commonParameterVo = JsonUtil.json2obj(request.getHeader("commonParameter"), CommonParameterVo.class);
        // 公共参数进行校验
        RestPrecondition.checkIfBadRequest(commonParameterVo != null);
       /* RestPrecondition.checkIfBadRequest(!StringUtils.isEmpty(commonParameterVo.getUuid()));
        RestPrecondition.checkIfBadRequest(!StringUtils.isEmpty(commonParameterVo.getLanguage()));
        RestPrecondition.checkIfBadRequest(!StringUtils.isEmpty(commonParameterVo.getLocale()));
        RestPrecondition.checkIfBadRequest(!StringUtils.isEmpty(commonParameterVo.getNetwork()));
        RestPrecondition.checkIfBadRequest(!StringUtils.isEmpty(commonParameterVo.getOsVersionName()));
        RestPrecondition.checkIfBadRequest(!StringUtils.isEmpty(commonParameterVo.getOsVersionCode()));
        RestPrecondition.checkIfBadRequest(!StringUtils.isEmpty(commonParameterVo.getPackageName()));
        RestPrecondition.checkIfBadRequest(!StringUtils.isEmpty(commonParameterVo.getScreenSize()));
        RestPrecondition.checkIfBadRequest(!StringUtils.isEmpty(commonParameterVo.getToken()));
        RestPrecondition.checkIfBadRequest(!StringUtils.isEmpty(commonParameterVo.getVersionCode()));
        RestPrecondition.checkIfBadRequest(!StringUtils.isEmpty(commonParameterVo.getVersionName()));*/

        return CommonParameterConvert.convertToDto(commonParameterVo);
    }

}
