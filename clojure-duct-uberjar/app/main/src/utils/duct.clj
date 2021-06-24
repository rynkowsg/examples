(ns utils.duct
  (:require
   [duct.core :as duct]
   [integrant.core :as ig]))

;; this method is a copy of duct.core/exec-config with one difference: it returns the system
(defn exec-config
  "Build, prep and initiate a configuration of modules, then block the thread
  (see [[await-daemons]]). By default it only runs profiles derived from
  `:duct.profile/prod` and keys derived from `:duct/daemon`.

  This function is designed to be called from `-main` when standalone operation
  is required."
  ([config]
   (exec-config config [:duct.profile/prod]))
  ([config profiles]
   (exec-config config profiles [:duct/daemon]))
  ([config profiles keys]
   (let [system (-> config (duct/prep-config profiles) (ig/init keys))]
     (duct/await-daemons system)
     system)))
