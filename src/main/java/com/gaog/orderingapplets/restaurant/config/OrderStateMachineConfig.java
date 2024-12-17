package com.gaog.orderingapplets.restaurant.config;

import com.gaog.orderingapplets.restaurant.enums.OrderStateChangeEvent;
import com.gaog.orderingapplets.restaurant.enums.OrderStatus;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

@Configuration
@EnableStateMachine
public class OrderStateMachineConfig extends StateMachineConfigurerAdapter<OrderStatus, OrderStateChangeEvent> {
    @Override
    public void configure(StateMachineStateConfigurer<OrderStatus, OrderStateChangeEvent> states) throws Exception {
        states.withStates()
                .initial(OrderStatus.PENDING)
                .states(EnumSet.allOf(OrderStatus.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStatus, OrderStateChangeEvent> transitions) throws Exception {
        transitions
                .withExternal()
                .source(OrderStatus.PENDING).target(OrderStatus.PAID)
                .event(OrderStateChangeEvent.PAYMENT_SUCCESS)
                .and()
                .withExternal()
                .source(OrderStatus.PAID).target(OrderStatus.PREPARING)
                .event(OrderStateChangeEvent.START_PREPARING);
        // ... 其他状态转换
    }
} 