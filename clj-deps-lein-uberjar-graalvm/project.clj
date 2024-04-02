(defproject example "0.1.0-SNAPSHOT"
  :min-lein-version "2.9.0"

  :plugins [[lein-tools-deps "0.4.5"]]
  :middleware [lein-tools-deps.plugin/resolve-dependencies-with-deps-edn]
  :lein-tools-deps/config {:config-files ["deps.edn"]}

  :main example.main
  :aot [example.main]

  :profiles {:uberjar {:uberjar-name "example-standalone.jar"}
             ;; nonnative/native
             :nonnative {:aot :all}
             :native {:lein-tools-deps/config {:aliases [:native]}
                      :jvm-opts ["-Dclojure.compiler.direct-linking=true"
                                 "-Dclojure.spec.skip-macros=true"]}})
