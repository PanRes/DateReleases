package gr.pr.datereleases.models;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "series", schema = "test_db")
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
        if (o == null || getClass() != o.getClass()) return false;

        SeriesModel that = (SeriesModel) o;

        if (seriesId != that.seriesId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (dateStarted != null ? !dateStarted.equals(that.dateStarted) : that.dateStarted != null) return false;
        if (ended != null ? !ended.equals(that.ended) : that.ended != null) return false;
        if (imageUrl != null ? !imageUrl.equals(that.imageUrl) : that.imageUrl != null) return false;
        return channel != null ? channel.equals(that.channel) : that.channel == null;
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
