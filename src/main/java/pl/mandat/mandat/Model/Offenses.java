package pl.mandat.mandat.Model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Offenses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public List<Penalty> getPenaltyList() {
        return penaltyList;
    }

    public void setPenaltyList(List<Penalty> penaltyList) {
        this.penaltyList = penaltyList;
    }

    private String Offense;

    @OneToMany(mappedBy = "offense" , cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Penalty> penaltyList = new ArrayList<>();


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public void addPenaltyToList(Penalty penalty){
        penalty.setOffense(this);
        this.penaltyList.add(penalty);
    }


    public String getOffense() {
        return Offense;
    }

    public void setOffense(String offense) {
        Offense = offense;
    }
}
