package sg.edu.nus.iss.app.paf_day30assessment.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.app.paf_day30assessment.models.Transaction;

@Repository
public class LogAuditRepo {
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    public void saveTransaction(Transaction transaction) {
        redisTemplate.opsForValue().set(transaction.getTransactionId(), Transaction.toJson(transaction).toString());

    }
}
