delete from SHRTIMZONDTL where cmpcod = 'VA';
INSERT INTO SHRTIMZONDTL ( TIMZONCOD, SERNUM, CMPCOD, OFSPER, STRDAT,ENDDAT, ACTFLG) VALUES ('Africa/Abidjan',1,'VA',0,to_date('1-1-2010 0:0:0','dd-mm-yyyy hh24:mi:ss'),to_date('31-12-2020 23:59:59','dd-mm-yyyy hh24:mi:ss'),'Y');
UPDATE SHRTIMZONDTL SET GMTSTRDAT=to_date('1-1-2010 0:0:0','dd-mm-yyyy hh24:mi:ss'),GMTENDDAT=to_date('31-12-2020 23:59:59','dd-mm-yyyy hh24:mi:ss') WHERE TIMZONCOD='Africa/Abidjan' AND SERNUM=1 AND CMPCOD='VA';
commit;