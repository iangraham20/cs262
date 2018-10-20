--
-- This SQL script builds a monopoly database, deleting any pre-existing version.
--
-- @author kvlinden
-- @version Summer, 2015
--

-- Drop previous versions of the tables if they they exist, in reverse order of foreign keys.
DROP TABLE IF EXISTS PlayerGame CASCADE;
DROP TABLE IF EXISTS Game CASCADE;
DROP TABLE IF EXISTS Player CASCADE;
DROP TABLE IF EXISTS PlayerStats;
DROP TABLE IF EXISTS HousingSituation;

-- Create the schema.
CREATE TABLE Game (
	ID integer PRIMARY KEY, 
	time timestamp
	);

CREATE TABLE Player (
	ID integer PRIMARY KEY, 
	emailAddress varchar(50) NOT NULL,
	name varchar(50)
	);

CREATE TABLE PlayerGame (
	gameID integer REFERENCES Game(ID), 
	playerID integer REFERENCES Player(ID),
	score integer
	);

CREATE TABLE PlayerStats (
	gameID integer REFERENCES Game(ID), 
	playerID integer REFERENCES Player(ID),
	playerCash integer,
	playerLocation integer
	);

-- 40spaces 28properties
CREATE TABLE HousingSituation (
	gameID integer REFERENCES Game(ID), 
	playerID integer REFERENCES Player(ID),
	propertyNumber integer,
	housingNumber integer -- 0 indicates no houses, 1-4 indicates up to 4 houses, and 5 indicates a hotel
	);

-- Allow users to select data from the tables.
GRANT SELECT ON Game TO PUBLIC;
GRANT SELECT ON Player TO PUBLIC;
GRANT SELECT ON PlayerGame TO PUBLIC;
GRANT SELECT ON PlayerStats TO PUBLIC;
GRANT SELECT ON HousingSituation TO PUBLIC;

-- Add sample records.
INSERT INTO Game VALUES (1, '2006-06-27 08:00:00');
INSERT INTO Game VALUES (2, '2006-06-28 13:20:00');
INSERT INTO Game VALUES (3, '2006-06-29 18:41:00');

INSERT INTO Player(ID, emailAddress) VALUES (1, 'me@calvin.edu');
INSERT INTO Player VALUES (2, 'king@gmail.edu', 'The King');
INSERT INTO Player VALUES (3, 'dog@gmail.edu', 'Dogbreath');

INSERT INTO PlayerGame VALUES (1, 1, 0.00);
INSERT INTO PlayerGame VALUES (1, 2, 0.00);
INSERT INTO PlayerGame VALUES (1, 3, 2350.00);
INSERT INTO PlayerGame VALUES (2, 1, 1000.00);
INSERT INTO PlayerGame VALUES (2, 2, 0.00);
INSERT INTO PlayerGame VALUES (2, 3, 500.00);
INSERT INTO PlayerGame VALUES (3, 2, 0.00);
INSERT INTO PlayerGame VALUES (3, 3, 5500.00);

INSERT INTO PlayerStats VALUES (1, 1, 1500, 24);
INSERT INTO PlayerStats VALUES (1, 2, 2600, 10);
INSERT INTO PlayerStats VALUES (1, 3, 1000, 15);
INSERT INTO PlayerStats VALUES (2, 1, 2425, 1);
INSERT INTO PlayerStats VALUES (2, 2, 2500, 8);
INSERT INTO PlayerStats VALUES (2, 3, 1750, 21);

INSERT INTO HousingSituation VALUES (1, 1, 6, 1);
INSERT INTO HousingSituation VALUES (1, 1, 18, 4);
INSERT INTO HousingSituation VALUES (1, 1, 21, 3);
INSERT INTO HousingSituation VALUES (1, 2, 3, 3);
INSERT INTO HousingSituation VALUES (1, 2, 5, 5);
INSERT INTO HousingSituation VALUES (1, 2, 14, 0);
INSERT INTO HousingSituation VALUES (1, 3, 6, 3);
INSERT INTO HousingSituation VALUES (1, 3, 12, 2);
INSERT INTO HousingSituation VALUES (1, 3, 25, 3);

INSERT INTO HousingSituation VALUES (2, 1, 3, 1);
INSERT INTO HousingSituation VALUES (2, 1, 16, 4);
INSERT INTO HousingSituation VALUES (2, 1, 23, 3);
INSERT INTO HousingSituation VALUES (2, 2, 1, 3);
INSERT INTO HousingSituation VALUES (2, 2, 9, 5);
INSERT INTO HousingSituation VALUES (2, 2, 17, 0);
INSERT INTO HousingSituation VALUES (2, 3, 5, 3);
INSERT INTO HousingSituation VALUES (2, 3, 11, 2);
INSERT INTO HousingSituation VALUES (2, 3, 2, 3);