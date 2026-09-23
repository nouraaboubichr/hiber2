# Gestion Hôtel – Hibernate

Application console Java de gestion d'hôtels et de chambres, utilisant **Hibernate** (ORM) et une base de données **MySQL / MariaDB**.

## Fonctionnalités

- Gestion des hôtels (ajout, modification, suppression, consultation)
- Gestion des chambres (ajout, modification, suppression, consultation)
- Afficher les chambres par hôtel
- Rechercher les chambres par **état** et **prix** (saisie dans la console, puis filtrage)

## Modèle de données

Relation : **Hôtel (1) — (*) Chambre**

| Hôtel     | Chambre                                    |
|-----------|--------------------------------------------|
| `id`      | `id`                                       |
| `nom`     | `prix`                                     |
| `adresse` | `type` (enum : SIMPLE, DOUBLE, F1, F2, SUITE) |
|           | `etat` (ex. : LIBRE, OCCUPEE)              |
|           | `hotel` (clé étrangère vers Hôtel)         |

## Technologies

- Java (JDK 8 ou supérieur)
- Hibernate 4.3.1
- MySQL / MariaDB (XAMPP)
- NetBeans IDE

## Structure du projet

```
src/
├── config/
│   └── hibernate.cfg.xml      # Configuration Hibernate (connexion, mapping)
├── dao/
│   └── IDao.java              # Interface générique (create, update, delete, findById, findAll)
├── entities/
│   ├── Hotel.java
│   ├── Chambre.java
│   └── Type.java              # Enum des types de chambre
├── services/
│   ├── HotelService.java      # CRUD des hôtels
│   └── ChambreService.java    # CRUD des chambres + recherches
├── test/
│   ├── Test.java              # Point d'entrée (main) + données de départ
│   └── Menu.java              # Menu console
└── util/
    └── HibernateUtil.java     # Création de la SessionFactory
```

## Méthodes de recherche (`ChambreService`)

| Méthode                                   | Description                                      |
|-------------------------------------------|--------------------------------------------------|
| `findByHotel(int hotelId)`                | Chambres d'un hôtel donné                        |
| `findByEtatAndPrix(String etat, double prix)` | Chambres ayant l'état saisi et un prix inférieur ou égal au prix saisi |

## Installation et exécution

### 1. Prérequis

- JDK installé
- XAMPP (ou tout serveur MySQL/MariaDB)
- Bibliothèques ajoutées au projet : **Hibernate** (JPA + annotations) et **MySQL JDBC Driver**

### 2. Base de données

1. Démarrer **MySQL** depuis le panneau de contrôle XAMPP.
2. La base `gestion_hotel` est créée automatiquement au premier lancement (`createDatabaseIfNotExist=true`). Elle peut aussi être créée manuellement depuis phpMyAdmin.
3. Les tables `hotel` et `chambre` sont générées par Hibernate (`hibernate.hbm2ddl.auto=update`).

### 3. Configuration

Dans `src/config/hibernate.cfg.xml`, vérifier les paramètres de connexion :

```xml
<property name="hibernate.connection.driver_class">com.mysql.jdbc.Driver</property>
<property name="hibernate.connection.url">jdbc:mysql://localhost:3306/gestion_hotel?createDatabaseIfNotExist=true</property>
<property name="hibernate.connection.username">root</property>
<property name="hibernate.connection.password"></property>
<property name="hibernate.dialect">org.hibernate.dialect.MySQLDialect</property>
<property name="hibernate.hbm2ddl.auto">update</property>
```

Le mapping des entités :

```xml
<mapping class="entities.Hotel"/>
<mapping class="entities.Chambre"/>
```

### 4. Lancer l'application

Exécuter la classe `test.Test` (clic droit → *Run File*). Au premier lancement, des données de départ sont insérées si la base est vide, puis le menu s'affiche.

## Exemple d'utilisation

<img width="1270" height="674" alt="1" src="images/Capture d'écran 2026-09-10 050352.png" />

<img width="1270" height="674" alt="1" src="images/Capture d'écran 2026-09-10 050412.png" />

<img width="1270" height="674" alt="1" src="images/Capture d'écran 2026-09-10 050431.png" />

<img width="1270" height="674" alt="1" src="images/Capture d'écran 2026-09-10 050441.png" />

<img width="1270" height="674" alt="1" src="images/Capture d'écran 2026-09-10 051122.png" />



