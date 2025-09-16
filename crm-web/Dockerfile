# Build stage
FROM node:18 AS build

WORKDIR /app

# Copy package files and install dependencies
COPY package*.json ./
RUN npm install

# Copy all frontend source code
COPY . .

# Pass backend API URL dynamically at build time
ARG VITE_BASE_API_URL
ENV VITE_BASE_API_URL=$VITE_BASE_API_URL

# Build the app
RUN npm run build

# Production stage with Nginx
FROM nginx:stable-alpine

# Copy the built frontend from the build stage
COPY --from=build /app/dist /usr/share/nginx/html

# Expose Nginx port
EXPOSE 80

# Start Nginx
CMD ["nginx", "-g", "daemon off;"]

