CREATE TABLE if not exists template_model (
   id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   nome VARCHAR(255),
   email VARCHAR(255),
   endereco VARCHAR(255),
--    logradouro VARCHAR(255),
--    numero INTEGER,
--    complemento VARCHAR(255),
--    bairro VARCHAR(255),
--    cidade VARCHAR(255),
--    estado VARCHAR(255),
   CONSTRAINT pk_template_model PRIMARY KEY (id)
);