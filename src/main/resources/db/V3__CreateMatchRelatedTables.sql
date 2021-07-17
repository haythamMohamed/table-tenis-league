CREATE TABLE IF NOT EXISTS `match` (
	`id` INT NOT NULL AUTO_INCREMENT PRIMARY_KEY ,
	`league` INT NOT NULL ,
	`action_date` DATETIME NOT NULL ,
	`round` INT NOT NULL ,
	`finished` BOOLEAN ,
	CONSTRAINT FK_match_league FOREIGN KEY (league) REFERECES league(id)  ,
	CONSTRAINT FK_match_round FOREIGN KEY (round) REFERECES round(id)  ,
) ;


CREATE TABLE IF NOT EXISTS `match_participant` (
	`id` INT NOT NULL AUTO_INCREMENT PRIMARY_KEY ,
	`match` INT NOT NULL ,
	`is_winner` BOOLEAN NOT NULL ,
	`participant` INT NOT NULL ,
	CONSTRAINT FK_match_participant_match FOREIGN KEY (match) REFERECES match(id)  ,
	CONSTRAINT FK_match_participant_participant FOREIGN KEY (participant) REFERECES participant(id)  ,
) ;

CREATE TABLE IF NOT EXISTS `match_result` (
	`id` INT NOT NULL AUTO_INCREMENT PRIMARY_KEY ,
	`game` INT NOT NULL ,
	`score` INT NOT NULL ,
	`match_participant` INT NOT NULL ,
	CONSTRAINT FK_match_result_match_participant FOREIGN KEY (match_participant) REFERECES match_participant(id)  ,
) ;
