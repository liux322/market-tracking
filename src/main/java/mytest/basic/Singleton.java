package mytest.basic;

public class Singleton {

    private Singleton(){}

    private static class SingletonHolder {
        private static final Singleton intance = new Singleton();
    }

    public Singleton getInstance(){
        return SingletonHolder.intance;
    }
}
