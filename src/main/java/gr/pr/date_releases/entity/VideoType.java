package gr.pr.date_releases.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "video_type", schema = "sql7256210")
public class VideoType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private int id;

	@Basic
	@Column(name = "video_type", nullable = false, unique = true)
	private String videoType;

	@OneToMany(mappedBy = "videoType")
	private List<SeriesEntity> series;

	public VideoType() {
	}

	public VideoType(String videoType) {
		this.videoType = videoType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVideoType() {
		return videoType;
	}

	public void setVideoType(String seriesType) {
		this.videoType = seriesType;
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

		VideoType that = (VideoType) o;

		if (getId() != that.getId()) return false;
		return getVideoType() != null ? getVideoType().equals(that.getVideoType()) : that.getVideoType() == null;
	}

	@Override
	public int hashCode() {
		int result = getId();
		result = 31 * result + (getVideoType() != null ? getVideoType().hashCode() : 0);
		return result;
	}
}
