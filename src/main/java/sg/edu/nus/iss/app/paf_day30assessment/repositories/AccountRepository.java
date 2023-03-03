package sg.edu.nus.iss.app.paf_day30assessment.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.app.paf_day30assessment.models.Account;

@Repository
public class AccountRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    final String SQL_SELECT_ALL = """
            Select * from accounts;
            """;

    final String SQL_SELECT_BY_ID = """
            Select * from accounts where account_id = ?
            """;

    final String SQL_DEPOSIT_BY_ID = """
            update accounts set balance = balance + ? where account_id = ?
            """;
    final String SQL_WITHDRAW_BY_ID = """
            update accounts set balance = balance - ? where account_id = ?
            """;

    public List<Account> findAll() {
        List<Account> results = jdbcTemplate.query(SQL_SELECT_ALL, BeanPropertyRowMapper.newInstance(Account.class));
        return results;
    }

    public Account findById(String id) {
        Account result = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, BeanPropertyRowMapper.newInstance(Account.class),
                id);
        return result;
    }

    public boolean withdrawById(String id, float amount) {
        return jdbcTemplate.update(SQL_WITHDRAW_BY_ID, amount, id) > 0;
    }

    public boolean depositbyId(String id, float amount) {
        return jdbcTemplate.update(SQL_DEPOSIT_BY_ID, amount, id) > 0;

    }
}
