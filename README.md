# 🏥 App Triagem

Um sistema de **triagem médica** em **Java 17** (CLI) para cadastrar pacientes, priorizar por **nível de risco** e **alocar por especialidade**. O projeto demonstra **estruturas de dados**, **algoritmos** (fila com prioridade e backtracking) e **OOP**.

---

## ✨ Funcionalidades
- Cadastro de pacientes com dados pessoais e sintomas
- Níveis de risco (Emergência → Não urgente) com prioridade numérica
- Especialidades médicas (Clínico, Ortopedia, Cardiologia, Pediatria)
- **Fila de triagem** com prioridade por risco e ordem de chegada
- **Índice de pacientes** (hash) para busca por CPF
- **Backtracking** para alocar pacientes conforme capacidade por especialidade
- Interface simples via **console**

---

## 🛠️ Tecnologias
- **Java 17**
- **Maven**
- Paradigma **OOP**
- Estruturas de dados: lista/fila com prioridade, hash table
- Algoritmos: **Backtracking**

---

## 📂 Estrutura
```
src/
 └── main/java/br/com/apptriagem
     ├── model/
     │   ├── CaseData.java
     │   ├── Patient.java
     │   ├── TriageEntry.java
     │   └── enums/
     │       ├── RiskLevel.java
     │       └── Specialty.java
     ├── service/
     │   ├── Backtracking.java
     │   ├── PatientIndex.java
     │   └── TriageQueue.java
     └── Main.java
```

---

## ▶️ Como executar

> Requisitos: **Java 17** e **Maven** instalados.

```bash
# 1) Clonar
git clone https://github.com/wesleywerikis/app-triagem.git
cd app-triagem

# 2) Compilar e executar (sem precisar alterar o pom)
mvn -q compile exec:java -Dexec.mainClass="br.com.apptriagem.Main"
```

Alternativa (se preferir empacotar):
```bash
mvn -q package
# Ajuste o nome do .jar se necessário
java -cp target/classes br.com.apptriagem.Main
```

---

## 🧭 Uso (menu)

Ao iniciar, o app mostra:

```
1) Cadastrar
2) Atender próximo
3) Alocar por especialidade (backtracking)
0) Sair
```

- **Cadastrar**: informa dados do paciente, sintomas, risco e especialidade → adiciona à fila e ao índice por CPF  
- **Atender próximo**: remove o próximo da fila (maior prioridade) e o retira do índice  
- **Alocar por especialidade**: informa capacidades por especialidade e CPFs a tentar alocar → aplica **backtracking** e exibe a distribuição

---

## 🧩 Principais classes

- `RiskLevel` (enum): níveis de risco com **prioridade numérica** e rótulos  
- `Specialty` (enum): especialidades médicas do domínio  
- `Patient`: dados pessoais (nome, CPF, telefone, endereço)  
- `CaseData`: informações clínicas (sintomas, flags e anotações) + `RiskLevel` e `Specialty`  
- `TriageEntry`: vínculo `Patient` ↔ `CaseData` com **ordem de chegada** (sequencial)  
- `TriageQueue`: **fila com prioridade** por risco e tempo de chegada  
- `PatientIndex`: **hash table** simples para buscar/remover por CPF  
- `Backtracking`: **alocação** de pacientes por especialidade respeitando capacidades  
- `Main`: interface de **console** e fluxo do menu

---

## 🧪 Ideias de testes manuais

- Cadastrar vários pacientes com riscos diferentes e conferir a ordem de atendimento  
- Tentar cadastrar o mesmo CPF novamente (o índice bloqueia)  
- Alocar com capacidades insuficientes (deve indicar “sem solução”)  
- Alocar com capacidades suficientes e ver a distribuição por especialidade

---

## 🏷️ Topics (GitHub)
`java` `java17` `maven` `oop` `healthcare` `triage-system` `data-structures` `algorithms` `console-application`

---

## 📄 Licença
Este projeto pode ser distribuído sob a licença de sua preferência (ex.: MIT).  
Sinta-se à vontade para abrir **issues** e **PRs**. ✨

---
