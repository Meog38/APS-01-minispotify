# Mini Spotify API 🎧

Uma API RestFul para simular o comportamento de uma plataforma de streaming de música (estilo Spotify). O projeto possui funcionalidades de gerenciamento de catálogos (Artistas, Álbuns, Músicas), interação de Usuários (Criação de Playlists, Histórico de Reprodução e Músicas Favoritas) e geração de Relatórios.

---

## 🚀 Tecnologias Essenciais

- **Java** (JDK 17+)
- **Spring Boot** (Web, Data JPA)
- **Banco de Dados PostgreSQL** (Hospedado na Nuvem via Aiven)
- **Design Pattern DTO** (Data Transfer Object) para tráfego seguro de dados

---

## 🛠️ Funcionalidades e Regras de Negócio

### 1. Gestão de Catálogo Escala CRUD
A API fornece rotas completas para gerenciar:
- **Artistas**: Criação, listagem e relacionamento com seus respectivos álbuns.
- **Álbuns**: Agrupam as músicas e pertencem a um artista específico.
- **Músicas**: Entidade final reproduzível. Carrega dados de duração, faixa, etc.

### 2. Usuários e Navegação
- Cadastro de sistema de tipos de Plano (FREE, PREMIUM).
- Ativação/Inativação soft delete (usando flag `ativo`).

### 3. Playlists Pessoais
- Usuários podem criar múltiplas playlists.
- Regra de Segurança: Apenas o dono (via header `X-USER-ID`) pode atualizar detalhes, adicionar músicas ou deletar a playlist.
- Impede a adição de músicas já existentes na lista.

### 4. Interações do Usuário (Engajamento)
- **Histórico de Reprodução**: Registra *quem* tocou *o que* e *quando*. Usado para saber os hábitos de cada ouvinte.
- **Curtir Músicas**: Usuários podem favoritar músicas (Regra: Uma música só pode ser favoritada uma vez por usuário).

### 5. Relatórios 
- Rotas específicas contendo dados analíticos:
  - Top Músicas na Plataforma.
  - Top Músicas (Ranking) individual por conta do Usuário.
  - Feed das últimas reproduções ao vivo da plataforma.

---

## 🗂 Estrutura de Endpoints Principais

A grande maioria dos serviços é focada num padrão `RESTful`.

| Recurso | Método | Endpoint Padrão | Descrição |
| :--- | :--- | :--- | :--- |
| **Catálogo** | `GET/POST/PUT/DELETE` | `/artistas`, `/albuns`, `/musicas` | Gestão do Acervo Musical |
| **Reprodução** | `POST` | `/musicas/{id}/reproduzir` | Simula dar "Play" na música. Precisa de `X-USER-ID`. |
| **Favoritar** | `POST` | `/musicas/{id}/curtir` | Curte uma trilha (Impede duplicações). |
| **Usuários** | `GET/POST...` | `/usuarios` | Criação / Delete suave de contas. |
| **Meus Dados** | `GET` | `/usuarios/{id}/historico` | Retorna exatamente tudo o que já foi tocado. |
| **Favoritas** | `GET` | `/usuarios/{id}/favoritas` | Todas as curtidas em ordem descrescente de datas. |
| **Playlists** | `GET/POST...` | `/playlists` | Requer `X-USER-ID` nos fluxos de `POST/PUT/DELETE`. |
| **Adicionar Faixa**| `POST` | `/playlists/{pId}/musicas/{mId}` | Anexa música na playlist. |
| **Relatórios** | `GET` | `/relatorios/...` | Endpoints focados nas análises e top views. |

*(Nota: Alguns endpoints requerem passagem do ID do usuário logado via header `X-USER-ID`)*

---

## 🏃 Como Rodar

1. Certifique-se de ter a JDK compatível instalada em sua máquina.
2. Acesse a pasta `api/src/main/resources` e renomeie o arquivo `application.properties.example` para `application.properties`.
3. Insira a URL, usuário e senha do seu Banco de Dados PostgreSQL no novo `application.properties`.
4. Navegue para a pasta raiz `api/`.
5. Use sua IDE (VS Code, IntelliJ, Eclipse) para rodar a classe principal `MiniSpotifyApplication.java`.
6. O Spring Boot iniciará, sincronizará as tabelas dinamicamente no banco e estará escutando em `http://localhost:8080`.

---

## 🧪 Cobertura de Testes (End-to-End no Postman)

Atualmente, toda a validação de regras de funcionamento da API (Caminho Feliz, Fluxos de Falhas/400/403/404) é validada por uma **Coleção do Postman** feita sob medida.

**Para Testar:**
1. Importe o arquivo `MiniSpotifyAPI.postman_collection.json` direto no seu Postman.
2. Ele agrupa os cenários e depende da criação em série, então use a aba `Run Collection` para rodar na seguinte ordem sem apagar nada no meio do caminho:
   - *1. Artistas -> 2. Albuns -> 3. Usuarios -> 4. Musicas -> 5. Playlists -> 6. Relatorios e Historico -> 7. Limpeza (Teardown).*


