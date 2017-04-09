package com.dafycredit.mall.catelog.service.impl;

import com.dafycredit.mall.catelog.bean.dto.BrandDto;
import com.dafycredit.mall.catelog.bean.vo.BrandVo;
import com.dafycredit.mall.catelog.dao.BrandDao;
import com.dafycredit.mall.catelog.service.BrandService;
import com.dafycredit.mall.catelog.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <br>品牌业务层：对品牌进行CRUD操作</br>
 *
 * @author lennylv
 * @version 1.0
 * @class BrandServiceImpl
 * @date 2017/4/8 15:32
 * @since 1.0
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandDao brandDao;

    @Override
    public BrandDto getBrandById(Integer brandId) {
        return brandDao.getBrandById(brandId);
    }

    @Override
    public List<BrandDto> listBrands(int page, int size) {
        return brandDao.listBrands(page,size);
    }

    @Override
    public int updateBrand(BrandDto brandDto) {
        return brandDao.updateBrand(brandDto);
    }

    @Override
    public int saveBrand(BrandDto brandDto) {
        return brandDao.saveBrand(brandDto);
    }

    @Override
    public int removeBrands(Integer[] brandIds, Constant.Status status) {
        return brandDao.removeBrands(brandIds,status);
    }


}
