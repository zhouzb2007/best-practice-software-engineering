Screencast: JSF Getting Started, Leadsheet
==========================================


(1) Webseite
------------

* Use Cases (Rollen, Use Cases)
* Technische Features
    * JSF
    * Web Container
    * Jetty als Servlet Container
    * Jetty & Maven
    * Aspekte (Grafik)
    * Hibernate
    * Internationalisierung
* Architektur-Abbildung
* Illustration der Layer 
    * (z.B. Use-Cases im Service Layer), potentielle erweiterung: Webservices
    * JSF zwischen Service und presentation layer
* Applikationsstart

(2) Verzeichnisstruktur
-----------------------

* pom
    * Jetty Plugin
* src/main/webapp
    * jsp seiten
    * css
    * Bilder
    * WEB-INF: Konfigurationsdateien (web.xml, faces-config.xml, applicationcontext.xml)
* src/main/java:
    * domain objekte
    * service objekte
    * geschäftslogik
* src/main/test: wie gehabt
    
(3) Maven & Eclipse
-------------------

* wie im ersten Getting Started
* starten der Applikation mit mvn jetty:run
* im Browser
    * login
    * Hauptseite zeigen
* parallel dazu kann in Eclipse entwickelt werden; aber keine Breakpoints!
* Hinweis auf Tomcat/Jetty Plugin für Eclipse


(4) Eclipse
-----------

Zusammenfassend die Struktur des Projektes in Eclipse zeigen
