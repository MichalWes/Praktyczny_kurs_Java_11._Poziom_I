package pl.javaskills.creditapp.core.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.javaskills.creditapp.core.CreditApplicationService;

import java.util.Set;
import java.util.UUID;

public class CreditApplication {

    private static final Logger log = LoggerFactory.getLogger(CreditApplication.class);
    private final Person person;
    private final PurposeOfLoan purposeOfLoan;
    private final Set<Guarantor> guarantors;
    private final UUID id;

    public CreditApplication(Person person, PurposeOfLoan purposeOfLoan, Set<Guarantor> guarantors) {
        this.person = person;
        this.purposeOfLoan = purposeOfLoan;
        this.guarantors = guarantors;
        this.id = UUID.randomUUID();
    }

    public Set<Guarantor> getGuarantors() {
        return guarantors;
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
