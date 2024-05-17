package com.example.bookservice.retry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MessageController {

    Logger logger = LoggerFactory.getLogger(MessageController.class);

    int i = 0;
    long lastCallTime = 0l;
    long timeDifference = 0l;

    @GetMapping("/message")
    @Retryable(maxAttempts = 5, backoff = @Backoff(delay = 2000, multiplier = 2))
    
    public String getMessage(){
        timeDifference = System.currentTimeMillis() - lastCallTime;
        logger.info(" Đang gọi Api Lần :  "+ i++ + " Thời gian chờ : " + timeDifference + " ms");
        RestTemplate rt = new RestTemplate();
        lastCallTime = System.currentTimeMillis();
        rt.getForObject("http://localhost:9002/api/v1/employees", Object.class);
        return "Gọi Api thành công !";
    }

    @Recover
    public String getRecoveryMessage(){
        logger.info(" Không thể gọi Api !");
        return "Không thể gọi Api !";
    }
}