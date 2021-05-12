# Gestion Etablissement

## Pré-requis
Version java : 15.0.2

## Installation

Pré-requis: avoir installé Java en local sur son ordinateur
Pré-requis: avoir un server de basse de donnée mysql installé en local sur son ordinateur
Pré-requis: avoir tomcat d'installé sur l'ordinateur

1. Récupéré le projet
  1. en zip
    * Aller dans le projet Github : https://github.com/Chatbrume/Gestion_Etablissement.git.
    * Cliquer sur le bouton vert "code" puis télécharger le fichier zip.
    * Dans Windows extraire le fichier zip.
  2. avec git
    * git clone https://github.com/Chatbrume/Gestion_Etablissement.git
2. La base de donnée
  * Allez dans le dossier Gestion_Etablissement
  * Se connecter a mysql
  * mysql -u utilisateur -p  gestionetablissement < gestionetablissement.sql
3. Utiliser l'application
  *

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

Pré-requis: avoir installé Java en local sur son ordinateur

* Se rendre dans le dossier "GestionEtablissement".
* Double-cliquer le fichier GestionEtablissement.jar.
* L'application s'ouvre.

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
