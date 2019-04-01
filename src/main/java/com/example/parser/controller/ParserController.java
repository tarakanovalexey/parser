package com.example.parser.controller;

import com.example.parser.domain.ParseURL;
import com.example.parser.domain.User;
import com.example.parser.repos.URLRepo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Controller
public class ParserController {
    @Autowired
    private URLRepo urlRepo;

    @GetMapping("/parser")
    public String webparser(
                @AuthenticationPrincipal User user,
                Map<String, Object> model
    ){
            Iterable<ParseURL> urls = urlRepo.findByUserId(user.getId());
            model.put("urls", urls);
            return "webparser";
        }

    @PostMapping("/parseit")
    public String add(
            @RequestParam(required = false, defaultValue = "") String ageFrom,
            @RequestParam(required = false, defaultValue = "") String ageTo,
            @RequestParam(required = false, defaultValue = "") String salaryFrom,
            @RequestParam(required = false, defaultValue = "") String salaryTo,
            @RequestParam String city,
            @AuthenticationPrincipal User user,
            Map<String, Object> model
    ){
        ParseURL url = new ParseURL(ageFrom, ageTo, salaryFrom, salaryTo, city);
        url.setDate(new Date());
        url.setURL("https://hh.ru/search/" +
                "resume" +
                "?age_from="+ageFrom+
                "&age_to="+ageTo+
                "&area="+city +
                "&clusters=true" +
                "&exp_period=all_time" +
                "&label=only_with_age" +
                "&label=only_with_salary" +
                "&logic=normal" +
                "&no_magic=false" +
                "&order_by=relevance" +
                "&pos=full_text" +
                "&text=" +
                "&salary_from="+salaryFrom +
                "&salary_to="+salaryTo +
                "&from=cluster_salary");

        url.setUserId(user.getId());
        try {
        Document doc = Jsoup.connect(url.getURL())
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .get();

        url.setFoundResumes(doc.getElementsByTag("h1").text());

        /*
        https://www.superjob.ru/resume/search_resume.html
        ?t=4 (город)
        &payment_no_agreement=1
        &sort_order=ORDER+BY+resume1.rank+DESC
        &old2=35
        &old1=30
        &detail_search=1
        &c=
        &o=
        &sbmit=1
        &paymentfrom=60000
        &paymentto=60000
         */

        urlRepo.save(url);

        Iterable<ParseURL> urls = urlRepo.findByUserId(user.getId());

        model.put("urls", urls);

        } catch (IOException e) {
        }
        return "redirect:/parser";
    }

    @PostMapping("/delete")
    public String delete(
            @RequestParam ParseURL thisid
    ){
        urlRepo.delete(thisid);

    return "redirect:/parser";
    }

    @PostMapping("/saveexcel")
    public String saveexcel(
            @RequestParam ParseURL thisid
    ){

        return "redirect:/parser";
    }
}
