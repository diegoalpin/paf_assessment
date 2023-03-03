package sg.edu.nus.iss.app.paf_day30assessment.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import sg.edu.nus.iss.app.paf_day30assessment.models.Account;
import sg.edu.nus.iss.app.paf_day30assessment.models.Transfer;
import sg.edu.nus.iss.app.paf_day30assessment.repositories.AccountRepository;
import sg.edu.nus.iss.app.paf_day30assessment.services.FundsTransferService;
import sg.edu.nus.iss.app.paf_day30assessment.services.LogAuditService;

@Controller
public class FundsTransferController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    FundsTransferService fundsTransferService;

    @Autowired
    LogAuditService logAuditService;

    @PostMapping(path = "/transfer")
    public String submitTransfer(@Valid Transfer transfer, BindingResult bindingResult, Model model) {
        String transactionId;

        transfer.setFrom(accountRepository.findById(transfer.getFromId()));
        transfer.setTo(accountRepository.findById(transfer.getToId()));

        System.out.println(transfer.toString());
        // C0
        if (transfer.getFrom() == null || transfer.getTo() == null) {
            bindingResult
                    .addError(new ObjectError("accountNotExistsError", "Either of From or To account does not exist"));
        }
        // C1
        if (transfer.getFrom().getAccount_id().length() != 10 || transfer.getTo().getAccount_id().length() != 10) {
            bindingResult.addError(new ObjectError("invalidAccountId", "Account Id must be of length 10"));
        }
        // C2
        if (transfer.getTo().getAccount_id().equals(transfer.getFrom().getAccount_id())) {
            bindingResult.addError(new ObjectError("sameAccountError", "From and To must be different account"));
        }
        // C5
        if (transfer.getAmount() > transfer.getFrom().getBalance()) {
            bindingResult.addError(
                    new ObjectError("balancenotenough", "Fund not sufficient " + transfer.getFrom().getBalance()));
        }
        // C3 and C4 is handled using validation Annotation
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors().toString());
            List<Account> accounts = accountRepository.findAll();
            model.addAttribute("accounts", accounts);
            model.addAttribute("transfer", transfer);
            return "form-new";
        }

        try {
            transactionId = fundsTransferService.transferFunds(transfer);
        } catch (Exception e) {
            bindingResult
                    .addError(new ObjectError("transactionError", e.getMessage()));
            List<Account> accounts = accountRepository.findAll();
            model.addAttribute("accounts", accounts);
            model.addAttribute("transfer", transfer);
            return "form-new";
        }
        // log to Redis and return the view
        logAuditService.saveTransactionById(transactionId, transfer);
        model.addAttribute("transactionId", transactionId);
        model.addAttribute("transfer", transfer);

        return "transfer-successful";
    }

}
