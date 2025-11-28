(ns currency-converter-web.test
  (:require  [clojure.test :as t]
             [environ.core :refer [env]]))

(env :exchange-rate-api-key)
