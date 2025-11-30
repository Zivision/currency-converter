(ns currency-converter-web.server
  (:require [compojure.core :refer [defroutes GET]]
            [compojure.route :as route]
            [ring.middleware.json :refer [wrap-json-response]]
            [ring.util.response :refer [response]]))

(def health-check
  #(response {:status 200
              :timestamp (System/currentTimeMillis)
              :uptime (str (/ (.. Runtime getRuntime totalMemory) 1024 1024) " MB")}))

(defroutes app-routes
  (GET "/health" [] (health-check))
  (GET "/" [] (response {:message "Server is running"}))
  (route/not-found {:status 404 :body {:error "Not found"}}))

(def app
  (wrap-json-response app-routes))
