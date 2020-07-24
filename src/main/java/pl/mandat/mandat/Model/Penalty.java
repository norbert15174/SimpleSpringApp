package pl.mandat.mandat.Model;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.*;

@Entity
public class Penalty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long offensePrice;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOffensePrice() {
        return offensePrice;
    }

    public void setOffensePrice(long offensePrice) {
        this.offensePrice = offensePrice;
    }

    public Offenses getOffense() {
        return offense;
    }

    public void setOffense(Offenses offense) {
        this.offense = offense;
    }

    @ManyToOne
    @JoinColumn(name = "id_offense")
    private Offenses offense;


}
