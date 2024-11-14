package ee.ivkhkdev.factory;

import java.util.HashMap;
import java.util.Map;

public class Factory {
    private static Factory FACTORY = null;
    private static Configuration configuration;
    private Factory(){

    }
    public static Factory getInstance(Configuration configuration){
        Factory.configuration = configuration;
        if(FACTORY == null){
            Factory.FACTORY= new Factory();
        }
        return Factory.FACTORY;
    }
    public <T> T getObject(String name){
        return (T) this.configuration.getMap().get(name);
    }
}
