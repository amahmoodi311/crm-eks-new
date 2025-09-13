# Deploying LMS in EKS Cluster 

1. Setup EKS Cluster 
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

● Select add-ons 
○ Core DNS 
○ Kube-proxy 
○ Amazon VPC CNI 

