(ns cljent.duhh
  (:require
    [ssb-client :as ssbClient]
    [pull-stream :as pull]
    ))
;; just including left-pad to demo another way of requiring node modules

(enable-console-print!)

(def test_message {:type "post" :text "first clojurey post"})

(defn ssb_error [err]
  (if err
    (println err)
    (println "message received")))

(defn pub_message [message sbot]
    (.publish sbot (clj->js message)
      (fn [err msg]
        (if err
          (println err)
          (println "no err"))

        (println msg))))

(defn parse_message [msg]
  (print (js->clj msg :keywordize-keys true)))

(defn get_message [sbot]
  (pull (.createFeedStream sbot)
        (.drain pull parse_message ssb_error)))

(defn sbot_callback [err sbot]
    (if err
      (println err)
      (println "no error"))

    (pub_message test_message sbot)
    (get_message sbot))

;; pad the number 42 with five zeros
#_(defn test_fn []
  (println (left-pad 42 5 0)))

(def ssb (ssbClient sbot_callback))
;;(.close ssb)
