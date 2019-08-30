package storage.Oceans;

import java.util.List;

public interface OceansDAO {

    public List<Oceans> getOceans();

    public boolean addOceans(Oceans oceans);

    public boolean changeOceans(Oceans oceans);

    public boolean deleteOceans(int id);
}
