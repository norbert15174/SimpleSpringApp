package pl.mandat.mandat.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mandat.mandat.Model.Offenses;
import pl.mandat.mandat.Repository.OffensesRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OffensesService {

    private OffensesRepository offensesRepository;
    @Autowired
    public OffensesService(OffensesRepository offensesRepository) {
        this.offensesRepository = offensesRepository;
    }

    public int CountOffenses(){
        return offensesRepository.countAll();
    }
    public List<Offenses> findBetween(long first,long second){
        return offensesRepository.findOffensesByIdBetween(first,second);
    }
    public Optional<Offenses> findById(long id){
        return offensesRepository.findById(id);
    }
    public Optional<Offenses> FindById(long id){

        return offensesRepository.findById(id);

    }
    public List<Offenses> MatchTheQuery(String name,long from,long to){
        return offensesRepository.findOF(name,from,to);
    }
}
