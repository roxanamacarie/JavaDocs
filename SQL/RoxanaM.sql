SELECT MIN(SALARY),MAX(SALARY)
FROM EMPLOYEES E LEFT JOIN DEPARTMENTS D ON E.DEPARTMENT_ID = D.DEPARTMENT_ID
WHERE D.LOCATION_ID=1700;