ALTER TABLE Flight
ADD CONSTRAINT fk_airport_from_airport_id FOREIGN KEY (airport_from_id)
        REFERENCES public.airport (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;
ALTER TABLE Flight
ADD     CONSTRAINT fk_airport_to_airport_id FOREIGN KEY (airport_to_id)
        REFERENCES public.airport (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;
ALTER TABLE Flight
ADD    CONSTRAINT fk_postponed_on_flight_id FOREIGN KEY (postponed_on_flight_id)
        REFERENCES public.flight (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;