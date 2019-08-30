package storage;

import storage.Oceans.OceansDAO;
import storage.Oceans.OceansFactory;

public class StorageSingleton {

    private static OceansDAO singletonOceans = null;

    public static OceansDAO getSingletonOceans(){
        if (singletonOceans == null){
            singletonOceans = new OceansFactory().factoryMethod();
            return singletonOceans;
        }
        else
            return singletonOceans;
    }
}
