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

    % bb run:dev
    CMD: clojure -M:dev -m example.main
    ---
    [dev] env-specific loaded
    Hello, World!

### Compiling to a capsule and running jar

    % bb compile:dev
    CMD: clojure -M:pack -m mach.pack.alpha.capsule uberjar-dev.jar -e app/dev/src --application-id example --application-version "1.0" -m example.main
    ---

    % bb run-jar:dev
    CMD: shell java -XX:-OmitStackTraceInFastThrow -jar uberjar-dev.jar
    ---
    [dev] env-specific.clj loaded
    Hello, World!
