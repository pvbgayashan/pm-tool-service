package com.pm.tool.repository;

import com.pm.tool.entity.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> getCommentsByUploadId(Long id);

}
