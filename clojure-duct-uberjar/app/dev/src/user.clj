(ns user
  (:require
   [clojure.tools.namespace.repl :as tools-repl]))

(defn dev
  "Load and switch to the 'dev' namespace."
  []
  (tools-repl/set-refresh-dirs "app/dev/src" "app/main/src" "app/test/src")
  (tools-repl/refresh)
  (require 'dev)
  (in-ns 'dev)
  :loaded)

#_ (dev)
