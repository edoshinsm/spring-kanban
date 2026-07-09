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
    USERS {
        Long id PK
        String username UK
        String password
    }

    ROLES {
        Long id PK
        String name UK
    }

    USER_ROLES {
        Long user_id FK
        Long role_id FK
    }

    PROJECTS {
        Long id PK
        String name
        Long user_id FK
    }

    BOARDS {
        Long id PK
        String name
        Long project_id FK
    }

    TASKS {
        Long id PK
        String name
        String description
        LocalDateTime createdAt
        Long board_id FK
    }

    USERS ||--o{ USER_ROLES : has
    ROLES ||--o{ USER_ROLES : assigned_to
    PROJECTS ||--o{ USERS : contains
    PROJECTS ||--o{ BOARDS : contains
    BOARDS ||--o{ TASKS : has
```
