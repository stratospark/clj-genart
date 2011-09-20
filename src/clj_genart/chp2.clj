(ns clj-genart.core
  (:use [rosado.processing]
        [rosado.processing.applet]))

(def *width* 500)
(def *height* 300)

(defn draw []
  "Evaluate to draw next frame."
  (background-float 230 230 230)
  (stroke-float 130 0 0)
  (stroke-weight 1)
  (let [padding 20]
    (doseq [x (range (/ padding 2) (- *width* padding) padding)
            y (range (/ padding 2) (- *height* padding) padding)]
      (stroke-weight (rand-int 3))
      (rect x y 15 15))))


(defn setup []
  "Runs once."
  (smooth)
  (no-loop))

(defapplet app :title "Chapter 2"
  :setup setup :draw draw :size [*width* *height*])

(run app)

(stop app)
