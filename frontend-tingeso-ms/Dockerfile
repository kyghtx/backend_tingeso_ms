# Etapa 1: Build del frontend
FROM node:20-alpine AS builder

# Crear carpeta de trabajo dentro del contenedor
WORKDIR /app

# Copiar dependencias
COPY package*.json ./

# Instalar dependencias
RUN npm install

# Copiar el resto del proyecto
COPY . .

# Compilar la app (React crea una carpeta "build")
RUN npm run build

# Etapa 2: Servir los archivos con NGINX
FROM nginx:alpine

# Copiar el build generado desde la etapa anterior a la carpeta pública de NGINX
COPY --from=builder app/dist /usr/share/nginx/html

# Exponer el puerto por donde NGINX sirve la app
EXPOSE 80

# Iniciar NGINX en primer plano
CMD ["nginx", "-g", "daemon off;"]
