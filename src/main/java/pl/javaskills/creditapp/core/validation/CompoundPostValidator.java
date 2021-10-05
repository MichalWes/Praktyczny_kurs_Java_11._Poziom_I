package pl.javaskills.creditapp.core.validation;

import pl.javaskills.creditapp.core.exception.RequirementNotMetException;
import pl.javaskills.creditapp.core.model.CreditApplication;
import pl.javaskills.creditapp.di.Inject;

public class CompoundPostValidator implements PostValidator {

    @Inject
    private PostValidator[] postValidators;

    public CompoundPostValidator(PostValidator... postValidators) {
        this.postValidators = postValidators;
    }

    public CompoundPostValidator(){
    }

    @Override
    public void validate(CreditApplication creditApplication, int scoring, double rating) throws RequirementNotMetException {
        for (PostValidator postValidator : postValidators) {
            postValidator.validate(creditApplication, scoring, rating);
        }
    }
}
