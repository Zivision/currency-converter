(ns currency-converter-web.core
  (:gen-class)
  (:require [currency-converter-web.requests :as requests]
            [currency-converter-web.server :as server]
            [ring.adapter.jetty :refer [run-jetty]]))

(defn -main
  [& args]
  ;; If data is 6 hours or older request new data
  (requests/get-currency-data-with-file-cache 6)
  (let [port (Integer/parseInt (or (System/getenv "PORT") "3000"))]
    (println (str "Starting server on port " port "..."))
    (run-jetty server/app {:port port :join? false})))
