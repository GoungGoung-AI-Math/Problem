#!/bin/bash

# 변수 정의
EC2_USER=ec2-user
EC2_IP=13.209.22.119
PEM_PATH=~/Desktop/aikey.pem
LOCAL_PATH=$(dirname "$0")
REMOTE_PATH=/home/$EC2_USER/problemapp
KEYSTORE_LOCAL_PATH="/Users/makisekurisu/key1.p12"
KEYSTORE_REMOTE_PATH="$REMOTE_PATH/key1.p12"
TOKEN_PATH="/home/ec2-user/problemapp/vault_token.txt"

# Clean and build the project
echo "Building the project..."
cd $LOCAL_PATH
./gradlew clean build

# Check if build was successful
if [ $? -ne 0 ]; then
  echo "Build failed. Exiting..."
  exit 1
fi

# 원격 디렉토리 생성
ssh -i $PEM_PATH $EC2_USER@$EC2_IP "mkdir -p $REMOTE_PATH"

# 파일들을 EC2로 복사
echo "파일들을 EC2로 복사 중..."
scp -i $PEM_PATH -r $LOCAL_PATH/docker-compose.yml $LOCAL_PATH/Dockerfile $LOCAL_PATH/build/libs/Math.AI-0.0.1-SNAPSHOT.jar $KEYSTORE_LOCAL_PATH $EC2_USER@$EC2_IP:$REMOTE_PATH

# EC2에 SSH로 접속하여 Docker Compose 실행
echo "EC2에 접속하여 Docker Compose 실행 중..."
ssh -t -i $PEM_PATH $EC2_USER@$EC2_IP << EOF
  # Vault 토큰 파일에서 읽기
  VAULT_TOKEN=\$(cat $TOKEN_PATH)
  export VAULT_ADDR='https://test.udongrang.com:8200'
  export VAULT_TOKEN

  echo "사용 중인 Vault 토큰: \$VAULT_TOKEN"

  # Vault CLI를 사용하여 비밀 정보 가져오기
  echo "Vault CLI를 사용하여 데이터베이스 비밀 정보 가져오는 중..."
  DB_SECRETS=\$(vault kv get -format=json kv/db-creds)
  DB_USERNAME=\$(echo \$DB_SECRETS | jq -r '.data.data.username_problem_db')
  DB_PASSWORD=\$(echo \$DB_SECRETS | jq -r '.data.data.password_problem_db')
  echo "데이터베이스 자격 증명 - 사용자명: \$DB_USERNAME, 비밀번호: \$DB_PASSWORD"

  # docker-compose.yml 파일에서 환경 변수 설정
  sed -i "s/SPRING_DATASOURCE_USERNAME: springproblem/SPRING_DATASOURCE_USERNAME: \$DB_USERNAME/" $REMOTE_PATH/docker-compose.yml
  sed -i "s/SPRING_DATASOURCE_PASSWORD: password/SPRING_DATASOURCE_PASSWORD: \$DB_PASSWORD/" $REMOTE_PATH/docker-compose.yml

  # Add SSL environment variables to docker-compose.yml if not already present
    if ! grep -q "SERVER_SSL_KEY_STORE" $REMOTE_PATH/docker-compose.yml; then
      sed -i "/SPRING_DATASOURCE_PASSWORD: \$DB_PASSWORD/a \ \ \ \ SERVER_SSL_KEY_STORE: /app/key1.p12\n \ \ \ \ SERVER_SSL_KEY_STORE_PASSWORD: abcd1234\n \ \ \ \ SERVER_SSL_KEY_STORE_TYPE: PKCS12\n \ \ \ \ SERVER_SSL_KEY_ALIAS: tomcat" $REMOTE_PATH/docker-compose.yml
    fi

  # 변경 사항을 확인하기 위해 로그 출력
  echo "수정된 docker-compose.yml:"
  grep "SPRING_DATASOURCE_USERNAME" $REMOTE_PATH/docker-compose.yml
  grep "SPRING_DATASOURCE_PASSWORD" $REMOTE_PATH/docker-compose.yml
  grep "SERVER_SSL_KEY_STORE" $REMOTE_PATH/docker-compose.yml

  # keystore 파일 확인
    if [ -f "$KEYSTORE_REMOTE_PATH" ]; then
      echo "key.p12 파일이 $REMOTE_PATH 경로에 존재합니다."
    else
      echo "key.p12 파일이 $REMOTE_PATH 경로에 존재하지 않습니다."
    fi

  sudo systemctl start docker
  cd $REMOTE_PATH
  sudo docker-compose -f docker-compose.yml down
  sudo docker-compose build --no-cache
  sudo docker-compose up -d
EOF

echo "EC2로의 배포 완료!"
