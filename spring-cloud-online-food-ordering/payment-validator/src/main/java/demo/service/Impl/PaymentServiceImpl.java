package demo.service.Impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import demo.model.OrderInfo;
import demo.model.OrderStatus;
import demo.model.PaymentInfo;
import demo.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.mockito.internal.matchers.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public boolean processPaymentInfo(PaymentInfo paymentInfo) {

        String prefix = "http://localhost:9002/restaurant/order/";
        String uri = prefix + paymentInfo.getOrderId();
        boolean isValid = validateInfo(paymentInfo);
        if (isValid) {
            log.info("Payment validated, calling order-service API to show confirmed info");

            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setOrderStatus(OrderStatus.paid);

            HttpEntity<OrderInfo> entity = new HttpEntity<>(orderInfo);
            this.restTemplate.put("http://localhost:9002/restaurant/order/{orderId}", entity, paymentInfo.getOrderId());
        }
        return isValid;
    }

    @Override
    public boolean validateInfo(PaymentInfo paymentInfo) {
        int m = 3;
        boolean isValid = (((paymentInfo.getCardNumber() + paymentInfo.getExpiration()).hashCode() - paymentInfo.getCode().hashCode())%m == 0);
        return isValid;
    }



}
