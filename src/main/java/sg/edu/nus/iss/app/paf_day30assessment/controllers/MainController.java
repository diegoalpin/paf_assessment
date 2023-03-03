package sg.edu.nus.iss.app.paf_day30assessment.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import sg.edu.nus.iss.app.paf_day30assessment.models.Account;
import sg.edu.nus.iss.app.paf_day30assessment.models.Transfer;
import sg.edu.nus.iss.app.paf_day30assessment.repositories.AccountRepository;

@Controller
public class MainController {

    @Autowired
    AccountRepository accountRepository;

    @GetMapping(path = "/")
    public String getTransferForm(Model model) {
        List<Account> accounts = accountRepository.findAll();
        model.addAttribute("accounts", accounts);
        model.addAttribute("transfer", new Transfer());
        return "form-new";
    }
}
