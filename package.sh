mvn clean package
#java -jar target/posts-1.0-SNAPSHOT.jar
docker build -t cleptes/posts:0.02 .
docker stop posts
docker rm posts
docker run -d --name posts -p 8091:8091 cleptes/posts:0.02