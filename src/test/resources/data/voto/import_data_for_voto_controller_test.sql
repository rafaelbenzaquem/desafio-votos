insert into associado(nome,cpf)
values ('teste1','21676925015');

insert into associado(nome,cpf)
values ('teste2','14293272003');

insert into pauta(nome)
values ('pauta teste');

insert into pauta(nome)
values ('pauta teste2');

insert into sessao(inicio, fim, nome, pauta_id)
values (timestampadd(MINUTE, -2, current_time()),timestampadd(MINUTE, -1, current_time()), 'sessao fechada', 1);

insert into sessao(inicio, fim, nome, pauta_id)
values (current_time(), timestampadd(MINUTE, 1, current_time()), 'sessao aberta', 1);

insert into sessao(inicio, fim, nome, pauta_id)
values (timestampadd(MINUTE, -2, current_time()),timestampadd(MINUTE, -1, current_time()), 'sessao fechada', 2);

insert into voto(associado_id, sessao_id,opcao) values (1,1,'NAO');

