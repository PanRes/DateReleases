package gr.pr.datereleases.models;

import javax.persistence.*;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

@Entity
@Table(name = "series_episodes", schema = "test_db")
public class SeriesEpisodesModel {

    private int seriesEpisodesId;
    private Integer season;
    private Integer episode;
    private Date releaseDate;
    private SeriesModel seriesBySeriesId;

    @Id
    @Column(name = "series_episodes_id", nullable = false)
    public int getSeriesEpisodesId() {
        return seriesEpisodesId;
    }

    public void setSeriesEpisodesId(int seriesEpisodesId) {
        this.seriesEpisodesId = seriesEpisodesId;
    }

    @Basic
    @Column(name = "season", nullable = true)
    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    @Basic
    @Column(name = "episode", nullable = true)
    public Integer getEpisode() {
        return episode;
    }

    public void setEpisode(Integer episode) {
        this.episode = episode;
    }

    @Basic
    @Column(name = "release_date", nullable = true)
    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SeriesEpisodesModel that = (SeriesEpisodesModel) o;

        if (seriesEpisodesId != that.seriesEpisodesId) return false;
        if (season != null ? !season.equals(that.season) : that.season != null) return false;
        if (episode != null ? !episode.equals(that.episode) : that.episode != null) return false;
        if (releaseDate != null ? !releaseDate.equals(that.releaseDate) : that.releaseDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = seriesEpisodesId;
        result = 31 * result + (season != null ? season.hashCode() : 0);
        result = 31 * result + (episode != null ? episode.hashCode() : 0);
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        return result;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "series_id", referencedColumnName = "series_id")
    public SeriesModel getSeriesBySeriesId() {
        return seriesBySeriesId;
    }

    public void setSeriesBySeriesId(SeriesModel seriesBySeriesId) {
        this.seriesBySeriesId = seriesBySeriesId;
    }

    @Override
    public String toString() {
        String print = seriesBySeriesId.getName() + "  ";

        if (releaseDate == null){
            print += "TBD  TBD  ";
        }
        else{
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(releaseDate);
            String releaseDay = gc.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.LONG_FORMAT, Locale.US);
            gc.add(Calendar.DATE,1);
            String viewDay = gc.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.LONG_FORMAT, Locale.US);

            print += releaseDay + "  " + viewDay + "  ";
        }

        if(season < 9){
            print += "S0" + season;
        }
        else{
            print += "S" + season;
        }

        if(episode < 9){
            print += "E0" + episode + "  ";
        }
        else{
            print += "E" + episode + "  ";
        }

        if (releaseDate == null){
            print += "TBA";
        }
        else{
            print += releaseDate;
        }

        return print;
    }
}
