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