package com.doubleslash.playground.infoGroup;

public class Join {
    // uri는 추후 서버 작업 끝난 후 추가
    private String imageUri;
    private String userName;
    private String location;
    private String univ;

    public Join(String userName, String location, String univ) {
        this.userName = userName;
        this.location = location;
        this.univ = univ;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUniv() {
        return univ;
    }

    public void setUniv(String univ) {
        this.univ = univ;
    }
}
