CRM - Modu³ CRM pracy magisterskiej
Author: Rafa³ Józwiak


Uruchomienie Testowe
------------
Testowe uruchomienie projektu, na wbudowanym kontenerze:

    mvn clean install tomcat7:run

Strona jest dostêpna pod adresem http://localhost:8080/{nazwa_paczki}/

    np. http://localhost:8080/CRM/

Komplacja
-----------

a) Baza danych zorientowana kolumnowo

    mvn clean package

b) Baza danych zorientowana wierszowo

    mvn clean package -P RODBMS

*.war jest dostêpny w katalogu target/