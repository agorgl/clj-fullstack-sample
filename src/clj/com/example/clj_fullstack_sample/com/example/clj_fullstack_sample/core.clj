(ns com.example.clj-fullstack-sample.com.example.clj-fullstack-sample.core
  (:gen-class)
  (:require
   [com.example.clj-fullstack-sample.com.example.clj-fullstack-sample.system :as system]))

(defn foo
  "I don't do a whole lot."
  [x]
  (prn x "Hello, World!"))

(defn -main [& args]
  (system/start))
