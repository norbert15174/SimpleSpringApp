package pl.mandat.mandat.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mandat.mandat.Model.Penalty;

public interface PenaltyRepository extends JpaRepository<Penalty,Long> {
}
