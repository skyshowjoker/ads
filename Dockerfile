# 使用官方的 OpenJDK 11 作为基础镜像
FROM openjdk:17-alpine
# 设置工作目录
WORKDIR /app

# 复制 Spring Boot 项目的 JAR 文件到镜像中
COPY target/ads-0.0.1.jar .

# 暴露 Spring Boot 项目的端口
EXPOSE 8082

# 启动 Spring Boot 项目
CMD ["java", "-jar", "ads-0.0.1.jar"]
