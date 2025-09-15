FROM node:18

WORKDIR /app

COPY package*.json ./
RUN npm install --production

COPY . .

ENV PORT=4000
EXPOSE 4000

CMD ["sh", "-c", "npm run start || node index.js"]
