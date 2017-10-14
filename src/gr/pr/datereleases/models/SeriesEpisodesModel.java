package gr.pr.datereleases.models;

import javax.persistence.*;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

@Entity
@Table(name = "series_episodes", schema = "test_db",catalog = "test_db")
@NamedQueries(value = {
        @NamedQuery(name="SeriesEpisodesModel.findAll",
                query = "SELECT se FROM SeriesEpisodesModel se"),
        @NamedQuery(name="SeriesEpisodesModel.findById",
                query = "SELECT se FROM SeriesEpisodesModel se WHERE se.id = :id"),
        @NamedQuery(name="SeriesEpisodesModel.findBySeriesId",
                query = "SELECT se FROM SeriesEpisodesModel se WHERE se.seriesBySeriesId.id = :seriesId"),
        @NamedQuery(name="SeriesEpisodesModel.findBySeason",
                query = "SELECT se FROM SeriesEpisodesModel se WHERE se.season = :season"),
        @NamedQuery(name="SeriesEpisodesModel.findByEpisode",
                query = "SELECT se FROM SeriesEpisodesModel se WHERE se.episode = :episode"),
        @NamedQuery(name="SeriesEpisodesModel.findByReleaseDate",
                query = "SELECT se FROM SeriesEpisodesModel se WHERE se.releaseDate = :releaseDate")
        })
public class SeriesEpisodesModel {

    private int seriesEpisodesId;
    private SeriesModel seriesBySeriesId;
    private Integer season;
    private Integer episode;
    private Date releaseDate;
    private String notes;

    @Id
    @Column(name = "series_episodes_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getSeriesEpisodesId() {
        return seriesEpisodesId;
    }

    public void setSeriesEpisodesId(int seriesEpisodesId) {
        this.seriesEpisodesId = seriesEpisodesId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "series_id", referencedColumnName = "series_id")
    public SeriesModel getSeriesBySeriesId() {
        return seriesBySeriesId;
    }

    public void setSeriesBySeriesId(SeriesModel seriesBySeriesId) {
        this.seriesBySeriesId = seriesBySeriesId;
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

    @Basic
    @Column(name = "notes", nullable = true)
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String releaseDay(){
        String releaseDay = null;
        if (releaseDate == null){
            releaseDay = "TBD";
        }
        else{
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(releaseDate);
            releaseDay = gc.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.LONG_FORMAT, Locale.US);
        }

        return releaseDay;
    }

    public String viewDay(){
        String releaseDay = null;
        if (releaseDate == null){
            releaseDay = "TBD";
        }
        else{
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(releaseDate);
            gc.add(Calendar.DATE,1);
            releaseDay = gc.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.LONG_FORMAT, Locale.US);
        }

        return releaseDay;
    }

    public String seasonEpisode(){
        String seasonEpisode = null;

        if(season < 9){
            seasonEpisode = "S0" + season;
        }
        else{
            seasonEpisode = "S" + season;
        }

        if(episode < 9){
            seasonEpisode += "E0" + episode + "  ";
        }
        else{
            seasonEpisode += "E" + episode + "  ";
        }

        return seasonEpisode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SeriesEpisodesModel that = (SeriesEpisodesModel) o;

        if (seriesEpisodesId != that.seriesEpisodesId) return false;
        if (seriesBySeriesId != null ? !seriesBySeriesId.equals(that.seriesBySeriesId) : that.seriesBySeriesId != null)
            return false;
        if (season != null ? !season.equals(that.season) : that.season != null) return false;
        if (episode != null ? !episode.equals(that.episode) : that.episode != null) return false;
        if (releaseDate != null ? !releaseDate.equals(that.releaseDate) : that.releaseDate != null) return false;
        return notes != null ? notes.equals(that.notes) : that.notes == null;
    }

    @Override
    public int hashCode() {
        int result = seriesEpisodesId;
        result = 31 * result + (seriesBySeriesId != null ? seriesBySeriesId.hashCode() : 0);
        result = 31 * result + (season != null ? season.hashCode() : 0);
        result = 31 * result + (episode != null ? episode.hashCode() : 0);
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        String print = seriesBySeriesId.getName() + "  " + releaseDay() + "  " + viewDay() + "  " + seasonEpisode() + "  ";

        if (releaseDate == null){
            print += "TBA";
        }
        else{
            print += releaseDate;
        }

        print += "  " + notes;

        return print;
    }
}
