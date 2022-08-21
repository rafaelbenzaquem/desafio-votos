create table pauta (
id bigint NOT NULL AUTO_INCREMENT,
nome varchar(255) DEFAULT NULL,
PRIMARY KEY (id)
);

insert into pauta(nome) values ('teste');