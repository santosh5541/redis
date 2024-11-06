package com.example.redis.controller.publisher;

import com.example.redis.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PublisherRestController {
    private final RedisTemplate redisTemplate;
    private final ChannelTopic channelTopic;

    @PostMapping("/publish")
    public String publish(@RequestBody Product product) {
        redisTemplate.convertAndSend(channelTopic.getTopic(), product);
        return "Product published successfully";
    }
}
