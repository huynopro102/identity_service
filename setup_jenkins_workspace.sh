#!/bin/bash

# Tên script: setup_jenkins_workspace.sh

# Kiểm tra quyền root
if [ "$EUID" -ne 0 ]; then
    echo "Please run as root"
    exit 1
fi

# Định nghĩa các biến
WORKSPACE_DIR="/var/jenkins_home/workspace"
REPO_DIR="identity_service"
GITHUB_URL="https://github.com/huynopro102/identity_service.git"
GIT_USER="huynopro102"
GIT_EMAIL="your-email@example.com"

# Logging function
log() {
    echo "[$(date '+%Y-%m-%d %H:%M:%S')] $1"
}

# Error handling
set -e

log "Starting workspace setup..."

# Di chuyển đến workspace
log "Changing to workspace directory..."
cd $WORKSPACE_DIR || exit 1

# Xóa thư mục cũ nếu tồn tại
if [ -d "$REPO_DIR" ]; then
    log "Removing old repository directory..."
    rm -rf $REPO_DIR
fi

# Tạo thư mục mới và khởi tạo Git
log "Creating new repository directory..."
mkdir -p $REPO_DIR
cd $REPO_DIR

log "Initializing Git repository..."
git init

# Thêm remote origin
log "Adding remote origin..."
git remote add origin $GITHUB_URL

# Cấu hình Git global
log "Configuring Git global settings..."
git config --global --add safe.directory '*'
git config --global user.name "$GIT_USER"
git config --global user.email "$GIT_EMAIL"

# Set quyền
log "Setting permissions..."
chown -R jenkins:jenkins $WORKSPACE_DIR

log "Setup completed successfully!"

# Hiển thị thông tin cấu hình
log "Current Git configuration:"
git config --list

exit 0