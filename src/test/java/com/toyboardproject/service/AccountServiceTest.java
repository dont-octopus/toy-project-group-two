package com.toyboardproject.service;

import com.toyboardproject.dto.AccountRequestDto;
import com.toyboardproject.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;


@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @InjectMocks
    AccountService accountService;

    @Mock
    AccountRepository accountRepository;

    @Test
    void createAccount() {
        AccountRequestDto dto = AccountRequestDto.builder()
                .userId("test")
                .userPassword("test")
                .userNickname("test")
                .userPhoneNum("test").build();
        accountService.createAccount(dto);
        then(accountRepository).should().save(any());
    }

    @Test
    void deleteAccountById() {
        given(accountRepository.existsById(any())).willReturn(true);
        accountService.deleteAccountById(anyLong());
        then(accountRepository).should().deleteById(any());
    }

    @Test
    void updateAccountById() {
    }

    @Test
    void checkExistUserId() {
        given(accountRepository.existsByUserId(any())).willReturn(true);
        accountService.checkExistUserId(anyString());
        then(accountRepository).should().existsByUserId(any());
    }
}