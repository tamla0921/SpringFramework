package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring.DuplicateMemberException;
import spring.MemberRegisterService;
import spring.RegisterRequest;
import spring.WrongIdPasswordException;

@Controller
public class RegisterController {
    
    private MemberRegisterService memberRegisterService;
    
    public void setMemberRegisterService(MemberRegisterService memberRegisterService) {
        this.memberRegisterService = memberRegisterService;
    }

    @RequestMapping("/register/step1")
    public String handlerStep1() {
        return "register/step1";
    }
    
    @PostMapping("/register/step2")
    public String handleStep2(@RequestParam(value = "agree", defaultValue = "false") Boolean agree, @ModelAttribute("formData") RegisterRequest registerRequest) {
        if (!agree) {
            return "register/step1";
        }
        
        return "register/step2";
    }
    
    @GetMapping("/register/step2")
    public String handleStep2Get() {
        
        return "redirect:/register/step1";
        // return "step2";
        // return "http://localhost:13216/sp5-chap11/register/step1";
    }
    
    @PostMapping("/register/step3")
    public String handleStep3(@ModelAttribute("formData") RegisterRequest regReq) {
        try {
            memberRegisterService.regist(regReq);
            return "register/step3";
        } catch (DuplicateMemberException e) {
            return "register/step2";
        } catch (WrongIdPasswordException e) {
            return "register/step2";
        }
    }
}
