(ns com.example.clj-fullstack-sample.com.example.clj-fullstack-sample.service
  (:require
   [clojure.string :as str]
   [io.pedestal.http :as http]
   [io.pedestal.http.route :refer [routes-from]]
   [io.pedestal.http.ring-middlewares :as middlewares]
   [io.pedestal.interceptor :refer [interceptor]]
   [io.pedestal.environment :refer [dev-mode?]]
   [ring.middleware.resource :as resource]
   [ring.util.request :as ring-request]
   [com.example.clj-fullstack-sample.com.example.clj-fullstack-sample.routes :as routes]))

(defn dir-index [root-path]
  (interceptor
   {:name ::dir-index
    :enter (fn [context]
             (let [{:keys [request]} context
                   path (ring-request/path-info request)]
               (if (str/ends-with? path "/")
                 (let [index-path (str path "index.html")
                       index-request (-> request
                                         (assoc :path-info index-path)
                                         (assoc :uri index-path))
                       response (resource/resource-request index-request root-path)]
                   (if response
                     (assoc context :request index-request)
                     context))
                 context)))}))

(defn static-content-interceptors [service-map]
  (let [resource-path (::http/resource-path service-map)
        dir-index (dir-index resource-path)]
    (-> service-map
        (update
         ::http/interceptors
         #(->> %
               (mapcat
                (fn [item]
                  (if (= (:name item) ::middlewares/resource)
                    [dir-index item]
                    [item])))
               (into []))))))

(defn service-opts [service-map opts]
  (let [{:keys [host port]} opts]
    (->> {::http/host host
          ::http/port port}
         (merge service-map))))

(defn service-map [opts]
  (let [{:keys [dev-mode]
         :or {dev-mode dev-mode?}} opts]
    (-> {::http/port 8080
         ::http/type :jetty
         ::http/routes (routes-from (routes/routes))
         ::http/resource-path "public"
         ::http/secure-headers {:content-security-policy-settings
                                {:script-src "'self' 'unsafe-inline' 'unsafe-eval' https: http:"
                                 :object-src "'none'"}}
         ::http/join? false}
        (service-opts opts)
        http/default-interceptors
        static-content-interceptors
        (cond-> dev-mode http/dev-interceptors))))
