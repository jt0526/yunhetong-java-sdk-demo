package com.yunhetong.model;

/**
 * Created by qianyj on 2017/5/1.
 */
public class ActorModel {
    private String appUserId;
    private String locationName;
    private String keyWord;

    public String getAppUserId() {
        return appUserId;
    }

    public ActorModel setAppUserId(String appUserId) {
        this.appUserId = appUserId;
        return this;
    }

    public String getLocationName() {
        return locationName;
    }

    public ActorModel setLocationName(String locationName) {
        this.locationName = locationName;
        return this;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public ActorModel setKeyWord(String keyWord) {
        this.keyWord = keyWord;
        return this;
    }

}
