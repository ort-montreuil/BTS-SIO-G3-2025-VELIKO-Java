# Projet Java : Connexion avec Compte Préconfiguré

Ce projet Java permet à l'utilisateur de se connecter via un compte préconfiguré dans une base de données. Avant de commencer, certaines étapes doivent être effectuées dans le projet PHP associé.

## Prérequis

### 1. Installation et configuration du projet PHP
Avant de lancer ce projet Java, vous devez avoir configuré le projet PHP associé. Assurez-vous d'avoir Symfony installé et suivez les étapes ci-dessous :

1. **Cloner le projet PHP :**  
   Récupérez le dépôt Git du projet PHP et installez les dépendances nécessaires.

2. **Lancer les fixtures Symfony :**  
   Exécutez la commande suivante dans le répertoire du projet Symfony pour insérer les données nécessaires dans la base de données :
   
   symfony console doctrine:fixtures:load

   le compte préconfiguré est le suivant :
    - **Nom d'utilisateur :** `toto@dev.com`
    - **Mot de passe :** `Motdepasse123//`


   
