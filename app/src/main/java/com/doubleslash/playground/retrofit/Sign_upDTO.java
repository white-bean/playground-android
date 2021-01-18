package com.doubleslash.playground.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import okhttp3.MultipartBody;

public class Sign_upDTO implements Serializable {

    @SerializedName("university")
    @Expose
    private String schoolname;
    @SerializedName("major")
    @Expose
    private String schoolnum;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("nickname")
    @Expose
    private String name;
    @SerializedName("sex")
    @Expose
    private String sex;
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("location")
    @Expose
    private String region;
    @SerializedName("hobby")
    @Expose
    private String hobby;
/*
    @SerializedName("selfimage1")
    @Expose
    private MultipartBody.Part[] selfimage1;
    @SerializedName("file")
    @Expose
    private MultipartBody.Part studentcard;
    public MultipartBody.Part getStudentcard(){
        return studentcard;
    }
    public void setStudentcard(MultipartBody.Part studentcard){
        this.studentcard=studentcard;
    }

    public MultipartBody.Part[] getselfimage1(){
        return selfimage1;
    }
    public void setselfimage1(MultipartBody.Part[] selfimage1){
        this.selfimage1=selfimage1;
    }

*/
    public String getSchoolname(){
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }

    public String getSchoolnum() {
        return schoolnum;
    }

    public void setSchoolnum(String schoolnum) {
        this.schoolnum = schoolnum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

}
