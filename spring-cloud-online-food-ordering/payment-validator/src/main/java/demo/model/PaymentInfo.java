package demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentInfo {

    private String cardNumber;
    private String expiration;
    private String code;
    private String orderId;
}

// {"cardNumber":"1234123412341234","expiration":"02/2012","code":"893","orderId":"orderId1"}
