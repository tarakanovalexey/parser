package com.example.parser.domain;

import javax.persistence.*;

@Entity
public class Resumes {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private Long userId;

    private Long searchId;

    private String profession;
    private String payment;
    private String experience;
    private String age;

    @Column(name = "link", length=1000)
    private String link;

    public Resumes() {
    }

    public Resumes(Long userId, Long searchId, String profession, String payment, String experience, String age, String link) {
        this.userId = userId;
        this.searchId = searchId;
        this.profession = profession;
        this.payment = payment;
        this.experience = experience;
        this.age = age;
        this.link = link;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSearchId() {
        return searchId;
    }

    public void setSearchId(Long searchId) {
        this.searchId = searchId;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Long getId() {
        return id;
    }
}
