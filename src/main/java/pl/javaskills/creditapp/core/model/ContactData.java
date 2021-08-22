package pl.javaskills.creditapp.core.model;

public class ContactData {
    private final String email;
    private final String phoneNumber;

    private ContactData(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public static class Builder{
        private String email;
        private String phoneNumber;

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

        public ContactData build(){
            return new ContactData(email, phoneNumber);
        }
    }





}
