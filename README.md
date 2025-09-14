# Deploying LMS in EKS Cluster 

# step-1. Setup EKS Cluster 
STEPS: 
1. Create EKS cluster and add Nodegroup 
2. Launch k8s Workstation and connect it to EKS cluster 
- STEP-1: Create EKS cluster and add Nodegroup 
- Login to aws and goto EKS section 
- Now click on create cluster

![file](https://github.com/amahmoodi311/crm-eks-new/blob/56a4bb152252b76c231d45ae84d03e2450180b01/eks-1.png)

![file](https://github.com/amahmoodi311/crm-eks-new/blob/55d2ff62fe923581b65b8e81327572d4a682ce5b/eks-2.png)

- Give a proper cluster name: hpa-cluster 
- Select IAM role if you don’t have create one

- Click on create role  
- Select EKS cluster related policies 

![file](https://github.com/amahmoodi311/crm-eks-new/blob/76e7b6f7e4d0343e686963c3c77e667bc7998a82/eks-3.png)

- Now goto your configuration and select created role 
- Configure cluster 
○ Insert your role here 

![file](https://github.com/amahmoodi311/crm-eks-new/blob/76e7b6f7e4d0343e686963c3c77e667bc7998a82/eks-4.png)

● Specify networking

![file](https://github.com/amahmoodi311/crm-eks-new/blob/76e7b6f7e4d0343e686963c3c77e667bc7998a82/eks-5.png)

● Configure logging

![file](https://github.com/amahmoodi311/crm-eks-new/blob/76e7b6f7e4d0343e686963c3c77e667bc7998a82/eks-6.png)

![file](https://github.com/amahmoodi311/crm-eks-new/blob/a4fdf45b00b52c46acf9d76741991f17b2f18240/eks-7.png)

● Configure selected add-ons 

![file](https://github.com/amahmoodi311/crm-eks-new/blob/a4fdf45b00b52c46acf9d76741991f17b2f18240/eks-8.png)

● Review and create 

![file](https://github.com/amahmoodi311/crm-eks-new/blob/a4fdf45b00b52c46acf9d76741991f17b2f18240/eks-9.png)

● Check your created cluster it will take few minutes to create 

![file](https://github.com/amahmoodi311/crm-eks-new/blob/59bb88d19d5d3a97b23841c16532a03c151fb991/image/eks-10.png)

- Once cluster created add Nodegroup to it 
- Goto your EKS cluster 
- Goto compute section 
- Click on Add Nodegroup 

![file](https://github.com/amahmoodi311/crm-eks-new/blob/59bb88d19d5d3a97b23841c16532a03c151fb991/image/eks-11.png)

- Select IAM role if you don’t have create one 
- Click on create role  
- Select EKS cluster Nodegroup related policies 
- Create IAM Role with following policies  
- AmazonEKSWorkerNodePolicy 
- AmazonEC2ContainerRegistryPullOnly 
- AmazonEKS_CNI_Policy 
- AmazonEC2ContainerRegistryReadOnly 
- AWS > Services > IAM > Roles > Create Role > Select AWS Service > 
Use Case : EC2 > Policy : AmazonEKSWorkerNodePolicy - 
AmazonEKS_CNI_Policy - AmazonEC2ContainerRegistryReadOnly  > 
Next > Role Name : AppName-EKSWorkerNodeRole > Create Role 

![file](https://github.com/amahmoodi311/crm-eks-new/blob/59bb88d19d5d3a97b23841c16532a03c151fb991/image/eks-12.png)

- Goto your cluster 
- Select: compute > add node group 
- Configure node group 
- Name: hpa-nodegroup 
- Add IAM role: amazoneksnoderole 

![file](https://github.com/amahmoodi311/crm-eks-new/blob/59bb88d19d5d3a97b23841c16532a03c151fb991/image/eks-13.png)

- Click next
-  Review and create

# STEP-2: Launch k8s Workstation and connect it to EKS cluster
```
 ● Os: ubuntu 20.0 
● T2.medium 
● Ports: 22, 80, 443 ( open all traffic ) 
● Repo: git clone https://github.com/amahmoodi311/crm-app-manual.git
```
# Install AWS CLI
```
sudo apt install unzip -y
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install
/usr/local/bin/aws --version
aws --version
```

# Install Kubectl
```
Official Docs: https://docs.aws.amazon.com/eks/latest/userguide/install-kubectl.html 

curl -O https://s3.us-west-2.amazonaws.com/amazon-eks/1.31.3/2024-12-12/bin/linux/amd64/kubectl
chmod +x ./kubectl
sudo cp kubectl /usr/local/bin/
kubectl version --client
```

# Configure AWS CLI

- Create Access & Secret Keys
- Login to AWS → IAM → Security Credentials
- Create new Access Key and Secret Key
- Save them securely


![file](https://github.com/amahmoodi311/crm-eks-new/blob/428adb9bb05b8313d5e8e6c00bad28f076522a9f/image/eks-14.png)

![file](https://github.com/amahmoodi311/crm-eks-new/blob/428adb9bb05b8313d5e8e6c00bad28f076522a9f/image/eks-15.png)

# aws configure
- AWS Access Key ID: *************
- AWS Secret Access Key: *************
- Default region name: ap-south-1
- Default output format: json

# Check the user credentials before config the cluster to host machine 
 ```
 aws sts get-caller-identity
 aws s3 ls
```

# Connect Workstation to EKS
```
aws eks update-kubeconfig --name <cluster-name> --region <region-name>
```
#  Deploy Application on EKS
```
- git clone  https://github.com/amahmoodi311/crm-app-manual.git
```
## Setup Docker
```bash
curl -fsSL https://get.docker.com -o get-docker.sh
sh get-docker.sh
sudo usermod -aG docker $USER
newgrp docker
docker --version
docker image ls
 ```


# Create Secrets
```
apiVersion: v1
kind: Secret
metadata:
  name: mysql-secret
type: Opaque
data:
  password: QWRtaW4xMjM= Admin123
```
# Encrypt the password 
```
echo -n <your-password> | base64
echo -n qwerty1234 | base64 
> UXdlcnR5QDEyMw==  
```

# Apply it:
```
kubectl apply -f mysql-secret.yml
kubectl get secret mysql-secret -o yaml
```

# Setup Database and service
```
apiVersion: apps/v1
kind: Deployment
metadata:
  name: mysql-deployment
spec:
  replicas: 1
  selector:
    matchLabels:
      app: mysql
  template:
    metadata:
      labels:
        app: mysql
    spec:
      containers:
        - name: mysql
          image: mysql:8.0
          env:
            - name: MYSQL_ROOT_PASSWORD
              valueFrom:
                secretKeyRef:
                  name: mysql-secret
                  key: password
            - name: MYSQL_DATABASE
              value: crmdb
          ports:
            - containerPort: 3306
---
apiVersion: v1
kind: Service
metadata:
  name: mysql-cluster-ip-service
spec:
  type: ClusterIP
  selector:
    app: mysql
  ports:
    - port: 3306
      targetPort: 3306
```

# Apply it:
```
kubectl apply -f mysql-deployment.yml
kubectl get pods
```
# Deploy Backend (crm-api)

# Make sure your application.yml looks like this:
```
spring:
  datasource:
    url: jdbc:mysql://${DB_HOST}:${DB_PORT}/${DB_NAME}?useSSL=false&allowPublicKeyRetrieval=true
    username: ${DB_USER}
    password: ${DB_PASSWORD}
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQLDialect
server:
  port: 3000
```

# Backend Deployment + Service
```
apiVersion: apps/v1
kind: Deployment
metadata:
  name: lms-backend
spec:
  replicas: 1
  selector:
    matchLabels:
      app: lms-be
  template:
    metadata:
      labels:
        app: lms-be
    spec:
      containers:
        - name: backend-container
          image: amahmoodi311/crmback:latest
          ports:
            - containerPort: 3000
          env:
            - name: DB_HOST
              value: "mysql-cluster-ip-service"
            - name: DB_PORT
              value: "3306"
            - name: DB_USER
              value: "root"
            - name: DB_NAME
              value: "crmdb"
            - name: DB_PASSWORD
              valueFrom:
                secretKeyRef:
                  name: mysql-secret
                  key: password
---
apiVersion: v1
kind: Service
metadata:
  name: lms-be-service
spec:
  type: LoadBalancer
  selector:
    app: lms-be
  ports:
    - port: 3000
      targetPort: 3000
```

# Apply it:
```
kubectl apply -f be-deployment.yml
kubectl get svc lms-be-service
```

# Check if the backend is working:

![file](https://github.com/amahmoodi311/crm-eks-new/blob/86cd987b3ab724f580b039da82e2e9c3ddffb4f5/image/jav-2.png)

# Deploy Frontend (crm-web) and service
```
apiVersion: apps/v1
kind: Deployment
metadata:
  name: lms-frontend
spec:
  replicas: 1
  selector:
    matchLabels:
      app: lms-fe
  template:
    metadata:
      labels:
        app: lms-fe
    spec:
      containers:
        - name: frontend-container
          image: amahmoodi311/crmfront:latest
          ports:
            - containerPort: 80
---
apiVersion: v1
kind: Service
metadata:
  name: lms-fe-service
spec:
  type: LoadBalancer
  selector:
    app: lms-fe
  ports:
    - port: 80
      targetPort: 80
 ```
# Apply it:
```
kubectl apply -f fe-deployment.yml
kubectl get svc lms-fe-service
```

# Final web-app:
![file](https://github.com/amahmoodi311/crm-eks-new/blob/86cd987b3ab724f580b039da82e2e9c3ddffb4f5/image/crm-doc5.png)
