-- *********************************************************************
-- SQL to roll back currently unexecuted changes
-- *********************************************************************
-- Change Log: D:/Home/Java/EnterpriseProjects/Maven/Module6/src/main/resources/liquibase/Changelog.xml
-- Ran at: 15.08.16 11:01
-- Against: user@jdbc:postgresql://localhost:5432/restaurant
-- Liquibase version: 3.5.1
-- *********************************************************************

-- Lock Database
UPDATE databasechangeloglock SET LOCKED = TRUE, LOCKEDBY = 'vvasilchenko-PC (192.168.1.125)', LOCKGRANTED = '2016-08-15 11:01:38.072' WHERE ID = 1 AND LOCKED = FALSE;

-- Rolling Back ChangeSet: src/main/resources/liquibase/changesets/add_data_stage2.sql::add_data_stage2::v.vasylchenko
-- Release Database Lock
UPDATE databasechangeloglock SET LOCKED = FALSE, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

