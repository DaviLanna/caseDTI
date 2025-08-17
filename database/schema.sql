-- database/schema.sql
CREATE TABLE IF NOT EXISTS filmes (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    titulo TEXT NOT NULL,
    diretor TEXT NOT NULL,
    ano_lancamento INTEGER NOT NULL,
    data_assistido TEXT, -- Formato YYYY-MM-DD (Opcional)
    nota REAL          -- Nota de 0 a 10 (Opcional)
);