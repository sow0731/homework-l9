DROP TABLE IF EXISTS vinyl;

CREATE TABLE vinyl (
  id Integer unsigned AUTO_INCREMENT,
  title VARCHAR(30) NOT NULL,
  artist VARCHAR(30) NOT NULL,
  label VARCHAR(30) NOT NULL,
  release_year int(4) NOT NULL,
  PRIMARY KEY(id)
);

INSERT INTO vinyl (title, artist, label, release_year)
VALUES ("Mr.Gone", "Weather Report", "CBS", 1978);

INSERT INTO vinyl (title, artist, label, release_year)
VALUES ("Real Phunk", "Freshmess On Wax", "Fierce!", 1996);

INSERT INTO vinyl (title, artist, label, release_year)
VALUES ("Synthax", "Luke Vibert", "Warp", 2003);