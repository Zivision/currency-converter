(ns currency-converter-web.core
  (:gen-class)
  (:require [currency-converter-web.requests :as requests]))

(defn -main
  [& args]
  ;; If data is 6 hours or older request new data
  (requests/get-currency-data-with-file-cache 6))
