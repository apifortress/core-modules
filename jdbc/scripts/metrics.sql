-- Table: public.metrics

-- DROP TABLE public.metrics;

CREATE TABLE public.metrics
(
  id text,
  company_id text,
  project_id text,
  test_id text,
  data text,
  date bigint,
  footprint text,
  success boolean,
  CONSTRAINT metrics_pkey PRIMARY KEY (id)
)