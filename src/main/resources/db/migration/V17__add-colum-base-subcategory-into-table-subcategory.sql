ALTER TABLE subcategory
    ADD COLUMN subcategory_base BIGINT,
    ADD CONSTRAINT fk_subcategory_base
    FOREIGN KEY (subcategory_base) REFERENCES subcategory(id);
