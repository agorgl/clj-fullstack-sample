(ns com.example.clj-fullstack-sample.com.example.clj-fullstack-sample.config
  (:require
   [clojure.java.io :as io]
   [aero.core :refer [read-config]]))

(defn config-map []
  (-> "config.edn"
      (io/resource)
      (read-config)))
