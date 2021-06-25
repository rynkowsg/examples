# duct + uberjar

## Execution

### Using REPL

    % bb local-repl
    ...
    user=> (dev)
    ...
    dev=> (go)
    Hello, World!
    nil

### Using deps.tools -M

    % time bb run
    CMD: clojure -M -m example.main
    ---
    Hello, World!
    bb run  10.84s user 0.33s system 355% cpu 3.144 total

### Creating a capsule & running it

    % bb capsule
    CMD: clojure -M:pack -m mach.pack.alpha.capsule target/example-capsule.jar --application-id example --application-version "1.0" -m example.main
    ---

    % time bb capsule:run
    CMD: shell java -XX:-OmitStackTraceInFastThrow -jar target/example-capsule.jar
    ---
    Hello, World!
    bb capsule:run  11.12s user 0.34s system 341% cpu 3.352 total

### Creating an uberjar & running it

    % bb uberjar                                                                                                                                                                                                                                                                                                                                                       !10517
    CMD: shell lein uberjar
    ---
    If there are a lot of uncached dependencies this might take a while ...
    Compiling example.greet
    Compiling example.main
    Compiling example.system
    Compiling utils.duct
    Hello, World!
    Created /Users/greg/Sources/rynkowski/examples/clojure-duct-uberjar/target/example-0.1.0-SNAPSHOT.jar
    Created /Users/greg/Sources/rynkowski/examples/clojure-duct-uberjar/target/example-0.1.0-SNAPSHOT-standalone.jar

    % time bb uberjar:run                                                                                                                                                                                                                                                                                                                                              !10519
    CMD: shell java -XX:-OmitStackTraceInFastThrow -jar target/example-0.1.0-SNAPSHOT-standalone.jar
    ---
    Hello, World!
    bb uberjar:run  4.24s user 0.24s system 277% cpu 1.617 total
