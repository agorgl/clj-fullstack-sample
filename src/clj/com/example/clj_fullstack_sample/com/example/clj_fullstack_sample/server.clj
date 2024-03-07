(ns com.example.clj-fullstack-sample.com.example.clj-fullstack-sample.server
  (:require
   [io.pedestal.http :as http]
   [com.example.clj-fullstack-sample.com.example.clj-fullstack-sample.service :as service]))

(defn start [opts]
  (-> (service/service-map opts)
      (http/create-server)
      (http/start)))

(defn stop [server]
  (-> server
      (http/stop)))
