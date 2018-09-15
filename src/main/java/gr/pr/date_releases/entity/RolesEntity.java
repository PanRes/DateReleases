package gr.pr.date_releases.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles", schema = "sql7256210")
public class RolesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;

	@Basic
	@Column(name = "name", unique = true, nullable = false, length = 20)
	private String roleName;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "user_role",
			joinColumns = @JoinColumn(name = "role_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id")
	)
	private Set<UserEntity> users;
	
	public RolesEntity() {
	}
	
	public RolesEntity(String roleName) {
		this.roleName = roleName;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public Set<UserEntity> getUsers() {
		return users;
	}
	
	public void setUsers(Set<UserEntity> users) {
		this.users = users;
	}
	
	public void addUser(UserEntity user) {
		this.users.add(user);
	}
	
	public void addUsers(Set<UserEntity> users) {
		this.users.addAll(users);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		RolesEntity that = (RolesEntity) o;

		if (getId() != that.getId()) return false;
		return getRoleName() != null ? getRoleName().equals(that.getRoleName()) : that.getRoleName() == null;
	}

	@Override
	public int hashCode() {
		int result = getId();
		result = 31 * result + (getRoleName() != null ? getRoleName().hashCode() : 0);
		return result;
	}
}
