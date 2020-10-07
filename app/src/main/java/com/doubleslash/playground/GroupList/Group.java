package com.doubleslash.playground.GroupList;

public class Group {
    String location;    // 위치
    String category;    // 카테고리
    String current_num; // 현재 인원 수
    String total_num;   // 전체 인원 수
    String subject;     // 그룹 제목
    String information; // 그룹 소개
    int picture;     // 그룹 사진 이미지



    public Group(String location, String category, String current_num, String total_num, String subject, String information, int picture) {
        this.location = location;
        this.category = category;
        this.current_num = current_num;
        this.total_num = total_num;
        this.subject = subject;
        this.information = information;
        this.picture = picture;
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

    public String getCurrent_num() {
        return current_num;
    }

    public void setCurrent_num(String current_num) {
        this.current_num = current_num;
    }

    public String getTotal_num() {
        return total_num;
    }

    public void setTotal_num(String total_num) {
        this.total_num = total_num;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }
}
