(ns example.scripts
  (:require
   [example.system :as system]))

(defn hello
  [& _args]
  (prn "Hello World!"))

(defn greet
  [args]
  (let [s (system/start {:keys     [:example.greet/greet-fn]
                         :profiles [:duct.profile/dev]})
        f (:example.greet/greet-fn s)]
    (f args)
    #_(system/stop s {:immediate true})))
