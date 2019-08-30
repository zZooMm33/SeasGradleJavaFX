package storage.Oceans;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Oceans {
    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty description;

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public Oceans() {
        this.id = new SimpleIntegerProperty(0);
        this.name = new SimpleStringProperty("");
        this.description = new SimpleStringProperty("");
    }

    public Oceans(int id, String name, String description) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
    }


    /**
     * Метод для создания билдера
     *
     * @return
     */

    public static Oceans.Builder newBuilder() {
        return new Oceans().new Builder();
    }

    public class Builder{
        public Builder setName(String name) {
            Oceans.this.name.set(name);
            return this;
        }

        public Builder setDescription(String description) {

            Oceans.this.description.set(description);
            return this;
        }

        public Oceans Build() {
            Oceans oceans = new Oceans();

            oceans.setName(Oceans.this.name.get());
            oceans.setDescription(Oceans.this.description.get());

            return oceans;
        }
    }
}
