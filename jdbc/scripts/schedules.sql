-- Table: public.schedules

-- DROP TABLE public.schedules;

CREATE TABLE public.schedules
(
  id text NOT NULL,
  name text,
  test_id text,
  project_id text,
  company_id text,
  last_update date,
  paused boolean,
  period_string text,
  timezone text,
  downloader_id text,
  overrides text,
  CONSTRAINT schedules_pkey PRIMARY KEY (id)
)