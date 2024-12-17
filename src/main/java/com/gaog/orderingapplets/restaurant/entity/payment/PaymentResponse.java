package com.gaog.orderingapplets.restaurant.entity.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {


    /**
     * 构造函数
     *
     * @param outTradeNo
     * @param tradeNo
     */
    public PaymentResponse(String outTradeNo, String tradeNo) {
        this.outTradeNo = outTradeNo;
        this.tradeNo = tradeNo;
        this.status = "SUCCESS";
        this.errorMsg = "";
        this.channel = "Alipay";
        this.amount = BigDecimal.ZERO;
        this.createTime = new Date();
        this.payTime = new Date();
    }

    /**
     * 支付订单号
     */
    private String tradeNo;

    /**
     * 商户订单号
     */
    private String outTradeNo;

    /**
     * 支付二维码内容
     */
    private String qrCode;

    /**
     * 支付链接
     */
    private String payUrl;

    /**
     * 支付状态
     */
    private String status;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 支付渠道(alipay/wechat)
     */
    private String channel;

    /**
     * 支付金额
     */
    private BigDecimal amount;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 支付时间
     */
    private Date payTime;


} 