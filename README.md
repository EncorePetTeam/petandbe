# Petandbe

## Members

\-

## Requirements

- [docker](https://docs.docker.com/get-docker/)
- [docker-compose](https://docs.docker.com/compose/install/)

## How to run

### 1. Reveal secret files

`git secret reveal` 명령어를 사용하기 위해서는 먼저 GPG 키가 git secret에 추가된 상태여야 합니다.  
관련 문서는 노션에 `ops/git secret` 문서로 정리해두었으니 참고 바랍니다.

```bash
git secret reveal
```

### 2. Build image

```bash
docker-compose -f docker-compose.yml build
```

### 3. Run docker-compose

```bash
docker-compose -f docker-compose.yml up -d
```

### 4. Status Check

```bash
# Check nginx health status.
# You will receive 'status ok' text and 200 status when nginx is running.
curl -X GET http://localhost:80/nginx-healthz

# Check app(spring) health status.
# You will receive 'Success' text and 200 status when spring app is running.
curl -X GET http://localhost:80/healthz
```
