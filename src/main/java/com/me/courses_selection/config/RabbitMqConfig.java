package com.me.courses_selection.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 消息队列定义&&交换机绑定
 * June
 */
@Configuration
public class RabbitMqConfig {

    private static final String QUEUE_CoursesChoose = "CoursesChooseQueue";
    private static final String EXCHANGE_CoursesChoose = "CoursesChooseExchange";
    private static final String QUEUE_AddCourses = "AddCoursesQueue";
    private static final String EXCHANGE_AddCourses = "AddCoursesExchange";

    /**
     * 获取队列
     */
    @Bean
    public Queue Queue_CoursesChoose() {
        return new Queue(QUEUE_CoursesChoose);
    }
    @Bean
    public Queue Queue_AddCourses() {
        return new Queue(QUEUE_AddCourses);
    }
    /**
     * 获取交换机
     */
    @Bean
    public TopicExchange TopicExchange_CoursesChoose() {return new TopicExchange(EXCHANGE_CoursesChoose);}
    @Bean
    public TopicExchange TopicExchange_AddCourses() {return new TopicExchange(EXCHANGE_AddCourses);}

    /**
     * 绑定交换机
     */
    @Bean
    public Binding Binding_OptCoursesChoose() {
        return BindingBuilder.bind(Queue_CoursesChoose()).to(TopicExchange_CoursesChoose()).with("CoursesChoose.#");
    }
    @Bean
    public Binding Binding_AddOptCourses() {
        return BindingBuilder.bind(Queue_AddCourses()).to(TopicExchange_AddCourses()).with("AddCourses.#");
    }
}
