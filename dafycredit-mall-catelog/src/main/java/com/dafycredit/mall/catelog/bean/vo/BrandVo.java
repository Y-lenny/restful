package com.dafycredit.mall.catelog.bean.vo;

import com.dafycredit.mall.catelog.util.Constant;

import java.io.Serializable;
import java.util.Date;

/**
 * <br>品牌视图层实体类</br>
 *
 * @author lennylv
 * @version 1.0
 * @class BrandVo
 * @date 2017/4/8 14:59
 * @since 1.0
 */
public class BrandVo implements Serializable {

    private static final long serialVersionUID = 3365039039146324948L;


    /**
     * 唯一标识
     */
    private Integer brandId;

    /**
     * 名称
     */
    private String name;

    /**
     * 图片
     */
    private String img;

    /**
     * 状态
     */
    private Constant.Status status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建者ID
     */
    private Integer createUserId;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新者ID
     */
    private Integer updateUserId;

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Constant.Status getStatus() {
        return status;
    }

    public void setStatus(Constant.Status status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    @Override
    public String toString() {
        return "BrandVo{" +
                "brandId=" + brandId +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                ", createUserId=" + createUserId +
                ", updateTime=" + updateTime +
                ", updateUserId=" + updateUserId +
                '}';
    }
}
