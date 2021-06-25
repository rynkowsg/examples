(ns repl-utils.string
  (:require
   [clojure.string :as str]))

(defn unlines
  ([coll]
   (str/join "\n" coll))
  ([coll prefix]
   (str prefix (str/join (str "\n" prefix) coll))))
