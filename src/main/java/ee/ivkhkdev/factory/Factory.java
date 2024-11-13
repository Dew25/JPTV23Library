package ee.ivkhkdev.factory;

public class Factory {
    private static final Factory FACTORY = null;
    private static Configurator configurator;

    private Factory(){}
    public static Factory getInstance(Configurator configurator){
        Factory.configurator = configurator;
        if(FACTORY == null){
            new Factory();
        }
        return FACTORY;
    }
    public <T> T getObject(String name){
        return (T) configurator.getMap().get(name);
    };
}
