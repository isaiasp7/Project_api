CREATE TABLE aluno
(
    cpf        VARCHAR(11) NOT NULL,
    nome       VARCHAR(50) NOT NULL,
    data_nasc  datetime    NOT NULL,
    quant_falt INT         NOT NULL,
    CONSTRAINT pk_aluno PRIMARY KEY (cpf)
);

CREATE TABLE disciplina
(
    id            INT         NOT NULL,
    nome          VARCHAR(50) NOT NULL,
    carga_horaria INT         NOT NULL,
    CONSTRAINT pk_disciplina PRIMARY KEY (id)
);

CREATE TABLE matricula
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    data_matricula date                  NULL,
    status         VARCHAR(255)          NOT NULL,
    aluno_cpf      VARCHAR(11)           NULL,
    turma_id       BIGINT                NULL,
    CONSTRAINT pk_matricula PRIMARY KEY (id)
);

CREATE TABLE notas
(
    id             BIGINT        NOT NULL,
    nota1          DECIMAL(5, 2) NULL,
    nota2          DECIMAL(5, 2) NULL,
    media          DECIMAL(5, 2) NULL,
    matricula_fk   BIGINT        NULL,
    disciplinan_fk INT           NULL,
    CONSTRAINT pk_notas PRIMARY KEY (id)
);

CREATE TABLE profesor_respons
(
    id            BIGINT       NOT NULL,
    nome          VARCHAR(50)  NOT NULL,
    email         VARCHAR(50)  NULL,
    telefone      VARCHAR(255) NULL,
    disciplina_id INT          NULL,
    CONSTRAINT pk_profesor_respons PRIMARY KEY (id)
);

CREATE TABLE turma
(
    id               BIGINT      NOT NULL,
    serie            VARCHAR(30) NOT NULL,
    nome_sala        VARCHAR(10) NULL,
    capacidade_atual INT         NULL,
    capacidade_max   INT         NULL,
    CONSTRAINT pk_turma PRIMARY KEY (id)
);

ALTER TABLE matricula
    ADD CONSTRAINT uc_matricula_aluno_cpf UNIQUE (aluno_cpf);

ALTER TABLE profesor_respons
    ADD CONSTRAINT uc_profesor_respons_email UNIQUE (email);

ALTER TABLE profesor_respons
    ADD CONSTRAINT uc_profesor_respons_nome UNIQUE (nome);

ALTER TABLE turma
    ADD CONSTRAINT uc_turma_nome_sala UNIQUE (nome_sala);

ALTER TABLE matricula
    ADD CONSTRAINT FK_MATRICULA_ON_ALUNO_CPF FOREIGN KEY (aluno_cpf) REFERENCES aluno (cpf);

ALTER TABLE matricula
    ADD CONSTRAINT FK_MATRICULA_ON_TURMA FOREIGN KEY (turma_id) REFERENCES turma (id);

ALTER TABLE notas
    ADD CONSTRAINT FK_NOTAS_ON_DISCIPLINAN_FK FOREIGN KEY (disciplinan_fk) REFERENCES disciplina (id);

ALTER TABLE notas
    ADD CONSTRAINT FK_NOTAS_ON_MATRICULA_FK FOREIGN KEY (matricula_fk) REFERENCES matricula (id);

ALTER TABLE profesor_respons
    ADD CONSTRAINT FK_PROFESOR_RESPONS_ON_DISCIPLINA FOREIGN KEY (disciplina_id) REFERENCES disciplina (id);