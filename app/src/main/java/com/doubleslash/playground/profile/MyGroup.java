package com.doubleslash.playground.profile;

public class MyGroup {
    String location;    // 위치
    String category;    // 카테고리
    Integer current_num; // 현재 인원 수
    Integer max_num;   // 전체 인원 수
    String name;     // 그룹 제목
    String content; // 그룹 소개
    String imageUri;     // 그룹 사진 이미지

    public MyGroup(String location, String category, Integer current_num, Integer max_num, String name, String content, String imageUri) {
        this.location = location;
        this.category = category;
        this.current_num = current_num;
        this.max_num = max_num;
        this.name = name;
        this.content = content;
        this.imageUri = imageUri;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getCurrent_num() {
        return current_num;
    }

    public void setCurrent_num(Integer current_num) {
        this.current_num = current_num;
    }

    public Integer getMax_num() {
        return max_num;
    }

    public void setMax_num(Integer max_num) {
        this.max_num = max_num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
