package pl.javaskills.creditapp.core;


import ch.qos.logback.classic.Level;
import pl.javaskills.creditapp.core.exception.RequirementNotMetCause;
import pl.javaskills.creditapp.core.model.CreditApplication;

import java.util.Locale;
import java.util.MissingResourceException;

import static pl.javaskills.creditapp.core.Constants.DEFAULT_SYSTEM_LOCALE;


public class CreditApplicationDecisionFactory {

    public CreditApplicationDecision createDecision(DecisionType decisionType, CreditApplication creditApplication, double creditRating, int score) {
        return new CreditApplicationDecision(decisionType, creditApplication.getPerson().getPersonalData(), creditRating, score, creditApplication.getClientLocale());
    }

    public CreditApplicationDecision createDecision(DecisionType decisionType, CreditApplication creditApplication, double creditRating, int score, RequirementNotMetCause requirementNotMetCause) {
        return new CreditApplicationDecision(decisionType, creditApplication.getPerson().getPersonalData(), creditRating, score, requirementNotMetCause, creditApplication.getClientLocale());
    }

//    public Locale chooseLocale(CreditApplication creditApplication) {
//
//            if (creditApplication.getClientLocale().equals(DEFAULT_SYSTEM_LOCALE) || creditApplication.getClientLocale().equals(Locale.US))
//            {
//                return creditApplication.getClientLocale();
//            } else {
//                return Locale.US;
//            }


}
