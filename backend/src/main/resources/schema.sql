CREATE TABLE jogo (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    nome            VARCHAR(100)      NOT NULL,
    preco           DECIMAL(10,2)     NOT NULL,
    data_lancamento DATE              NOT NULL,
    plataforma      ENUM('PC','Console') NOT NULL,
    multiplayer  TINYINT(1)   DEFAULT 0 NOT NULL,
    categoria_id    INT               NOT NULL
);

CREATE TABLE categoria (
    id   INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL
);