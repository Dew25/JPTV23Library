package ee.ivkhkdev.factory;

public class Factory {
    private static final Factory FACTORY = null;
    private final Configurator configurator;

    private Factory(){
        this.configurator = new JavaConfigurator();
    }
    public static Factory getInstance(){
        if(FACTORY == null){
            new Factory();
        }
        return FACTORY;
    }
    public <T> T getObject(String name){
        return (T) configurator.getMap().get(name);
    };
}
