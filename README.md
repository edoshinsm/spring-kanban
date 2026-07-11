# О приложении

Учебный проект предсталвяет собой систему управления задачи для совместной работы на основе канбан-досок. Приложение включает в себя возможность глубокого структурированного хранения проектов и текущих задач.

# Функциональные требования

1. Безопасность и доступ
   * [ ] Аутентификация пользователей
   * [ ] Регистрация пользователей
2. База данных
   * [x] Создание логической базы данных
   * [x] Реализация базы данных через JPA
   * [x] Инициализация базы данных через файл data.sql
4. Работа с проектами
   * [x] Создание скелета сайта
   * [x] Просмотр списка проектов
   * [x] Просмотр списка досок
   * [x] Просмотр списка задач
   * [ ] Drag-and-drop механизм для задач
5. Управление задачами
   * [x] Создание проектов
   * [ ] Создание досок
   * [ ] Создание задач
   * [ ] Редактирование проектов
   * [ ] Редактирование досок
   * [ ] Редактирование задач

# Нефункциональные требования

1. Java 26. HTMX, Thymeleaf, Tailwind, Lombok, Hibernate
2. Spring Framework, Spring Boot, Spring Security
3. H2 БД

```mermaid
erDiagram
    EntityProject {
        Long id PK
        String name "NOT NULL"
        LocalDateTime createdAt "NOT NULL"
    }

    EntityColumn {
        Long id PK
        int position "NOT NULL"
        String name "NOT NULL"
        Long project_id "NOT NULL"
    }

    EntityIssue {
        Long id PK
        String name "NOT NULL"
        String description
        String status "NOT NULL"
        LocalDateTime createdAt "NOT NULL"
        LocalDateTime dueDate
        Long column_id "NOT NULL"
        Long creator_id "NOT NULL"
    }

    EntityUser {
        Long id PK
        String username "UNIQUE, NOT NULL"
        String name "NOT NULL"
        String surname "NOT NULL"
        String password_hash "NOT NULL"
        String email "UNIQUE, NOT NULL"
    }

    EntityProjectMembers {
        Long id PK
        String role "NOT NULL"
        Long user_id "NOT NULL"
        Long project_id "NOT NULL"
    }

    EntityProject ||--o{ EntityColumn : ""
    EntityColumn ||--o{ EntityIssue : ""
    EntityIssue }o--|| EntityUser : ""
    EntityProject ||--o{ EntityProjectMembers : ""
    EntityUser ||--o{ EntityProjectMembers : ""
```
