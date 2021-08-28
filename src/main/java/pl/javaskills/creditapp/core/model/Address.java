package pl.javaskills.creditapp.core.model;

public class Address {
    private final String street;
    private final String city;
    private final String houseNumber;
    private final String zipCode;
    private final String state;

    private Address(String street, String city, String houseNumber, String zipCode, String state) {
        this.street = street;
        this.city = city;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        Address address = (Address) o;
        return street.equalsIgnoreCase(address.street) &&
                city.equalsIgnoreCase(address.city) &&
                houseNumber.equalsIgnoreCase(address.houseNumber) &&
                zipCode.equalsIgnoreCase(address.zipCode) &&
                state.equalsIgnoreCase(address.state);
    }

    public static class Builder{
        private String street;
        private String city;
        private String houseNumber;
        private String zipCode;
        private String state;

        private Builder(){
        }

        public static Builder create(){
            return new Builder();
        }

        public Builder withStreet(String street){
            this.street = street;
            return this;
        }

        public Builder withCity(String city){
            this.city = city;
            return this;
        }

        public Builder withHouseNumber(String houseNumber){
            this.houseNumber = houseNumber;
            return this;
        }

        public Builder withZipCode(String zipCode){
            this.zipCode = zipCode;
            return this;
        }

        public Builder withState(String state){
            this.state = state;
            return this;
        }

        public Address build(){
            return new Address(this.street, this.city, this.houseNumber, this.zipCode, this.state);
        }
    }
}
