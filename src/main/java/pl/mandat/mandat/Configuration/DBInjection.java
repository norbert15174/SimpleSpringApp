package pl.mandat.mandat.Configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import pl.mandat.mandat.Model.Offenses;
import pl.mandat.mandat.Model.Penalty;
import pl.mandat.mandat.Repository.OffensesRepository;
import pl.mandat.mandat.Repository.PenaltyRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Configuration
@RequiredArgsConstructor
public class DBInjection{

    private OffensesRepository offensesRepository;
    private PenaltyRepository penaltyRepository;

    @Autowired
    public DBInjection(OffensesRepository offensesRepository, PenaltyRepository penaltyRepository) throws FileNotFoundException {
        this.offensesRepository = offensesRepository;
        this.penaltyRepository = penaltyRepository;



        List<Offenses> offenses = new ArrayList<>();

        File file = new File("src\\main\\resources\\static\\Mandat.txt");
        Scanner in = new Scanner(file);
        while (in.hasNextLine()) {
            String text = in.nextLine();
            String splitedText[] = text.split("\t");
            String splitedTextMandate[] = splitedText[0].split("[.]",7);
            String splitedMandatePrice[] = splitedText[1].split(",");




            Offenses offensesOne = new Offenses();
            offensesOne.setOffense(splitedTextMandate[1]);
            for (String parseLongMandate: splitedMandatePrice) {
                Penalty penaltySave = new Penalty();
                penaltySave.setOffensePrice(Long.parseLong(parseLongMandate));
                offensesOne.addPenaltyToList(penaltySave);

            }
            offensesRepository.save(offensesOne);

        }

    }
}
