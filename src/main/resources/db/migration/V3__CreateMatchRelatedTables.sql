CREATE TABLE IF NOT EXISTS `match` (
	`id` INT AUTO_INCREMENT PRIMARY KEY ,
	`league` INT NOT NULL ,
	`action_date` DATETIME NOT NULL ,
	`round` INT NOT NULL ,
	`finished` BOOLEAN ,
	CONSTRAINT FK_match_league FOREIGN KEY (league) REFERENCES league(id)  ,
	CONSTRAINT FK_match_round FOREIGN KEY (round) REFERENCES round(id) 
) ;


CREATE TABLE IF NOT EXISTS `match_participant` (
	`id` INT AUTO_INCREMENT PRIMARY KEY ,
	`match` INT NOT NULL ,
	`is_winner` BOOLEAN NOT NULL ,
	`participant` INT NOT NULL ,
	CONSTRAINT FK_match_participant_match FOREIGN KEY(match) REFERENCES match(id)  ,
	CONSTRAINT FK_match_participant_participant FOREIGN KEY(participant) REFERENCES participant(id) 
) ;

CREATE TABLE IF NOT EXISTS `match_result` (
	`id` INT AUTO_INCREMENT PRIMARY KEY ,
	`game` INT NOT NULL ,
	`score` INT NOT NULL ,
	`match_participant` INT NOT NULL ,
	CONSTRAINT FK_match_result_match_participant FOREIGN KEY(match_participant) REFERENCES match_participant(id)
) ;
