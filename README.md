# API для Интернет-банка

---

## Структура базы данных <br>
Структура базы данных представлена 1 таблицей, в которой содержатся ID клиента и его текущий баланс.
Первичным ключем таблицы является ID клиента.

![DB structure][1]

## Описание функциональности <br>

**Сервис позволяет выполнять следующие операции:** 
- Узнать баланс по ID пользователя;
- Снять заданную сумму с баланса пользователя;
- Пополнить баланс на заданную сумму.

**API документацию можно посмотреть [здесь](http://localhost:8080/swagger-ui/index.html) после запуска приложения.** 

**Для корректной работы приложения необходимо определить следующие переменные окружения**

| Переменная окружения  |       Значение переменной       |
|:---------------------:|:-------------------------------:|
|      DB_USERNAME      |    Пользователь базы данный     |
|      DB_PASSWORD      | Пароль пользователя базы данных |

    

[1]: img/bankDB.png