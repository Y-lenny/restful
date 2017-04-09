package com.dafycredit.mall.catelog.controller;

import com.dafycredit.mall.catelog.bean.dto.BrandDto;
import com.dafycredit.mall.catelog.service.BrandService;
import com.dafycredit.mall.catelog.util.Constant;
import com.dafycredit.mall.catelog.util.UrlMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <br>品牌控制器层：对品牌进行CRUD操作</br>
 *
 * @author lennylv
 * @version 1.0
 * @class BrandController
 * @date 2017/4/8 15:08
 * @since 1.0
 */
@Controller
@RequestMapping(UrlMapping.BRANDS)
public class BrandController extends AbstractController {

    @Autowired
    private BrandService brandService;

    @RequestMapping("/toSaveBrand")
    public String toSaveBrand() {
        return "/brand/brand";
    }

    @RequestMapping(value = "/{brandId}", method = RequestMethod.GET)
    public String toUpdateBrand(@PathVariable("brandId") Integer brandId, HttpServletRequest req) {

        BrandDto brandDto = brandService.getBrandById(brandId);
        if (brandDto == null) {
            req.setAttribute("message", "品牌不存在");
            return "redirect:/brands.html";
        }
        return "/brand/brand";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String listBrands(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                             @RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
                             HttpServletRequest req) {

        List<BrandDto> brandDtos = brandService.listBrands(page, size);
        req.setAttribute("brandDtos", brandDtos);

        return "/brand/brands";
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String updateBrand(BrandDto brandDto, HttpServletRequest req) {

        int affectCols = brandService.updateBrand(brandDto);
        //未更新
        if (affectCols < 1) {
            req.setAttribute("message", "品牌更新失败");
        }

        return "redirect:/brands";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveBrand(BrandDto brandDto, HttpServletRequest req) {

        int affectCols = brandService.saveBrand(brandDto);
        // 未新增
        if (affectCols < 1) {
            req.setAttribute("message", "品牌新增失败");
        }

        return "redirect:/brands";
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public String removeBrands(Integer[] brandIds, HttpServletRequest req) {

        int affectCols = brandService.removeBrands(brandIds, Constant.Status.DELETE);
        // TODO ...
        if (affectCols < 1) {
            req.setAttribute("message", "品牌状态更新失败");
        }

        return "redirect:/brands";
    }

}
