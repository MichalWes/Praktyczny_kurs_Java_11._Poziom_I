package pl.javaskills.creditapp.core.model;

import pl.javaskills.creditapp.core.Constants;
import pl.javaskills.creditapp.core.annotation.NotNull;
import pl.javaskills.creditapp.core.annotation.Regex;
import pl.javaskills.creditapp.core.annotation.ValidateObject;

import java.util.Optional;

public class ContactData {
    @NotNull
    @Regex(Constants.EMAIL_REGEX)
    private String email;
    @NotNull
    @Regex(Constants.PHONE_NUMBER_REGEX)
    private String phoneNumber;
    @NotNull
    @ValidateObject
    private Address homeAddress;
    @NotNull
    private Optional<Address> correspondeceAddress;

    private ContactData(String email, String phoneNumber, Address homeAddress, Optional<Address> correspondenceAddress) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.homeAddress = homeAddress;
        this.correspondeceAddress = correspondenceAddress;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public Optional<Address> getCorrespondenceAddress() {
        return correspondeceAddress;
    }

    public static class Builder{
        private String email;
        private String phoneNumber;
        private Address homeAddress;
        private Address correspondeceAddress;

        private Builder(){
        }

        public static Builder create(){
            return new Builder();
        }

        public Builder withEmail(String email){
            this.email = email;
            return this;
        }

        public Builder withPhoneNumber(String phoneNumber){
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder withHomeAddress(Address homeAddress){
            this.homeAddress = homeAddress;
            return this;
        }

        public Builder withCorrespondeceAddress(Address correspondeceAddress){
            this.correspondeceAddress = correspondeceAddress;
            return this;
        }

        public ContactData build(){
            Optional<Address> correspondeceAddress = this.homeAddress.equals(this.correspondeceAddress) ?
                    Optional.empty() : Optional.ofNullable(this.correspondeceAddress);

            return new ContactData(this.email, this.phoneNumber, this.homeAddress, correspondeceAddress);
        }
    }
}
