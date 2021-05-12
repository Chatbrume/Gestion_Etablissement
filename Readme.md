# Gestion Etablissement

## Pré-requis
Version java : 15.0.2
Système de gestion de Base de donnée: MySQL
Container web Tomcat : version 7.0

## Installation

1. Récupéré le projet
  1. en zip
    * Aller dans le projet Github : https://github.com/Chatbrume/Gestion_Etablissement.git.
    * Cliquer sur le bouton vert "code" puis télécharger le fichier zip.
    * Dans Windows extraire le fichier zip.
  2. avec git
    * ouvrir un terminale dans le dossier ou vous souhaiter avoir l'application
    * git clone https://github.com/Chatbrume/Gestion_Etablissement.git
2. La base de donnée
  * Allez dans le dossier Gestion_Etablissement
  * utiliser le fichier gestionetablissement.sql pour généré la base de donnée
3. Utiliser l'application
  * Copier/Coller le fichier GestionEtablissement.war dans le répertoire 'webapps' de votre container web. Vous devez avoir une instance de tomcat en fonctionnement.
  * Ouvrir la page web http://localhost:8080/GestionEtablissement dans votre navigateur

# GestionEtablissement

## Fonctionalités

Ce logiciel permet de gérer un etablissement scolaire.
Il est actuellement possible de :
* Ajout un étudiant
* Gérer les étudiants
* Gérer les cours
* Gérer les notes
* Associer un cours à un étudiant;
* Accéder à la liste des étudiants (pour le directeur uniquement)

## Utilisation

Deux choix sont alors possibles.

* Accéder à l'application avec le compte Directeur :

  > Identifiant : e.chaumont@ensup.eu
  >  <br/>Mot de passe : e.chaumont

* Accéder à l'application avec le compte Responsable :

  > Identifiant : m.demaison@ensup.eu
  >  <br/>Mot de passe : m.demaison

## Localisation du répertoire des Logs

* Le répertoire des logs est situé dans le même répertoire que le .jar.
    
## Tests unitaires
Pour lancer les tests unitaires
> mvn test
