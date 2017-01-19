package com.rest.restapi.bean.vo;

/**
 * <br></br>
 *      
 * @class   CommonParameterVo
 * @author  lennylv
 * @date    2017-1-16 14:27
 * @version 1.0
 * @since   1.0
 */
public class CommonParameterVo {

    /**
     * 身份认证
     */
    private String token;

    /**
     * 包名
     */
    private String packageName;

    /**
     * 应用版本号
     */
    private String versionCode;

    /**
     * 应用版本名称
     */
    private String versionName;

    /**
     * 设备唯一标识
     */
    private String uuid;
    /**
     * 语言
     */
    private String language;

    /**
     * 地区
     */
    private String locale;

    /**
     * 屏幕尺寸
     */
    private String screenSize;

    /**
     * 网络类型：wifi 、
     */
    private String network;

    /**
     * 系统版本号
     */
    private String osVersionCode;

    /**
     * 系统版本名称
     */
    private String osVersionName;

    public String getOsVersionName() {
        return osVersionName;
    }

    public void setOsVersionName(String osVersionName) {
        this.osVersionName = osVersionName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getOsVersionCode() {
        return osVersionCode;
    }

    public void setOsVersionCode(String osVersionCode) {
        this.osVersionCode = osVersionCode;
    }
}
