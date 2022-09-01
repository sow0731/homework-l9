drop table IF EXISTS vinyl;

create TABLE vinyl (
  id Integer unsigned AUTO_INCREMENT,
  title VARCHAR(50) NOT NULL,
  artist VARCHAR(50) NOT NULL,
  label VARCHAR(50) NOT NULL,
  release_year int(4) NOT NULL,
  PRIMARY KEY(id)
);

insert into vinyl (title, artist, label, release_year)
values ("Mr.Gone", "Weather Report", "CBS", 1978);

insert into vinyl (title, artist, label, release_year)
values ("Real Phunk", "Freshmess On Wax", "Fierce!", 1996);

insert into vinyl (title, artist, label, release_year)
values ("Synthax", "Luke Vibert", "Warp", 2003);