(ns com.example.clj-fullstack-sample.com.example.clj-fullstack-sample.events
  (:require
   [re-frame.core :as re-frame]
   [com.example.clj-fullstack-sample.com.example.clj-fullstack-sample.db :as db]))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))
