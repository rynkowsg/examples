(ns dev
  (:refer-clojure :exclude [test])
  (:require [clojure.repl :refer :all]
            [fipp.edn :refer [pprint]]
            [clojure.tools.namespace.repl :as ns-repl]
            [clojure.java.io :as io]
            [clojure.pprint :refer [pp]]
            [duct.core :as duct]
            [duct.core.repl :as duct-repl]
            [eftest.runner :as eftest]
            [integrant.core :as ig]
            [integrant.repl :refer [init prep] :as ig-repl]
            [integrant.repl.state :as ig-state]
            [taoensso.timbre :as timbre]
            [speculative.instrument]
            [utils.dev]
            [example.system :as system]))

;; Lifecycle, system, config, testing

(defn refresh [] (ns-repl/refresh))

(defn refresh-all [] (ns-repl/refresh-all))

(defn go []
  (let [keys     nil
        profiles [:duct.profile/dev :duct.profile/local]]
    (duct/load-hierarchy)
    (ig-repl/set-prep! #(duct/prep-config (system/config) profiles))
    (ig-repl/go keys)))

(defn halt [] (ig-repl/halt))

(defn reset [] (ig-repl/reset))

(defn restart [] (reset) (halt) (go))

(defn clear [] (ig-repl/clear))

(defn read-config [] (system/config))

(defn system [] (->> ig-state/system (into (sorted-map))))

(defn test [] (eftest/run-tests (eftest/find-tests ["app/test/src"])))

(defn print-config [] (-> ig-state/system pprint))

(defn print-system [] (-> (system) pprint))

;; Init DEV/REPL env

(defn init-repl []
  (when (io/resource "local.clj") (load "local"))
  (repl-utils.dev/enable-expound)
  (repl-utils.dev/enable-orchestra))

(init-repl)
