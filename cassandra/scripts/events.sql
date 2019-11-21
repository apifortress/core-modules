-- Table: public.events

-- DROP TABLE public.events;

CREATE TABLE apiftest.events (
	company_id varchar,
	id varchar,
	date bigint,
	test test_ids,
	counters test_counters,
	data varchar,
	PRIMARY KEY (company_id,id)
);