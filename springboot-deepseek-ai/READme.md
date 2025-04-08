HOW TO START THIS APPLICATION
1) make sure that docker is running
2) create build using mvn clean install
2) docker build -t springboot-deepseek-demo . 
3) docker run -p 8081:8081 springboot-deepseek-demo