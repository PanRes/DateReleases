package gr.pr.date_releases.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "series_episodes", schema = "sql7256210", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"series_id", "season", "episode"})
})
@NamedQueries({
		@NamedQuery(name = "SeriesEpisodes.findAll", query = "FROM SeriesEpisodesEntity se"),
		@NamedQuery(name = "SeriesEpisodes.findEpisodesBySeries", query = "FROM SeriesEpisodesEntity se where se.series = :series")
})
public class SeriesEpisodesEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;
	
	@Basic
	@Column(name = "season", nullable = false)
	private int season;
	
	@Basic
	@Column(name = "episode", nullable = false)
	private int episode;
	
	@Basic
	@Column(name = "release_date")
	private Date releaseDate;
	
	@Basic
	@Column(name = "notes", length = 45)
	private String notes;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "series_id")
	private SeriesEntity series;
	
	public SeriesEpisodesEntity() {
	}
	
	public SeriesEpisodesEntity(int season, int episode, SeriesEntity series) {
		this.season = season;
		this.episode = episode;
		this.series = series;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = this.id;
	}
	
	public int getSeason() {
		return season;
	}

	public void setSeason(int season) {
		this.season = season;
	}

	public int getEpisode() {
		return episode;
	}

	public void setEpisode(int episode) {
		this.episode = episode;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public SeriesEntity getSeries() {
		return series;
	}
	
	public void setSeries(SeriesEntity series) {
		this.series = series;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		SeriesEpisodesEntity that = (SeriesEpisodesEntity) o;
		
		if (id != that.id) return false;
		if (getSeason() != that.getSeason()) return false;
		if (getEpisode() != that.getEpisode()) return false;
		if (getReleaseDate() != null ? !getReleaseDate().equals(that.getReleaseDate()) : that.getReleaseDate() != null)
			return false;
		return getNotes() != null ? getNotes().equals(that.getNotes()) : that.getNotes() == null;
	}
	
	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + getSeason();
		result = 31 * result + getEpisode();
		result = 31 * result + (getReleaseDate() != null ? getReleaseDate().hashCode() : 0);
		result = 31 * result + (getNotes() != null ? getNotes().hashCode() : 0);
		return result;
	}
}
