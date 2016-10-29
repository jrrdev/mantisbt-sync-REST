-- Add an index on mantis_bug_table.date_submitted to improve aggregated stats query performance
CREATE INDEX idx_bug_date_submitted ON mantis_bug_table (date_submitted);