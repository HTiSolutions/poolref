# Pool Referee

Welcome to the initial README for the Pool Referee Application



Deployment (Setting up the box)

1. Install git: `sudo yum install git`

2. Install docker: `sudo yum install docker`

3. Enable docker on boot load: `sudo systemctl enable docker.service`
4. Start docker now: `sudo systemctl start docker`
5. Pull the test hello world docker: `sudo docker pull hello-world`
6. Run the test hello world docker: `sudo docker run hello-world`
7. Confirm you see the following:
    
    "Hello from Docker!
    This message shows that your installation appears to be working correctly."

8. Install a mySql server docker `sudo docker pull mysql/mysql-server`
9. Place the .sql startup script in the docker-entrypoint-initdb.d directory in the root directory.
10. Start a server instance `sudo docker run -p 127.0.0.1:3306:3306 --name pool_ref -e MYSQL_ROOT_PASSWORD=root -d -v /mysql-data:/var/lib/mysql -v /docker-entrypoint-initdb.d:/docker-entrypoint-initdb.d mysql/mysql-server
10.5. Change mysql permissions:
		sudo docker exec -ti pool_ref mysql -u root -p
		grant all privileges on *.* to 'root'@'%' identified by 'password goes here' with grant option;
		flush privileges;

11. Install java `sudo yum install java`
12. Find latest version of java jdk `yum search java | grep 'java-'`
13. Install latest java JDK version`sudo yum install java-1.8.0-openjdk*`
14. Change gradlew privileges `sudo chmod +x gradlew`
15. Build latest version of web application `./gradlew clean build`
16. Run web application `./gradlew bootrun`
