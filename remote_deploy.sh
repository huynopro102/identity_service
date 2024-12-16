#!/bin/bash

# VPS details
VPS_USER="ubuntu"
VPS_IP="15.235.197.40"
VPS_SSH_KEY="/mnt/e/personal project fix/identity_service/id_rsa" # Sử dụng đường dẫn Linux WSL

# Path to the deploy script trên VPS
DEPLOY_SCRIPT="/home/ubuntu/deploy_identity_service.sh"

# SSH vào VPS và thực thi file có sẵn
ssh -i "$VPS_SSH_KEY" "$VPS_USER@$VPS_IP" "bash $DEPLOY_SCRIPT"
