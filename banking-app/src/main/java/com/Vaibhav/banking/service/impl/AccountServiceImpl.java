package com.Vaibhav.banking.service.impl;

import com.Vaibhav.banking.dto.AccountDto;
import com.Vaibhav.banking.entity.Account;
import com.Vaibhav.banking.mapper.AccountMapper;
import com.Vaibhav.banking.repository.AccountRepository;
import com.Vaibhav.banking.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto  createAccount(AccountDto accountDto) {
        Account account= AccountMapper.mapToAccount(accountDto);
     Account savedAccount=   accountRepository.save(account);

        return  AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountByID(Long id) {
       Account account= accountRepository
               .findById(id)
               .orElseThrow(()-> new RuntimeException("Account does not exist"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account= accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account does not exist"));
     double total=   account.getBalance()+amount;
     account.setBalance(total);
        accountRepository.save(account);
        return null;
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account= accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account does not exist"));
        if(account.getBalance()<amount){
            throw new RuntimeException("Insufficient amount");
        }
        double total=account.getBalance()-amount;
        account.setBalance(total);
     Account savedAccount=   accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts=accountRepository.findAll();
      return  accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account))
                .collect(Collectors.toList());

    }

    @Override
    public void deleteAccount(Long id) {
        Account account= accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account does not exist"));
        accountRepository.deleteById(id);

    }

}
