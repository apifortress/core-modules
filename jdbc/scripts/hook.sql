-- Table: public.hooks

-- DROP TABLE public.hooks;

CREATE TABLE public.hooks
(
  identifier text NOT NULL,
  company_id text,
  project_id text,
  CONSTRAINT hooks_pkey PRIMARY KEY (identifier)
)