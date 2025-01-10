#!/bin/bash

PROJECT_DIR=$(pwd)
JAR_FILE="logistics-0.0.1-SNAPSHOT.jar"
JAR_PATH="$PROJECT_DIR/build/libs/$JAR_FILE"
LOG_FILE="$PROJECT_DIR/output.log"

nohup java -jar $JAR_PATH > $LOG_FILE 2>&1 &

if [ ! -f "$JAR_PATH" ]; then
  echo "오류: $JAR_PATH에 빌드된 JAR 파일이 존재하지 않습니다."
  exit 1
fi

nohup java -jar $JAR_PATH > $LOG_FILE 2>&1 &

echo "Spring Boot 애플리케이션이 PID: $!로 시작되었습니다."
echo "로그는 다음 파일에 기록됩니다: $LOG_FILE"
echo "로그를 확인하려면 다음 명령어를 사용하세요: tail -f $LOG_FILE"