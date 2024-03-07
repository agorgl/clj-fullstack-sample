(ns com.example.clj-fullstack-sample.com.example.clj-fullstack-sample.system
  (:require
   [integrant.core :as ig]
   [com.example.clj-fullstack-sample.com.example.clj-fullstack-sample.config :refer [config-map]]
   [com.example.clj-fullstack-sample.com.example.clj-fullstack-sample.server :as server]))

(defn system-map [config]
  {::server
   {:host (-> config :server :host)
    :port (-> config :server :port)}})

(defn start []
  (-> (config-map)
      (system-map)
      (ig/prep)
      (ig/init)))

(defn stop [system]
  (-> system
      (ig/halt!)))

(defmethod ig/init-key ::server [_ opts]
  (server/start opts))

(defmethod ig/halt-key! ::server [_ server]
  (server/stop server))
