package sg.edu.nus.iss.app.paf_day30assessment.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.iss.app.paf_day30assessment.models.Transfer;
import sg.edu.nus.iss.app.paf_day30assessment.repositories.AccountRepository;
import sg.edu.nus.iss.app.paf_day30assessment.util.FundTransferException;

@Service
public class FundsTransferService {
    @Autowired
    AccountRepository accountRepository;

    @Transactional(rollbackFor = FundTransferException.class, propagation = Propagation.REQUIRED)
    public String transferFunds(Transfer transfer) {

        String transactionId = UUID.randomUUID().toString().substring(0, 8);
        final float amount = transfer.getAmount();
        boolean withdrawStatus = accountRepository.withdrawById(transfer.getFrom().getAccount_id(), amount);
        boolean depositStatus = accountRepository.depositbyId(transfer.getTo().getAccount_id(), amount);

        if (!withdrawStatus || !depositStatus) {
            throw new FundTransferException("withdraw or deposit cannot be done");
        }

        return transactionId;
    }

}
