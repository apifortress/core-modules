-- Table: public.events

-- DROP TABLE public.events;

CREATE TABLE public.events
(
  id text NOT NULL,
  company_id text,
  project_id text,
  test_id text,
  data text,
  date bigint,
  CONSTRAINT events_pkey PRIMARY KEY (id)
)
