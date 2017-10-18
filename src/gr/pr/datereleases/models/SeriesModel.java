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
    private Byte ended;
    private String imageUrl;
    private String channel;
    private Collection<SeriesEpisodesModel> seriesEpisodesBySeriesId;

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
    public Byte getEnded() {
        return ended;
    }

    public void setEnded(Byte ended) {
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

    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "seriesBySeriesId")
    public Collection<SeriesEpisodesModel> getSeriesEpisodesBySeriesId() {
        return seriesEpisodesBySeriesId;
    }

    public void setSeriesEpisodesBySeriesId(Collection<SeriesEpisodesModel> seriesEpisodesBySeriesId) {
        this.seriesEpisodesBySeriesId = seriesEpisodesBySeriesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        SeriesModel that = (SeriesModel) o;

        if (this.getSeriesId() != that.getSeriesId()) {
            return false;
        }
        if (this.getName() != null ? !this.getName().equals(that.getName()) : that.getName() != null) {
            return false;
        }
        if (this.getDateStarted() != null ? !this.getDateStarted().equals(that.getDateStarted()) : that.getDateStarted() != null) {
            return false;
        }
        if (this.getEnded() != null ? !this.getEnded().equals(that.getEnded()) : that.getEnded() != null) {
            return false;
        }
        if (this.getImageUrl() != null ? !this.getImageUrl().equals(that.getImageUrl()) : that.getImageUrl() != null) {
            return false;
        }
        return this.getChannel() != null ? this.getChannel().equals(that.getChannel()) : that.getChannel() == null;
    }

    @Override
    public int hashCode() {
        int result = seriesId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (dateStarted != null ? dateStarted.hashCode() : 0);
        result = 31 * result + (ended != null ? ended.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + (channel != null ? channel.hashCode() : 0);
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
