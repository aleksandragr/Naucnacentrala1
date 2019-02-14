INSERT INTO `nc`.`editor_reviewer` (`id`, `name`, `is_editor`, `is_reviewer`, `username`, `password`, `email`) VALUES ('1','Pera',1,1,'pera','$2a$10$C3Csh880Volm4lDyaXPIf.KbaeLZEmPNQhbiCLmBJblYLApxVvkk6','donthavename3478@gmail.com');

INSERT INTO `nc`.`editor_reviewer` (`id`, `name`, `is_editor`, `is_reviewer`, `username`, `password`, `email`) VALUES ('2','Mika',0,1,'mika','$2a$10$zg1MJVAesqU5dTR5icBqLuF./O1hEZ23mGea0BffNdqVk/uOctgVa','donthavename3478@gmail.com');

INSERT INTO `nc`.`magazine` (`id`, `name`, `main_editor_id`, `payment_method`, `issnnumber`, `merchant_id`, `merchant_password`, `amountmag`, `dbfile_id`, `bitcointoken`, `client_id`, `client_secret`) VALUES ('1','Hemijska industrija','1','no open-access','0367-598X','977MHIik87?9iuT7ug%RFhu5d./','password@1',2,'8','qzBsz_brLvJz-pqaSJoiJLPCtfEB4gLfx6ppqJgF','AZtrQ1uCTTPMt5O7dW85gDol7yrOFyKVOrsNBA_Wg5LVa4hRS4PqyN_DYhcJ9VECXozuuPdfoLaEeNk7','EOqa9RNchmILmCz-k_VEy8JwRQBxypMMOlCcZa6ZEXFMHS-E_KjDz-6vHNYZ-9_anhBAxfShmO_y87Eg');
INSERT INTO `nc`.`magazine` (`id`, `name`, `main_editor_id`, `payment_method`, `issnnumber`, `merchant_id`, `merchant_password`, `amountmag`, `dbfile_id`, `bitcointoken`, `client_id`, `client_secret`) VALUES ('2','Zastita materijala', '2','no open-access','0351-9465','411BNG5dryh654f6j/9hrEEESX0','password@2',3,'9','av5sS6kdwu2Kn7_gsbBoxHFxuwfNyRvzpEFhMvHP','AShgvwXsTGRklfK9XbnpTFI1Izbxmr9ZTko1bvdsb8K7W0giKmIRGD0aN9cxpI-GiNf3v16JL8lQIAKC','EN99azhDWWQ9ErDYhzpKtJcQkk7bC-ZFbXDLy-RDZvGtC4dBvPVu6YOZSiL6koWOfB_lcZZGj1whZbRe');

INSERT INTO `nc`.`magazine` (`id`, `name`, `main_editor_id`, `payment_method`, `issnnumber`, `merchant_id`, `merchant_password`, `amountmag`, `dbfile_id`, `bitcointoken`, `client_id`, `client_secret`) VALUES ('3','Stomatoloski informator', '2','open-access','1451-3439','411BNG5dryh654f6j/9hroooooo','password@3',1,'10','HZduitZqgbPHAYXHEkP27UViDbfbxEs2ct7ER4C_','AWiC5_aNn8KZOJwdgrTPiKpA_KLXV3hAChnsbB2iVokalArW__hRkJkCTvWhoq4fDPhZzTwdmDUqMzrR','EIxn6-h5lDVEOMEteW-7IcTZ6JQlN9niGGRp82gwUiS3pMy47LTujZwWRgUpTLFVaeP237JeF4GaHOeV');


INSERT INTO `nc`.`magazine_other_editors` (`magazine_id`, `other_editors_id`) VALUES ('1','2');

INSERT INTO `nc`.`magazine_reviewers` (`magazine_id`, `reviewers_id`) VALUES ('1','3');

INSERT INTO `nc`.`scientific_area` (`id`, `code`, `name`) VALUES ('1','EHI','Elektrohemijsko inzenjerstvo');
INSERT INTO `nc`.`scientific_area` (`id`, `code`, `name`) VALUES ('2','FAI','Farmaceutsko inzenjerstvo');
INSERT INTO `nc`.`scientific_area` (`id`, `code`, `name`) VALUES ('3','PRH','Primenjena hemija');

INSERT INTO `nc`.`scientific_area` (`id`, `code`, `name`) VALUES ('4','FIZ','Fizika');
INSERT INTO `nc`.`scientific_area` (`id`, `code`, `name`) VALUES ('5','HEM','Hemija');


INSERT INTO `nc`.`magazine_sections` (`magazine_id`, `sections_id`) VALUES ('1','1');
INSERT INTO `nc`.`magazine_sections` (`magazine_id`, `sections_id`) VALUES ('1','2');

INSERT INTO `nc`.`role` (`id`, `description`, `name`) VALUES ('1','user','USER');
INSERT INTO `nc`.`role` (`id`, `description`, `name`) VALUES ('2','author','AUTHOR');
INSERT INTO `nc`.`role` (`id`, `description`, `name`) VALUES ('3','editor','EDITOR');
INSERT INTO `nc`.`role` (`id`, `description`, `name`) VALUES ('4','reviewer','REVIEWER');

INSERT INTO `nc`.`user_author` (`id`, `username`, `password`, `is_author`, `name`, `surname`, `email`) VALUES ('1','gagi','$2a$10$2sQKNmLgwfIyHkmHNqT8V.ib75p8RKZI7.CciEc5NBO/yPkVYPeue',1,'Dragan','Dulic','isaisic1@gmail.com');
INSERT INTO `nc`.`user_author` (`id`, `username`, `password`, `is_author`, `name`, `surname`, `email`) VALUES ('3','marko','$2a$10$RaeLOT0MMg5jgTd2VHUO2OGgyZlnS6RJOVmkLL47m3ziCXqziCXZG',1,'Marko','Juric','isaisic1@gmail.com');
INSERT INTO `nc`.`user_author` (`id`, `username`, `password`, `is_author`, `name`, `surname`, `email`) VALUES ('2','saska','$2a$10$hT9rS7aXo5KiZe3/VCD4NO/sX9fq0/A02n8yrp8p79gxgK7zO.5Iu',0,'Aleksandra','Grujic','isaisic1@gmail.com');



INSERT INTO `nc`.`membership_fee` (`id`, `start_date`, `end_date`, `price`, `magazine_id`, `user_id`) VALUES ('1','2018.12.23.','2019.02.11.','1','1','1');


INSERT INTO `nc`.`user_author_roles` (`user_author_id`, `roles_id`) VALUES ('1','2');
INSERT INTO `nc`.`user_author_roles` (`user_author_id`, `roles_id`) VALUES ('3','2');
INSERT INTO `nc`.`user_author_roles` (`user_author_id`, `roles_id`) VALUES ('2','1');

INSERT INTO `nc`.`editor_reviewer_roles` (`editor_reviewer_id`, `roles_id`) VALUES ('1','3');
INSERT INTO `nc`.`editor_reviewer_roles` (`editor_reviewer_id`, `roles_id`) VALUES ('2','3');

INSERT INTO `nc`.`labor` (`id`, `heading`, `abstracttext`, `scientificarea_id`, `magazine_id`, `amountlabor`, `dbfile_id`) VALUES ('1','Mineraloske i kristalografske osobine zeolitskog tufa HEU-tipa lokaliteta Novakovic, Bosna i Hercegovina','','3','1','1','1');
INSERT INTO `nc`.`labor` (`id`, `heading`, `abstracttext`, `scientificarea_id`, `magazine_id`, `amountlabor`, `dbfile_id`) VALUES ('2','Upravljacko – nadzorni sistem pogona za proizvodnju biodizela','','1','1','1','2');
INSERT INTO `nc`.`labor` (`id`, `heading`, `abstracttext`, `scientificarea_id`, `magazine_id`, `amountlabor`, `dbfile_id`) VALUES ('3','Cvrste disperzije sa karbamazepinom optimizacija formulacija, karakterizacija i ispitivanje dugorocne stabilnosti','','2','1','1','3');


INSERT INTO `nc`.`labor` (`id`, `heading`, `abstracttext`, `scientificarea_id`, `magazine_id`, `amountlabor`, `dbfile_id`) VALUES ('4','Hibridni metod za detekciju ivica na TEM slikama nanocestica','','4','2','2','4');
INSERT INTO `nc`.`labor` (`id`, `heading`, `abstracttext`, `scientificarea_id`, `magazine_id`, `amountlabor`, `dbfile_id`) VALUES ('5','Ispitivanje uticaja tretmana i mineraloskog sastava na tacku nultog naelektrisanja crvenog mulja','','4','2','2','5');
INSERT INTO `nc`.`labor` (`id`, `heading`, `abstracttext`, `scientificarea_id`, `magazine_id`, `amountlabor`, `dbfile_id`) VALUES ('6','Uticaj dodatka nanopunila na svojstva silikonskih materijala na osnovu razlicitih prekursora mreza','','5','2','1','6');
INSERT INTO `nc`.`labor` (`id`, `heading`, `abstracttext`, `scientificarea_id`, `magazine_id`, `amountlabor`, `dbfile_id`) VALUES ('7','Mineraloska, fizicko-hemijska i keramicka svojstva gline Brezaci','','5','2','1','7');


INSERT INTO `nc`.`labor` (`id`, `heading`, `abstracttext`, `scientificarea_id`, `magazine_id`, `amountlabor`, `dbfile_id`) VALUES ('8','Rad1','','4','3','3','11');


INSERT INTO `nc`.`magazine_scientific_area` (`magazine_id`, `scientific_area_id`) VALUES ('1','1');
INSERT INTO `nc`.`magazine_scientific_area` (`magazine_id`, `scientific_area_id`) VALUES ('1','2');
INSERT INTO `nc`.`magazine_scientific_area` (`magazine_id`, `scientific_area_id`) VALUES ('1','3');

INSERT INTO `nc`.`magazine_scientific_area` (`magazine_id`, `scientific_area_id`) VALUES ('2','4');
INSERT INTO `nc`.`magazine_scientific_area` (`magazine_id`, `scientific_area_id`) VALUES ('2','5');

