package com.gaog.orderingapplets.restaurant.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradeCreateRequest;
import com.alipay.api.response.AlipayTradeCreateResponse;
import com.gaog.orderingapplets.restaurant.entity.Order;
import com.gaog.orderingapplets.restaurant.entity.payment.PaymentResponse;
import com.gaog.orderingapplets.restaurant.exception.PaymentException;
import com.gaog.orderingapplets.restaurant.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能描述: 支付宝服务实施
 *
 * @CLASSNAME： AlipayServiceImpl
 * @VERSION: 1.0.0
 * @Date：2024/12/17
 * @Author： ZSJ
 */
@Service
public class AlipayServiceImpl implements PaymentService {
    @Autowired
    private AlipayClient alipayClient;

    /**
     * 创建支付订单
     *
     * @param order
     * @return
     */
    @Override
    public PaymentResponse createPayment(Order order) {
        AlipayTradeCreateRequest request = new AlipayTradeCreateRequest();
        request.setBizContent("{" +
                "\"out_trade_no\":\"" + order.getId() + "\"," +
                "\"total_amount\":\"" + order.getTotalAmount() + "\"," +
                "\"subject\":\"餐厅订单-" + order.getId() + "\"" +
                "}");
        try {
            AlipayTradeCreateResponse response = alipayClient.execute(request);
            return new PaymentResponse(response.getTradeNo(), response.getOutTradeNo());
        } catch (AlipayApiException e) {
            throw new PaymentException("创建支付订单失败");
        }
    }

    @Override
    public boolean verifyPayment(String paymentId, String paymentInfo) {
        return false;
    }
} 