CREATE TABLE IF NOT EXISTS users (
	id bigint GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	"name" varchar NOT NULL,
	email varchar NOT NULL,
	CONSTRAINT user_pk PRIMARY KEY (id),
	CONSTRAINT user_unique UNIQUE (email)
);
CREATE TABLE IF NOT EXISTS items (
	id bigint GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	"name" varchar NOT NULL,
	description varchar NULL,
	available bool NULL,
	request bigint NULL,
	"owner" bigint NULL,
	CONSTRAINT items_pk PRIMARY KEY (id),
	CONSTRAINT items_users_fk FOREIGN KEY ("owner") REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS bookings (
	id bigint GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	"start" timestamp without time zone NULL,
	"end" timestamp without time zone NULL,
	item bigint NOT NULL,
	booker bigint NOT NULL,
	status varchar NULL,
	CONSTRAINT bookings_pk PRIMARY KEY (id),
	CONSTRAINT bookings_items_fk FOREIGN KEY (item) REFERENCES items(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT bookings_users_fk FOREIGN KEY (booker) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS requests (
	id bigint GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	description varchar NULL,
	requestor bigint NOT NULL,
	created timestamp without time zone NULL,
	CONSTRAINT requests_pk PRIMARY KEY (id),
	CONSTRAINT requests_users_fk FOREIGN KEY (requestor) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS "comments" (
	id bigint GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	"text" varchar NULL,
	item bigint NOT NULL,
	author bigint NOT NULL,
	created timestamp without time zone NULL,
	CONSTRAINT comments_pk PRIMARY KEY (id),
	CONSTRAINT comments_items_fk FOREIGN KEY (item) REFERENCES items(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT comments_users_fk FOREIGN KEY (author) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE
);

