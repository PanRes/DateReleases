package gr.pr.date_releases.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "series_type", schema = "sql7256210")
public class SeriesType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private int id;

	@Basic
	@Column(name = "sereis_type", nullable = false, unique = true)
	private String seriesType;

	@OneToMany(mappedBy = "seriesType")
	private List<SeriesEntity> series;

	public SeriesType() {
	}

	public SeriesType(String seriesType) {
		this.seriesType = seriesType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSeriesType() {
		return seriesType;
	}

	public void setSeriesType(String seriesType) {
		this.seriesType = seriesType;
	}

	public List<SeriesEntity> getSeries() {
		return series;
	}

	public void setSeries(List<SeriesEntity> series) {
		this.series = series;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		SeriesType that = (SeriesType) o;

		if (getId() != that.getId()) return false;
		return getSeriesType() != null ? getSeriesType().equals(that.getSeriesType()) : that.getSeriesType() == null;
	}

	@Override
	public int hashCode() {
		int result = getId();
		result = 31 * result + (getSeriesType() != null ? getSeriesType().hashCode() : 0);
		return result;
	}
}
