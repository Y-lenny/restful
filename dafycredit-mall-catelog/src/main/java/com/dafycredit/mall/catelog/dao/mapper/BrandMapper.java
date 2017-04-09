package com.dafycredit.mall.catelog.dao.mapper;

import com.dafycredit.mall.catelog.bean.dto.BrandDto;
import com.dafycredit.mall.catelog.util.Constant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandMapper {

    public BrandDto findBrandById(@Param("brandId") Integer brandId);

    List<BrandDto> listBrands(@Param("page") int page, @Param("size") int size);

    int updateBrand(@Param("brandDto") BrandDto brandDto);

    int saveBrand(@Param("brandDto") BrandDto brandDto);

    int removeBrands(@Param("brandIds") Integer[] brandIds, @Param("status") Constant.Status status);
}
