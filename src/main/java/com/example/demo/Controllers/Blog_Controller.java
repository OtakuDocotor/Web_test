package com.example.demo.Controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("/blog/{id}")
    public String Blog_Details(@PathVariable(value = "id") Long Id, Model model) {
        if(!_Article_Repository.existsById(Id))
        {
            return "redirect:/blog";
        }
        Optional <Article> Article1 = _Article_Repository.findById(Id);
        ArrayList<Article> res = new ArrayList<>();
        Article1.ifPresent(res::add);
        model.addAttribute("article", res);
        return "blogdetails";

    }

    @GetMapping("/blog/{id}/edit")
    public String Blog_edit(@PathVariable(value = "id") Long Id, Model model) {
        if(!_Article_Repository.existsById(Id))
        {
            return "redirect:/blog";
        }
        Optional <Article> Article1 = _Article_Repository.findById(Id);
        ArrayList<Article> res = new ArrayList<>();
        Article1.ifPresent(res::add);
        model.addAttribute("article", res);
        return "blogedit";

    }

    @PostMapping("/blog/{id}/edit")
    public String BlogEditPost(@RequestParam String title,@RequestParam String anons,@RequestParam String full_text, Model model,@PathVariable(value = "id") Long Id) {    
        Article article =_Article_Repository.findById(Id).orElseThrow();
        article.set_Title(title);
        article.set_Text(full_text);
        article.set_Anons(anons);
        _Article_Repository.save(article);
        return "redirect:/blog";
    }
    @PostMapping("/blog/{id}/delete")
    public String BlogDeletePost(Model model,@PathVariable(value = "id") Long Id) {    
        Article article =_Article_Repository.findById(Id).orElseThrow();
        _Article_Repository.delete(article);
        return "redirect:/blog";
    }
}
