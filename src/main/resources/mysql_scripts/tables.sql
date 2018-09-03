create

create table date_releases.roles
(
	id        int auto_increment
		primary key,
	role_name varchar(20) not null,
	constraint roles_role_name_uindex
	unique (role_name)
);

create table date_releases.series
(
	id           int auto_increment
		primary key,
	name         varchar(45)                                             not null,
	date_started date                                                    null,
	ended        tinyint(1) default '0'                                  not null,
	imageUrl     varchar(255) default '/contentFiles/imgs/not-found.png' null,
	channel      varchar(45)                                             null,
	constraint name_UNIQUE
	unique (name)
);

create table date_releases.series_episodes
(
	series_episodes_id int auto_increment
		primary key,
	series_id          int         not null,
	season             int         not null,
	episode            int         not null,
	release_date       date        null,
	notes              varchar(45) null
);

create index series_id
	on series_episodes (series_id);

create table date_releases.users
(
	id          int auto_increment
		primary key,
	user_name   varchar(45)  not null,
	password    varchar(70)  not null,
	img_url     varchar(255) null,
	first_name  varchar(45)  null,
	middle_name varchar(45)  null,
	last_name   varchar(45)  null,
	email       varchar(255) not null,
	constraint users_email_uindex
	unique (email),
	constraint users_user_name_uindex
	unique (user_name)
);

create table date_releases.user_favorites_series
(
	user_id   int null,
	series_id int null,
	constraint user_favorites_series_user_id_series_id_uindex
	unique (user_id, series_id),
	constraint user_favorites_series_series_id_fk
	foreign key (series_id) references series (id)
		on update cascade
		on delete cascade,
	constraint user_favorites_series_users_id_fk
	foreign key (user_id) references users (id)
		on update cascade
		on delete cascade
);

create table date_releases.user_role
(
	user_id int not null,
	role_id int not null,
	constraint user_role_user_id_role_id_uindex
	unique (user_id, role_id),
	constraint user_role_roles_id_fk
	foreign key (role_id) references roles (id)
		on update cascade
		on delete cascade,
	constraint user_role_users_id_fk
	foreign key (user_id) references users (id)
		on update cascade
		on delete cascade
);

