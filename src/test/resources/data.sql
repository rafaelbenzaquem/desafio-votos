create table pauta
(
    id   bigint NOT NULL AUTO_INCREMENT,
    nome varchar(255) DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE sessao
(
    id       bigint NOT NULL AUTO_INCREMENT,
    inicio   datetime(6)  DEFAULT NULL,
    fim      datetime(6)  DEFAULT NULL,
    nome     varchar(255) DEFAULT NULL,
    pauta_id bigint       DEFAULT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (pauta_id) REFERENCES pauta(id)
);

insert into pauta(nome)
values ('teste');

insert into sessao(inicio, fim, nome, pauta_id)
values ('2022-01-01 10:00', '2022-01-01 10:01', 'teste', 1);