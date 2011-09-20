(ns clj-genart.core
  (:use [rosado.processing]
        [rosado.processing.applet]))

(def *width* 400)
(def *height* 700)

(defn draw []
  "Evaluate to draw next frame."
  (framerate 2)
  (background-float 230 230 230)
  (stroke-float 0 0 0)
  (stroke-weight 1)
  (let [padding 20]
    (doseq [x (range (/ padding 2) (- *width* padding) padding)
            y (range (/ padding 2) (- *height* padding) padding)]
      (fill-float 0 0 255 (rand-int 100))
      (rect x y 15 15))))


(defn setup []
  "Runs once."
  (smooth)
  (no-loop))

(defapplet app :title "Chapter 2"
  :setup setup :draw draw :size [*width* *height*])

(run app)

(stop app)
