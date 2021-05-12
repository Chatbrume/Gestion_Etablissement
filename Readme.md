# Gestion Etablissement


## Pré-requis
Version java : 15.0.2

## Utilisation
Cloner le projet dans le répertoire de votre choix.
> git clone https://github.com/Chatbrume/Gestion_Etablissement.git

# GestionEtablissement

## Fonctionalités

Ce logiciel permet de gérer un etablissement scolaire. Il est actuellement possible de :

* Gérer les cours de l'école;
* Gérer les étudiants;
* Associer un cours à un étudiant;
* Accéder à la liste des étudiants (pour le directeur uniquement)

## Installation

Pré-requis: avoir installé Java en local sur son ordinateur

* Aller dans le projet Github : https://github.com/Chatbrume/Gestion_Etablissement.git.
* Cliquer sur le bouton vert "code" puis télécharger le fichier zip.
* Dans Windows extraire le fichier zip.
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

## Convention de nommage

1. <b/>Package:</b>
   <br/>Utiliser la convention <u/>dotcase</u>.
   <br/>Examples:
    * com.sun.eng
    * com.apple.quicktime.v2
    * edu.cmu.cs.bovik.cheese
      <br/>
2. <b/>Attribut:</b>
   <br/>Utiliser la convention <u/>camelCase</u>.
   <br/>Examples:
    * int     iDea;
    * char     n;
    * float    myWidth;
3. <b/>Méthode:</b>
   <br/>Utiliser la convention <u/>camelCase</u>.
   <br/>Examples:
    * run();
    * runFast();
    * getBackground();
4. <b/>Class:</b>
   <br/>Utiliser la convention <u/>PascalCase</u>.
   <br/>Examples:
    * class Raster;
    * class ImageSprite;
5. <b/>Interface:</b>
   <br/>Utiliser la convention <u/>IPascalCase</u>.
   <br/>Examples:
    * interface IRasterDelegate;
    * interface IStoring;
    
## Tests unitaires
Pour lancer les tests unitaires
> mvn test
