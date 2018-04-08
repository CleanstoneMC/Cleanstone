package rocks.cleanstone.io.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class InGamePlayerData {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private UUID uuid;
    private GameMode gameMode;

    protected InGamePlayerData() {}

    public InGamePlayerData(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "InGamePlayerData[id=%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }
}
