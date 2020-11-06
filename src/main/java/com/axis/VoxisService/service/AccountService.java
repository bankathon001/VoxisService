package com.axis.VoxisService.service;

import com.axis.VoxisService.enums.ValidatePinStatus;
import com.axis.VoxisService.request.ValidatePinRequest;
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
        List txn = Lists.newArrayList("Debited Rs 2000 at ATM on 1st November 2020",
                "Credited Rs 500 at UPI on 2nd November 2020",
                "Debited Rs 700 at UPI on 3rd November 2020",
                "Debited Rs 800 at UPI on 4th November 2020",
                "Credited Rs 900 at UPI on 5h November 2020");
        return txn;
    }

    public ValidatePinStatus validateDebitCard(@NotNull ValidatePinRequest validatePinRequest) {
        return ValidatePinStatus.SUCCESS;
    }
}
