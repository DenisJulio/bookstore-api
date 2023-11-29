-- Insert authors
INSERT INTO
  authors (name, birth_date, country_name, biography)
VALUES
  (
    'Harper Lee',
    '1926-04-28',
    'USA',
    'Harper Lee was an American novelist widely known for To Kill a Mockingbird, published in 1960.'
  ),
  (
    'George Orwell',
    '1903-06-25',
    'UK',
    'George Orwell was an English novelist, essayist, and critic most famous for his novels ''1984'' and ''Animal Farm''.'
  ),
  (
    'Jane Austen',
    '1775-12-16',
    'UK',
    'Jane Austen was an English novelist known primarily for her six major novels, which interpret, critique and comment upon the British landed gentry at the end of the 18th century.'
  ),
  (
    'Herman Merville',
    '1819-08-01',
    'USA',
    'Herman Melville was an American novelist, short story writer, and poet. He is best known for his novel ''Moby-Dick'' and his novella ''Billy Budd''.'
  ),
  (
    'Leo Tolstoy',
    '1828-09-09',
    'Russia',
    'Leo Tolstoy was a Russian writer who is regarded as one of the greatest authors of all time. He is best known for his novels ''War and Peace'' and ''Anna Karenina''.'
  ),
  (
    'J.R.R. Tolkien',
    '1892-01-03',
    'South Africa',
    'J.R.R. Tolkien was an English writer, poet, philologist, and academic, best known as the author of the high fantasy works ''The Hobbit'' and ''The Lord of the Rings''.'
  );

-- Insert genres
INSERT INTO
  genres (name)
VALUES
  ('Southern Gothic'),
  ('Political fiction'),
  ('Romance'),
  ('Adventure'),
  ('Historical novel'),
  ('Fantasy');

-- Insert books
INSERT INTO
  books (
    title,
    author_id,
    publication_date,
    description,
    cover_image_url,
    number_of_pages
  )
VALUES
  (
    'To Kill a Mockingbird',
    1,
    '1960-07-11',
    'A novel by Harper Lee published in 1960.',
    'https://bookcovers.com/cover1.jpg',
    281
  ),
  (
    '1984',
    2,
    '1949-06-08',
    'A dystopian novel by George Orwell.',
    'https://bookcovers.com/cover2.jpg',
    328
  ),
  (
    'Pride and Prejudice',
    3,
    '1813-01-28',
    'A romantic novel of manners written by Jane Austen.',
    'https://bookcovers.com/cover3.jpg',
    279
  ),
  (
    'Moby Dick',
    4,
    '1851-10-18',
    'A novel by Herman Melville about the voyage of the whaling ship Pequod.',
    'https://bookcovers.com/cover4.jpg',
    720
  ),
  (
    'War and Peace',
    5,
    '1869-01-01',
    'A novel by Leo Tolstoy, detailing the history of the French invasion of Russia.',
    'https://bookcovers.com/cover5.jpg',
    1225
  ),
  (
    'The Fellowship of the Ring',
    6,
    '1954-07-29',
    'The first volume of The Lord of the Rings by J.R.R. Tolkien.',
    'https://bookcovers.com/cover6.jpg',
    423
  ),
  (
    'The Two Towers',
    6,
    '1954-11-11',
    'The second volume of The Lord of the Rings by J.R.R. Tolkien.',
    'https://bookcovers.com/cover7.jpg',
    352
  ),
  (
    'The Return of the King',
    6,
    '1955-10-20',
    'The third volume of The Lord of the Rings by J.R.R. Tolkien.',
    'https://bookcovers.com/cover8.jpg',
    416
  );

-- Insert books_genres
INSERT INTO
  books_genres (book_id, genre_id)
VALUES
  (1, 1),
  (2, 2),
  (3, 3),
  (4, 4),
  (5, 5),
  (6, 4),
  (6, 6),
  (7, 4),
  (7, 6),
  (8, 4),
  (8, 6);