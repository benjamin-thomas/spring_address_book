CREATE TABLE city
(
    id         BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    country_id BIGINT NOT NULL REFERENCES country(id),

    name TEXT NOT NULL UNIQUE CHECK (length(name) < 100),
    zip_code TEXT NOT NULL CHECK (length(zip_code) < 50)
)