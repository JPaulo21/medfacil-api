ALTER TABLE medicines RENAME COLUMN duration to end_medication;

ALTER TABLE medicines ALTER COLUMN end_medication TYPE TIMESTAMP USING end_medication::TIMESTAMP;