package be.bobberttech.movielistr.movies.domain;

public class Movie {

    private String id;
    private String title;
    private Director director;
    private int rating;
    private int duration;
    private int year;
    private boolean seen;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Director getDirector() {
        return director;
    }

    public int getRating() {
        return rating;
    }

    public int getDuration() {
        return duration;
    }

    public int getYear() {
        return year;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public static Builder newMovie() {
        return new Builder();
    }

    public static class Builder {

        private Movie movie;

        private Builder() {
            movie = new Movie();
        }

        public Builder withId(String id) {
            movie.id = id;
            return this;
        }

        public Builder withTitle(String title) {
            movie.title = title;
            return this;
        }

        public Builder withDirector(Director director) {
            movie.director = director;
            return this;
        }

        public Builder withRating(int rating) {
            movie.rating = rating;
            return this;
        }

        public Builder withDuration(int duration) {
            movie.duration = duration;
            return this;
        }

        public Builder withYear(int year) {
            movie.year = year;
            return this;
        }

        public Builder withSeen(boolean seen) {
            movie.seen = seen;
            return this;
        }

        public Movie build() {
            return movie;
        }
    }
}
