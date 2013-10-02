(ns ring.middleware.index-file
  (:require ring.util.response)
  (import java.lang.String)
  (:require [clojure.pprint :refer [pprint]]))


(defn wrap-index-paths [app index-file]
  (fn [req]
    (let [get-method? (= :get (:request-method req))
          uri         (:uri req)
          path        (:path-info req)]
      (cond
        (not get-method?) (app req)
        (= "" path) (ring.util.response/redirect (str uri "/" index-file))
        (= "/" path) (ring.util.response/redirect (str uri index-file))
        :else (app req)))))
