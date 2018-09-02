package gr.pr.date_releases.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "series_episodes", schema = "date_releases", catalog = "")
public class SeriesEpisodesEntity {

	private int seriesEpisodesId;
	private int seriesId;
	private int season;
	private int episode;
	private Date releaseDate;
	private String notes;

	@Id
	@Column(name = "series_episodes_id", nullable = false)
	public int getSeriesEpisodesId() {
		return seriesEpisodesId;
	}

	public void setSeriesEpisodesId(int seriesEpisodesId) {
		this.seriesEpisodesId = seriesEpisodesId;
	}

	@Basic
	@Column(name = "series_id", nullable = false)
	public int getSeriesId() {
		return seriesId;
	}

	public void setSeriesId(int seriesId) {
		this.seriesId = seriesId;
	}

	@Basic
	@Column(name = "season", nullable = false)
	public int getSeason() {
		return season;
	}

	public void setSeason(int season) {
		this.season = season;
	}

	@Basic
	@Column(name = "episode", nullable = false)
	public int getEpisode() {
		return episode;
	}

	public void setEpisode(int episode) {
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
	@Column(name = "notes", nullable = true, length = 45)
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		SeriesEpisodesEntity that = (SeriesEpisodesEntity) o;

		if (seriesEpisodesId != that.seriesEpisodesId) return false;
		if (seriesId != that.seriesId) return false;
		if (season != that.season) return false;
		if (episode != that.episode) return false;
		if (releaseDate != null ? !releaseDate.equals(that.releaseDate) : that.releaseDate != null) return false;
		if (notes != null ? !notes.equals(that.notes) : that.notes != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = seriesEpisodesId;
		result = 31 * result + seriesId;
		result = 31 * result + season;
		result = 31 * result + episode;
		result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
		result = 31 * result + (notes != null ? notes.hashCode() : 0);
		return result;
	}
}
