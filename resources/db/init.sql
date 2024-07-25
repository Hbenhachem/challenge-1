SELECT 'CREATE DATABASE bm_profile' WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'bm_profile');
\gexec
SELECT 'CREATE DATABASE bm_document' WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'bm_document');
\gexec
