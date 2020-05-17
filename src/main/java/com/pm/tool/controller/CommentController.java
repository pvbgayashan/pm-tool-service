package com.pm.tool.controller;

import com.pm.tool.entity.Comment;
import com.pm.tool.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/get-by-upload-id/{id}")
    public List<Comment> getCommentsByUploadId(@PathVariable Long id) {
        return commentService.getCommentsByUploadId(id);
    }

    @PostMapping("/save")
    public void saveComment(@RequestBody Comment comment) {
        commentService.saveComment(comment);
    }

}
