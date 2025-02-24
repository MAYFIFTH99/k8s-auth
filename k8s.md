## k8s 환경 구축
1. minikube 설치 (가벼운 k8s 구현체)

- 로컬 머신에 클러스터를 구성하기 위해 vm 을 사용 -> docker 사용
 
2. vm 도구 설치
- brew install docker

3. kubectl & minikube 설치
- brew install kubectl
- brew install minikube

4. 실행
- minikube start
- kubectl get all

5. docker image 생성 후 repository에 push
- docker build -t {dockerhub_id}/{image_name}:{tag} .