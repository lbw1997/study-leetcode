package com.project.jvm.netty;

import java.io.Serializable;

public class SubscribeReq implements Serializable {

    private static final long serialVersionUID = 1L;

    private int subReqID;
    private String username;
    private String productName;
    private String phoneName;
    private String address;

    public int getSubReqID() {
        return subReqID;
    }

    public void setSubReqID(int subReqID) {
        this.subReqID = subReqID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SubscribeReq{" +
                "subReqID=" + subReqID +
                ", username='" + username + '\'' +
                ", productName='" + productName + '\'' +
                ", phoneName='" + phoneName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

