package gr.pr.datereleases.models;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "series", schema = "test_db",catalog = "test_db")
@NamedQueries(value = {
        @NamedQuery(name = "SeriesModel.findAll", query = "SELECT s FROM SeriesModel s"),
        @NamedQuery(name = "SeriesModel.findById", query = "SELECT s FROM SeriesModel s WHERE s.id = :id"),
})
public class SeriesModel {

    private int seriesId;
    private String name;
    private Date dateStarted;
    private boolean ended;
    private String imageUrl;
    private String channel;
    private Collection<SeriesEpisodesModel> seriesEpisodesBySeriesId;
    private Collection<UsersFavoritesSeriesModel> usersFavoritesSeriesModels;

    @Id
    @Column(name = "series_id", nullable = false)
    public int getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(int seriesId) {
        this.seriesId = seriesId;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "date_started", nullable = true)
    public Date getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(Date dateStarted) {
        this.dateStarted = dateStarted;
    }

    @Basic
    @Column(name = "ended", nullable = true)
    public boolean getEnded() {
        return ended;
    }

    public void setEnded(boolean ended) {
        this.ended = ended;
    }

    @Basic
    @Column(name = "imageUrl", nullable = true, length = 45)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Basic
    @Column(name = "channel", nullable = true, length = 45, unique = true)
    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "seriesBySeriesId")
    public Collection<SeriesEpisodesModel> getSeriesEpisodesBySeriesId() {
        return seriesEpisodesBySeriesId;
    }

    public void setSeriesEpisodesBySeriesId(Collection<SeriesEpisodesModel> seriesEpisodesBySeriesId) {
        this.seriesEpisodesBySeriesId = seriesEpisodesBySeriesId;
    }

    public boolean isEnded(){
        return ended;
    }

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy = "seriesBySeriesId")
    public Collection<UsersFavoritesSeriesModel> getUsersFavoritesSeriesModels(){
        return usersFavoritesSeriesModels;
    }

    public void setUsersFavoritesSeriesModels(Collection<UsersFavoritesSeriesModel> usersFavoritesSeriesModels){
        this.usersFavoritesSeriesModels = usersFavoritesSeriesModels;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof SeriesModel)) return false;

        SeriesModel that = (SeriesModel) o;

        if (getSeriesId() != that.getSeriesId()) return false;
        if (isEnded() != that.isEnded()) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getDateStarted() != null ? !getDateStarted().equals(that.getDateStarted()) : that.getDateStarted() != null)
            return false;
        if (getImageUrl() != null ? !getImageUrl().equals(that.getImageUrl()) : that.getImageUrl() != null)
            return false;
        return getChannel() != null ? getChannel().equals(that.getChannel()) : that.getChannel() == null;
    }

    @Override
    public int hashCode(){
        int result = getSeriesId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDateStarted() != null ? getDateStarted().hashCode() : 0);
        result = 31 * result + (isEnded() ? 1 : 0);
        result = 31 * result + (getImageUrl() != null ? getImageUrl().hashCode() : 0);
        result = 31 * result + (getChannel() != null ? getChannel().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SeriesModel{" +
                "seriesId=" + seriesId +
                ", name='" + name + '\'' +
                ", dateStarted=" + dateStarted +
                ", ended=" + ended +
                ", imageUrl='" + imageUrl + '\'' +
                ", channel='" + channel + '\'' +
                '}';
    }
}
