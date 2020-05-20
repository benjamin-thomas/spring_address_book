TRUNCATE country RESTART IDENTITY CASCADE; -- only used when iterating on this file

-- Declare ids that will be targeted by JPA tests here.
-- Negative values will never clash with autoincrements

DO $$
DECLARE france_id INT;
DECLARE spain_id INT;

DECLARE paris_id INT;
DECLARE madrid_id INT;

BEGIN
    -- Country
    SELECT -1 INTO france_id;
    SELECT -2 INTO spain_id;

    -- City
    SELECT -1 INTO paris_id;
    SELECT -2 INTO madrid_id;

INSERT INTO country (id, name, code) OVERRIDING SYSTEM VALUE
     VALUES (france_id, 'France', 'fr')
          , (spain_id, 'Spain', 'en')
    ;

INSERT INTO city (country_id, id, name, zip_code) OVERRIDING SYSTEM VALUE
     VALUES (france_id, paris_id, 'Paris', '75001')
          , (spain_id, madrid_id, 'Madrid', '28001')
    ;

-- Test expectation: France will have 11 cities (Paris + 10)
INSERT INTO city (country_id, name, zip_code)
       SELECT france_id
     , 'City#' || i
     , 'ABC-' || (i*random()*1000)::INT
       FROM generate_series(1, 10)i
     ;

END $$;

SELECT * FROM country;
SELECT * FROM city;
