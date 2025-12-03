# Catalog Service (Cloud Native Spring in Action)

![cloud native](./img.png)


## Minikube

로컬 이미지 레지스트리에서 로컬 클러스터로 가져오기

```bash
# minikube image load [image-name]
minikube image load catalog-service:0.0.1-SNAPSHOT
```

#### minikube 정지

```bash
minikube stop
```