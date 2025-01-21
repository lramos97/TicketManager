# 📦 Setup do Ambiente com Podman e Docker Compose no Windows

Este documento explica como configurar o **Podman** e o **Docker Compose** no Windows, permitindo rodar múltiplos contêineres sem precisar do Docker Desktop.

---

## 🚀 **Pré-requisitos**

Antes de começar, certifique-se de ter:

- **Windows 10 ou 11** (preferencialmente com WSL 2 instalado)
- **Permissão de administrador** para instalar softwares

---

## 🔹 **1. Instalar o Podman**

### **1️⃣ Baixar e instalar o Podman**

- Acesse: [Podman Windows Install](https://podman.io/getting-started/installation)
- Baixe o **Windows MSI Installer** e siga as instruções de instalação.

### **2️⃣ Verificar a instalação**

Abra o **PowerShell** e execute:

```terminal
podman --version
```

Se o comando retornar a versão instalada, o Podman foi instalado corretamente.

---

## 🔹 **2. Configurar Podman para emular Docker**

O Docker Compose depende da API do Docker. Precisamos rodar o Podman em **modo compatível com Docker**:

### **1️⃣ Ativar o serviço compatível com Docker**

```terminal
podman system service --time=0 &
```

### **2️⃣ Testar se o serviço está rodando**

```terminal
podman info
```

Se exibir informações do Podman, a API está funcionando.

---

## 🔹 **3. Instalar o Docker Compose**

Mesmo sem o Docker Desktop, podemos instalar o Docker Compose manualmente:

### **1️⃣ Baixar o Docker Compose**

```terminal
curl -L "https://github.com/docker/compose/releases/latest/download/docker-compose-Windows-x86_64.exe" -o C:\Program Files\Docker\docker-compose.exe
```

Caso o `curl` não esteja disponível, baixe manualmente em:
🔗 [Docker Compose Releases](https://github.com/docker/compose/releases)

### **2️⃣ Adicionar ao PATH**

- Vá para **Configurações do Windows** → **Sistema** → **Sobre** → **Configurações Avançadas do Sistema**.
- Clique em **Variáveis de Ambiente**.
- Encontre a variável `Path` e adicione:
  ```
  C:\Program Files\Docker\
  ```

### **3️⃣ Verificar a instalação**

```terminal
docker-compose --version
```

Se retornar a versão instalada, o Compose está pronto para uso.

---

## 🔹 **4. Rodar Docker Compose com Podman**

Agora podemos rodar nossos serviços com Podman:

### **1️⃣ Certifique-se de que o serviço do Podman está rodando**

```terminal
podman system service --time=0 &
```

### **2️⃣ Navegue até a pasta onde está o `docker-compose.yml`**

### **3️⃣ Execute o Docker Compose**

```terminal
docker-compose up --build
```

### **4️⃣ Para parar os serviços**

```terminal
docker-compose down
```

---

## ✅ **Resumo dos Comandos**

| Etapa                               | Comando                            |
| ----------------------------------- | ---------------------------------- |
| **Verificar instalação do Podman**  | `podman --version`                 |
| **Ativar API Docker do Podman**     | `podman system service --time=0 &` |
| **Verificar se a API está rodando** | `podman info`                      |
| **Baixar o Docker Compose**         | `curl -L ...`                      |
| **Verificar instalação do Compose** | `docker-compose --version`         |
| **Rodar Docker Compose**            | `docker-compose up --build`        |
| **Parar os contêineres**            | `docker-compose down`              |
