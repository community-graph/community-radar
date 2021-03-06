# Community-Graph Information Radar

A simple display of community activity from the community graph implemented in Kotlin

We deployed both to Heroku.

The frontend: http://kotlin-kudos.herokuapp.com

The backend: http://kotlin-kudos-backend.herokuapp.com

The dashboard: http://kotlin-dashboard.herokuapp.com

## Getting set up

Data from Neo4j is enriched in real-time using Twitter APIs, which require access credentials.

* Obtain Twitter API creds from <a href="https://apps.twitter.com/">apps.twitter.com</a>
* Register a new application, once done, edit `backend/src/main/resources/application.properties`

```properties
twitter.consumerKey=$$YOUR_CONSUMER_KEY_HERE$$
twitter.consumerSecret=$$YOUR_CONSUMER_SECRET_HERE$$
twitter.accessKey=$$YOUR_OAUTH_KEY_HERE$$
twitter.accessSecret=$$YOUR_OAUTH_SECRET_HERE$$
```

## Running the backend

* `cd backend`
* `./gradlew bootRun`
* Confirm working at <a href="http://localhost:8080/kudos/random">http://localhost:8080/kudos/random</a>

## Running the frontend

You'll need node.js, version 7 or above. A nice way to install that is with <a href="https://github.com/creationix/nvm">Node Version Manager</a>.

* `cd frontend`
* `npm install && npm install -g bower gulp`
* `bower install`
* `gulp serve`
* Browse at <a href="http://localhost:3000/kudos/random">http://localhost:3000/kudos/random</a>

![Kotlin Kudos Frontend](./frontend/kotlin-kudos-ui.jpg)

## Statistics Dashboard

The beginnings of a statistics dashboard as a React application can be found in `dashboard`.

Currently it only lists tweets per month from the backend (`StatsRepository`).

It is running on: http://kotlin-dashboard.herokuapp.com

## KotlinConf Competition

### Tweet #Kudos to your #Kotlin Heros

![Kotlin Conf Kudos](kotlin-conf-kudos.jpg)

See your tweets at http://kotlin-kudos.herokuapp.com

### Competition

If you want to win a cool t-shirt at the [Neo4j](http://neo4j.com/developer) booth during KotlinConf do the following:

1. Fork this repository
2. Improve it, just a small bit (code, frameworks, UI, add a new community-graph statistics query)
3. Run it locally
4. Send a Pull Request
5. **Come by, show us & get your prize**

![Kotlin Competition Prizes](kotlin-conf-comp.jpg)

## Kotlin Community Graph

A Neo4j database capturing all the public activity of the Kotlin Community on

* Twitter
* StackOverflow
* GitHub
* Meetup

### Connection Details

* Database-URL: http://ec2-52-90-58-108.compute-1.amazonaws.com:7474
* User: neo4j
* Password: kotlin

You can run Neo4j Cypher Queries on it, like we do in [StatsRepository](https://github.com/community-graph/community-radar/blob/master/backend/src/main/java/radar/stats/repositories/bolt/BoltStatsRepository.kt) and render the results as charts, lists or networks.
