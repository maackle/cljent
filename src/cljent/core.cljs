(ns cljent.core
  (:require left-pad)
  )
;; just including left-pad to demo another way of requiring node modules

(enable-console-print!)

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)

;; pad the number 42 with five zeros
(defn test_fn []
  (println (left-pad 42 5 5)))
