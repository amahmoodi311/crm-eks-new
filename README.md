# Deploying LMS in EKS Cluster 

# step-1. Setup EKS Cluster 
STEPS: 
1. Create EKS cluster and add Nodegroup 
2. Launch k8s Workstation and connect it to EKS cluster 
STEP-1: Create EKS cluster and add Nodegroup 
● Login to aws and goto EKS section 
● Now click on create cluster

![file](https://github.com/amahmoodi311/crm-eks-new/blob/56a4bb152252b76c231d45ae84d03e2450180b01/eks-1.png)

![file](https://github.com/amahmoodi311/crm-eks-new/blob/55d2ff62fe923581b65b8e81327572d4a682ce5b/eks-2.png)

○ Give a proper cluster name: hpa-cluster 
○ Select IAM role if you don’t have create one

○ Click on create role  
■ Select EKS cluster related policies 

![file](https://github.com/amahmoodi311/crm-eks-new/blob/76e7b6f7e4d0343e686963c3c77e667bc7998a82/eks-3.png)

● Now goto your configuration and select created role 
● Configure cluster 
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

● Once cluster created add Nodegroup to it 
● Goto your EKS cluster 
● Goto compute section 
● Click on Add Nodegroup 

![file](https://github.com/amahmoodi311/crm-eks-new/blob/59bb88d19d5d3a97b23841c16532a03c151fb991/image/eks-11.png)

● Select IAM role if you don’t have create one 
○ Click on create role  
■ Select EKS cluster Nodegroup related policies 
● Create IAM Role with following policies  
○ AmazonEKSWorkerNodePolicy 
○ AmazonEC2ContainerRegistryPullOnly 
○ AmazonEKS_CNI_Policy 
○ AmazonEC2ContainerRegistryReadOnly 
● AWS > Services > IAM > Roles > Create Role > Select AWS Service > 
Use Case : EC2 > Policy : AmazonEKSWorkerNodePolicy - 
AmazonEKS_CNI_Policy - AmazonEC2ContainerRegistryReadOnly  > 
Next > Role Name : AppName-EKSWorkerNodeRole > Create Role 

![file](https://github.com/amahmoodi311/crm-eks-new/blob/59bb88d19d5d3a97b23841c16532a03c151fb991/image/eks-12.png)

● Goto your cluster 
● Select: compute > add node group 
○ Configure node group 
■ Name: hpa-nodegroup 
■ Add IAM role: amazoneksnoderole 

![file](https://github.com/amahmoodi311/crm-eks-new/blob/59bb88d19d5d3a97b23841c16532a03c151fb991/image/eks-13.png)

- Click next
-  Review and create

# STEP-2: Launch k8s Workstation and connect it to EKS cluster

 ● Os: ubuntu 20.0 
● T2.medium 
● Ports: 22, 80, 443 ( open all traffic ) 
● Repo: git clone https://github.com/amahmoodi311/crm-app-manual.git

# Install AWS CLI
sudo apt install unzip -y
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install
/usr/local/bin/aws --version
aws --version

# Install Kubectl

Official Docs: https://docs.aws.amazon.com/eks/latest/userguide/install-kubectl.html 

curl -O https://s3.us-west-2.amazonaws.com/amazon-eks/1.31.3/2024-12-12/bin/linux/amd64/kubectl
chmod +x ./kubectl
sudo cp kubectl /usr/local/bin/
kubectl version --client

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
- aws sts get-caller-identity
- aws s3 ls

# Connect Workstation to EKS
aws eks update-kubeconfig --name <cluster-name> --region <region-name>

#  Deploy Application on EKS
- git clone  https://github.com/amahmoodi311/crm-app-manual.git

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
- echo -n <your-password> | base64
- echo -n qwerty1234 | base64 
> UXdlcnR5QDEyMw==  




