package com.me.courses_selection.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 消息队列发送接口
 * June
 */
@Service
@Slf4j
public class MQSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息
     */
    public void sendCoursesChooseMessage(String message) {
        log.info("发送消息:" + message);
        rabbitTemplate.convertAndSend("CoursesChooseExchange",  "CoursesChoose.msg", message);
    }

    public void sendAddCoursesMessage(String message) {
        log.info("发送消息:" + message);
        rabbitTemplate.convertAndSend("AddCoursesExchange",  "AddCourses.msg", message);
    }
}
