## Build Steps

* Set up environment variables
	* MYSQL_ENDPOINT : the endpoint of the mysql database for example jdbc:mysql://XXXX:3306/XXX
	* MYSQL_USERNAME : mysql db username
	* MYSQL_PASSWORD : mysql db password
	* ACR_LOGIN_SERVER : the registry url of the docker image. For example index.docker.io/v1/
	* ACR_NAME: server id in the maven setting file. The server setting is for the ACR_LOGIN_SERVER
* Run maven command to package and push docker image: mvn package docker:build -DpushImage
* Run in local host: java  -jar target/data-app-[version]
* Or run it in docker:
```
docker run -e MYSQL_ENDPOINT=${MYSQL_ENDPOINT} \
-e MYSQL_USERNAME=${MYSQL_USERNAME} \
-e MYSQL_PASSWORD=${MYSQL_PASSWORD} \
-p [XXXX]:[XXXX] $ACR_LOGIN_SERVER/data-app
```

