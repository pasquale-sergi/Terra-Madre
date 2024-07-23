package dev.pasq.terra_madre.utilities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShippingDetails {
    private String address;
    private String city;
    private String postalCode;
    private String country;
    private String shippingMethod;

}
