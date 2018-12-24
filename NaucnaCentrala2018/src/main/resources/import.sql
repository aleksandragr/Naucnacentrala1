INSERT INTO `nc`.`editor_reviewer` (`id`, `name`, `is_editor`, `is_reviewer`, `username`, `password`) VALUES ('1','Pera',1,1,'pera','pera');
INSERT INTO `nc`.`editor_reviewer` (`id`, `name`, `is_editor`, `is_reviewer`) VALUES ('2','Gagi',1,1);
INSERT INTO `nc`.`editor_reviewer` (`id`, `name`, `is_editor`, `is_reviewer`) VALUES ('3','Mika',0,1);

INSERT INTO `nc`.`magazine` (`id`, `name`, `main_editor_id`, `payment_method`) VALUES ('1','Hemijska industrija','1','open-access');
INSERT INTO `nc`.`magazine` (`id`, `name`, `main_editor_id`, `payment_method`) VALUES ('2','Zastita materijala', '2','open-access');
INSERT INTO `nc`.`magazine` (`id`, `name`, `main_editor_id`, `payment_method`) VALUES ('3','Matematicki vesnik','1','no open-access');
INSERT INTO `nc`.`magazine` (`id`, `name`, `main_editor_id`, `payment_method`) VALUES ('4','Filomat','2', 'no open-access');

INSERT INTO `nc`.`magazine_other_editors` (`magazine_id`, `other_editors_id`) VALUES ('1','2');

INSERT INTO `nc`.`magazine_reviewers` (`magazine_id`, `reviewers_id`) VALUES ('1','3');

INSERT INTO `nc`.`scientific_area` (`id`, `code`, `name`) VALUES ('1','MAT','Matematika');
INSERT INTO `nc`.`scientific_area` (`id`, `code`, `name`) VALUES ('2','HEM','Hemija');

INSERT INTO `nc`.`magazine_sections` (`magazine_id`, `sections_id`) VALUES ('1','1');
INSERT INTO `nc`.`magazine_sections` (`magazine_id`, `sections_id`) VALUES ('1','2');

INSERT INTO `nc`.`role` (`id`, `description`, `name`) VALUES ('1','user','USER');
INSERT INTO `nc`.`role` (`id`, `description`, `name`) VALUES ('2','author','AUTHOR');

