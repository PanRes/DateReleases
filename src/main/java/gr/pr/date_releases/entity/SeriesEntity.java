package gr.pr.date_releases.entity;

import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "series", schema = "sql7256210")
@NamedQueries({
		@NamedQuery(name = "Series.findAll", query = "FROM SeriesEntity s"),
		@NamedQuery(name = "Series.findSeriesByName", query = "FROM SeriesEntity s where s.name = :name")
})
public class SeriesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;

	@Basic
	@Column(name = "name", nullable = false, length = 45, unique = true)
	private String name;

	@Basic
	@Column(name = "date_started")
	private Date dateStarted;

	@Basic
	@Column(name = "ended", nullable = false)
	private boolean ended;

	@Basic
	@Column(name = "img_url")
	private String imageUrl;
	
	@Basic
	@Column(name = "channel")
	private String channel;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "user_series_favorites",
			joinColumns = @JoinColumn(name = "series_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id")
	)
	private List<UserEntity> usersFavorite;
	
	@OneToMany(mappedBy = "series", orphanRemoval = true)
	private List<SeriesEpisodesEntity> seriesEpisodes;
	
	public SeriesEntity() {
	}
	
	public SeriesEntity(String name) {
		this.name = name;
		this.ended = false;
	}
	
	public SeriesEntity(String name, boolean ended) {
		this.name = name;
		this.ended = ended;
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

	public Date getDateStarted() {
		return dateStarted;
	}

	public void setDateStarted(Date dateStarted) {
		this.dateStarted = dateStarted;
	}
	
	public boolean isEnded() {
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
	
	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	public List<UserEntity> getUsersFavorite() {
		return usersFavorite;
	}
	
	public void setUsersFavorite(List<UserEntity> usersFavorite) {
		this.usersFavorite = usersFavorite;
	}
	
	public void addUser(UserEntity user) {
		this.usersFavorite.add(user);
	}
	
	public void addUsers(List<UserEntity> users) {
		this.usersFavorite.addAll(users);
	}
	
	//TODO : check if entry is removed from user_series_favorite table
	public void removeUserFavorite(UserEntity user) {
		usersFavorite.remove(user);
	}
	
	public boolean hasUser() {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		return usersFavorite.stream()
				.map(user -> user.getUserName().equals(userName))
				.count() > 0;
	}

	//TODO : create methods for seriesEpisodes

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		SeriesEntity that = (SeriesEntity) o;
		
		if (getId() != that.getId()) return false;
		if (isEnded() != that.isEnded()) return false;
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
		result = 31 * result + (isEnded() ? 1 : 0);
		result = 31 * result + (getImageUrl() != null ? getImageUrl().hashCode() : 0);
		result = 31 * result + (getChannel() != null ? getChannel().hashCode() : 0);
		return result;
	}
}
