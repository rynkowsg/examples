# duct + uberjar

## Works when not compiled

Running from REPL works fine:

    % bb local-repl
    ...
    user=> (dev)
    ...
    dev=> (system/start {:keys [:example.greet/greet]})
    Hello, World!
    nil

Running with clojure works fine:

    % bb run:dev
    CMD: clojure -M:dev -m example.main
    ---
    [dev] env-specific loaded
    Hello, World!

## Problem when compiled

Compile:

    % bb compile:dev
    CMD: clojure -X:uberjar :aliases '[:dev]' :jar main-dev.jar :main-class example.main
    ---
    [main] INFO hf.depstar.uberjar - Compiling example.main ...
    [main] INFO hf.depstar.uberjar - Building uber jar: main-dev.jar

Run:

    % bb run-jar:dev
    CMD: shell java -XX:-OmitStackTraceInFastThrow -jar main-dev.jar
    ---
    [dev] env-specific loaded
    2021-06-24T15:35:36.452Z grayswan ERROR [example.main:9] -
    example.main.main
    ...
    example.main/-main       main.clj:    8
    example.main/-main       main.clj:   10
    example.system/start     system.clj:   39
    duct.core/exec-config       core.clj:  233
    duct.core/prep-config       core.clj:  201
    duct.core/build-config       core.clj:  190
    integrant.core/init      core.cljc:  431
    integrant.core/build      core.cljc:  325
    clojure.core/reduce       core.clj: 6830
    clojure.core.protocols/fn/G  protocols.clj:   13
    clojure.core.protocols/fn  protocols.clj:   75
    clojure.core.protocols/seq-reduce  protocols.clj:   31
    clojure.core.protocols/fn/G  protocols.clj:   19
    clojure.core.protocols/fn  protocols.clj:  168
    clojure.core/partial/fn       core.clj: 2643
    integrant.core/build-key      core.cljc:  302
    integrant.core/try-build-action      core.cljc:  294
    ...
    java.lang.IllegalArgumentException: No method in multimethod 'init-key' for dispatch value: :duct.profile/base
    clojure.lang.ExceptionInfo: Error on key :duct.profile/base when building system
    function: #object[clojure.lang.MultiFn 0x49f40c00 "clojure.lang.MultiFn@49f40c00"]
    key: :duct.profile/base
    reason: :integrant.core/build-threw-exception
    system: {}
    value: {:duct.core/project-ns "example",
    :duct.logger/timbre
    {:set-root-config? true,
    :level :debug,
    :appenders {:spit {:key :duct.logger.timbre/spit}}},
    :duct.logger.timbre/spit {:fname "logs/dev.log"},
    :example.greet/greet-fn {:logger {:key :duct/logger}},
    :example.greet/greet
    {:greet-fn {:key :example.greet/greet-fn},
    :logger {:key :duct/logger}}}
    
    Exception in thread "main" clojure.lang.ExceptionInfo: Error on key :duct.profile/base when building system {:reason :integrant.core/build-threw-exception, :system {}, :function #object[clojure.lang.MultiFn 0x49f40c00 "clojure.lang.MultiFn@49f40c00"], :key :duct.profile/base, :value {:duct.core/project-ns "example", :duct.logger/timbre {:set-root-config? true, :
    level :debug, :appenders {:spit #duct.core.InertRef{:key :duct.logger.timbre/spit}}}, :duct.logger.timbre/spit {:fname "logs/dev.log"}, :example.greet/greet-fn {:logger #duct.core.InertRef{:key :duct/logger}}, :example.greet/greet {:greet-fn #duct.core.InertRef{:key :example.greet/greet-fn}, :logger #duct.core.InertRef{:key :duct/logger}}}}
    at integrant.core$build_exception.invokeStatic(core.cljc:285)
    at integrant.core$build_exception.invoke(core.cljc:284)
    at integrant.core$try_build_action.invokeStatic(core.cljc:296)
    at integrant.core$try_build_action.invoke(core.cljc:293)
    at integrant.core$build_key.invokeStatic(core.cljc:302)
    at integrant.core$build_key.invoke(core.cljc:298)
    at clojure.core$partial$fn__5861.invoke(core.clj:2643)
    at clojure.core.protocols$fn__8181.invokeStatic(protocols.clj:168)
    at clojure.core.protocols$fn__8181.invoke(protocols.clj:124)
    at clojure.core.protocols$fn__8136$G__8131__8145.invoke(protocols.clj:19)
    at clojure.core.protocols$seq_reduce.invokeStatic(protocols.clj:31)
    at clojure.core.protocols$fn__8168.invokeStatic(protocols.clj:75)
    at clojure.core.protocols$fn__8168.invoke(protocols.clj:75)
    at clojure.core.protocols$fn__8110$G__8105__8123.invoke(protocols.clj:13)
    at clojure.core$reduce.invokeStatic(core.clj:6830)
    at clojure.core$reduce.invoke(core.clj:6812)
    at integrant.core$build.invokeStatic(core.cljc:325)
    at integrant.core$build.invoke(core.cljc:305)
    at integrant.core$init.invokeStatic(core.cljc:431)
    at integrant.core$init.invoke(core.cljc:423)
    at duct.core$build_config.invokeStatic(core.clj:190)
    at duct.core$build_config.invoke(core.clj:181)
    at duct.core$prep_config.invokeStatic(core.clj:201)
    at duct.core$prep_config.invoke(core.clj:192)
    at duct.core$exec_config.invokeStatic(core.clj:233)
    at duct.core$exec_config.invoke(core.clj:221)
    at example.system$start.invokeStatic(system.clj:39)
    at example.system$start.invoke(system.clj:35)
    at example.main$_main.invokeStatic(main.clj:10)
    at example.main$_main.doInvoke(main.clj:8)
    at clojure.lang.RestFn.invoke(RestFn.java:397)
    at clojure.lang.AFn.applyToHelper(AFn.java:152)
    at clojure.lang.RestFn.applyTo(RestFn.java:132)
    at example.main.main(Unknown Source)
    Caused by: java.lang.IllegalArgumentException: No method in multimethod 'init-key' for dispatch value: :duct.profile/base
    at clojure.lang.MultiFn.getFn(MultiFn.java:156)
    at clojure.lang.MultiFn.invoke(MultiFn.java:233)
    at integrant.core$try_build_action.invokeStatic(core.cljc:294)
    ... 31 more
    Error while executing task: run-jar:dev
