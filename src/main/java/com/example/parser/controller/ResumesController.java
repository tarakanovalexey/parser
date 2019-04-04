package com.example.parser.controller;

import com.example.parser.domain.ParseURL;
import com.example.parser.domain.Resumes;
import com.example.parser.domain.User;
import com.example.parser.repos.ResumesRepo;
import com.example.parser.repos.URLRepo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Map;

@Controller
public class ResumesController {
    @Autowired
    private URLRepo urlRepo;

    @Autowired
    private ResumesRepo resumesRepo;

    @PostMapping("/saveresumes")
    public String add(
    @RequestParam(required = false, defaultValue = "") String keyword,
    @RequestParam(required = false, defaultValue = "") String ageFrom,
    @RequestParam(required = false, defaultValue = "") String ageTo,
    @RequestParam(required = false, defaultValue = "") String salaryFrom,
    @RequestParam(required = false, defaultValue = "") String salaryTo,
    @RequestParam(required = false, defaultValue = "") String city,
    @AuthenticationPrincipal User user,
    Map<String, Object> model
    ) throws IOException {
        //superjob
        keyword = keyword.replaceAll(" ", "+");
        int page = 0;
        boolean more = true;
        ParseURL geturl = new ParseURL();
        geturl.setKeyword(keyword);
        geturl.setDate(new Date());
        geturl.setCity(city);
        geturl.setSalaryTo(salaryTo);
        geturl.setSalaryFrom(salaryFrom);
        geturl.setNo_agreement(1);
        geturl.setAgeTo(ageTo);
        geturl.setAgeFrom(ageFrom);
        geturl.setUserId(user.getId());
        urlRepo.save(geturl);

        Iterable<ParseURL> urls = urlRepo.findByUserId(user.getId());

        model.put("urls", urls);
        while (more) {
            String url = geturl.SuperJobURLByPage(page);
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            int responseCode = con.getResponseCode();

            /*
            System.out.println("\nSending 'GET' request to URL : " + "\"" + url + "\"");
            System.out.println("Response Code : " + responseCode);*/
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "UTF-8"));
            StringBuffer response = new StringBuffer();
            JSONObject objectJson = new JSONObject(in.readLine());
            JSONObject objJS;
            int total = Integer.parseInt(objectJson.get("total").toString());
          //  System.out.println("Всего найдено вакансий по запросу: " + total);

            for (int i = 0; i < objectJson.getJSONArray("objects").length(); i++) {
                objJS = objectJson.getJSONArray("objects").getJSONObject(i);
                Resumes resume = new Resumes(
                        user.getId(),
                        geturl.getId(),
                        objJS.get("profession").toString(),
                        objJS.get("payment").toString(),
                        objJS.get("experience_month_count").toString(),
                        objJS.get("age").toString(),
                        objJS.get("link").toString()
                );

                resumesRepo.save(resume);

               /* System.out.println("UserID: " + objJS.get("id_user")
                        + ", должность: " + objJS.get("profession")
                        + ", желаемая зп: " + objJS.get("payment")
                        + ", стаж: " + objJS.get("experience_month_count") + " мес."
                        + ", возраст: " + objJS.get("age")
                        + ", url: " + objJS.get("link")
                );*/
            }

            if (page >= total / 100)
                more = false;
            in.close();
            System.out.println("Страница: " + page + ", total:" + total);
            page++;
        }

        return "redirect:/parser";
    }
}
