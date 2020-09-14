package com.project.jvm.designpattern;

public class ComponentFactory {

    private String name;
    private String kind;

    ComponentFactory factory(String name,String kind) {
        if ("car".equals(kind)) {
            return new Car();
        } else if ("PC".equals(kind)) {
            return new PC();
        }else {
          return new Component();
        }
    }
    class Car extends ComponentFactory {

    }

    class PC extends ComponentFactory {

    }
    class Component extends ComponentFactory {

    }
}

