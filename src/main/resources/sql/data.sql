-- Insert authors
INSERT INTO authors (name, birth_date, country_name, biography)
VALUES ('Author 1', '1990-05-15', 'USA', 'Author 1 biography'),
       ('Author 2', '1985-08-20', 'UK', 'Author 2 biography'),
       ('Author 3', '1978-03-10', 'Canada', 'Author 3 biography');

-- Insert genres
INSERT INTO genres (name)
VALUES ('Genre 1'),
       ('Genre 2');

-- Insert books
INSERT INTO books (title, author_id, publication_date, description, cover_image_url, number_of_pages)
VALUES ('Book 1 by Author 1', 1, '2022-01-15', 'Description of Book 1', 'https://bookcovers.com/cover1.jpg', 300),
       ('Book 2 by Author 1', 1, '2020-06-30', 'Description of Book 2', 'https://bookcovers.com/cover2.jpg', 250),
       ('Book 1 by Author 2', 2, '2019-03-25', 'Description of Book 3', 'https://bookcovers.com/cover3.jpg', 400),
       ('Book 2 by Author 2', 2, '2018-11-10', 'Description of Book 4', 'https://bookcovers.com/cover4.jpg', 350),
       ('Book by Author 3', 3, '2021-09-05', 'Description of Book 5', 'https://bookcovers.com/cover5.jpg', 500);

-- Insert books_genres
INSERT INTO books_genres (book_id, genre_id)
VALUES (1, 1),
       (2, 2),
       (3, 1),
       (4, 2),
       (5, 1);
