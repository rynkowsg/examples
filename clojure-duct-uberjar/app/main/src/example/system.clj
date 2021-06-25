(ns example.system
  (:require
   [clojure.java.io :as io]
   [duct.core :as duct]
   [integrant.core :as ig]
   [medley.core :refer [map-vals]]
   [utils.duct :as uduct]))

(defn compose-duct-config [app-config]
  (let [modules-config
        {;; Project name
         :duct.core/project-ns    "example"
         ;; Configure :duct.module/logging
         :duct.logger/timbre      {:set-root-config? true
                                   :level            :debug
                                   :appenders        {:spit (ig/ref :duct.logger.timbre/spit)}}
         :duct.logger.timbre/spit {:fname "logs/dev.log"}}]
    {:duct.profile/base   (merge modules-config app-config)
     :duct.profile/dev    (or (some-> (io/resource "dev.edn") (duct/read-config)) {})
     :duct.profile/prod   (or (some-> (io/resource "prod.edn") (duct/read-config)) {})
     :duct.profile/local  (or (some-> (io/resource "local.edn") (duct/read-config)) {})
     :duct.module/logging {}}))

(defn system-config []
  (let [c {:example.greet/greet-fn {}
           :example.greet/greet    {:greet-fn (ig/ref :example.greet/greet-fn)}}]
    (->> c
         ;; add logger to every component
         (map-vals #(assoc % :logger (ig/ref :duct/logger))))))

(defn config []
  (compose-duct-config (system-config)))

(defn start
  [{:keys [keys profiles]
    :or   {keys [:duct/daemon] profiles [:duct.profile/prod]}}]
  (duct/load-hierarchy)
  (duct/exec-config (config) profiles keys))

#_(defn stop
    [s & [{:keys [immediate] :or {immediate false}}]]
    (ig/halt! s)
    (when immediate (System/exit 0)))
