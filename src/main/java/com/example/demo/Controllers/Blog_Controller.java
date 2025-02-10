package com.example.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.Models.Article;
import com.example.demo.repo.Article_Repo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class Blog_Controller {

    @Autowired
    private Article_Repo _Article_Repository;

    @GetMapping("/blog")
    public String Blog_Main(Model model) {
        Iterable<Article> Articles=_Article_Repository.findAll();
        model.addAttribute("articles", Articles);
        return "blogmain";
    }

    @GetMapping("/blog/add")
    public String Blog_Add(Model model) {
        return "blogadd";
    }

    @PostMapping("/blog/add")
    public String BlogAddPost(@RequestParam String title,@RequestParam String anons,@RequestParam String full_text, Model model) {    
        Article article =new Article(title,anons,full_text);
        _Article_Repository.save(article);
        return "redirect:/blog";
    }
    
}
