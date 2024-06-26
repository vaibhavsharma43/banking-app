package com.Vaibhav.banking.repository;

import com.Vaibhav.banking.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository  extends JpaRepository<Account,Long> {
}
