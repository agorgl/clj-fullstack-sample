(ns com.example.clj-fullstack-sample.com.example.clj-fullstack-sample.core
  (:require
   [reagent.dom.client :as rdomc]
   [re-frame.core :as re-frame]
   [com.example.clj-fullstack-sample.com.example.clj-fullstack-sample.events :as events]
   [com.example.clj-fullstack-sample.com.example.clj-fullstack-sample.views :as views]
   [com.example.clj-fullstack-sample.com.example.clj-fullstack-sample.config :as config]))

(defonce root
  (delay (rdomc/create-root (.getElementById js/document "app"))))

(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")]
    (rdomc/render @root [views/main-panel])))

(defn init []
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
