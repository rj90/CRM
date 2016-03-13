CRM - Moduł CRM pracy magisterskiej
Author: Rafał Józwiak


Uruchomienie Testowe
------------
Testowe uruchomienie projektu, na wbudowanym kontenerze:

    mvn clean install tomcat7:run

Strona jest dostępna pod adresem http://localhost:8080/

    np. http://localhost:8080/

Komplacja
-----------

a) Baza danych zorientowana kolumnowo

    mvn clean package

b) Baza danych zorientowana wierszowo

    mvn clean package -P RODBMS

*.war jest dostępny w katalogu target/