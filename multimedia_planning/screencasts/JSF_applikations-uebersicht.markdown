Screencast: JSF Applikations-Übersicht, Leadsheet
==================================================


(1) Aufbau der Anwendung
------------------------

bottom up erklärt:

(2) Domainenobjekte
-------------------

* Person & Professor
* Hibernate Mappings


(3) Datenbank EER Diagramm
--------------------------

(4) Datenbankkonfiguration über Spring
---------------------------------------

* propertyPlaceholder
* jdbcProperties
* Anbindung and hsqldb
* dataSource Konfiguration

(5) Hibernate Konfiguration
---------------------------

* Datenquelle (s.o.)
* Spring Komponenten: Session Factory Bean
* Wo sind die Mapping Konfigurationen? Möglich
    * XML Mappings
    * **Annotationen**
* Wer löst Annotations auf?
* se-hibernate.cfg.xml

(6) Transaktionsmanagement (Konfiguration)
------------------------------------------

* Transaktions-Manager für Datenzugriff
* Tranksations-Interceptor für deklaratives Transaktions-Management (AOP)

(7) Data Access Objects
-----------------------

* Spring Definition
    * abstractDaoPattern
    * konkrete DAOs
* StudentDAO.java
    * Spring Hibernate DAO Support
    * HibernateTemplate (Session Handling, )
* Spring: daoExceptionAdvice: Exception logging Aspekt
* Java Klasse des Aspektes
* Decorator Pattern
    * Exception Advice und Transaktions-Management wird als Aspekt hinzugefügt
    * Transaktions-Management wurde schon definiert (s.o.)

    
(8) Service Schicht
-------------------

Nur ein Bean: Java Klasse ansehen


(9) Java Server Faces
---------------------

* webapp/WEB-INF/web.xml 
    * Faces Servlet
    * Spring im Context
    * myFaces Konfigurationen (Überblick)
* applicationContext.xml
* faces-config.xml
    * Managed Beans
    * Zugriff auf Spring-Beans
    * Navigations-Regeln für Seitenablauf
* courseList.jsp
    * Taglibs
    * Message Bundle
    * Konkrete Funktionalität der Seite;
        * z.B. Command Button
        * z.B. dataTable

(10) Anwendung starten
----------------------

kurzer Überblick der gerade gesehenen Funktionalität in der Anwendung