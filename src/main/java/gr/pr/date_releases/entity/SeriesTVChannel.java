package gr.pr.date_releases.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "series_tv_channel", schema = "sql7256210")
@NamedQueries({
	@NamedQuery(name = "SeriesTvChannel.findAll", query = "FROM SeriesTVChannel ch"),
	@NamedQuery(name = "SeriesTvChannel.findByName", query = "FROM SeriesTVChannel ch WHERE name = :name")
})
public class SeriesTVChannel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private int id;

	@Basic
	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@OneToMany(mappedBy = "channel")
	private List<SeriesEntity> series;

	public SeriesTVChannel() {
	}

	public SeriesTVChannel(String name) {
		this.name = name;
	}

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		SeriesTVChannel that = (SeriesTVChannel) o;

		if (getId() != that.getId()) return false;
		return getName() != null ? getName().equals(that.getName()) : that.getName() == null;
	}

	@Override
	public int hashCode() {
		int result = getId();
		result = 31 * result + (getName() != null ? getName().hashCode() : 0);
		return result;
	}
}
