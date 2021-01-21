package com.doubleslash.playground.infoGroup;

public class Join {
    // uri는 추후 서버 작업 끝난 후 추가
    private String userName;
    private String location;
    private String university;

    private String imageUri;

    public Join(String userName, String location, String university) {
        this.userName = userName;
        this.location = location;
        this.university = university;
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

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
