(ns currency-converter-web.requests
  (:require [clojure.edn :as edn]
            [org.httpkit.client :as hk-client]
            [environ.core :refer [env]]))

;; Make requests to api
(defn get-req-promise
  "Send request to URL with API-KEY to ENDPOINT"
  [url api-key endpoint]
  (hk-client/get (str url api-key endpoint)))

;; Caching
;; Set currency cache file
(def cache-file "currency-cache.edn")

(def read-cache
  #(when (.exists (clojure.java.io/file cache-file))
    (edn/read-string (slurp cache-file))))

(defn write-cache
  "Write DATA to cache.edn file"
  [data]
  (spit cache-file (pr-str {:data data
                            :timestamp (System/currentTimeMillis)})))

(defn get-currency-data-with-file-cache
  "Make request to api and update cache based on HOURS since call"
  [hours]
  (let [cached (read-cache)
        now (System/currentTimeMillis)
        age-ms (when (:timestamp cached) (- now (:timestamp cached)))
        max-age-ms (* hours 60 60 1000)]
     (if (and cached (< age-ms max-age-ms))
       (do
       (println "Data up to date!")
       (:data cached))
       (let [fresh-data
             @(get-req-promise
              "https://v6.exchangerate-api.com/v6/"
              (env :exchange-rate-api-key)
              "/latest/USD")]
         (println "Api data recieved! And cache updated")
         (write-cache fresh-data)
         fresh-data))))
