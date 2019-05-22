-- Table: public.vault

-- DROP TABLE public.vault;

CREATE TABLE public.vault
(
  project_id text,
  company_id text,
  data text,
  CONSTRAINT vault_pkey PRIMARY KEY (project_id, company_id)
)