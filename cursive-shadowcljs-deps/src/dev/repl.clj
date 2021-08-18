(ns repl
 (:require
  [shadow.cljs.devtools.api :as shadow]))

(println "ns repl loaded")

(defn dev []
 (require 'dev)
 (in-ns 'dev)
 (println "running shadow repl...")
 (shadow/repl :script))

(comment
 (shadow/compile :script)
 (shadow/repl :script)
 (dev))
