create table tbl_usuario(
id bigint not null,
nome character varying(255),
email character varying(255),
constraint user_pk primary key (id)
);

insert into tbl_usuario(id, nome, email) values (1, 'vilson', 'vilson@gmail.com');
insert into tbl_usuario(id, nome, email) values (2, 'joão', 'joao@gmail.com');

select * from tbl_usuario;


create SEQUENCE tbl_usuariosequence
increment 1
minvalue 1
maxvalue 9223372036854775807
start 7;

alter table tbl_usuario ALTER column id set default nextval('tbl_usuariosequence'::regclass);

alter table tbl_usuario add unique (id);

create table tbl_telefone(
id bigint not null,
numero character varying(255) not null,
tipo character varying(255) not null,
usuariopessoa bigint not null,
constraint telefone_id primary key (id)
);

alter table tbl_telefone add foreign key (usuariopessoa) references tbl_usuario(id);

create SEQUENCE tbl_telefonesequence
increment 1
minvalue 1
maxvalue 9223372036854775807
start 1
cache 1;
alter table tbl_telefonesequence
owner to postgres

ALTER TABLE tbl_telefone ALTER COLUMN id SET DEFAULT nextval('tbl_telefonesequence'::regclass);

INSERT INTO tbl_usuario(nome, email) VALUES ('joao maria','joaomaria@gmail.com');
INSERT INTO tbl_telefone(numero, tipo, usuariopessoa) VALUES ('544454545', 'celular', 8);

select * from tbl_telefone;