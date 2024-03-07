(ns com.example.clj-fullstack-sample.com.example.clj-fullstack-sample.routes)

(defn hello-handler [_request]
  {:status 200
   :body "Hello"})

(defn routes []
  #{["/hello" :get [`hello-handler] :route-name ::hello]})
