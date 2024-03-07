(ns user
  (:require
   [clojure.core :refer [Throwable->map]]
   [clojure.repl :refer [apropos dir doc find-doc pst source]]
   [integrant.core :as ig]
   [integrant.repl :refer [clear go halt prep init reset reset-all]]
   [com.example.clj-fullstack-sample.com.example.clj-fullstack-sample.config :as config]
   [com.example.clj-fullstack-sample.com.example.clj-fullstack-sample.system :as system]))

(integrant.repl/set-prep!
 #(-> (config/config-map)
      (system/system-map)
      (ig/prep)))
