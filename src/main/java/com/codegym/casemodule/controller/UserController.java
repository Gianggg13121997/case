package com.codegym.casemodule.controller;

import com.codegym.casemodule.action.GetUsername;
import com.codegym.casemodule.model.AppUser;
import com.codegym.casemodule.service.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.context.SecurityContextHolder;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IAppUserService appUserService;
    @GetMapping()
    public ModelAndView index(){
        Iterable<AppUser> appUsers = appUserService.findAll();
        ModelAndView m = new ModelAndView("/product/list");
        m.addObject("list", appUsers);
        return m;
    }






}