package pl.javaskills.creditapp.core.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.javaskills.creditapp.core.CreditApplicationService;

import java.util.UUID;

public class CreditApplication {

    private static final Logger log = LoggerFactory.getLogger(CreditApplicationService.class);
    private final Person person;
    private final PurposeOfLoan purposeOfLoan;
    private final UUID id;

    public CreditApplication(Person person, PurposeOfLoan purposeOfLoan) {
        this.person = person;
        this.purposeOfLoan = purposeOfLoan;
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public Person getPerson() {
        return person;
    }

    public PurposeOfLoan getPurposeOfLoan() {
        return purposeOfLoan;
    }
}
