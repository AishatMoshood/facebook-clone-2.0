package com.aishatmoshood.facebookclone.controllers;

import com.aishatmoshood.facebookclone.dto.commentdto.CreateCommentDto;
import com.aishatmoshood.facebookclone.entity.Comment;
import com.aishatmoshood.facebookclone.entity.Post;
import com.aishatmoshood.facebookclone.exceptions.EmailNotValidException;
import com.aishatmoshood.facebookclone.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final HttpSession httpSession;

    @PostMapping("/create/comment/{id}")
    public String createComment(@PathVariable Long id, @ModelAttribute CreateCommentDto createCommentDto) throws EmailNotValidException {
        commentService.createComment(id,createCommentDto);
        return "redirect:/feed";
    }

    @GetMapping("/comment/update/{id}")
    public String updateCommentForm(@PathVariable Long id, Model model){
       Comment comment = commentService.getCommentById(id);
        model.addAttribute("commentById",comment);
        return "edit_comment";
    }

    @PostMapping("/comment/{id}")
    public String updateComment(@PathVariable Long id, @ModelAttribute("commentById") Comment comment, Model model){
        commentService.updateComment(id, comment);
        model.addAttribute("updateCommentSuccess","Comment edited successfully");
        return "redirect:/feed";
    }

    @GetMapping("/comment/delete/{id}")
    public String deleteComment(@PathVariable Long id, Model model){
        commentService.deleteComment(id);
        model.addAttribute("deleteCommentSuccess","Comment deleted successfully");
        return "redirect:/feed";
    }

}
