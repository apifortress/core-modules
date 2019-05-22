-- Table: public.tests

-- DROP TABLE public.tests;

CREATE TABLE public.tests
(
  id text,
  company_id text,
  project_id text,
  unit text,
  input text,
  name text,
  CONSTRAINT tests_pkey PRIMARY KEY (id)
)