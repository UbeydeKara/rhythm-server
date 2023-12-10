# Rhythm - Music Streaming APIs

### Chart Endpoints (prefix = "/api/v1/chart")

| Path                   | Method | Description                                                                                             |
|------------------------|--------|---------------------------------------------------------------------------------------------------------|
| /weeklyTracks          | GET    | Retrieves weekly popular tracks                                                                         |
| /weeklyAlbums          | GET    | Retrieves weekly popular albums                                                                         |
| /weeklyArtists         | GET    | Retrieves weekly popular artists                                                                        |
| /weeklyReleases        | GET    | Retrieves weekly new releases                                                                           |
| /import                | POST   | It pulls weekly updated information from Spotify API and saves it temporarily in the database.          |
| /import/weeklyReleases | POST   | It pulls the week's newly added tracks from the Spotify API and temporarily saves them in the database. |

### Spotify Endpoints (prefix = "/api/v1/spotify")

| Path           | Method | Description               |
|----------------|--------|---------------------------|
| /playlist/{id} | GET    | Retrieves playlist tracks |
| /album/{id}    | GET    | Retrieves album detail    |

### Youtube Endpoints (prefix = "/api/v1/youtube")

| Path  | Method | Description                                                                                                                |
|-------|--------|----------------------------------------------------------------------------------------------------------------------------|
| /find | POST   | It searches YouTube for the song on Spotify and saves the videoId it finds in the database. (Each search costs 100 units.) |

## How to Deploy Your Application?

You can host docker containers with Public IP using the Amazon Elastic Container Service.
It's free!

### First of all, you need to create docker images with Google JIB
Build the apps with images:
```shell
$ mvn clean package -Pbuild-image
```

### Secondly, we need to create docker context for Amazon ECS
List docker contexts
```shell
$ docker context ls
```

Create context for ECS
```shell
$ docker context create ecs rhythm-ecs
```

Switch context
```shell
$ docker context use rhythm-ecs
```

Push the images to ECS server
```shell
$ docker compose up
```

You did it! Your application is deployed on Elastic Container Service.
You can browse the Load Balancer tab in ECS to access your application's DNS address.