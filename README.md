# community-radar
A simple display of community activity from the community graph implemented in Kotlin

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

You'll need Gradle installed. A nice way to install that is with <a href="http://sdkman.io/">SDKMAN</a>

* `cd backend`
* `gradle bootRun`
* Confirm working at <a href="http://localhost:8080/kudos/random">http://localhost:8080/kudos/random</a>

## Running the frontend

You'll need node.js, version 7 or above. A nice way to install that is with <a href="https://github.com/creationix/nvm">Node Version Manager</a>.
  
* `cd frontend` 
* `npm install && npm install -g bower gulp` 
* `bower install` 
* `gulp serve`
* Browse at <a href="http://localhost:3000/kudos/random">http://localhost:3000/kudos/random</a>


