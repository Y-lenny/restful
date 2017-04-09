package com.dafycredit.mall.catelog.dao.impl;

import com.dafycredit.mall.catelog.bean.dto.BrandDto;
import com.dafycredit.mall.catelog.dao.BrandDao;
import com.dafycredit.mall.catelog.dao.mapper.BrandMapper;
import com.dafycredit.mall.catelog.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <br>品牌持久层：对品牌进行CRUD操作</br>
 *
 * @author lennylv
 * @version 1.0
 * @class BrandDaoImpl
 * @date 2017/4/8 15:39
 * @since 1.0
 */
@Repository
public class BrandDaoImpl implements BrandDao {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public BrandDto getBrandById(Integer brandId) {
        return brandMapper.findBrandById(brandId);
    }

    @Override
    public List<BrandDto> listBrands(int page, int size) {
        return brandMapper.listBrands(page, size);
    }

    @Override
    public int updateBrand(BrandDto brandDto) {
        return brandMapper.updateBrand(brandDto);
    }

    @Override
    public int saveBrand(BrandDto brandDto) {
        return brandMapper.saveBrand(brandDto);
    }

    @Override
    public int removeBrands(Integer[] brandIds, Constant.Status status) {
        return brandMapper.removeBrands(brandIds, status);
    }
}
