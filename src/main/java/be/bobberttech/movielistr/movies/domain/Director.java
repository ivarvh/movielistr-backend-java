package be.bobberttech.movielistr.movies.domain;

public class Director {

    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public static Builder newDirector() {
        return new Builder();
    }

    public static class Builder {

        private Director director;

        private Builder() {
            director = new Director();
        }

        public Builder withFirstName(String firstName) {
            director.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            director.lastName = lastName;
            return this;
        }

        public Director build() {
            return director;
        }
    }
}
