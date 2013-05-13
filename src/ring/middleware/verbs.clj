
(ns ring.middleware.verbs
  (:require [clojure.string :as s]))

(def ^ {:private true}
  verbs
  "Supported HTTP verbs"
  #{"delete" "head" "put" "options" "trace" "connect" "get" "post"})

(defn- http-verb
  "If the requests _method is a HTTP verb name, return it as a keyword, otherwise return nil"
  [req]
  (let [method (get-in req [:params :_method] "")
        verb (s/lower-case method)]
    (if (some #(= verb %) verbs)
      (keyword verb))))

;; Public
;; ------

(defn wrap-verbs
  "Convert special _method parameter to the request method for HTTP verbs"
  [handler]
  (fn [req]
    (if-let [verb (http-verb req)]
      (handler (assoc req :request-method verb))
      (handler req))))

