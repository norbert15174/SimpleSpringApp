package pl.mandat.mandat.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String context;
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    private UserModel userModel;

    @ManyToOne(fetch = FetchType.EAGER)
    private Offenses offenses;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public Offenses getOffenses() {
        return offenses;
    }

    public void setOffenses(Offenses offenses) {
        this.offenses = offenses;
    }
}
