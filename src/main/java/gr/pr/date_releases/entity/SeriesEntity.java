package gr.pr.date_releases.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "series", schema = "date_releases")
public class SeriesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;

	@Basic
	@Column(name = "name", nullable = false, length = 45)
	private String name;

	@Basic
	@Column(name = "date_started", nullable = true)
	private Date dateStarted;

	@Basic
	@Column(name = "ended", nullable = false)
	private boolean ended;

	@Basic
	@Column(name = "imageUrl", nullable = true, length = 255)
	private String imageUrl;
	private String channel;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateStarted() {
		return dateStarted;
	}

	public void setDateStarted(Date dateStarted) {
		this.dateStarted = dateStarted;
	}

	public boolean hasEnded() {
		return ended;
	}

	public void setEnded(boolean ended) {
		this.ended = ended;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Basic
	@Column(name = "channel", nullable = true, length = 45)
	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		SeriesEntity that = (SeriesEntity) o;

		if (getId() != that.getId()) return false;
		if (ended != that.ended) return false;
		if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
		if (getDateStarted() != null ? !getDateStarted().equals(that.getDateStarted()) : that.getDateStarted() != null)
			return false;
		if (getImageUrl() != null ? !getImageUrl().equals(that.getImageUrl()) : that.getImageUrl() != null)
			return false;
		return getChannel() != null ? getChannel().equals(that.getChannel()) : that.getChannel() == null;
	}

	@Override
	public int hashCode() {
		int result = getId();
		result = 31 * result + (getName() != null ? getName().hashCode() : 0);
		result = 31 * result + (getDateStarted() != null ? getDateStarted().hashCode() : 0);
		result = 31 * result + (ended ? 1 : 0);
		result = 31 * result + (getImageUrl() != null ? getImageUrl().hashCode() : 0);
		result = 31 * result + (getChannel() != null ? getChannel().hashCode() : 0);
		return result;
	}
}
