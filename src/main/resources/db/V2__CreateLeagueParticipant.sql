CREATE TABLE IF NOT EXISTS `league_participant` (
	`id` INT NOT NULL AUTO_INCREMENT PRIMARY_KEY ,
	`league` INT NOT NULL ,
	`join_date` DATETIME NOT NULL ,
	`participant` INT NOT NULL ,
	CONSTRAINT FK_league_participant_league FOREIGN KEY (league) REFERECES league(id)  ,
	CONSTRAINT FK_league_participant_participant FOREIGN KEY (participant) REFERECES participant(id)  ,
) ;

CREATE TABLE IF NOT EXISTS `league_winner` (
	`id` INT NOT NULL AUTO_INCREMENT PRIMARY_KEY ,
	`league` INT NOT NULL ,
	`crowning_date` DATETIME NOT NULL ,
	`participant` INT NOT NULL ,
	CONSTRAINT FK_league_winner_league FOREIGN KEY (league) REFERECES league(id)  ,
	CONSTRAINT FK_league_winner_participant FOREIGN KEY (participant) REFERECES participant(id)  ,
) ;
