(defproject currency-converter-web "0.1.0-SNAPSHOT"
  :description "Currency Converter API pipeline"
  :url "https://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.12.2"]
                 [http-kit "2.8.1"]
                 [cheshire "6.1.0"]
                 [environ "1.2.0"]
                 [ring/ring-core "1.10.0"]
                 [ring/ring-jetty-adapter "1.10.0"]
                 [ring/ring-json "0.5.1"]
                 [compojure "1.7.0"]
                 [seancorfield/next.jdbc "1.2.659"]]
  :plugins [[lein-environ "1.2.0"]]
  :main ^:skip-aot currency-converter-web.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
