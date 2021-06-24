(ns example.greet
  (:require
   [duct.logger :refer [log]]
   [integrant.core :as ig]
   [taoensso.timbre :as log]))

(defn greet
  "Callable entry point to the application."
  [& [{:keys [name] :or {name "World"} :as data}]]
  (println (str "Hello, " name "!"))
  (log/info :debug :greet-called data))

(defmethod ig/init-key ::greet-fn [_ {:keys [logger]}]
  (log logger :debug ::initialized)
  greet)

(defmethod ig/init-key ::greet [_ {:keys [greet-fn]}]
  (greet-fn))
