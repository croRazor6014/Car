Simple Car microservice
ready for integration with API gateway


to run application:

1. create database example_db
2. adjust connection string in application.yaml
3. add local profile to run configuration  
3. start Application

Example curl

curl --location --request GET 'localhost:8088/api/car' \
--header 'x-consumer-identity: ewogICJuYW1lIjogImN0Zi1hZG1pbkBwYW4tbmV0LmV1IiwKImZhbWlseV9uYW1lIjogImN0Zi1hZG1pbkBwYW4tbmV0LmV1IiwKImdpdmVuX25hbWUiOiAiY3RmLWFkbWluQHBhbi1uZXQuZXUiLAoiZW1haWwiOiAiY3RmLWFkbWluQHBhbi1uZXQuZXUiLAogICJwcmVmZXJyZWRfdXNlcm5hbWUiOiJjdGYtYWRtaW5AcGFuLW5ldC5ldSIKfQ=='


