package com.example.bulletinboard.BulletinBoard.controller;

import com.example.bulletinboard.BulletinBoard.infrastracture.entity.Comment;
import com.example.bulletinboard.BulletinBoard.infrastracture.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/bbs")
@RequiredArgsConstructor
public class BbsController {

    private final CommentRepository commentRepository;

    @GetMapping("/")
    public String indexGet(Model model) {
        List<Comment> commentList = commentRepository.findAll();
        model.addAttribute("commentList", commentList);
        return "bbs/index";
    }

    @PostMapping("/")
    public String indexPost(@RequestParam("name") String name, @RequestParam("text") String text, Model model) {
        Comment comment = new Comment();
        comment.setName(name);
        comment.setText(text);
        commentRepository.save(comment);
        return indexGet(model);
    }
}
