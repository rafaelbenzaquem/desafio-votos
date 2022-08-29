insert into associado(nome,cpf)
values ('teste1','75124377062');

insert into associado(nome,cpf)
values ('teste2','33643624085');

insert into associado(nome,cpf)
values ('teste3','48681499033');

insert into pauta(nome)
values ('pauta teste');

insert into pauta(nome)
values ('pauta teste2');


insert into sessao(inicio, fim, nome, pauta_id)
values (timestampadd(MINUTE, -2, current_time()), timestampadd(MINUTE, -1, current_time()), 'sessao fechada', 1);

insert into sessao(inicio, fim, nome, pauta_id)
values (timestampadd(MINUTE, -2, current_time()), timestampadd(MINUTE, -1, current_time()), 'sessao fechada', 2);

insert into sessao(inicio, fim, nome, pauta_id)
values (current_time(), timestampadd(MINUTE, 1, current_time()), 'sessao aberta', 2);


insert into voto(associado_id, sessao_id,opcao) values (1,1,'NAO');
insert into voto(associado_id, sessao_id,opcao) values (2,1,'SIM');
insert into voto(associado_id, sessao_id,opcao) values (3,1,'NAO');

insert into voto(associado_id, sessao_id,opcao) values (1,3,'SIM');
insert into voto(associado_id, sessao_id,opcao) values (2,3,'SIM');
insert into voto(associado_id, sessao_id,opcao) values (3,3,'SIM');
