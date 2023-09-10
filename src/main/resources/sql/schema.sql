CREATE TABLE IF NOT EXISTS authors
(
    author_id   SERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    birth_date  DATE,
    country_name VARCHAR(255),
    biography   TEXT
);

CREATE TABLE IF NOT EXISTS genres
(
    genre_id SERIAL PRIMARY KEY,
    name     VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS books
(
    book_id          SERIAL PRIMARY KEY,
    title            VARCHAR(255) NOT NULL,
    author_id        INT,
    publication_date DATE,
    description      TEXT,
    cover_image_url  VARCHAR(255),
    number_of_pages  INT,
    FOREIGN KEY (author_id) REFERENCES authors (author_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS books_genres
(
    book_id  INT,
    genre_id INT,
    PRIMARY KEY (book_id, genre_id),
    FOREIGN KEY (book_id) REFERENCES books (book_id) ON DELETE CASCADE,
    FOREIGN KEY (genre_id) REFERENCES genres (genre_id) ON DELETE CASCADE
);
