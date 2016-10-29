-- Table storing the organization linked to the users
CREATE TABLE organization  (
    org_id int NOT NULL PRIMARY KEY,
    org_name varchar(32) NOT NULL
);

-- Add a link column
ALTER TABLE mantis_user_table ADD COLUMN org_id int;
ALTER TABLE mantis_user_table ADD FOREIGN KEY (org_id) REFERENCES organization(org_id);