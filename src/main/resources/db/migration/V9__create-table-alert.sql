CREATE TABLE alerts (
  id SERIAL NOT NULL,
   message VARCHAR(255) NOT NULL,
   date_alert date NOT NULL,
   hour_alert TIME NOT NULL,
   user_id INTEGER NOT NULL,
   medicine_id INTEGER NOT NULL,
   CONSTRAINT pk_alerts PRIMARY KEY (id)
);

ALTER TABLE alerts ADD CONSTRAINT fk_alerts_on_medicine FOREIGN KEY (medicine_id) REFERENCES medicines (id);
ALTER TABLE alerts ADD CONSTRAINT fk_alerts_on_user FOREIGN KEY (user_id) REFERENCES users (id);