package pl.mandat.mandat.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.mandat.mandat.Model.Offenses;

import java.util.List;

@Repository
public interface OffensesRepository extends JpaRepository<Offenses,Long> {

    List<Offenses> findOffensesByIdBetween(long first, long second);

    @Query("SELECT c FROM Offenses c join c.penaltyList pl where c.Offense LIKE %:name% AND pl.offensePrice >= :from AND pl.offensePrice<= :to")
    List<Offenses> findOF(@Param("name") String name,@Param("from") long from, @Param("to") long to);


    @Query("SELECT count(c) FROM Offenses c")
    int countAll();

}
