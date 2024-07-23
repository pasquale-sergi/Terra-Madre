package dev.pasq.terra_madre.utilities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDetails {

    private String paymentMethod;
    private String cardNumber;
    private String expiryDate;
    private String cvv;
}
