Screencast: Basic, Architecture, Leadsheet
======================================

    (1) Basic und Core Modul
    (2) Core Modul
    (3) Basic Modul
    (4) Basis.java
    (5) GUI
    (6) Zugriff auf Daten
    (7) DAO Implementation
    (8) Testen (DAO Test als Beispiel, All Tests)
    (9) Import/Export
    (10) Resources: log4j.properties
    (11) hsqldb Datenbank (src/target), testen: test-beans
    (12) Maven vs. Eclipse Build

- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 

(2) Core Modul
--------------

* Student.java
* Person.java
* IStudentDAO

(3) Basic Modul
---------------

zunächst Überblick

* Basis.java
* DAOs
* Export, Import
* GUI
* helper
* resources

(4) Basis.java
--------------


(5) GUI
-------

MainFrame.java:

Kein Swing Kurs

* extends JFrame
* Logging
* DAO Zugriff über Spring
* initComponents
* Business Methoden


(6) Zugriff auf Daten
---------------------

Zugriff nur über Interface-Variable. Konkrete DAO Implementation wird über Spring verdrahtet. Beispiel:

* getBean("StudentDAO")
* Nachsehen in beans.xml
* Erklären der Bean Def: dataSource, Transaction manager
* 

(7) DAO Implementation
----------------------

(8) Testen
----------

* DAO Test als Beispiel 
* All Tests

(9) Import/Export
-----------------

keine Details, selbst ansehen

(10) Resources
--------------

* log4j.properties
* Datenbank
* beans.xml

(11) hsqldb Datenbank
---------------------

* Wo liegt die DB?
* src/target
* testen: test-beans
* DBVisualizer (oder hsqldb tool)
* shutdown script
* SET WRITE_DELAY 0 MILLIS

(12) Maven vs. Eclipse Build