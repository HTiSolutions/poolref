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
9. Start a server instance `sudo docker run --name pool_ref -e MYSQL_ROOT_PASSWORD=root -d mysql/mysql-server`
10. Run sql initialisation script

11. Install java `sudo yum install java`
12. Find latest version of java jdk `yum search java | grep 'java-'`
13. Install latest java JDK version`sudo yum install java-1.8.0-openjdk*`
14. Change gradlew privileges `sudo chmod +x gradlew`
15. Build latest version of web application `./gradlew clean build`
16. Run web application `gradle bootrun`
