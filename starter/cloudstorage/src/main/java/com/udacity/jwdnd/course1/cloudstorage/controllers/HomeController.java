package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private FileService fileService;

    @Autowired
    private NoteService noteService;

    @Autowired
    private EncryptionService encryptionService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String homeView(Model model, RedirectAttributes redirectAttributes, Authentication authentication) {
        String username = authentication.getName();
        User user = userService.getUser(username);

        if (user == null || username == null) {
            redirectAttributes.addFlashAttribute("errorMessage","Please login first. Thanks!");
            return "redirect:/login";
        } else {
            int userId = user.getUserId();
            model.addAttribute("fileslist", fileService.getAllFiles(userId));
            model.addAttribute("noteslist", noteService.getAllNotes(userId));
            model.addAttribute("credentialslist", credentialService.getCredentials(userId));
            model.addAttribute("encryptionService", encryptionService);
            return "home";
        }
    }


}
