Ad placement system.

Приложение разрабатывалась с использованием следующего программного обеспечения:
- Java 11.0.11 openjdk
- Tomcat 9.0.40
- Apache Maven 3.8.1
- PostgreSQL 12.7

Для развёртывания приложения необходимо:
1) Иметь в постгресе дефолтного юзера с именем 'postgres' и паролем '1234'
2) Иметь дефолтную базу данных с именем 'postgres'
3) Запустить в этой бд скрипт ./SQL/CreatingDBFinal.sql для подготовки бд к работе приложения
4) Запустить скрипт ./Senla.sh для сборки приложения в готовый .war файл

Приложение готово к развёртыванию на Томкате.

Чтобы развернуть приложение через докер, необходимо в файле ./resources/META-INF/persistence.xml 
изменить URL подключения к бд с '127.0.0.1:5432/postgres' на 'db:5432/postgres' и запустить 
скрипт ./Senla-docker.sh