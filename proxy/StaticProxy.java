package com.project.jvm.proxy;

/**
 * 静态代理
 */
public class StaticProxy {

    public static void main(String[] args) {
        MySQLServiceRealProxy real = new MySQLServiceRealProxy();
        MySQLServiceProxy proxy = new MySQLServiceProxy(real);
        proxy.getName();
    }

    private static class MySQLServiceProxy implements MySQLServiceInterface {

        private MySQLServiceRealProxy target;

        MySQLServiceProxy(MySQLServiceRealProxy target) {
            this.target = target;
        }

        @Override
        public String getName() {
            System.out.println("before getName");
            String name = target.getName();
            System.out.println("after getName");
            return name;
        }
    }

    private static class MySQLServiceRealProxy implements MySQLServiceInterface{

        @Override
        public String getName() {
            System.out.println("this is sql");
            return "result";
        }
    }


    private interface MySQLServiceInterface{
        String getName();
    }
}
