package com.draxter.draxter.Controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.draxter.draxter.Exception.InvalidTokenException;
import com.draxter.draxter.Exception.UnkownIdentifierException;
import com.draxter.draxter.Service.CustomerAccountService;
import com.draxter.draxter.data.ResetPasswordData;

@Controller
@RequestMapping("/password")
public class DraxterPasswordResetController {
    private static final String REDIRECT_LOGIN = "redirect:/iniciarSesion";
    private static final String MSG = "resetPasswordMsg";

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private CustomerAccountService customerAccountService;

    @PostMapping("/request")
    public String resetPassword(@ModelAttribute("forgotPassword") final ResetPasswordData forgotPasswordForm,
            RedirectAttributes redirAttr) {
        try {
            customerAccountService.forgottenPassword(forgotPasswordForm.getEmail());
        } catch (UnkownIdentifierException e) {
            // log the error
        }
        redirAttr.addFlashAttribute(MSG,
                messageSource.getMessage("user.forgotpwd.msg", null, LocaleContextHolder.getLocale()));
        return REDIRECT_LOGIN;
    }

    @GetMapping("/change")
    public String changePassword(@RequestParam(required = false) String token, final RedirectAttributes redirAttr,
            final Model model) {
        if (StringUtils.isEmpty(token)) {
            redirAttr.addFlashAttribute("tokenError",
                    messageSource.getMessage("user.registration.verification.missing.token", null,
                            LocaleContextHolder.getLocale()));
            return REDIRECT_LOGIN;
        }

        ResetPasswordData data = new ResetPasswordData();
        data.setToken(token);
        setResetPasswordForm(model, data);

        return "/emails/changePassword";
    }

    @PostMapping("/change")
    public String changePassword(final ResetPasswordData data, final Model model) {
        try {
            customerAccountService.updatePassword(data.getPassword(), data.getToken());
        } catch (InvalidTokenException | UnkownIdentifierException e) {
            // log error statement
            model.addAttribute("tokenError",
                    messageSource.getMessage("user.registration.verification.invalid.token", null,
                            LocaleContextHolder.getLocale()));

            return "/emails/changePassword";
        }
        model.addAttribute("passwordUpdateMsg",
                messageSource.getMessage("user.password.updated.msg", null, LocaleContextHolder.getLocale()));
        setResetPasswordForm(model, new ResetPasswordData());
        return "/emails/changePassword";
    }

    private void setResetPasswordForm(final Model model, ResetPasswordData data) {
        model.addAttribute("forgotPassword", data);
    }
}
