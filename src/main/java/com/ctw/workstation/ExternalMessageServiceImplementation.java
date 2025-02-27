package com.ctw.workstation;

public class ExternalMessageServiceImplementation implements ExternalMessageService {
    @Override
    public String sayHelloFromOuterSpace() {
        return "Hello from outer space";
    }

    @Override
    public String sayHelloFromOuterSpace(String name) {
        return "Hello from outer space" + name;
    }
}
