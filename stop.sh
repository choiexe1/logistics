#!/bin/bash

PROJECT_DIR=$(pwd)
PID_FILE="$PROJECT_DIR/application.pid"

if [ ! -f "$PID_FILE" ]; then
  echo "오류: PID 파일이 존재하지 않습니다. 애플리케이션이 실행되지 않았을 수 있습니다."
  exit 1
fi

PID=$(cat $PID_FILE)

echo "Spring Boot 애플리케이션 (PID: $PID)을 종료합니다."
kill $PID

rm -f $PID_FILE