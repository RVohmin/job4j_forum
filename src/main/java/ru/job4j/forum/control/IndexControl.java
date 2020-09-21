package ru.job4j.forum.control;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.forum.repository.MemStore;

@Controller
public class IndexControl {
    private final MemStore service;

    public IndexControl(MemStore service) {
        this.service = service;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        service.getAllTopics().forEach(System.out::println);
        model.addAttribute("topics", service.getAllTopics());
        model.addAttribute("username",
                SecurityContextHolder.getContext().getAuthentication().getName());
        return "index";
    }
//
//    @GetMapping("/edit")
//    public String newTheme() {
//        return "edit";
//    }
//
//    @PostMapping("/newPost")
//    public String createTheme(Model model) {
//        String theme = (String) model.getAttribute("name");
//        String describe = (String) model.getAttribute("describe");
//
//        posts.createPost(new Post(theme, describe, Calendar.getInstance()));
//        return "redirect:/";
//    }
//
//    @GetMapping("/post")
//    public String showPost(Model model) {
////        model.addAttribute("theme", theme);
////        model.addAttribute("posts", posts.getAll().get(theme)); //return List<Post>
//        return "post";
//    }


}
