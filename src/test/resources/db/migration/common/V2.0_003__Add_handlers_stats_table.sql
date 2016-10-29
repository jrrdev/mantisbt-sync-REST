-- Table storing the number of issues by handler and status at a given time
CREATE TABLE handlers_stats  (
    id int AUTO_INCREMENT NOT NULL PRIMARY KEY,
    compute_date datetime NOT NULL,
    project_id int NOT NULL,
    handler_id int,
    status_id int,
    nb_issues int NOT NULL,
    
    CONSTRAINT FK_HST_PRJ FOREIGN KEY (project_id) REFERENCES mantis_project_table(id),
	CONSTRAINT FK_HST_REP FOREIGN KEY (handler_id) REFERENCES mantis_user_table(id),
	CONSTRAINT FK_HST_STA FOREIGN KEY (status_id) REFERENCES mantis_enum_status(id)
);