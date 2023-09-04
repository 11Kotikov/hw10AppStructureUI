# hw10AppStructureUI
# Пример приложения для управления заказами на кофейном аппарате

Этот проект представляет собой простое консольное приложение для управления заказами (кофе и пончиков). 
Оно реализует три ключевых паттерна проектирования: Агрегатор, Репозиторий и Кеширование. 
Проект создан в учебных целях и демонстрирует основные принципы разработки на Java.

## Особенности проекта

- Создание и сохранение заказов с продуктами.
- Загрузка заказов из памяти или кэша.
- Взаимодействие с пользователем через консольный интерфейс.
- Использование библиотеки Caffeine для кеширования данных.
- Обработка исключений при работе с заказами.
- Дополнительная реализация работы с базой данных SQLite (через класс ***`OrderRepositoryImpl.java`*** https://github.com/sqlite/sqlite)

## Зависимости

Проект использует следующие зависимости:

- Caffeine (для кеширования) - [ссылка](https://github.com/ben-manes/caffeine)
- SQLite (база данных) - [ссылка](https://github.com/sqlite/sqlite)

## Как использовать

1. Клонируйте репозиторий на свой компьютер.
2. Откройте проект в вашей среде разработки (например, IntelliJ IDEA или Eclipse).
3. Запустите приложение, запустив класс ***`Main.java`***.

### Консольное меню

После запуска приложения вы увидите следующее консольное меню:

1. Создать заказ
2. Показать все заказы
3. Выйти

Для взаимодействия с меню используйте цифры-клавиши клавитуры.

### Продукты

В приложении доступны следующие продукты:

1. Латте - $2.35
2. Капучино - $2.00
3. Американо - $1.75
4. Клубничный пончик - $1.50
5. Шоколадный пончик - $1.50
6. Карамельный пончик - $1.50

### Примечания

- Созданные заказы могут быть сохранены и загружены из памяти.
- Заказы также кешируются, что позволяет быстро получать доступ к ним.
- В приложении обрабатываются исключения, такие как ошибки ввода пользователя и отсутствие заказов.