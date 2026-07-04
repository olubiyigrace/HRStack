package com.hrstack.controllers;

import com.hrstack.security.JwtService;
import com.hrstack.services.UserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;


@Controller
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class InvitationController {
    private final JwtService jwtService;
    private final UserService userService;


    @GetMapping("/accept-invite")
    public void acceptInvite(@RequestParam String token, HttpServletResponse response) throws IOException {
        Claims claims = jwtService.validateWorkspaceInviteToken(token);
        userService.validateWorkspaceInvite(token, claims);
        response.sendRedirect("/api/v1/invited-users-login?token=" + token);
    }

    @GetMapping("/invited-users-login")
    public ModelAndView invitedUsersLoginPage(@RequestParam String token) {
        ModelAndView mv = new ModelAndView("invited-users-login");
        mv.addObject("token", token);
        return mv;
    }
}
