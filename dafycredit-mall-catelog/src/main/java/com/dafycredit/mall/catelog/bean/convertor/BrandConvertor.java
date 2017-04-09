package com.dafycredit.mall.catelog.bean.convertor;

import com.dafycredit.mall.catelog.bean.dto.BrandDto;
import com.dafycredit.mall.catelog.bean.vo.BrandVo;
import org.springframework.beans.BeanUtils;

/**
 * <br>Brand Convert</br>
 *
 * @author lennylv
 * @version 1.0
 * @class BrandConvert
 * @date 2017/4/8 16:08
 * @since 1.0
 */
public class BrandConvertor {

    public BrandVo convertVo(BrandDto brandDto) {

        if (brandDto == null) {
            return null;
        }
        
        BrandVo brandVo = new BrandVo();
        BeanUtils.copyProperties(brandDto, brandVo);
        return brandVo;
    }
}
