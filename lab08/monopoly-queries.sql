-- monopoly-queries.sql provides the commands necessary for monopoly.sql
-- Author: Ian Graham Christensen
-- Professor: Keith Vander Linden
-- Class: 262, for Calvin College
-- Semester Created: Autumn, 2018

-- Retrieve the number of Game records.
SELECT *
FROM Game;

-- Retrieve the player records.
SELECT * 
FROM Player;

-- Retrieve all the users with Calvin email addresses.
SELECT *
FROM Player
WHERE emailAddress LIKE '%calvin%';

-- Retrieve the highest score ever recorded.
SELECT score
FROM PlayerGame
ORDER BY score DESC
LIMIT 1;

-- Retrieve the cross-product of all the tables.
SELECT *
FROM Player, PlayerGame, Game;

-- Retrieve all the PlayerStats
SELECT *
FROM PlayerStats;

-- Retrieve all the HousingSituations
SELECT *
FROM HousingSituation;

-- Retrieve a list of all the games, ordered by date with the most recent game coming first
SELECT *
FROM Game
ORDER BY time ASC;

-- Retrieve all the games that occurred in the past week
SELECT *
FROM Game
WHERE time BETWEEN (DATE(NOW()) - 7) AND DATE(NOW());

-- Retrieve a list of players who have (non-NULL) names
SELECT *
FROM Player
WHERE name IS NOT NULL;

-- Retrieve a list of IDs for players who have some game score larger than 2000
SELECT PlayerID
FROM PlayerGame
WHERE score > 2000 AND score IS NOT NULL;

-- Retrieve a list of players who have GMail accounts
SELECT *
FROM Player
WHERE emailAddress LIKE '%gmail%';

-- Retrieve all “The King”’s game scores in decreasing order
SELECT score
FROM Player, PlayerGame
WHERE Player.ID = PlayerGame.playerID
  AND Player.name = 'The King'
  ORDER BY score DESC;

-- Retrieve the name of the winner of the game played on 2006-06-28 13:20:00 (with ANDs)
SELECT Player.name
FROM Player, PlayerGame, Game
WHERE Player.ID = PlayerGame.playerID
  AND PlayerGame.gameID = Game.ID
  AND score = (SELECT MAX(score) FROM PlayerGame, Game 
    WHERE time = '2006-06-28 13:20:00' AND PlayerGame.GameID = Game.ID);

-- Retrieve the name of the winner of the game played on 2006-06-28 13:20:00 (with JOINs)
SELECT name FROM Player 
JOIN PlayerGame ON Player.ID = PlayerGame.playerID
JOIN GAME ON PlayerGame.gameID = Game.ID
WHERE score = (
  SELECT MAX(score) FROM PlayerGame
  JOIN Game ON PlayerGame.gameID = Game.ID
  WHERE time = '2006-06-28 13:20:00');

-- Retrieve the names of the players who share the same name
SELECT P1.name
FROM Player AS P1, Player AS P2
WHERE P1.name = P2.name
  AND P1.ID < P2.ID;

-- So what does that P1.ID < P2.ID clause do in the last example query?
-- This double checks that the IDs are different making sure that they are not duplicates.

-- The query that joined the Player table to itself seems rather contrived. Can you think of a realistic situation in which you’d want to join a table to itself?
-- Whenever a table contains references to data in itself like if an employee table contained a supervisorid column pointing to an employee that is the supervisor of the current employee.