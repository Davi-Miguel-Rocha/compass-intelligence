# 🧭 Compass Intelligence

### Inteligência, mobilidade e informação para explorar o mundo.

O **Compass Intelligence** é uma aplicação web desenvolvida em Java com o objetivo de reunir informações financeiras, conversão de moedas e recursos relacionados ao turismo em uma única plataforma.

A proposta é construir uma ferramenta que ajude usuários a acompanhar cotações internacionais, realizar conversões monetárias e, futuramente, planejar viagens com maior consciência financeira.

Mais do que um conversor de moedas, o Compass Intelligence nasce com a ideia de conectar **informação, tecnologia e tomada de decisão**.

---

## 🌎 Visão do projeto

Viajar envolve muito mais do que escolher um destino. É necessário compreender custos, acompanhar moedas estrangeiras e organizar recursos financeiros.

O Compass Intelligence busca centralizar essas informações em uma experiência integrada, utilizando tecnologias Java e serviços externos para transformar dados em informações úteis.

### Objetivos principais

- Monitorar cotações de moedas internacionais.
- Realizar conversões monetárias utilizando taxas atualizadas.
- Disponibilizar informações relacionadas a destinos turísticos.
- Evoluir para uma plataforma inteligente de apoio ao planejamento de viagens.

---

## ✨ Funcionalidades

### 💱 Monitoramento de moedas

Acompanhe cotações de diferentes moedas em relação a uma moeda de referência.

- Consulta de taxas de câmbio por meio de API externa.
- Exibição das cotações na interface.
- Organização das moedas em uma apresentação visual.

### 🔄 Conversor de moedas

Uma ferramenta para calcular o valor equivalente entre diferentes moedas.

- Definição do valor de origem.
- Seleção da moeda de origem.
- Seleção da moeda de destino.
- Consulta da taxa de conversão.
- Apresentação do resultado calculado.

*Em desenvolvimento.*

### ✈️ Turismo

Uma área dedicada à exploração de destinos e informações relevantes para viagens.

*Em desenvolvimento.*

### 🤖 Inteligência aplicada ao turismo — visão futura

A proposta de evolução do Compass Intelligence inclui recursos inteligentes para auxiliar no planejamento de viagens, considerando informações financeiras, destinos e custos.

Essa etapa poderá incorporar mecanismos de análise e assistência baseados em inteligência artificial.

---

## 🛠️ Tecnologias utilizadas

| Tecnologia | Aplicação |
|---|---|
| Java 21 | Linguagem principal |
| Jakarta EE | Plataforma de desenvolvimento |
| Jakarta Faces (JSF) | Construção da camada web |
| PrimeFaces 15 | Componentes e interface |
| Maven | Gerenciamento de dependências e build |
| WildFly 40 | Servidor de aplicação |
| Jackson | Desserialização de respostas JSON |
| Frankfurter API | Consulta de taxas de câmbio |
| Git e GitHub | Versionamento e colaboração |

---

## 🏗️ Arquitetura e organização

O projeto utiliza uma organização em camadas e responsabilidades separadas, buscando facilitar a manutenção e a evolução da aplicação.

```text
src/
└── main/
    ├── java/
    │   ├── bean/
    │   │   └── Beans de interação com a interface
    │   │
    │   ├── converter/
    │   │   └── Conversores de objetos para JSF
    │   │
    │   ├── modelo/
    │   │   └── Modelos e estruturas de dados
    │   │
    │   ├── pais/
    │   │   └── Enumerações relacionadas aos países
    │   │
    │   └── service/
    │       └── Regras de negócio e integração externa
    │
    └── webapp/
        ├── pages/
        │   ├── inicio.xhtml
        │   ├── monitoramento.xhtml
        │   ├── convercao.xhtml
        │   └── turismo.xhtml
        │
        └── WEB-INF/
            └── web.xml
```

**Responsabilidades principais:**

- **Beans:** fazem a comunicação entre a interface e a lógica da aplicação.
- **Services:** concentram operações e regras de negócio.
- **Modelos:** representam os dados utilizados pelo sistema.
- **Converters:** realizam a conversão de objetos para valores compatíveis com os componentes JSF.
- **XHTML:** define as páginas e seus componentes visuais.

---

## 🌐 Integração com API externa

O projeto utiliza a [Frankfurter API](https://www.frankfurter.app/) para consultar taxas de câmbio.

Exemplo de consulta:

```http
GET https://api.frankfurter.app/latest?from=USD&to=EUR
```

A resposta é disponibilizada em JSON e processada utilizando Jackson.

Essa integração permite que a aplicação obtenha taxas de câmbio sem precisar manter manualmente os valores das moedas.

---

## 🚀 Executando o projeto

### Pré-requisitos

- JDK 21.
- Apache Maven.
- WildFly 40 ou compatível com as dependências Jakarta utilizadas.
- Conexão com a internet para consultas à API de câmbio.

### 1. Clone o repositório

```bash
git clone <URL_DO_REPOSITORIO>
```

### 2. Entre na pasta

```bash
cd compass-intelligence
```

### 3. Compile o projeto

```bash
mvn clean package
```

### 4. Implante no WildFly

Copie o arquivo WAR gerado em:

```text
target/projetoPrimeFaces-1.0-SNAPSHOT.war
```

Para a pasta de deployments do WildFly:

```text
WILDFLY_HOME/standalone/deployments/
```

### 5. Acesse a aplicação

Com o servidor iniciado, acesse:

```text
http://localhost:8080/projetoPrimeFaces-1.0-SNAPSHOT/
```

---

## 🗺️ Roadmap

O Compass Intelligence está em desenvolvimento e possui uma evolução planejada em etapas.

- [x] Estrutura inicial do projeto Java.
- [x] Configuração do ambiente Jakarta Faces.
- [x] Integração inicial com API de câmbio.
- [x] Implementação inicial da tela de monitoramento.
- [x] Estrutura inicial do conversor de moedas.
- [ ] Integração das moedas do monitoramento com os menus de conversão.
- [ ] Finalização e validação da conversão monetária.
- [ ] Evolução da interface de turismo.
- [ ] Persistência de dados em banco de dados.
- [ ] Implementação de recursos inteligentes para planejamento de viagens.
- [ ] Testes automatizados e melhorias de qualidade.

---

## 🔭 Visão de longo prazo

O objetivo é transformar o Compass Intelligence em uma plataforma que conecte informações financeiras e turísticas de maneira inteligente.

A evolução do projeto poderá envolver:

- Análise de custos de viagem.
- Comparação de destinos.
- Estimativas de gastos em moedas estrangeiras.
- Assistência inteligente para planejamento de itinerários.
- Recomendações baseadas em informações financeiras e turísticas.

---

## 👨‍💻 Desenvolvimento

O Compass Intelligence é um projeto desenvolvido com foco em aprendizado, engenharia de software e aplicação prática de tecnologias Java.

A construção prioriza a separação de responsabilidades, integração com serviços externos e evolução incremental da arquitetura.

**Projeto em desenvolvimento.**

**Projeto em funcionamento**

<img width="1907" height="857" alt="image" src="https://github.com/user-attachments/assets/358fb8b9-ed79-4138-a537-c8db80eac2e0" />
---
<img width="1900" height="1027" alt="image" src="https://github.com/user-attachments/assets/dc290868-e3d9-48fb-8eed-60e52ac60206" />
---
<img width="1682" height="912" alt="image" src="https://github.com/user-attachments/assets/2b7d148c-9716-466d-bd1d-28b373a20a15" />

---

<p align="center">
  <strong>Compass Intelligence</strong><br/>
  <em>Find your direction. Explore beyond.</em>
</p>
