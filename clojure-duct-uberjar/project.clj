(defproject example "0.1.0-SNAPSHOT"
  :main ^:skip-aot example.main
  :plugins [[duct/lein-duct "0.12.3"]
            [lein-tools-deps "0.4.5"]]
  :middleware [lein-duct.plugin/middleware
               lein-tools-deps.plugin/resolve-dependencies-with-deps-edn]
  :lein-tools-deps/config {:config-files [:install :user :project]}
  :prep-tasks ["javac" "compile" ["run" ":duct/compiler"]]
  :profiles {:uberjar {; assuming deps.edn contains some build-relevant :prod alias,
                       ; we can import it here like below:
                       ;:lein-tools-deps/config {:aliases [:prod]}
                       :aot :all}})

;; NOTE
;; ----
;; In this project, Leiningen is used only to build uberjars.
