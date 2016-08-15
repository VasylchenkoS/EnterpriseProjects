--liquibase formatted sql
--changeset v.vasylchenko:functions runOnChange:true failOnError:true


-- This code must be in some business logic scope, but for now we doing it here comment

CREATE FUNCTION check_aviable_ingridients(d VARCHAR(50)) RETURNS BOOLEAN AS
$BODY$
DECLARE
  t INT := 0;
  f INT := 0;
  s INT := 0;
  y INT := 0;
BEGIN
  FOR s IN (SELECT id_ingridient FROM dishingredients WHERE id_dish=
                                                            (SELECT id_dish FROM dish WHERE name=d))
  LOOP
    IF ((SELECT quantity FROM storage WHERE id_ingridient=s) > 0)
    THEN
      t := t + 1;
    ELSE
      f := f + 1;
    END IF;
  END LOOP;
  LOOP
    IF (t>f) THEN
      FOR y IN (SELECT id_ingridient FROM dishingredients WHERE id_dish=
                                                                (SELECT id_dish FROM dish WHERE name=d))
        LOOP
          UPDATE storage SET quantity = quantity - 1 WHERE id_ingridient = y ;
        END LOOP;
      RETURN true;
    ELSE
      RETURN false;
    END IF;
  END LOOP;
END
$BODY$
LANGUAGE plpgsql;

(SELECT id_employee FROM employee WHERE
  id_position=(SELECT id_position FROM position WHERE position='Cook' AND surname='Koskitalo');

CREATE FUNCTION choose_cook() RETURNS INT AS
  $BODY$
    DECLARE
      i INT := 0;
    BEGIN
      FOR i IN (SELECT id_employee FROM employee WHERE id_position = (SELECT id_position FROM position WHERE position='Cook'))
        LOOP
          IF ((SELECT count(id_status) FROM kitchen WHERE id_employee=i AND id_status=(SELECT id_dishstatus FROM dishstatus WHERE dishstatus='In Process'))
              >=
              (SELECT count(id_status) FROM kitchen WHERE id_employee=i+1 AND id_status=(SELECT id_dishstatus FROM dishstatus WHERE dishstatus='In Process')))
          THEN
            RETURN i;
          ELSE
            RETURN i+1;
          END IF;
        END LOOP;
    END
  $BODY$
LANGUAGE plpgsql;



