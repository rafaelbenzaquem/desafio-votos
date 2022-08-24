create table associado
(
    id   bigint NOT NULL AUTO_INCREMENT,
    nome varchar(255) DEFAULT NULL,
    cpf varchar(11) not null unique,
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

CREATE TABLE voto
(
    associado_id       bigint NOT NULL,
    sessao_id          bigint NOT NULL,
    opcao     varchar(255) DEFAULT NULL,
    PRIMARY KEY (associado_id,sessao_id),
    FOREIGN KEY (associado_id) REFERENCES associado (id),
    FOREIGN KEY (sessao_id) REFERENCES sessao (id)
);

insert into associado(nome,cpf)
values ('teste1','75124377062');

insert into associado(nome,cpf)
values ('teste2','33643624085');

insert into pauta(nome)
values ('pauta teste');

insert into pauta(nome)
values ('pauta teste2');

insert into pauta(nome)
values ('pauta teste3');

insert into sessao(inicio, fim, nome, pauta_id)
values (timestampadd(MINUTE, -1, current_time()), current_time(), 'sessao fechada', 1);

insert into sessao(inicio, fim, nome, pauta_id)
values (current_time(), timestampadd(MINUTE, 1, current_time()), 'sessao aberta', 1);

insert into sessao(inicio, fim, nome, pauta_id)
values (current_time(), timestampadd(MINUTE, 1, current_time()), 'sessao aberta 2', 2);

insert into sessao(inicio, fim, nome, pauta_id)
values (timestampadd(MINUTE, -1, current_time()), current_time(), 'sessao fechada', 3);

insert into voto(associado_id, sessao_id,opcao) values (1,2,'NAO');
insert into voto(associado_id, sessao_id,opcao) values (2,2,'SIM');
insert into voto(associado_id, sessao_id,opcao) values (2,3,'NAO');