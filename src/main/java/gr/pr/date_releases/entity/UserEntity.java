package gr.pr.date_releases.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "sql7256210", name = "users")
@NamedQueries({
		@NamedQuery(name = "User.findAll", query = "FROM UserEntity u"),
		@NamedQuery(name = "User.findUserByUserNameOtherThan", query = "FROM UserEntity u WHERE u.userName = :userName AND u.id <> :id"),
		@NamedQuery(name = "User.findUserByEmailOtherThan", query = "FROM UserEntity u WHERE u.email = :email AND u.id <> :id"),
		@NamedQuery(name = "User.findUserByUserNameOrEmail", query = "FROM UserEntity u WHERE u.userName = :userName OR u.email = :userName")
})
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private int id;

	@Basic
	@Column(name = "user_name", nullable = false, unique = true,length = 45)
	@NotNull(message = "UserName is required")
	private String userName;

	@Basic
	@Column(name = "password", nullable = false, length = 70)
	private String password;

	@Basic
	@Column(name = "img_url")
	private String userImgUrl;

	@Basic
	@Column(name = "first_name", length = 45)
	private String firstName;

	@Basic
	@Column(name = "middle_name", length = 45)
	private String middleName;

	@Basic
	@Column(name = "last_name", length = 45)
	private String lastName;

	@Basic
	@Column(name = "email", unique = true, length = 100)
	private String email;
	
	@Basic
	@Column(name = "enabled", nullable = false)
	private boolean enabled;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "user_role",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private List<RolesEntity> roles;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
			name = "user_series_favorites",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "series_id")
	)
	private List<SeriesEntity> favoriteSeries;

	public UserEntity() {
	}

	public UserEntity(String userName, String password, String email) {
		this.userName = userName;
		this.password = password;
		this.email = email;
		enabled = true;
	}

	public UserEntity(String userName, String password, String userImgUrl,
					  String firstName, String middleName, String lastName, String email) {
		this.userName = userName;
		this.password = password;
		this.userImgUrl = userImgUrl;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.email = email;
		enabled = true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserImgUrl() {
		return userImgUrl;
	}

	public void setUserImgUrl(String userImgUrl) {
		this.userImgUrl = userImgUrl;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public List<RolesEntity> getRoles() {
		return roles;
	}
	
	public void setRoles(List<RolesEntity> roles) {
		this.roles = roles;
	}
	
	public void addRole(RolesEntity role) {
		if (this.roles == null) {
			roles = new ArrayList<>();
		}
		this.roles.add(role);
	}
	public void addRole(String roleName) {
		if (this.roles == null) {
			roles = new ArrayList<>();
		}
		this.roles.add(new RolesEntity(roleName));
	}
	
	public void addRoles(List<RolesEntity> roles) {
		if (this.roles == null) {
			roles = new ArrayList<>();
		}
		this.roles.addAll(roles);
	}
	
	//TODO : check if entry is removed from user_roles table
	public void removeRole(RolesEntity role) {
		this.roles.remove(role);
	}
	
	public List<SeriesEntity> getFavoriteSeries() {
		return favoriteSeries;
	}
	
	public void setFavoriteSeries(List<SeriesEntity> favoriteSeries) {
		this.favoriteSeries = favoriteSeries;
	}
	
	public void addSeries(SeriesEntity series) {
		if (this.favoriteSeries == null) {
			this.favoriteSeries = new ArrayList<>();
		}
		this.favoriteSeries.add(series);
	}
	
	public void addSeries(List<SeriesEntity> series) {
		if (this.favoriteSeries == null) {
			this.favoriteSeries = new ArrayList<>();
		}
		this.favoriteSeries.addAll(series);
	}
	
	//TODO : check if entry is removed from user_series_favorite table
	public void removeFavoriteSeries(SeriesEntity series) {
		favoriteSeries.remove(series);
	}
	
	public boolean hasFavoriteSeries(SeriesEntity series) {
		return favoriteSeries.contains(series);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		UserEntity that = (UserEntity) o;
		
		if (getId() != that.getId()) return false;
		if (isEnabled() != that.isEnabled()) return false;
		if (getUserName() != null ? !getUserName().equals(that.getUserName()) : that.getUserName() != null)
			return false;
		if (getPassword() != null ? !getPassword().equals(that.getPassword()) : that.getPassword() != null)
			return false;
		if (getUserImgUrl() != null ? !getUserImgUrl().equals(that.getUserImgUrl()) : that.getUserImgUrl() != null)
			return false;
		if (getFirstName() != null ? !getFirstName().equals(that.getFirstName()) : that.getFirstName() != null)
			return false;
		if (getMiddleName() != null ? !getMiddleName().equals(that.getMiddleName()) : that.getMiddleName() != null)
			return false;
		if (getLastName() != null ? !getLastName().equals(that.getLastName()) : that.getLastName() != null)
			return false;
		return getEmail() != null ? getEmail().equals(that.getEmail()) : that.getEmail() == null;
	}
	
	@Override
	public int hashCode() {
		int result = getId();
		result = 31 * result + (getUserName() != null ? getUserName().hashCode() : 0);
		result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
		result = 31 * result + (getUserImgUrl() != null ? getUserImgUrl().hashCode() : 0);
		result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
		result = 31 * result + (getMiddleName() != null ? getMiddleName().hashCode() : 0);
		result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
		result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
		result = 31 * result + (isEnabled() ? 1 : 0);
		return result;
	}
	
	@Override
	public String toString() {
		return "UserEntity{ userName: " + userName +
				", full name" + firstName + " " + middleName != null ? middleName + " " : lastName +
				", email: " + email + ", isEnabled: " + enabled + "}";
	}
}
