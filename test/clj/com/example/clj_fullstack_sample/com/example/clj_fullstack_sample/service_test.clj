(ns com.example.clj-fullstack-sample.com.example.clj-fullstack-sample.service-test
  (:require
   [clojure.test :refer [deftest is use-fixtures]]
   [matcher-combinators.test]
   [io.pedestal.http :as http]
   [io.pedestal.test :as test]
   [com.example.clj-fullstack-sample.com.example.clj-fullstack-sample.service :refer [service-map]]))

(def ^:dynamic *server* nil)

(use-fixtures :once
  (fn [f]
    (let [server
          (-> (service-map nil)
              http/create-server
              http/start)]
      (try
        (binding [*server* server]
          (f))
        (finally (http/stop server))))))

(defn response-for
  "Bypasses the servlet for the running service to send a request directly into the interceptor chain."
  [verb url & args]
  (apply test/response-for (::http/service-fn *server*) verb url args))

(deftest can-access-hello
  (is (match? {:status 200
               :body "Hello"}
              (response-for :get "/hello"))))

(deftest can-access-public-resources
  (is (match? {:status 200}
              (response-for :get "/index.html"))))
