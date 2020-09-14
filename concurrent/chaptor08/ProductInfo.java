package com.project.jvm.concurrent.chaptor08;

public class ProductInfo {

    private String msg;

    ProductInfo(String msg) {
        this.msg = msg;
        System.out.println(msg);
    }

    @Override
    public String toString() {
        return "ProductInfo{" +
                "msg='" + msg + '\'' +
                '}';
    }

    public static ProductInfo loadProductInfo() {
        return new ProductInfo("信息创建了");
    }
}
