package gr.pr.datereleases.models;

import javax.persistence.*;

@Entity
@Table(name = "users_favorites_series", schema = "test_db", catalog = "")
public class UsersFavoritesSeriesModel {

    private int id;
    private UsersModel usersByUserId;
    private SeriesModel seriesBySeriesId;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public UsersModel getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(UsersModel usersByUserId) {
        this.usersByUserId = usersByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "series_id", referencedColumnName = "series_id", nullable = false)
    public SeriesModel getSeriesBySeriesId() {
        return seriesBySeriesId;
    }

    public void setSeriesBySeriesId(SeriesModel seriesBySeriesId) {
        this.seriesBySeriesId = seriesBySeriesId;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof UsersFavoritesSeriesModel)) return false;

        UsersFavoritesSeriesModel that = (UsersFavoritesSeriesModel) o;

        if (getId() != that.getId()) return false;
        if (getUsersByUserId() != null ? !getUsersByUserId().equals(that.getUsersByUserId()) : that.getUsersByUserId() != null)
            return false;
        return getSeriesBySeriesId() != null ? getSeriesBySeriesId().equals(that.getSeriesBySeriesId()) : that.getSeriesBySeriesId() == null;
    }

    @Override
    public int hashCode(){
        int result = getId();
        result = 31 * result + (getUsersByUserId() != null ? getUsersByUserId().hashCode() : 0);
        result = 31 * result + (getSeriesBySeriesId() != null ? getSeriesBySeriesId().hashCode() : 0);
        return result;
    }

    @Override
    public String toString(){
        return "UsersFavoritesSeriesModel{" +
                "id=" + id +
                ", usersByUserId=" + usersByUserId +
                ", seriesBySeriesId=" + seriesBySeriesId +
                '}';
    }
}
