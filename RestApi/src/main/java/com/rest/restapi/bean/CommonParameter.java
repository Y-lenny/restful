package com.rest.restapi.bean;

import java.io.Serializable;

/**
 * @author : feelingxu@tcl.com
 * @DESC 标题
 * @department : 应用产品中心/JAVA工程师
 * @date : 2017-1-6
 * @since : 1.0.0
 */
public class CommonParameter implements Serializable {
    private static final long serialVersionUID = -3849655140908496261L;
    private String imei;
    private String language;
    private String locale;
    private String screenSize;
    private String network;
    private String packageName;
    private String versionCode;
    private String versionName;
    private String osVersionCode;
    private String osVersion;
    private String channel;
    private String token;

    public CommonParameter() {
    }

    public CommonParameter(String language) {
        this.language = language;
    }

    public String getImei() {
        return this.imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLocale() {
        return this.locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getScreenSize() {
        return this.screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    public String getNetwork() {
        return this.network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getVersionCode() {
        return this.versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return this.versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getOsVersionCode() {
        return this.osVersionCode;
    }

    public void setOsVersionCode(String osVersionCode) {
        this.osVersionCode = osVersionCode;
    }

    public String getOsVersion() {
        return this.osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getChannel() {
        return this.channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
