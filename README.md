# Projet Java : Connexion avec Compte Préconfiguré (admin)

Ce projet Java permet à un utilisateur de se connecter à un compte administrateur préconfiguré dans une base de données. Avant de lancer le projet Java, certaines étapes de configuration doivent être réalisées sur le projet PHP associé.

Membres de l'équipe : Shirel Marciano, Maxime Benoit Legrand

## Prérequis

Avant de lancer ce projet Java, vous devez avoir configuré le projet PHP associé. Assurez-vous d'avoir Symfony installé et suivez les étapes ci-dessous :

1. **Cloner le projet PHP :**  
   - Récupérez le dépôt Git du projet PHP 
   - Installez les dépendances nécessaires.
   - Lancez les services Docker pour configurer la base de données avec la commande suivante :
   ```bash
    docker compose up -d
   ```

2. **Lancer les fixtures Symfony :**  

   Une fois les services démarrés, insérez les données nécessaires dans la base de données en exécutant cette commande dans le répertoire du projet Symfony :
    ```bash
    symfony console doctrine:fixtures:load
    ```
   Le compte préconfiguré est le suivant :
   - **Nom d'utilisateur :** `toto@dev.com`
   - **Mot de passe :** `Motdepasse123//`
 
  
3. **Ajout de la bibliothèque (.jar) au projet Java :**

   Pour intégrer la bibliothèque requise dans le projet Java, procédez comme suit :
  
   - Cliquez avec le bouton droit à la racine du projet dans votre IDE.
   - Allez dans Open Module Settings
   - Cliquez sur le bouton + (Ajouter).
   - Sélectionnez JARS or Directories.
   - Ajoutez le fichier .jar présent dans le dossier lib du projet.
   
4. **Remarque sur l'affichage des stations (MAP)**

     - Lors de l’utilisation de la fonctionnalité MAP, les stations peuvent mettre du temps à s’afficher. Si le problème persiste, appuyer plusieurs fois sur le bouton pour forcer le rendu de l’affichage. 





   
