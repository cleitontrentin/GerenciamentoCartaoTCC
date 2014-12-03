drop database if exists dbCartao;

create database dbCartao;

use dbCartao;

CREATE TABLE Cartao (
	idCartao INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(45) NOT NULL,
	Carencia INT NOT NULL,
	taxa DECIMAL(8,4) NOT NULL
);



CREATE TABLE usuario (
	idusuario INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(45) NOT NULL,
	login VARCHAR(45) NOT NULL,
	senha VARCHAR(45) NOT NULL
);




CREATE TABLE transacao (
	idtransacao INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	dataVenda DATE NOT NULL,
	valor DECIMAL(8,2) NOT NULL,
	idCartao INT NOT NULL,
	idusuario INT NOT NULL,
	CONSTRAINT fk_transacao_Cartao FOREIGN KEY (idCartao) REFERENCES Cartao (idCartao) ON DELETE NO ACTION ON UPDATE cascade,
	CONSTRAINT fk_transacao_usuario FOREIGN KEY (idusuario) REFERENCES usuario (idusuario) ON DELETE NO ACTION ON UPDATE cascade
);



select * from usuario;
select * from cartao;
