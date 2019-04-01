package com.example.parser.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ParseURL {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private Long userId;
    private String ageFrom;
    private String ageTo;
    private String salaryFrom;
    private String salaryTo;
    private String city;
    private String foundResumes;
    private Date date;

    @Column(name = "urls", length=1000)
    private String URL;

    public ParseURL() {
    }

    public ParseURL(String ageFrom, String ageTo, String salaryFrom, String salaryTo, String city) {
        this.ageFrom = ageFrom;
        this.ageTo = ageTo;
        this.salaryFrom = salaryFrom;
        this.salaryTo = salaryTo;
        this.city = city;
    }

    public String getAgeFrom() {
        return ageFrom;
    }

    public void setAgeFrom(String ageFrom) {
        this.ageFrom = ageFrom;
    }

    public String getAgeTo() {
        return ageTo;
    }

    public void setAgeTo(String ageTo) {
        this.ageTo = ageTo;
    }

    public String getSalaryFrom() {
        return salaryFrom;
    }

    public void setSalaryFrom(String salaryFrom) {
        this.salaryFrom = salaryFrom;
    }

    public String getSalaryTo() {
        return salaryTo;
    }

    public void setSalaryTo(String salaryTo) {
        this.salaryTo = salaryTo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFoundResumes() {
        return foundResumes;
    }

    public void setFoundResumes(String foundResumes) {
        this.foundResumes = foundResumes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }
}
