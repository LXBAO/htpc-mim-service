FROM openjdk:8u342-jdk
# 设置时区为东八区
ENV TZ Asia/Shanghai
COPY ./hpcc-customer-manage-web/target/hpcc-customer-manage-web*.jar  /hpcc-customer-manage-web-1.0.0.jar
COPY entrypoint.sh entrypoint.sh
RUN chmod 755 entrypoint.sh
CMD ["bash","./entrypoint.sh"]
