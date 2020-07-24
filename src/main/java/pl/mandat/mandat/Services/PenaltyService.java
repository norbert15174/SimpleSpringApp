package pl.mandat.mandat.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mandat.mandat.Repository.PenaltyRepository;

@Service
public class PenaltyService {
    private PenaltyRepository penaltyRepository;
    @Autowired
    public PenaltyService(PenaltyRepository penaltyRepository) {
        this.penaltyRepository = penaltyRepository;
    }



}
