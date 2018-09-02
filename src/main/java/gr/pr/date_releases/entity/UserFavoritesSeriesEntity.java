package gr.pr.date_releases.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_favorites_series", schema = "date_releases", catalog = "")
public class UserFavoritesSeriesEntity {

	private SeriesEntity seriesBySeriesId;

	@ManyToOne
	@JoinColumn(name = "series_id", referencedColumnName = "id")
	public SeriesEntity getSeriesBySeriesId() {
		return seriesBySeriesId;
	}

	public void setSeriesBySeriesId(SeriesEntity seriesBySeriesId) {
		this.seriesBySeriesId = seriesBySeriesId;
	}
}
