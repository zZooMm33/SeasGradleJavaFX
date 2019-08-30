package storage.Oceans;

import utils.PropReader;

public class OceansFactory{

    public OceansDAO factoryMethod(){

        if (PropReader.getVal("storageType").equals("databaseSQLite") || PropReader.getVal("storageType").equals("databaseH2"))
            return new OceansDataBase();
        else if (PropReader.getVal("storageType").equals(".txt"))
            return new OceansTxt();
        else
            return null;

    }
}
