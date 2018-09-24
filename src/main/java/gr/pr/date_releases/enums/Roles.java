package gr.pr.date_releases.enums;

public enum Roles {

	USER("ROLE_USER"),
	ADMIN("ROLE_ADMIN");

	private String role;

	Roles(final String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return this.role;
	}
}
