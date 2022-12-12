# Petandbe

## Members

\-

## Requirements

- [docker](https://docs.docker.com/get-docker/)
- [docker-compose](https://docs.docker.com/compose/install/)

## Installation

### 1. Build image

```bash
docker-compose -f docker-compose.yml build
```

### 2. Run docker-compose

```bash
docker-compose -f docker-compose.yml up -d
```

### 3. Status Check

```bash
# Check nginx health status.
# You will receive 'status ok' text and 200 status when nginx is running.
curl -X GET http://localhost:80/nginx-healthz

# Check app(spring) health status.
# You will receive 'Success' text and 200 status when spring app is running.
curl -X GET http://localhost:80/healthz
```
