package pl.mandat.mandat.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.model.IProcessingInstruction;
import pl.mandat.mandat.Model.Comment;
import pl.mandat.mandat.Model.Offenses;
import pl.mandat.mandat.Model.UserModel;
import pl.mandat.mandat.Repository.CommentRepository;
import pl.mandat.mandat.Services.CommentService;
import pl.mandat.mandat.Services.OffensesService;
import pl.mandat.mandat.Services.WebUserService;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

@Controller
public class WebController {


    private WebUserService webUserService;
    private OffensesService offensesService;
    private CommentService commentService;
    @Autowired
    private CommentRepository commentRepository;

    public WebController(WebUserService webUserService, OffensesService offensesService, CommentService commentService) {
        this.webUserService = webUserService;
        this.offensesService = offensesService;
        this.commentService = commentService;
    }

    @GetMapping("/Mandaty")
    public String Mandat(Model model, @RequestParam(defaultValue = "1") int a){

        int table[] = new int[(int)(offensesService.CountOffenses()/20)+1];
        for (int i=0;i<table.length;i++){
            table[i] = i+1;
        }
        model.addAttribute("strona",table);
        model.addAttribute("mandat",offensesService.findBetween((a-1)*20,(a)*20));




        return "Mandaty";
    }
    @GetMapping("/Mandaty/{id}")
    public String CheckMandaty(@PathVariable() long id, Model model){

        model.addAttribute("mandat",offensesService.FindById(id));
        model.addAttribute("comment", commentService.findComment(id));
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("id",id);


        return "MandatyInfo";
    }
    @PostMapping("/Mandaty/addComment")
    public String addComment(@RequestParam String name, @RequestParam String context, @RequestParam String id){
        Comment comment = new Comment();
        comment.setDate(new Date());
        comment.setContext(context);
        comment.setOffenses(offensesService.FindById(Long.parseLong(id)).get());
        comment.setUserModel((UserModel)webUserService.loadUserByUsername(name));
        commentService.addComment(comment);
        return "redirect:/Mandaty/" + id;
    }


    @GetMapping("/login")
    String login(){
        return "login";
    }

    @GetMapping("/register")
    String register(Model model){
        model.addAttribute("user",new UserModel());
        return "register";
    }

    @PostMapping("/register")
    String addUser(@ModelAttribute @Valid UserModel userModel){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        webUserService.saveUser(userModel,passwordEncoder);

        return "/login";
    }

    @PostMapping("/Mandaty")
    String FindPentalys(Model model, @RequestParam String nameSearch, long from, long to ){
        if(nameSearch == "" && from == 0 && to == 1000) {
            return "redirect:/Mandaty";
        }
        model.addAttribute("mandat", offensesService.MatchTheQuery(nameSearch, from, to));
        return "/Mandaty";
    }
}
