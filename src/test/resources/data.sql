create table associado
(
    id   bigint NOT NULL AUTO_INCREMENT,
    nome varchar(255) DEFAULT NULL,
    PRIMARY KEY (id)
);

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
    FOREIGN KEY (pauta_id) REFERENCES pauta (id)
);

insert into associado(nome)
values ('teste');

insert into pauta(nome)
values ('pauta teste');

insert into sessao(inicio, fim, nome, pauta_id)
values (timestampadd(MINUTE, -1, current_time()), current_time(), 'sessao fechada', 1);

insert into sessao(inicio, fim, nome, pauta_id)
values (current_time(), timestampadd(MINUTE, 1, current_time()), 'sessao aberta', 1);