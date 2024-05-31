create database Q1;
use Q1;

create table Produto(
    idP int(11) not null auto_increment ,
    nome varchar(20) not null , 
    valor_unitario float not null,
    quantidade_estoque int(11) not null default '0' ,
    PRIMARY KEY(idP)
);

create table Venda(
    idV int(11) not null auto_increment ,
    idP int(11) not null ,
    quantidade int(11) not null  , 
    valor_total float not null ,
    PRIMARY KEY(idV),
    KEY idP (idP),
    constraint venda_auto foreign KEY (idP) references Produto (idP)
);

 DELIMITER $$


CREATE PROCEDURE Verificar_Q(in nome_p varchar(20) , out media float)
BEGIN
    declare media float;
    declare quantidade_venda integer;
    declare soma float;
    SELECT COUNT(*) INTO quantidade_venda
    FROM Produto as P, Venda as V where P.nome = nome_p and V.idP = P.idP ;
    SELECT COUNT(*) INTO soma FROM Produto as P,
    Venda as V where P.nome = nome_p and V.quantidade = P.quantidade_estoque ;
     set media = quantidade_venda / soma ;
END $$

DELIMITER ;