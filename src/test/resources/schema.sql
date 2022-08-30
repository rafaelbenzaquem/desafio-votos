create table IF NOT EXISTS associado
(
    id   bigint NOT NULL AUTO_INCREMENT,
    nome varchar(255) DEFAULT NULL,
    cpf varchar(11) not null unique,
    PRIMARY KEY (id)
);

create table IF NOT EXISTS pauta
(
    id   bigint NOT NULL AUTO_INCREMENT,
    nome varchar(255) DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS sessao
(
    id       bigint NOT NULL AUTO_INCREMENT,
    inicio   datetime(6)  DEFAULT NULL,
    fim      datetime(6)  DEFAULT NULL,
    nome     varchar(255) DEFAULT NULL,
    pauta_id bigint       DEFAULT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (pauta_id) REFERENCES pauta (id)
);

CREATE TABLE IF NOT EXISTS voto
(
    associado_id       bigint NOT NULL,
    sessao_id          bigint NOT NULL,
    opcao     varchar(255) DEFAULT NULL,
    hora_do_voto        datetime,
    PRIMARY KEY (associado_id,sessao_id),
    FOREIGN KEY (associado_id) REFERENCES associado (id),
    FOREIGN KEY (sessao_id) REFERENCES sessao (id)
);