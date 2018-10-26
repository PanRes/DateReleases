package gr.pr.date_releases.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "video_type", schema = "sql7256210")
@NamedQueries({
	@NamedQuery(name = "VideoType.findAll", query = "FROM VideoType v ORDER BY id")
})
public class VideoType implements Comparable<VideoType> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private int id;

	@Basic
	@Column(name = "type_en", nullable = false, unique = true)
	private String typeEn;

	@Basic
	@Column(name = "type_el", nullable = false, unique = true)
	private String typeEl;

	@OneToMany(mappedBy = "videoType")
	private List<SeriesEntity> series;

	public VideoType() {
	}

	public VideoType(String typeEn) {
		this.typeEn = typeEn;
	}

	public VideoType(String typeEn, String typeEl) {
		this.typeEn = typeEn;
		this.typeEl = typeEl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypeEn() {
		return typeEn;
	}

	public void setTypeEl(String typeEl) {
		this.typeEl = typeEl;
	}

	public String getTypeEl() {
		return typeEl;
	}

	public void setTypeEn(String seriesType) {
		this.typeEn = seriesType;
	}

	public List<SeriesEntity> getSeries() {
		return series;
	}

	public void setSeries(List<SeriesEntity> series) {
		this.series = series;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		VideoType videoType = (VideoType) o;

		if (getId() != videoType.getId()) {
			return false;
		}
		if (getTypeEn() != null ? !getTypeEn().equals(videoType.getTypeEn()) : videoType.getTypeEn() != null) {
			return false;
		}
		if (getTypeEl() != null ? !getTypeEl().equals(videoType.getTypeEl()) : videoType.getTypeEl() != null) {
			return false;
		}
		return getSeries() != null ? getSeries().equals(videoType.getSeries()) : videoType.getSeries() == null;
	}

	@Override
	public int hashCode() {
		int result = getId();
		result = 31 * result + (getTypeEn() != null ? getTypeEn().hashCode() : 0);
		result = 31 * result + (getTypeEl() != null ? getTypeEl().hashCode() : 0);
		result = 31 * result + (getSeries() != null ? getSeries().hashCode() : 0);
		return result;
	}

	@Override
	public int compareTo(VideoType o) {
		if (this.getId() < o.getId()){
			return -1;
		}
		else if (this.getId() > this.getId()) {
			return 1;
		}
		else {
			return 0;
		}
	}
}
