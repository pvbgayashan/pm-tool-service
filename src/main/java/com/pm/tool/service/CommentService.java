package com.pm.tool.service;

import com.pm.tool.entity.Comment;
import com.pm.tool.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getCommentsByUploadId(Long id) {
        List<Comment> comments = new ArrayList<>();
        commentRepository.getCommentsByUploadId(id).forEach(comments::add);
        return comments;
    }

    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

}
