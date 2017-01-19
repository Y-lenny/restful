package com.rest.restapi.convert;


import com.rest.restapi.bean.dto.CommonParameterDto;
import com.rest.restapi.bean.vo.CommonParameterVo;
import org.springframework.beans.BeanUtils;

/**
 * <br>公共参数转换器</br>
 *
 * @author lennylv
 * @version 1.0
 * @class CommonParameterConvert
 * @date 2017-1-16 15:14
 * @since 1.0
 */
public class CommonParameterConvert {

    /**
     * <br></br>
     *
     * @param commonParameterVo
     * @return com.rest.restapi.bean.dto.CommonParameterDto
     * @throws
     * @author lennylv
     * @date 2017-1-16 15:14
     * @version 1.0
     * @since 1.0
     */
    public static CommonParameterDto convertToDto(CommonParameterVo commonParameterVo) {

        if (commonParameterVo == null) {
            return null;
        }

        CommonParameterDto commonParameterDto = new CommonParameterDto();
        BeanUtils.copyProperties(commonParameterVo, commonParameterDto);

        return commonParameterDto;
    }
}
