# SHIFT_java

Java: OpenJDK 24.0.2 (или совместимая версия Java 17+)

Система сборки: Apache Maven 3.9.6 (или выше)

Сборка проекта производилась через Maven:
1. Выполните сборку через Maven: mvn clean package
2. После сборки исполняемый JAR появится в папке target: target\filter-utility-1.0-SNAPSHOT-jar-with-dependencies.jar

Запуск: 
1. Базовый запуск:
   
   java -jar target\filter-utility-1.0-SNAPSHOT-jar-with-dependencies.jar examples\in1.txt examples\in2.txt
2. Задание пути для выходных файлов:
   
   java -jar target\filter-utility-1.0-SNAPSHOT-jar-with-dependencies.jar -o out examples\in1.txt examples\in2.txt
3. Задание префикса для выходных файлов:
   
   java -jar target\filter-utility-1.0-SNAPSHOT-jar-with-dependencies.jar -p result- examples\in1.txt examples\in2.txt
4. Вывод краткой статистики (по умолчанию):
   
   java -jar target\filter-utility-1.0-SNAPSHOT-jar-with-dependencies.jar -s examples\in1.txt
5. Вывод полной статистики:
    
   java -jar target\filter-utility-1.0-SNAPSHOT-jar-with-dependencies.jar -f examples\in1.txt
6. Добавление в существующие файлы (вместо перезаписи):
    
   java -jar target\filter-utility-1.0-SNAPSHOT-jar-with-dependencies.jar -a examples\in1.txt

Особенности реализации:

  Если не указаны входные файлы, утилита завершает работу с сообщением:
    Нет входных файлов

  Если не указан параметр -p, файлы создаются с именами integers.txt, floats.txt, strings.txt.

  Если не указан параметр -o, файлы создаются в текущей папке.

  Опция -a включает режим добавления, иначе файлы перезаписываются при каждом запуске.
