(ns com.example.clj-fullstack-sample.com.example.clj-fullstack-sample.views
  (:require
   [re-frame.core :as re-frame]
   [com.example.clj-fullstack-sample.com.example.clj-fullstack-sample.subs :as subs]))

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div
     [:h1
      "Hello from " @name]]))
