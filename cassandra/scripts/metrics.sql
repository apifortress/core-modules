-- Table: public.metrics

-- DROP TABLE apiftest.metrics;

CREATE TABLE apiftest.metrics
(
  company_id varchar,
  id varchar,
  date bigint,
  footprint varchar,
  success boolean,
  test frozen<test_ids>,
  data text,
  PRIMARY KEY (company_id,id)
)