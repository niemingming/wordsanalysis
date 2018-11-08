package com.nmm.study.word.mode;

import lombok.Data;

@Data
public class OrderInfo {
    //姓名
    private String username;
    //商品
    private String product;
    //发货时间
    private String sendTime;
    //订单编号
    private String orderNo;
    //创建时间
    private String createTime;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (this.username != null) {
            return;
        }
        this.username = username;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        if (this.product != null) {
            return;
        }
        this.product = product;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        if (this.sendTime == null)
            this.sendTime = sendTime;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        if (this.createTime == null)
            this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "收货人='" + username + '\'' +
                "\r\n 商品='" + product + '\'' +
                "\r\n 发货时间='" + sendTime + '\'' +
                "\r\n 订单编号='" + orderNo + '\'' +
                "\r\n 创建时间='" + createTime + '\'' +
                '}';
    }
}
