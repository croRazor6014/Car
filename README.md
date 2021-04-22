Simple Car microservice
ready for integration with API gateway


to run application:

1. create database example_db
2. adjust connection string in application.yaml
3. add local profile to run configuration  
3. start Application

Example curl

curl --location --request GET 'localhost:8088/api/car' \
--header 'x-consumer-identity: ewogICJuYW1lIjogImxvdnJvLnZybGVjQGdtYWlsLmNvbSIsCiJmYW1pbHlfbmFtZSI6ICJsb3Zyby52cmxlY0BnbWFpbC5jb20iLAoiZ2l2ZW5fbmFtZSI6ICJsb3Zyby52cmxlY0BnbWFpbC5jb20iLAoiZW1haWwiOiAibG92cm8udnJsZWNAZ21haWwuY29tIiwKICAicHJlZmVycmVkX3VzZXJuYW1lIjoibG92cm8udnJsZWNAZ21haWwuY29tIgp9'
