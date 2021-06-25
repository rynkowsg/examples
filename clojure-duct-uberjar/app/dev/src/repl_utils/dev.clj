(ns repl-utils.dev
  (:require
   [clojure.spec.alpha :as s]
   [expound.alpha :as expound]
   [orchestra.spec.test :as st]
   [taoensso.timbre :as log]
   [utils.string :as mystr]))

(defn enable-orchestra []
  (let [functions (-> (st/instrument) (sort))]
    (log/infof "Enabled orchestration for:\n%s" (mystr/unlines functions "  * "))))

(defn enable-expound []
  (let [opts {:show-valid-values? true}]
    (set! s/*explain-out* (expound/custom-printer opts))
    (log/info "Enabled expound with opts" opts)))
