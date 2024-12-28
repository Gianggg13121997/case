package com.codegym.casemodule.controller;

import com.codegym.casemodule.model.AppRole;
import com.codegym.casemodule.model.AppUser;
import com.codegym.casemodule.model.ROLENAME;
import com.codegym.casemodule.service.IAppRoleService;
import com.codegym.casemodule.service.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private IAppUserService appUserService;

    @Autowired
    private IAppRoleService appRoleService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @ModelAttribute
    public Set<AppRole> getAppUser() {
        AppRole appRole = appRoleService.findByName(ROLENAME.ROLE_USER.toString());
        Set<AppRole> appRoleSet = new HashSet<>();
        appRoleSet.add(appRole);
        return appRoleSet;
    }


    @GetMapping
    public String register(Model model) {
        model.addAttribute("user", new AppUser());
        return "register";
    }

//    @PostMapping
//    public String register(AppUser user){
////        tao role mac dinh khi dang ky la user
//        user.setRoll(getAppUser());
//        //String newp = passwordEncoder.encode(user.getPassword());
//        //user.setPassword(newp);
////        luu vao trong db
//        appUserService.save(user);
//        return "redirect:/login";
//    }
//}

    @PostMapping
    public String register(AppUser user, Model model) {
        // Kiểm tra username không chứa ký tự đặc biệt
        if (!user.getUsername().matches("^[a-zA-Z0-9]+$")) {
            model.addAttribute("error", "Tên đăng nhập không được chứa ký tự đặc biệt!");
            model.addAttribute("user", user);
            return "register";
        }

        

        if (user.getPassword().length() < 6) {
            model.addAttribute("error", "Mật khẩu phải có ít nhất 6 ký tự!");
            model.addAttribute("user", user);
            return "register";
        }
        // Mã hóa mật khẩu
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        // Gán quyền mặc định là ROLE_USER
        user.setRoll(getAppUser());
        // Lưu vào cơ sở dữ liệu
        appUserService.save(user);

        return "redirect:/login";
    }
}
