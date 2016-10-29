-- Tables for MantisBT enumerations

CREATE TABLE mantis_enum_custom_field_types  (
    id int NOT NULL PRIMARY KEY,
    name varchar(32) NOT NULL
);

INSERT INTO mantis_enum_custom_field_types (id, name) values (0, 'String');
INSERT INTO mantis_enum_custom_field_types (id, name) values (1, 'Numeric');
INSERT INTO mantis_enum_custom_field_types (id, name) values (2, 'Float');
INSERT INTO mantis_enum_custom_field_types (id, name) values (3, 'Enumeration');
INSERT INTO mantis_enum_custom_field_types (id, name) values (4, 'E-mail');
INSERT INTO mantis_enum_custom_field_types (id, name) values (5, 'Checkbox');
INSERT INTO mantis_enum_custom_field_types (id, name) values (6, 'List');
INSERT INTO mantis_enum_custom_field_types (id, name) values (7, 'Multiselection list');
INSERT INTO mantis_enum_custom_field_types (id, name) values (8, 'Date');
INSERT INTO mantis_enum_custom_field_types (id, name) values (9, 'Radio');
INSERT INTO mantis_enum_custom_field_types (id, name) values (10, 'Textarea');


CREATE TABLE mantis_enum_etas  (
    id int NOT NULL PRIMARY KEY,
    name varchar(32) NOT NULL
);

CREATE TABLE mantis_enum_priorities  (
    id int NOT NULL PRIMARY KEY,
    name varchar(32) NOT NULL
);

CREATE TABLE mantis_enum_projections  (
    id int NOT NULL PRIMARY KEY,
    name varchar(32) NOT NULL
);

CREATE TABLE mantis_enum_project_status  (
    id int NOT NULL PRIMARY KEY,
    name varchar(32) NOT NULL
);

CREATE TABLE mantis_enum_project_view_states  (
    id int NOT NULL PRIMARY KEY,
    name varchar(32) NOT NULL
);

CREATE TABLE mantis_enum_reproducibilities  (
    id int NOT NULL PRIMARY KEY,
    name varchar(32) NOT NULL
);

CREATE TABLE mantis_enum_resolutions  (
    id int NOT NULL PRIMARY KEY,
    name varchar(32) NOT NULL
);

CREATE TABLE mantis_enum_severities  (
    id int NOT NULL PRIMARY KEY,
    name varchar(32) NOT NULL
);

CREATE TABLE mantis_enum_status  (
    id int NOT NULL PRIMARY KEY,
    name varchar(32) NOT NULL
);

-- Tables for data related to projects

CREATE TABLE mantis_project_table  (
    id int NOT NULL PRIMARY KEY,
    name varchar(128)
);

CREATE TABLE mantis_project_hierarchy_table  (
    parent_id int NOT NULL,
    child_id int NOT NULL,
    
    PRIMARY KEY(parent_id, child_id),
    CONSTRAINT FK_PRH_PRJ_1 FOREIGN KEY (parent_id) REFERENCES mantis_project_table(id),
    CONSTRAINT FK_PRH_PRJ_2 FOREIGN KEY (child_id) REFERENCES mantis_project_table(id)
);

CREATE TABLE mantis_category_table  (
    id int AUTO_INCREMENT NOT NULL PRIMARY KEY,
    project_id int NOT NULL,
    name varchar(128) NOT NULL,
    
    CONSTRAINT FK_CAT_PRJ FOREIGN KEY (project_id) REFERENCES mantis_project_table(id)
);

CREATE TABLE mantis_custom_field_table  (
    id int NOT NULL PRIMARY KEY,
    name varchar(64) NOT NULL,
    type_id int,
    possible_values text,
    default_value varchar(255),
    valid_regexp varchar(255),
    
    CONSTRAINT FK_CUF_CFT FOREIGN KEY (type_id) REFERENCES mantis_enum_custom_field_types(id)
);

CREATE TABLE mantis_custom_field_project_table  (
    field_id int NOT NULL,
    project_id int NOT NULL,
    
    PRIMARY KEY(project_id, field_id),
    CONSTRAINT FK_CFP_CUF FOREIGN KEY (field_id) REFERENCES mantis_custom_field_table(id),
    CONSTRAINT FK_CFP_PRJ FOREIGN KEY (project_id) REFERENCES mantis_project_table(id)
);

CREATE TABLE mantis_user_table  (
    id int NOT NULL PRIMARY KEY,
    name varchar(64)
);

CREATE TABLE mantis_project_user_list_table  (
    project_id int NOT NULL,
    user_id int NOT NULL,
    
    PRIMARY KEY(project_id, user_id),
    CONSTRAINT FK_PUL_USR FOREIGN KEY (user_id) REFERENCES mantis_user_table(id),
    CONSTRAINT FK_PUL_PRJ FOREIGN KEY (project_id) REFERENCES mantis_project_table(id)
);

CREATE TABLE mantis_project_version_table (
	id int NOT NULL PRIMARY KEY,
	project_id int NOT NULL,
	version varchar(64) NOT NULL,
	description text,
	released boolean,
	obsolete boolean,

    CONSTRAINT FK_PJV_PRJ FOREIGN KEY (project_id) REFERENCES mantis_project_table(id)
);

-- Tables for the data related to issues

CREATE TABLE mantis_bug_table (
	id int NOT NULL PRIMARY KEY,
	project_id int NOT NULL,
	reporter_id int,
	handler_id int,
	priority_id int,
	severity_id int,
	status_id int,
	resolution_id int,
	description text,
	steps_to_reproduce text,
	additional_information text,
	platform varchar(32),
	version varchar(64),
	fixed_in_version varchar(64),
	target_version varchar(64),
	summary varchar(128) NOT NULL,
	category varchar(128),
	date_submitted datetime,
	last_updated datetime,
	last_sync datetime NOT NULL,
	
	CONSTRAINT FK_BUG_PRJ FOREIGN KEY (project_id) REFERENCES mantis_project_table(id),
	CONSTRAINT FK_BUG_REP FOREIGN KEY (reporter_id) REFERENCES mantis_user_table(id),
	CONSTRAINT FK_BUG_HDR FOREIGN KEY (handler_id) REFERENCES mantis_user_table(id),
	CONSTRAINT FK_BUG_PRI FOREIGN KEY (priority_id) REFERENCES mantis_enum_priorities(id),
	CONSTRAINT FK_BUG_SEV FOREIGN KEY (severity_id) REFERENCES mantis_enum_severities(id),
	CONSTRAINT FK_BUG_STA FOREIGN KEY (status_id) REFERENCES mantis_enum_status(id),
	CONSTRAINT FK_BUG_RES FOREIGN KEY (resolution_id) REFERENCES mantis_enum_resolutions(id)
);

CREATE TABLE mantis_bugnote_table (
	id int NOT NULL PRIMARY KEY,
	bug_id int NOT NULL,
	reporter_id int NOT NULL,
	text_note text,
	date_submitted datetime,
	last_modified datetime,
	
	CONSTRAINT FK_BGN_BUG FOREIGN KEY (bug_id) REFERENCES mantis_bug_table(id),
	CONSTRAINT FK_BGN_REP FOREIGN KEY (reporter_id) REFERENCES mantis_user_table(id)
);

CREATE TABLE mantis_custom_field_string_table (
	field_id int NOT NULL,
	bug_id int NOT NULL,
	field_value varchar(255),
	
	PRIMARY KEY(field_id, bug_id),
	CONSTRAINT FK_CFS_CUF FOREIGN KEY (field_id) REFERENCES mantis_custom_field_table(id),
	CONSTRAINT FK_CFS_BUG FOREIGN KEY (bug_id) REFERENCES mantis_bug_table(id)
);

CREATE TABLE mantis_bug_history_table (
	id int AUTO_INCREMENT NOT NULL PRIMARY KEY,
	bug_id int NOT NULL,
	user_id int NOT NULL,
	field_name varchar(64),
	old_value varchar(255),
	new_value varchar(255),
	history_type int,
	date_modified datetime NOT NULL,
	
	CONSTRAINT FK_BGH_USR FOREIGN KEY (user_id) REFERENCES mantis_user_table(id),
	CONSTRAINT FK_BGH_BUG FOREIGN KEY (bug_id) REFERENCES mantis_bug_table(id)
);

-- Indexes

CREATE INDEX idx_cat_proj ON mantis_category_table (project_id);
CREATE INDEX idx_field_type ON mantis_custom_field_table (type_id);
CREATE INDEX idx_version_proj ON mantis_project_version_table (project_id);

CREATE INDEX idx_mantis_proj ON mantis_bug_table (project_id);
CREATE INDEX idx_mantis_reporter_usr ON mantis_bug_table (reporter_id);
CREATE INDEX idx_mantis_handler_usr ON mantis_bug_table (handler_id);
CREATE INDEX idx_mantis_priority ON mantis_bug_table (priority_id);
CREATE INDEX idx_mantis_severity ON mantis_bug_table (severity_id);
CREATE INDEX idx_mantis_status ON mantis_bug_table (status_id);
CREATE INDEX idx_mantis_resolution ON mantis_bug_table (resolution_id);

CREATE INDEX idx_bugnote_mantis ON mantis_bugnote_table (bug_id);
CREATE INDEX idx_bugnote_reporter_usr ON mantis_bugnote_table (reporter_id);

CREATE INDEX idx_bughistory_mantis ON mantis_bug_history_table (bug_id);
CREATE INDEX idx_bughistory_reporter_usr ON mantis_bug_history_table (user_id);