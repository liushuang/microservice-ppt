package org.newit.springboot.sayhello;

public class HelloService {

    private String msg;

    public String sayHello(){
        return "hello, " + msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
