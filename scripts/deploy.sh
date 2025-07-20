#!/bin/bash
cd ~/TechRevWeb || exit 1

# Fix permissions
sudo chown -R $USER:$USER .
sudo chmod -R u+rwX .

# Pull latest code
echo "Pulling latest changes..."
git fetch origin developement
git reset --hard origin/developement
git clean -fd

# Restart backend (Spring Boot)
echo "Restarting backend..."
cd backend || exit 1
./gradlew build -x test
pkill -f 'java -jar' || true
nohup ./gradlew bootRun > ~/backend.log 2>&1 &

# Restart Nginx
echo "Reloading Nginx..."
sudo systemctl reload nginx

echo "Deployment complete!"