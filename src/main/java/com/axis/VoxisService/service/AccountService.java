package com.axis.VoxisService.service;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class AccountService {



    public String getBalance(@NotNull @NotBlank String mobileNumber) {
        return mobileNumber.substring(0,4);
    }

    public List<String> getLastTXn(@NotNull @NotBlank String mobileNumber) {
        List txn = Lists.newArrayList("Recharge of 100 Rupees","Debit 200 Rupees at atm","Debit 300 Rupees at atm","Debit 1000 Rupees at Mutual fund","Debit 2000 Rupees at amazon");
        return txn;
    }
}
