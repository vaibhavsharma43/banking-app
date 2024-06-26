package com.Vaibhav.banking.service;

import com.Vaibhav.banking.dto.AccountDto;

import java.util.List;


public interface AccountService {
 AccountDto  createAccount(AccountDto accountDto);
 AccountDto getAccountByID(Long id);
 AccountDto deposit(Long id,double amount);
 AccountDto withdraw(Long id,double amount);
 List <AccountDto> getAllAccounts();
 void deleteAccount(Long id);
}
