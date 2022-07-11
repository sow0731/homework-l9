DROP TABLE IF EXISTS vinyl;

CREATE TABLE vinyl (
  id Integer unsigned AUTO_INCREMENT,
  title VARCHAR(100) NOT NULL,
  artist VARCHAR(100) NOT NULL,
  label VARCHAR(100) NOT NULL,
  year YEAR(4) NOT NULL,
  PRIMARY KEY(id)
);

INSERT INTO vinyl (id, title, artist, label, year)
VALUES (1, "Mr.Gone", "Weather Report", "CBS", 1978);

INSERT INTO vinyl (id, title, artist, label, year)
VALUES (2, "Real Phunk", "Freshmess On Wax", "Fierce!", 1996);

INSERT INTO vinyl (id, title, artist, label, year)
VALUES (3, "Synthax", "Luke Vibert", "Warp", 2003);