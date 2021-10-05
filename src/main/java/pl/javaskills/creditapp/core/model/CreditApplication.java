package pl.javaskills.creditapp.core.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.javaskills.creditapp.core.annotation.NotNull;
import pl.javaskills.creditapp.core.annotation.ValidateCollection;
import pl.javaskills.creditapp.core.annotation.ValidateObject;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

public class CreditApplication {

    private static final Logger log = LoggerFactory.getLogger(CreditApplication.class);
    @NotNull
    @ValidateObject
    private final Person person;
    private final ZoneId clientTimeZoneId;
    private final ZonedDateTime creationDateClientZone;
    private final Locale clientLocale;
    @NotNull
    @ValidateObject
    private final PurposeOfLoan purposeOfLoan;
    @NotNull
    @ValidateCollection
    private final Set<Guarantor> guarantors;
    @NotNull
    private final UUID id;


    public CreditApplication(ZoneId clientTimeZoneId, Locale clientLocale, Person person, PurposeOfLoan purposeOfLoan) {
        this.person = person;
        this.purposeOfLoan = purposeOfLoan;
        this.guarantors = new TreeSet<>();
        this.id = UUID.randomUUID();
        this.clientTimeZoneId = clientTimeZoneId;
        this.creationDateClientZone = ZonedDateTime.now(clientTimeZoneId);
        this.clientLocale = clientLocale;
    }

    public CreditApplication(ZoneId clientTimeZoneId, Locale clientLocale, Person person, PurposeOfLoan purposeOfLoan, Set<Guarantor> guarantors) {
        this.person = person;
        this.purposeOfLoan = purposeOfLoan;
        this.guarantors = new TreeSet<>(guarantors);
        this.id = UUID.randomUUID();
        this.clientTimeZoneId = clientTimeZoneId;
        this.creationDateClientZone = ZonedDateTime.now(clientTimeZoneId);
        this.clientLocale = clientLocale;
    }

    public Locale getClientLocale() {
        return clientLocale;
    }

    public Set<Guarantor> getGuarantors() {
        return guarantors;
    }

    public ZonedDateTime getCreationDateClientZone() {
        return creationDateClientZone;
    }

    public ZoneId getClientTimeZoneId() {
        return clientTimeZoneId;
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
