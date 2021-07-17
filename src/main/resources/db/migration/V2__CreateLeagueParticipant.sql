CREATE TABLE `league_participant` (
	`id` INT AUTO_INCREMENT PRIMARY KEY ,
	`league` INT NOT NULL ,
	`join_date` DATETIME NOT NULL ,
	`participant` INT NOT NULL ,
	CONSTRAINT FK_league_participant_league FOREIGN KEY(league) REFERENCES league(id)  ,
	CONSTRAINT FK_league_participant_participant FOREIGN KEY(participant) REFERENCES participant(id)
) ;

CREATE TABLE `league_winner` (
	`id` INT AUTO_INCREMENT PRIMARY KEY ,
	`league` INT NOT NULL ,
	`crowning_date` DATETIME NOT NULL ,
	`participant` INT NOT NULL ,
	CONSTRAINT FK_league_winner_league FOREIGN KEY(league) REFERENCES league(id)  ,
	CONSTRAINT FK_league_winner_participant FOREIGN KEY(participant) REFERENCES participant(id)
) ;
