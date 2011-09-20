(ns clj-genart.core
  (:use [rosado.processing]
        [rosado.processing.applet]))

(defn draw []
  "Evaluate to draw next frame."
  (background-float 230 230 230)
  (stroke-float 130 0 0)
  (stroke-weight 4)
  (let [cx (/ (width) 2)
        cy (/ (height) 2)]
    (line (- cx 70) (- cy 70) (+ cx 70) (+ cy 70))
    (line (- cx 70) (+ cy 70) (+ cx 70) (- cy 70))
    (fill 255 150)
    (ellipse cx cy 50 50)))

(defn setup []
  "Runs once."
  (smooth)
  (no-loop))

(defapplet app :title "Chapter 2"
  :setup setup :draw draw :size [500 300])

(run app)

(stop app)
