package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.entity.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/credential")
public class CredentialController {
    private final CredentialService credentialService;

    public CredentialController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @PostMapping("/createOrUpdate")
    public String createOrUpdateCredential(@ModelAttribute Credential credential, RedirectAttributes redirectAttributes) {

        if (credential.getCredentialId() == null) {
            credentialService.createCredential(credential);
            redirectAttributes.addFlashAttribute("success", "Create credentials successful!");
        } else {
            credentialService.updateCredential(credential);
            return "redirect:/home/result?success=true";
        }
        return "redirect:/home";
    }

    @GetMapping("/delete/{credentialId}")
    public String deleteCredential(@PathVariable("credentialId") Integer credentialId, RedirectAttributes redirectAttrs) {

        if (!credentialService.deleteCredential(credentialId)) {
            redirectAttrs.addFlashAttribute("error", "An error occurred while deleting the credentials!");
            return "redirect:/home";
        }

        return "redirect:/home/result?success=true";
    }
}
