insert into pauta(nome)
values ('pauta teste');

insert into pauta(nome)
values ('pauta teste2');


insert into sessao(inicio, fim, nome, pauta_id)
values (timestampadd(MINUTE, -2, current_time()), timestampadd(MINUTE, -1, current_time()), 'sessao fechada', 1);

insert into sessao(inicio, fim, nome, pauta_id)
values (current_time(), timestampadd(MINUTE, 2, current_time()), 'sessao aberta', 1);

insert into sessao(inicio, fim, nome, pauta_id)
values (timestampadd(MINUTE, -2, current_time()), timestampadd(MINUTE, -1, current_time()), 'sessao fechada 2', 2);

insert into sessao(inicio, fim, nome, pauta_id)
values (current_time(), timestampadd(MINUTE, 2, current_time()), 'sessao aberta 2', 2);