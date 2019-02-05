INSERT INTO `nc`.`editor_reviewer` (`id`, `name`, `is_editor`, `is_reviewer`, `username`, `password`) VALUES ('1','Pera',1,1,'pera','pera');
INSERT INTO `nc`.`editor_reviewer` (`id`, `name`, `is_editor`, `is_reviewer`) VALUES ('2','Gagi',1,1);
INSERT INTO `nc`.`editor_reviewer` (`id`, `name`, `is_editor`, `is_reviewer`) VALUES ('3','Mika',0,1);

INSERT INTO `nc`.`magazine` (`id`, `name`, `main_editor_id`, `payment_method`, `issnnumber`, `merchant_id`, `merchant_password`, `amountmag`) VALUES ('1','Hemijska industrija','1','open-access','0367-598X','977MHIik87?9iuT7ug%RFhu5d./','password@1',2);
INSERT INTO `nc`.`magazine` (`id`, `name`, `main_editor_id`, `payment_method`, `issnnumber`, `merchant_id`, `merchant_password`, `amountmag`) VALUES ('2','Zastita materijala', '2','no open-access','0351-9465','411BNG5dryh654f6j/9hrEEESX0','password@2',3);





INSERT INTO `nc`.`magazine_other_editors` (`magazine_id`, `other_editors_id`) VALUES ('1','2');

INSERT INTO `nc`.`magazine_reviewers` (`magazine_id`, `reviewers_id`) VALUES ('1','3');

INSERT INTO `nc`.`scientific_area` (`id`, `code`, `name`) VALUES ('1','MAT','Matematika');
INSERT INTO `nc`.`scientific_area` (`id`, `code`, `name`) VALUES ('2','HEM','Hemija');

INSERT INTO `nc`.`magazine_sections` (`magazine_id`, `sections_id`) VALUES ('1','1');
INSERT INTO `nc`.`magazine_sections` (`magazine_id`, `sections_id`) VALUES ('1','2');

INSERT INTO `nc`.`role` (`id`, `description`, `name`) VALUES ('1','user','USER');
INSERT INTO `nc`.`role` (`id`, `description`, `name`) VALUES ('2','author','AUTHOR');

INSERT INTO `nc`.`user_author` (`id`, `username`, `password`, `is_author`, `membership_fee_id`, `name`, `surname`) VALUES ('1','gagi','$2a$10$2sQKNmLgwfIyHkmHNqT8V.ib75p8RKZI7.CciEc5NBO/yPkVYPeue',1,'1','Dragan','Dulic');

INSERT INTO `nc`.`membership_fee` (`id`, `user_id`, `start_date`, `end_date`, `price`) VALUES ('1','1','2018.12.23.','2019.02.03.','1');

INSERT INTO `nc`.`user_author_roles` (`user_author_id`, `roles_id`) VALUES ('1','2');

INSERT INTO `nc`.`labor` (`id`, `heading`, `is_abstract`, `section`, `magazine_id`, `amountlabor`) VALUES ('1','Mineraloske i kristalografske osobine zeolitskog tufa HEU-tipa lokaliteta Novakovic, Bosna i Hercegovina',0,'','1','1');
INSERT INTO `nc`.`labor` (`id`, `heading`, `is_abstract`, `section`, `magazine_id`, `amountlabor`) VALUES ('2','Upravljacko – nadzorni sistem pogona za proizvodnju biodizela',0,'','1','1');

INSERT INTO `nc`.`labor` (`id`, `heading`, `is_abstract`, `section`, `magazine_id`, `amountlabor`) VALUES ('3','Hibridni metod za detekciju ivica na TEM slikama nanocestica',0,'','2','2');
INSERT INTO `nc`.`labor` (`id`, `heading`, `is_abstract`, `section`, `magazine_id`, `amountlabor`) VALUES ('4','Ispitivanje uticaja tretmana i mineraloskog sastava na tacku nultog naelektrisanja crvenog mulja',0,'','2','2');
INSERT INTO `nc`.`labor` (`id`, `heading`, `is_abstract`, `section`, `magazine_id`, `amountlabor`) VALUES ('5','Uticaj dodatka nanopunila na svojstva silikonskih materijala na osnovu razlicitih prekursora mreza',0,'','2','1');
INSERT INTO `nc`.`labor` (`id`, `heading`, `is_abstract`, `section`, `magazine_id`, `amountlabor`) VALUES ('6','Mineraloska, fizicko-hemijska i keramicka svojstva gline Brezaci',0,'','2','1');






