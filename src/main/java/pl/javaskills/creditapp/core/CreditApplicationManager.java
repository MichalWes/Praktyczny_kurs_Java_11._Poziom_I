package pl.javaskills.creditapp.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import pl.javaskills.creditapp.core.model.CreditApplication;
import pl.javaskills.creditapp.di.Inject;

import java.util.ArrayDeque;
import java.util.Deque;

public class CreditApplicationManager {

    private static final Logger log = LoggerFactory.getLogger(CreditApplicationManager.class);
    @Inject
    private CreditApplicationService creditApplicationService;
    @Inject
    private CreditApplicationDecisionFactory creditApplicationDecisionFactory;

    private final Deque<CreditApplication> queue = new ArrayDeque<>();

    public CreditApplicationManager(CreditApplicationService creditApplicationService) {
        this.creditApplicationService = creditApplicationService;
    }

    public CreditApplicationManager() {
    }

    public void add(CreditApplication creditApplication) {
        log.info(String.format("Application %s is added to queue", creditApplication.getId().toString()));
        queue.addFirst(creditApplication);

    }

    public void startProcessing() {

        while (!queue.isEmpty()) {
            MDC.remove("id");
            CreditApplication creditApplication = queue.pollLast();
            log.info(String.format("Start processing application with id %s", creditApplication.getId().toString()));
            CreditApplicationDecision decision = creditApplicationService.getDecision(creditApplication);
            log.info(creditApplicationDecisionFactory.getDecisionString(decision, creditApplication));
            log.info("");
        }

    }
}
