# Requisitos do Projeto: CuboLab Inteligente

Este documento detalha os requisitos funcionais e não funcionais para o desenvolvimento do sistema de gestão de reservas "CuboLab Inteligente", com base no desafio de modelagem proposto.

---

## 1. Objetivo Geral

Desenvolver um sistema em Java para gerenciar de forma eficiente as reservas, manutenções e a disponibilidade dos espaços de um coworking, eliminando conflitos e otimizando a alocação de recursos.

---

## 2. Requisitos Funcionais (RF)

### RF-01: Gestão de Espaços
- O sistema deve permitir o cadastro de três tipos de espaços: **Salas de Reunião**, **Mesas Individuais** e **Auditórios**.
- Cada espaço deve ter os seguintes atributos: identificador único, nome, localização e capacidade.
- Salas de Reunião podem ter um atributo adicional para **recursos audiovisuais**.

### RF-02: Gestão de Reservas
- O sistema deve permitir **registrar, confirmar e cancelar** reservas.
- Uma reserva deve conter: um intervalo de tempo (início e fim), nome do solicitante, número de participantes e uma finalidade (reunião, workshop, etc.).
- O sistema **não deve permitir** a criação de reservas que se sobreponham a outras no mesmo espaço.

### RF-03: Gestão de Manutenções
- O sistema deve permitir agendar **períodos de manutenção** para qualquer espaço.
- O sistema **não deve permitir** a criação de reservas durante um período de manutenção agendado.

### RF-04: Consulta de Disponibilidade
- Usuários devem poder consultar a agenda de um espaço para uma data específica.
- O sistema deve ser capaz de informar se um espaço está livre, ocupado ou em manutenção para um determinado horário.

### RF-05: Alocação Inteligente
- Ao solicitar uma reserva, o sistema deve **avaliar automaticamente** se o espaço atende aos requisitos (capacidade, recursos, etc.).
- Se o espaço desejado estiver indisponível, o sistema deve ser capaz de **sugerir um espaço alternativo** compatível.

---

## 3. Requisitos Não Funcionais (RNF)

### RNF-01: Desempenho
- O sistema deve fornecer acesso rápido aos dados de espaços e reservas (ex: busca por ID).
- As buscas por tipo de espaço, localização e data devem ser eficientes.

### RNF-02: Integridade de Dados
- O sistema deve evitar a duplicação de dados e manter a integridade referencial entre reservas e espaços.

### RNF-03: Arquitetura e Manutenibilidade
- O código deve ser organizado com **alta coesão** (componentes com responsabilidades bem definidas) e **baixo acoplamento** (módulos independentes).
- A arquitetura deve ser extensível para permitir a adição de novos tipos de espaços ou regras de negócio no futuro.

### RNF-04: Justificativa de Design
- A escolha das estruturas de dados para armazenamento e consulta deve ser documentada e justificada com base nos requisitos de desempenho e simplicidade.

---

## 4. Cenários de Validação Obrigatórios

O sistema final deve ser capaz de demonstrar os seguintes cenários:
1.  **Rejeição por Conflito:** Uma tentativa de reserva é bloqueada devido a um conflito de horário com uma reserva existente.
2.  **Rejeição por Manutenção:** Uma tentativa de reserva é bloqueada porque o espaço está em manutenção.
3.  **Redirecionamento de Reserva:** Um pedido de reserva para um espaço ocupado é automaticamente redirecionado para outro espaço compatível e disponível.
