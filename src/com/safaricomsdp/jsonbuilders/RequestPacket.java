package com.safaricomsdp.jsonbuilders;

public class RequestPacket {
    public String requestId;
    public String channel;
    public String operation;
    public RequestParam requestParam;

    public RequestPacket(String operation, RequestParam requestParam) {
        super();
        this.requestId = setRequestId();
        this.channel = setChannel("SMS");
        this.operation = operation;
        this.requestParam = requestParam;
    }

    public String setRequestId() {
        Double rand = Math.random() * 1000000000;
        return rand.toString();
    }

    public String setChannel(String channel) {
        return channel;
    }
}
