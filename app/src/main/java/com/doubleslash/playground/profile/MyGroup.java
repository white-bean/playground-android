package com.doubleslash.playground.profile;

public class MyGroup {
    String name;         // 그룹 제목
    String category;     // 카테고리
    String location;     // 위치
    String imageUri;     // 그룹 사진 이미지
    Integer current_num; // 현재 인원 수
    Integer maximum_num; // 최대 인원 수

    public MyGroup(String name, String category, String location, String imageUri, Integer current_num, Integer maximum_num) {
        this.name = name;
        this.category = category;
        this.location = location;
        this.imageUri = imageUri;
        this.current_num = current_num;
        this.maximum_num = maximum_num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public Integer getCurrent_num() {
        return current_num;
    }

    public void setCurrent_num(Integer current_num) {
        this.current_num = current_num;
    }

    public Integer getMaximum_num() {
        return maximum_num;
    }

    public void setMaximum_num(Integer maximum_num) {
        this.maximum_num = maximum_num;
    }
}
