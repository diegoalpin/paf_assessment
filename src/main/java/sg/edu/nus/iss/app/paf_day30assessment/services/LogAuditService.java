package sg.edu.nus.iss.app.paf_day30assessment.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.app.paf_day30assessment.models.Transaction;
import sg.edu.nus.iss.app.paf_day30assessment.models.Transfer;
import sg.edu.nus.iss.app.paf_day30assessment.repositories.LogAuditRepo;

@Service
public class LogAuditService {

    @Autowired
    LogAuditRepo logAuditRepo;

    public void saveTransactionById(String transactionId, Transfer transfer) {

        Transaction transaction = new Transaction();
        transaction.setFrom_account(transfer.getFrom().getAccount_id());
        transaction.setTo_account(transfer.getTo().getAccount_id());
        transaction.setTransactionId(transactionId);
        transaction.setAmount(transfer.getAmount());
        transaction.setDate(LocalDate.now().toString());

        logAuditRepo.saveTransaction(transaction);
    }
}
