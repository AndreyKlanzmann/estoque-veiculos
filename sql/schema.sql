-- Modelo fisico do banco (MySQL)
-- O Hibernate cria as mesmas tabelas sozinho (ddl-auto=update),
-- este script serve de documentacao e para criar o banco manualmente.

CREATE DATABASE IF NOT EXISTS estoque_veiculos
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE estoque_veiculos;

CREATE TABLE IF NOT EXISTS marca (
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(60) NOT NULL,
    CONSTRAINT uk_marca_nome UNIQUE (nome)
);

CREATE TABLE IF NOT EXISTS modelo (
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome     VARCHAR(80) NOT NULL,
    marca_id BIGINT NOT NULL,
    CONSTRAINT fk_modelo_marca FOREIGN KEY (marca_id) REFERENCES marca (id),
    CONSTRAINT uk_modelo_marca_nome UNIQUE (marca_id, nome)
);

CREATE TABLE IF NOT EXISTS veiculo (
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    modelo_id      BIGINT NOT NULL,
    ano_fabricacao INT NOT NULL,
    cor            VARCHAR(30) NOT NULL,
    preco          DECIMAL(12,2) NOT NULL,
    quilometragem  INT NOT NULL,
    status         VARCHAR(20) NOT NULL,
    CONSTRAINT fk_veiculo_modelo FOREIGN KEY (modelo_id) REFERENCES modelo (id)
);
