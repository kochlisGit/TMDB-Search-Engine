package entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Movie
{
    private static final String URL_POSTER = "https://image.tmdb.org/t/p/w500";
    private static final String URL_NO_POSTER = "https://cdn.bestmoviehd.net/share/images/no-cover.png";

    private String mid;
    private String title;
    private String overview;
    private String releaseDate;
    private double popularity;
    private double rating;
    private int[] genreArray;
    private List<String> genreList;
    private String posterPath;

    public Movie() {
        genreList = new ArrayList<>();
    }

    public String getMid() {
        return mid;
    }
    public void setMid(int mid) {
        this.mid = "" + mid;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getOverview() {
        return overview;
    }
    public void setOverview(String overview) {
        this.overview = overview;
    }
    public String getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
    public double getPopularity() {
        return popularity;
    }
    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }
    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }
    public int[] getGenreArray() {
        return genreArray;
    }
    public void setGenreArray(int[] genreArray) {
        this.genreArray = genreArray;
    }
    public void addGenre(String genre) {
        genreList.add(genre);
    }
    public List<String> getGenreList() {
        return genreList;
    }
    public String getPosterPath() {
        return posterPath;
    }
    public void setPosterPath(String posterPath) {
        if (posterPath == null)
            this.posterPath = URL_NO_POSTER;
        else
            this.posterPath = URL_POSTER + posterPath;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "mid='" + mid + '\'' +
                ", title='" + title + '\'' +
                ", overview='" + overview + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", popularity=" + popularity +
                ", rating=" + rating +
                ", genreArray=" + Arrays.toString(genreArray) +
                ", genreList=" + genreList +
                ", posterPath='" + posterPath + '\'' +
                '}';
    }
}
