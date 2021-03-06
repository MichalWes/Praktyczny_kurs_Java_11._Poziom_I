package pl.javaskills.creditapp.core.scoring;

import jdk.jshell.execution.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.javaskills.creditapp.core.bik.BikApi;
import pl.javaskills.creditapp.core.bik.ScoringRequest;
import pl.javaskills.creditapp.core.bik.ScoringResponse;
import pl.javaskills.creditapp.core.model.CreditApplication;
import pl.javaskills.creditapp.core.model.NaturalPerson;
import pl.javaskills.creditapp.core.model.Person;
import pl.javaskills.creditapp.core.model.SelfEmployed;

public class BikScoringCalculator implements ScoringCalculator {
    private static final Logger log = LoggerFactory.getLogger(BikScoringCalculator.class);

    private final BikApi bikApi;

    public BikScoringCalculator(BikApi bikApi) {
        this.bikApi = bikApi;
    }

    @Override
    public int calculate(CreditApplication creditApplication) {
        ScoringRequest request = new ScoringRequest();
        Person person = creditApplication.getPerson();

        if (person instanceof SelfEmployed) {
            request.setNip(((SelfEmployed) (person)).getNip());
        }
        if (person instanceof NaturalPerson) {
            request.setNip(((NaturalPerson) (person)).getPesel());
        }

        final ScoringResponse scoringResponse = bikApi.getScoring(request);
        int scoring = 100 * scoringResponse.getScoring() / 600;
        log.info("Bik scoring={}/600, scoring={}/100. {}", scoringResponse.getScoring(), scoring, ScoringUtils.getPointsString(scoring));
        return scoring;
    }
}
