package com.rest.restapi.vo;

import org.springframework.stereotype.Component;

/***
 * 请求公共参数
 */
@Component
public class CommonParameterVo {


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

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
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

    public String getOsVersionCode() {
        return osVersionCode;
    }

    public void setOsVersionCode(String osVersionCode) {
        this.osVersionCode = osVersionCode;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "CommonParameter [imei=" + imei + ", language=" + language + ", locale=" + locale
                + ", screenSize=" + screenSize + ", network=" + network + ", packageName="
                + packageName + ", versionCode=" + versionCode + ", versionName=" + versionName
                + ", osVersionCode=" + osVersionCode + ", osVersion=" + osVersion + ", channel="
                + channel + ", token=" + token + "]";
    }



}
