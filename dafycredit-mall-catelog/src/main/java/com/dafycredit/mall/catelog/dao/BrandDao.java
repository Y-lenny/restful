package com.dafycredit.mall.catelog.dao;

import com.dafycredit.mall.catelog.bean.dto.BrandDto;
import com.dafycredit.mall.catelog.util.Constant;

import java.util.List;

public interface BrandDao {

    public BrandDto getBrandById(Integer brandId);

    List<BrandDto> listBrands(int page, int size);

    int updateBrand(BrandDto brandDto);

    int saveBrand(BrandDto brandDto);

    int removeBrands(Integer[] brandIds, Constant.Status status);
}
