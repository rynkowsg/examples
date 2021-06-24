(ns example.main
  (:gen-class)
  (:require
   [duct.core :as duct]
   [taoensso.timbre :as timbre]
   [example.system :as system]))

(defn -main [& args]
  (timbre/log-and-rethrow-errors
   (system/start {:keys [:example.greet/greet]})))
