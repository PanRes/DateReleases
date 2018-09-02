package gr.pr.date_releases.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles", schema = "date_releases")
public class RolesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;

	@Basic
	@Column(name = "role_name", unique = true, nullable = false, length = 20)
	private String roleName;

	@ManyToMany
	@JoinTable(
			name = "user_role",
			joinColumns = @JoinColumn(name = "role_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id")
	)
	private List<UserEntity> users;

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
