package pl.mandat.mandat.Services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mandat.mandat.Model.Comment;
import pl.mandat.mandat.Repository.CommentRepository;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void addComment(Comment comment){
        commentRepository.save(comment);
    }

    public List<Comment> findComment(long id){
        return commentRepository.findCommentsByOffenses_Id(id);
    }
}
