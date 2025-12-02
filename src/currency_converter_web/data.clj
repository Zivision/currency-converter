(ns currency-converter-web.data
  (:require [next.jdbc :as jdbc]
            [cheshire.core :as json]
            [clojure.edn :as edn]
            [currency-converter-web.types :as types]))



(def cache (edn/read-string (slurp "currency-cache.edn")))

(defn symbol->name
  "Takes SYMBOL and returns currency name if available"
  [symbol]
  (try
    (.getDisplayName
     (java.util.Currency/getInstance (name symbol)) java.util.Locale/ENGLISH)
    (catch Exception e
        (name symbol))))

(symbol->name :TOK)


(defn exchange-rate-clean
  "Cleans EX-DATA from exchangerate-api"
  [ex-data]
  (as-> ex-data $
       (:data $)
       (:body $)
       (json/parse-string $ true)
       (:conversion_rates $)
       (map (fn
              [[symbol value-in-usd]]
              (types/->Currency-Data
               (symbol->name symbol)
               symbol value-in-usd)) $)))


 (exchange-rate-clean cache)

;; Define database
;;(def cache-db {:dbtype "sqlite" :dbname "currency-converter-cache.db"})

;; Create cache connection
;;(def cache-ds (jdbc/get-datasource cache-db))

;;(jdbc/execute! cache-ds)
