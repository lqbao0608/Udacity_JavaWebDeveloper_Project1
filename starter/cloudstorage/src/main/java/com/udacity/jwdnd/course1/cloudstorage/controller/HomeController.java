package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.dto.CredentialDTO;
import com.udacity.jwdnd.course1.cloudstorage.entity.File;
import com.udacity.jwdnd.course1.cloudstorage.entity.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final FileService fileService;
    private final NoteService noteService;
    private final CredentialService credentialService;

    public HomeController(
            FileService fileService,
            NoteService noteService,
            CredentialService credentialService
    ) {
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
    }

    @GetMapping()
    public String getHomePage(Model model) {

        List<File> files = fileService.getFileListByUserId();
        boolean filesEmpty = files.isEmpty();
        model.addAttribute("filesEmpty", filesEmpty);
        model.addAttribute(files);

        List<Note> notes = noteService.getNoteListByUserId();
        boolean notesEmpty = notes.isEmpty();
        model.addAttribute("notesEmpty", notesEmpty);
        model.addAttribute(notes);

        List<CredentialDTO> credentialDTOs = credentialService.getCredentialListByUserId();
        boolean credentialsEmpty = credentialDTOs.isEmpty();
        model.addAttribute("credentialsEmpty", credentialsEmpty);
        model.addAttribute(credentialDTOs);

        return "home";
    }

    @GetMapping("/result")
    public String loginView(@RequestParam("success") String success, Model model) {
        model.addAttribute("success", success);
        return "result";
    }

    @GetMapping("/errorUpload")
    public String errorUpload(@RequestParam("warning") String warning, Model model) {
        model.addAttribute("warning", warning);
        return "result";
    }

}
